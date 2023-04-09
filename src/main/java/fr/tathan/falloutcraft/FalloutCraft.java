package fr.tathan.falloutcraft;

import com.mojang.logging.LogUtils;
import fr.tathan.falloutcraft.client.ClientEventHandlers;
import fr.tathan.falloutcraft.client.gui.nuka_cola_machine.NukaColaMachineScreen;
import fr.tathan.falloutcraft.client.gui.radiation_remover.RadiationRemoverScreen;
import fr.tathan.falloutcraft.client.pack.PackLoader;
import fr.tathan.falloutcraft.common.config.CommonConfig;
import fr.tathan.falloutcraft.common.fluid.ModFluidTypes;
import fr.tathan.falloutcraft.common.loot.ModLootModifiers;
import fr.tathan.falloutcraft.common.network.ModMessages;
import fr.tathan.falloutcraft.common.registries.*;
import fr.tathan.falloutcraft.common.util.BetterBrewingRecipe;
import fr.tathan.falloutcraft.common.worldgen.FalloutRegion;
import fr.tathan.falloutcraft.common.worldgen.FalloutSurfaceRuleData;
import fr.tathan.falloutcraft.common.worldgen.features.FalloutConfiguredFeatures;
import fr.tathan.falloutcraft.common.worldgen.features.FalloutPlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
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

import java.util.List;

import static fr.tathan.falloutcraft.common.config.CommonConfig.usePimpBoy;

@Mod(FalloutCraft.MODID)
public class FalloutCraft
{
    public static final String MODID = "falloutcraft";
    public static final Logger LOGGER = LogUtils.getLogger();

    public FalloutCraft()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.CONFIG);


        ItemsRegistry.ITEMS.register(modEventBus);
        ItemsRegistry.POTIONS.register(modEventBus);
        EffectsRegistry.MOB_EFFECTS.register(modEventBus);
        PaintingsRegistry.PAINTING_VARIANTS.register(modEventBus);
        BlocksRegistry.BLOCKS.register(modEventBus);
        FluidsRegistry.FLUIDS.register(modEventBus);
        ModFluidTypes.FLUID_TYPES.register(modEventBus);
        BiomesRegistry.BIOME_REGISTER.register(modEventBus);
        FalloutConfiguredFeatures.CONFIGURED_FEATURES.register(modEventBus);
        FalloutPlacedFeatures.PLACED_FEATURES.register(modEventBus);
        SoundsRegistry.SOUNDS.register(modEventBus);
        BlockEntityRegistry.BLOCK_ENTITIES.register(modEventBus);
        MenuTypes.MENUS.register(modEventBus);
        RecipeTypeRegistry.SERIALIZERS.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        PotionsRegistry.POTIONS.register(modEventBus);
        VillagerRegistry.register(modEventBus);


        BiomesRegistry.registerBiomes();

        modEventBus.addListener(this::commonSetup);

        if (FMLEnvironment.dist.isClient())
        {
            ClientEventHandlers.init(MinecraftForge.EVENT_BUS);
            PackLoader.loadOnInitialStartup();

        }

        MinecraftForge.EVENT_BUS.register(this);


    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            Regions.register(new FalloutRegion(new ResourceLocation(MODID, "overworld"), 3));

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BlocksRegistry.RADIOACTIVA.getId(), BlocksRegistry.POTTED_RADIOACTIVA);

            ModMessages.register();

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

