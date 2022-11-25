package fr.tathan.falloutcraft.client;

import fr.tathan.falloutcraft.client.gui.tooltip.ClientRadiationTooltip;
import fr.tathan.falloutcraft.client.gui.tooltip.RadiationTooltip;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRegister {




    @SubscribeEvent
    public static void registerTooltips(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(RadiationTooltip.class, ClientRadiationTooltip::new);
    }


}
