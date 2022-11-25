package fr.tathan.falloutcraft.common.radiation;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemRadiationProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static final Capability<ItemRadiation> ITEM_RADIATION = CapabilityManager.get(new CapabilityToken<ItemRadiation>() {

    });

    private ItemRadiation itemRadiation = null;
    private final LazyOptional<ItemRadiation> optional = LazyOptional.of(this::createRadiation);

    private ItemRadiation createRadiation() {
        if (itemRadiation == null) {
            itemRadiation = new ItemRadiation();
        }
        return this.itemRadiation;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ITEM_RADIATION) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createRadiation().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createRadiation().loadNBTData(nbt);

    }
}
