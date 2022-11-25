package fr.tathan.falloutcraft.client.event;

import com.mojang.datafixers.util.Either;
import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.client.gui.tooltip.RadiationTooltip;
import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import fr.tathan.falloutcraft.common.radiation.ItemRadiationProvider;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

//My this thing fuck because it's not Dist.CLIENT anymore
@Mod.EventBusSubscriber(modid = FalloutCraft.MODID)
public class ClientEvents {


    @SubscribeEvent
    public static void gatherComponents(RenderTooltipEvent.GatherComponents event){
            List<Either<FormattedText, TooltipComponent>> components = event.getTooltipElements();

        NonNullList<ItemStack> nonnulllist = NonNullList.create();
        ItemRadiation radiation = event.getItemStack().getCapability(ItemRadiationProvider.ITEM_RADIATION).orElseThrow(() -> new IllegalStateException("Damn! An Error ?! This is Spooky !!"));

        components.add(components.size(), Either.right(new RadiationTooltip(radiation)));

    }


}
