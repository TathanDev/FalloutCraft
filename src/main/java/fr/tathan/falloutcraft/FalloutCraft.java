package fr.tathan.falloutcraft;

import com.mojang.logging.LogUtils;
import fr.tathan.falloutcraft.client.gui.NukaColaMachine.NukaColaMachineScreen;
import fr.tathan.falloutcraft.common.fluid.ModFluidTypes;
import fr.tathan.falloutcraft.common.network.ModMessages;
import fr.tathan.falloutcraft.common.registries.*;
import fr.tathan.falloutcraft.common.worldgen.FalloutRegion;
import fr.tathan.falloutcraft.common.worldgen.FalloutSurfaceRuleData;
import fr.tathan.falloutcraft.common.worldgen.features.FalloutConfiguredFeatures;
import fr.tathan.falloutcraft.common.worldgen.features.FalloutPlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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

        BiomesRegistry.registerBiomes();



        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            Regions.register(new FalloutRegion(new ResourceLocation(MODID, "overworld"), 2));

            ModMessages.register();

            // Register our surface rules
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, FalloutSurfaceRuleData.makeRules());
        });


    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");
    }


    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(MenuTypes.NUKA_COLA_MACHINE_MENU.get(), NukaColaMachineScreen::new);        }
    }


}
