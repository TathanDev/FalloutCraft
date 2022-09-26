package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TabsRegistry {

    public static final CreativeModeTab TAB = new CreativeModeTab("falloutcraft_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemsRegistry.NUKA_COLA.get());
        }
    };

}
