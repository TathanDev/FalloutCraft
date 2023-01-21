package fr.tathan.falloutcraft.common.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.world.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class PimpBoy extends Item {


    public PimpBoy(Properties pProperties) {
        super(pProperties);

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        if(pLevel.isClientSide) {
            Minecraft.getInstance().setScreen(new InventoryScreen(pPlayer));
        }

        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));

    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pMiningEntity) {
        return false;
    }

}
