package fr.tathan.falloutcraft.common.registries;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TabsRegistry {

    public static final CreativeModeTab TAB = new CreativeModeTab("falloutcraft_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemsRegistry.NUKA_COLA_CLASSIC.get());
        }
    };

    public static final CreativeModeTab TAB_DECORATION = new CreativeModeTab("decoration_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemsRegistry.PAPERS_ON_THE_GROUND_ITEM.get());
        }
    };

}
