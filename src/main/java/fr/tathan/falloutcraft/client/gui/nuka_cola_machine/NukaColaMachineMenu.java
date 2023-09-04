package fr.tathan.falloutcraft.client.gui.nuka_cola_machine;

import fr.tathan.falloutcraft.common.blocks.entity.NukaColaMachineBlockEntity;
import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import fr.tathan.falloutcraft.common.registries.MenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.IContainerFactory;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;

public class NukaColaMachineMenu {
    public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
    public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
        BlockPos pos = extraData.readBlockPos();
        NukaColaMachineBlockEntity blockEntity = (NukaColaMachineBlockEntity) inv.player.level().getBlockEntity(pos);
        return new GuiContainer(id, inv, blockEntity);
    }
}

    public static class GuiContainer extends AbstractContainerMenu {
        private final NukaColaMachineBlockEntity blockEntity;
        private FluidStack fluidStack;

        public GuiContainer(int id, Inventory inv, NukaColaMachineBlockEntity blockEntity) {
            super(MenuTypes.NUKA_COLA_MACHINE_MENU.get(), id);
            this.blockEntity = blockEntity;

            IItemHandlerModifiable internal = blockEntity.getItemHandler();
            this.addSlot(new SlotItemHandler(internal, 0, 12, 15));
            this.addSlot(new SlotItemHandler(internal, 1, 85, 14));
            this.addSlot(new SlotItemHandler(internal, 2, 86, 58));
            this.fluidStack = blockEntity.getFluidStack();

            addPlayerInventory(inv);
            addPlayerHotbar(inv);

        }

        public NukaColaMachineBlockEntity getBlockEntity() {
            return this.blockEntity;
        }

        public void setFluid(FluidStack fluidStack) {
            this.fluidStack = fluidStack;
        }

        @Override
        public boolean stillValid(Player player) {
            return stillValid(ContainerLevelAccess.create(player.level(), blockEntity.getBlockPos()),
                    player, BlocksRegistry.NUKA_COLA_MACHINE.get());
        }

        private static final int HOTBAR_SLOT_COUNT = 9;
        private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
        private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
        private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
        private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
        private static final int VANILLA_FIRST_SLOT_INDEX = 0;
        private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

        // THIS YOU HAVE TO DEFINE!
        private static final int TE_INVENTORY_SLOT_COUNT = 3;  // must be the number of slots you have!

        @Override
        public ItemStack quickMoveStack(Player playerIn, int index) {
            Slot sourceSlot = slots.get(index);
            if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
            ItemStack sourceStack = sourceSlot.getItem();
            ItemStack copyOfSourceStack = sourceStack.copy();

            // Check if the slot clicked is one of the vanilla container slots
            if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
                // This is a vanilla container slot so merge the stack into the tile inventory
                if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                        + TE_INVENTORY_SLOT_COUNT, false)) {
                    return ItemStack.EMPTY;  // EMPTY_ITEM
                }
            } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
                // This is a TE slot so merge the stack into the players inventory
                if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                System.out.println("Invalid slotIndex:" + index);
                return ItemStack.EMPTY;
            }
            // If stack size == 0 (the entire stack was moved) set slot contents to null
            if (sourceStack.getCount() == 0) {
                sourceSlot.set(ItemStack.EMPTY);
            } else {
                sourceSlot.setChanged();
            }
            sourceSlot.onTake(playerIn, sourceStack);
            return copyOfSourceStack;
        }



        private void addPlayerInventory(Inventory playerInventory) {
            for (int i = 0; i < 3; ++i) {
                for (int l = 0; l < 9; ++l) {
                    this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
                }
            }
        }

        private void addPlayerHotbar(Inventory playerInventory) {
            for (int i = 0; i < 9; ++i) {
                this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
            }
        }

        public FluidStack getFluidStack() {
            return fluidStack;
        }



    }
}