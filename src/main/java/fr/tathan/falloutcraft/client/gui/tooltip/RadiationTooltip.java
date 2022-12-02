package fr.tathan.falloutcraft.client.gui.tooltip;

import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTextTooltip;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.inventory.tooltip.BundleTooltip;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

public class RadiationTooltip implements TooltipComponent {


    public static final int SINGLE_HEIGHT = 11;
    public static final int SINGLE_WIDTH = 70;
                                        //16


    private final double radiation;



    public RadiationTooltip(ItemRadiation radiation) {

        this.radiation = radiation.getRadiation();

    }


    public static int getHeight(){
        return SINGLE_HEIGHT;
    }

    public int getWidth(){
        return  SINGLE_WIDTH;
    }

    public double getRadiation() { return radiation; }



}


