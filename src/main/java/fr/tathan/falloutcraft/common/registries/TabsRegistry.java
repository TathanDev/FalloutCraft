package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TabsRegistry {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FalloutCraft.MODID);

    public static final RegistryObject<CreativeModeTab> FALLOUTCRAFT_TAB = CREATIVE_MODE_TABS.register("falloutcraft_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemsRegistry.NUKA_COLA_CLASSIC.get()))
                    .title(Component.literal("Falloutcraft")).build());
    public static final RegistryObject<CreativeModeTab> FALLOUTCRAFT_DECORATIONS_TAB = CREATIVE_MODE_TABS.register("falloutcraft_deco_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemsRegistry.NUKA_COLA_CLASSIC.get()))
                    .title(Component.literal("Falloutcraft Deco")).build());

}
