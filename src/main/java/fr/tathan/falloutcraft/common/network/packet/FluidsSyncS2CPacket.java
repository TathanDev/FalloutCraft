package fr.tathan.falloutcraft.common.network.packet;
/**
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FluidsSyncS2CPacket {

    private final FluidStack fluidStack;
    private final FluidStack fluidStack2;


    private final BlockPos pos;

    public FluidsSyncS2CPacket(FluidStack fluidStack,FluidStack fluidStack2, BlockPos pos) {
        this.fluidStack = fluidStack;
        this.fluidStack2 = fluidStack2;


        this.pos = pos;
    }

    public FluidsSyncS2CPacket(FriendlyByteBuf buf) {
        this.fluidStack = buf.readFluidStack();
        this.fluidStack2 = buf.readFluidStack();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFluidStack(fluidStack);
        buf.writeFluidStack(fluidStack2);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {

            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof RadiationRemoverBlockEntity blockEntity) {
                blockEntity.setWater(this.fluidStack);
                blockEntity.setRadiatedWater(this.fluidStack2);

                if(Minecraft.getInstance().player.containerMenu instanceof RadiationRemoverMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    menu.setRadiatedWater(this.fluidStack2);
                    menu.setWater(this.fluidStack);
                }

        }
        return true;
    }

}
*/