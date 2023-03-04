package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FalloutCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TabsRegistry {

    public static CreativeModeTab FALLOUTCRAFT_TAB;

    public static CreativeModeTab FALLOUTCRAFT_DECORATIONS_TAB;


    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        FALLOUTCRAFT_TAB = event.registerCreativeModeTab(new ResourceLocation(FalloutCraft.MODID, "falloutcraft_tab"),
                builder -> builder.icon(() -> new ItemStack(ItemsRegistry.NUKA_COLA_CLASSIC.get()))
                        .title(Component.literal("Falloutcraft"))
                        .build());

        FALLOUTCRAFT_DECORATIONS_TAB = event.registerCreativeModeTab(new ResourceLocation(FalloutCraft.MODID, "falloutcraft_deco_tab"),
                builder -> builder.icon(() -> new ItemStack(ItemsRegistry.PAPERS_ON_THE_GROUND_ITEM.get())).title(Component.literal("Falloutcraft Deco")).build());
    }

}
