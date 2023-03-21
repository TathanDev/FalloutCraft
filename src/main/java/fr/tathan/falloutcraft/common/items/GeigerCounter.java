package fr.tathan.falloutcraft.common.items;

import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import fr.tathan.falloutcraft.common.radiation.ItemRadiationProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GeigerCounter extends Item {
    public GeigerCounter(Properties pProperties) {
        super(pProperties);
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        Inventory inventory = pPlayer.getInventory();

        if (!pLevel.isClientSide) {
            int maxRadiation = 0;
            double playerRadiation = 0;

            for (ItemStack itemStack : inventory.items) {
                ItemRadiation radiation = itemStack.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElse(null);
                Double itemRadiation = radiation.getRadiation();

                playerRadiation += itemRadiation;

                if(!itemStack.isEmpty()) {
                    maxRadiation += 10;
                }

            }

            if(maxRadiation/2 > (int) playerRadiation) {
                pPlayer.displayClientMessage(Component.literal("Radiation: " + playerRadiation + "/" + maxRadiation).withStyle(ChatFormatting.DARK_GREEN), true);
            } else {
                pPlayer.displayClientMessage(Component.literal("Radiation: " + playerRadiation + "/" + maxRadiation).withStyle(ChatFormatting.DARK_RED), true);
            }
        }
        return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));

    }
}
