package fr.tathan.falloutcraft.common.items;

import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import fr.tathan.falloutcraft.common.radiation.ItemRadiationProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class Radaway extends Item {
    public Radaway(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getOffhandItem();
        ItemStack radaway = pPlayer.getItemInHand(pUsedHand);
        ItemRadiation radiation = itemStack.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElse(null);
        radiation.loadNBTData(itemStack.getOrCreateTagElement("radiation"));


            if (itemStack == ItemStack.EMPTY || itemStack.getItem() == Items.AIR) {
                pPlayer.sendSystemMessage(Component.translatable("message.falloutcraft.radaway.no_item"));
            } else if( radiation.getRadiation() == 0) {
                pPlayer.sendSystemMessage(Component.translatable("message.falloutcraft.radaway.no_radiation", itemStack.getDisplayName()));
            } else {
                radaway.shrink(1);
                radiation.subRadiation(1.0);
                radiation.saveNBTData(itemStack.getOrCreateTagElement("radiation"));

                FalloutCraft.LOGGER.debug(itemStack.getDisplayName() + "now has" + radiation.getRadiation() + "radiation");
                return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));

            }


        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));

    }
}

