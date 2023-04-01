package fr.tathan.falloutcraft;

import com.mojang.logging.LogUtils;
import fr.tathan.falloutcraft.client.ClientEventHandlers;
import fr.tathan.falloutcraft.client.gui.nuka_cola_machine.NukaColaMachineScreen;
import fr.tathan.falloutcraft.client.gui.radiation_remover.RadiationRemoverScreen;
import fr.tathan.falloutcraft.common.config.CommonConfig;
import fr.tathan.falloutcraft.common.fluid.ModFluidTypes;
import fr.tathan.falloutcraft.common.loot.ModLootModifiers;
import fr.tathan.falloutcraft.common.network.ModMessages;
import fr.tathan.falloutcraft.common.registries.*;
import fr.tathan.falloutcraft.common.util.BetterBrewingRecipe;
import fr.tathan.falloutcraft.common.worldgen.FalloutRegion;
import fr.tathan.falloutcraft.common.worldgen.FalloutSurfaceRuleData;
import fr.tathan.falloutcraft.common.worldgen.features.FalloutConfiguredFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.material.LavaFluid;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

@Mod(FalloutCraft.MODID)
public class FalloutCraft
{
    public static final String MODID = "falloutcraft";
    public static final Logger LOGGER = LogUtils.getLogger();

    public FalloutCraft()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.CONFIG);

        EffectsRegistry.MOB_EFFECTS.register(modEventBus);
        ItemsRegistry.ITEMS.register(modEventBus);
        ItemsRegistry.POTIONS.register(modEventBus);
        PaintingsRegistry.PAINTING_VARIANTS.register(modEventBus);
        BlocksRegistry.BLOCKS.register(modEventBus);
        FluidsRegistry.FLUIDS.register(modEventBus);
        ModFluidTypes.FLUID_TYPES.register(modEventBus);
        BiomesRegistry.BIOME_REGISTER.register(modEventBus);
        FalloutConfiguredFeatures.CONFIGURED_FEATURES.register(modEventBus);
        BlockEntityRegistry.BLOCK_ENTITIES.register(modEventBus);
        MenuTypes.MENUS.register(modEventBus);
        RecipeTypeRegistry.SERIALIZERS.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        PotionsRegistry.POTIONS.register(modEventBus);
        VillagerRegistry.register(modEventBus);


        modEventBus.addListener(this::commonSetup);

        if (FMLEnvironment.dist.isClient())
        {
            ClientEventHandlers.init(MinecraftForge.EVENT_BUS);

        }

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == TabsRegistry.FALLOUTCRAFT_TAB) {
            event.accept(ItemsRegistry.PIMP_BOY);
            event.accept(ItemsRegistry.PIP_BOY);

            event.accept(ItemsRegistry.GEIGER_COUNTER);
            event.accept(ItemsRegistry.HAZMAT_BOOTS);
            event.accept(ItemsRegistry.HAZMAT_LEGGINGS);
            event.accept(ItemsRegistry.HAZMAT_CHESTPLATE);
            event.accept(ItemsRegistry.HAZMAT_MASK);
            event.accept(ItemsRegistry.NUKA_COLA_MACHINE_ITEM);
            event.accept(ItemsRegistry.NUKA_COLA_CLASSIC);
            event.accept(ItemsRegistry.NUKA_COLA_MIXTURE);
            event.accept(ItemsRegistry.NUKA_COLA_BERRY);
            event.accept(ItemsRegistry.NUKA_COLA_BERRY_MIXTURE);
            event.accept(ItemsRegistry.IRRADIATED_OAK_SAPLING_ITEM);
            event.accept(ItemsRegistry.RADAWAY);
            event.accept(ItemsRegistry.STIMPAK);
            event.accept(ItemsRegistry.RADIOACTIVA);
            event.accept(ItemsRegistry.QUICKDIRT);
            event.accept(ItemsRegistry.QUICKSAND);

        }

        if(event.getTab() == TabsRegistry.FALLOUTCRAFT_DECORATIONS_TAB) {
            event.accept(ItemsRegistry.PAPERS_ON_THE_GROUND_ITEM);
            event.accept(ItemsRegistry.VAULT_BUTTONS);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            Regions.register(new FalloutRegion(new ResourceLocation(MODID, "overworld"), 3));

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BlocksRegistry.RADIOACTIVA.getId(), BlocksRegistry.POTTED_RADIOACTIVA);

            ModMessages.register();
            VillagerRegistry.registerPOIs();

            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, FalloutSurfaceRuleData.makeRules());

            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD,
                    ItemsRegistry.NUKA_COLA_CLASSIC.get(), PotionsRegistry.RADIATION_POTION.get()));

        });

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");
    }


    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value= Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(MenuTypes.NUKA_COLA_MACHINE_MENU.get(), NukaColaMachineScreen::new);
            MenuScreens.register(MenuTypes.RADIATION_REMOVER_MENU.get(), RadiationRemoverScreen::new);

        }
    }

}

