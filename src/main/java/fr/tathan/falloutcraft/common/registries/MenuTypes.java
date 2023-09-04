package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.client.gui.nuka_cola_machine.NukaColaMachineMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, FalloutCraft.MODID);
    public static final RegistryObject<MenuType<NukaColaMachineMenu.GuiContainer>> NUKA_COLA_MACHINE_MENU = MENUS.register("nuka_cola_machine_menu",
            () -> new MenuType<>(new NukaColaMachineMenu.GuiContainerFactory(), null));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }

}
