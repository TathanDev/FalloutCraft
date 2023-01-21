package fr.tathan.falloutcraft.common.network.packet;

import fr.tathan.falloutcraft.client.gui.nuka_cola_machine.NukaColaMachineMenu;
import fr.tathan.falloutcraft.client.gui.radiation_remover.RadiationRemoverMenu;
import fr.tathan.falloutcraft.common.blocks.entity.NukaColaMachineBlockEntity;
import fr.tathan.falloutcraft.common.blocks.entity.RadiationRemoverBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;
public class FluidSyncS2CPacket {

    private final FluidStack fluidStack;
    private final BlockPos pos;

    public FluidSyncS2CPacket(FluidStack fluidStack, BlockPos pos) {
        this.fluidStack = fluidStack;
        this.pos = pos;
    }

    public FluidSyncS2CPacket(FriendlyByteBuf buf) {
        this.fluidStack = buf.readFluidStack();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFluidStack(fluidStack);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof NukaColaMachineBlockEntity blockEntity) {
                blockEntity.setFluid(this.fluidStack);

                if(Minecraft.getInstance().player.containerMenu instanceof NukaColaMachineMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    menu.setFluid(this.fluidStack);
                }
            }

            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof RadiationRemoverBlockEntity blockEntity) {
                blockEntity.setRadiatedWater(this.fluidStack);
                blockEntity.setWater(this.fluidStack);

                if(Minecraft.getInstance().player.containerMenu instanceof RadiationRemoverMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    menu.setRadiatedWater(this.fluidStack);
                    menu.setWater(this.fluidStack);
                }
            }
        });
        return true;
    }

}
