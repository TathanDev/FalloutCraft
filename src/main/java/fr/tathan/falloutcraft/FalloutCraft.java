package fr.tathan.falloutcraft;

import com.mojang.logging.LogUtils;
//import fr.tathan.falloutcraft.client.entity.render.radroach.RadroachRenderer;
import fr.tathan.falloutcraft.client.gui.nuka_cola_machine.NukaColaMachineScreen;
import fr.tathan.falloutcraft.client.gui.radiation_remover.RadiationRemoverScreen;
import fr.tathan.falloutcraft.client.pack.PackLoader;
import fr.tathan.falloutcraft.common.config.CommonConfig;
import fr.tathan.falloutcraft.common.fluid.ModFluidTypes;
import fr.tathan.falloutcraft.common.network.ModMessages;
import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import fr.tathan.falloutcraft.common.radiation.ItemRadiationProvider;
import fr.tathan.falloutcraft.common.registries.*;
import fr.tathan.falloutcraft.common.util.BetterBrewingRecipe;
import fr.tathan.falloutcraft.common.worldgen.FalloutRegion;
import fr.tathan.falloutcraft.common.worldgen.FalloutSurfaceRuleData;
import fr.tathan.falloutcraft.common.worldgen.features.FalloutConfiguredFeatures;
import fr.tathan.falloutcraft.common.worldgen.features.FalloutPlacedFeatures;
import net.minecraft.ChatFormatting;
import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
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
import org.slf4j.Logger;
//import software.bernie.geckolib3.GeckoLib;
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
      //  EntityTypes.ENTITY_TYPES.register(modEventBus);
        PotionsRegistry.POTIONS.register(modEventBus);

        BiomesRegistry.registerBiomes();




        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::discoverResourcePacks);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(this::onItemTooltip);
        MinecraftForge.EVENT_BUS.addListener(this::onScreenOpen);

        PackLoader.loadOnInitialStartup();



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

    public void discoverResourcePacks(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            event.addRepositorySource(new PackLoader(ModList.get().getModFileById(MODID).getFile()));
        }
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
            MenuScreens.register(MenuTypes.NUKA_COLA_MACHINE_MENU.get(), NukaColaMachineScreen::new);
            MenuScreens.register(MenuTypes.RADIATION_REMOVER_MENU.get(), RadiationRemoverScreen::new);

            //EntityRenderers.register(EntityTypes.RADROACH.get(), RadroachRenderer::new);

        }


    }

    private void onItemTooltip(ItemTooltipEvent event) {

        addItemTooltip(event.getItemStack(), event.getFlags(), event.getToolTip());
    }

    private void onScreenOpen(ScreenEvent.Init event) {

        disableInventoryScreen(event.getScreen(), event);
    }

    public static void addItemTooltip(ItemStack stack, TooltipFlag context, List<Component> tooltip) {

        if (!stack.isEmpty()) {

            ItemRadiation radiation = stack.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElseThrow(() -> new IllegalStateException("Damn! An Error ?! This is Spooky !!"));
            tooltip.add(Component.literal("Radiation: " + radiation.getRadiation()).withStyle(ChatFormatting.DARK_GREEN).withStyle(ChatFormatting.ITALIC));
        }
    }

    public static void disableInventoryScreen(Screen inventory, ScreenEvent event) {

    if (usePimpBoy.get()) {


        if (inventory.getMinecraft() == null) {
            return;
        }


        LocalPlayer player = inventory.getMinecraft().player;


        if (inventory instanceof InventoryScreen && player != null) {

            ItemStack mainHand = player.getMainHandItem();
            ItemStack offHand = player.getOffhandItem();

            if (player.level.isClientSide) {

                if (player.isCreative()) {
                    return;
                }

                if (CommonConfig.pimpBoyUtilisation.get()) {

                    if (mainHand.getItem() == ItemsRegistry.PIMP_BOY.get() || offHand.getItem() == ItemsRegistry.PIMP_BOY.get()) {
                        return;
                    }

                    inventory.onClose();
                    FalloutCraft.LOGGER.debug("Screen is canceled");
                } else if (!CommonConfig.pimpBoyUtilisation.get()) {

                    if (!player.getInventory().contains(ItemsRegistry.PIMP_BOY.get().getDefaultInstance())) {
                            inventory.onClose();
                            FalloutCraft.LOGGER.debug("Screen is canceled");
                        }
                    }
                }
            }
        }
    }
}

