package fr.tathan.falloutcraft.common.events;


import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.registries.EffectsRegistry;
import fr.tathan.falloutcraft.common.registries.ItemsRegistry;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FalloutCraft.MODID)
public class Events {


    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            Level level = player.level;

            if (player.getArmorSlots().equals(ItemsRegistry.NUKA_COLA.get())) { //
                if (player.hasEffect(EffectsRegistry.RADIATION.get())) {

                    player.removeEffect(EffectsRegistry.RADIATION.get());
                }
            }
        }
    }
}
