package fr.tathan.falloutcraft.common.util;

import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import fr.tathan.falloutcraft.common.radiation.ItemRadiationProvider;
import net.minecraft.world.item.ItemStack;

public class Methods {

    public static void addRadiation(ItemStack stack, float count) {
        ItemRadiation itemRadiation = stack.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElseThrow(() -> new IllegalStateException("Damn! An Error ?! This is Spooky !!"));
        itemRadiation.addRadiation(count);
    }

    public static void subRadiation(ItemStack stack, float count) {
        ItemRadiation itemRadiation = stack.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElseThrow(() -> new IllegalStateException("Damn! An Error ?! This is Spooky !!"));
        itemRadiation.subRadiation(count);
    }


}
