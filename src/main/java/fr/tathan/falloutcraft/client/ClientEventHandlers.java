package fr.tathan.falloutcraft.client;

import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.config.CommonConfig;
import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import fr.tathan.falloutcraft.common.radiation.ItemRadiationProvider;
import fr.tathan.falloutcraft.common.registries.ItemsRegistry;
import fr.tathan.falloutcraft.common.registries.MenuTypes;
import fr.tathan.falloutcraft.common.registries.TagsRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;

import java.util.List;

import static fr.tathan.falloutcraft.common.config.CommonConfig.usePimpBoy;

public class ClientEventHandlers {

    private static void onItemTooltip(ItemTooltipEvent event) {

        addItemTooltip(event.getItemStack(), event.getFlags(), event.getToolTip());
    }

    private static void onScreenOpen(ScreenEvent.Init event) {

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

                        if (mainHand.is(TagsRegistry.IS_PIP_BOY) || offHand.is(TagsRegistry.IS_PIP_BOY)) {
                            return;
                        }

                        inventory.onClose();
                        FalloutCraft.LOGGER.debug("Screen is canceled");
                    } else if (!CommonConfig.pimpBoyUtilisation.get()) {

                        if (!player.getInventory().contains(TagsRegistry.IS_PIP_BOY)) {
                            inventory.onClose();
                            FalloutCraft.LOGGER.debug("Screen is canceled");
                        }
                    }
                }
            }
        }
    }



    public static void init(IEventBus modEventBus) {

        modEventBus.addListener(ClientEventHandlers::onItemTooltip);
        modEventBus.addListener(ClientEventHandlers::onScreenOpen);

    }

    public static void openInventory(Player pPlayer) {
        Minecraft.getInstance().setScreen(new InventoryScreen(pPlayer));

    }

}
