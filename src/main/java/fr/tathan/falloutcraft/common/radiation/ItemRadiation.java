package fr.tathan.falloutcraft.common.radiation;

import net.minecraft.nbt.CompoundTag;

public class ItemRadiation {


    private double radiation = 0;

    private final double MIN_RADIATION = 0;
    private final double MAX_RADIATION = 10;

    //What it's better between Float and Double ?
    public double getRadiation() {
        return radiation;
    }

    public void addRadiation(double add) {
        this.radiation = Math.min(radiation + add, MAX_RADIATION);
    }

    public void subRadiation(double sub) {
        this.radiation = Math.max(radiation - sub, MIN_RADIATION);
    }

    public void setRadiation(double set) {
        this.radiation = Math.max(set, MAX_RADIATION);
    }


    public void copyFrom(ItemRadiation source) {
        this.radiation = source.radiation;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putDouble("radiation", radiation);
    }

    public void loadNBTData(CompoundTag nbt) {
        radiation = nbt.getDouble("radiation");
    }


}
