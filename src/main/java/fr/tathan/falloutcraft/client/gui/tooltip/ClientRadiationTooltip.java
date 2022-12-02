package fr.tathan.falloutcraft.client.gui.tooltip;

import com.mojang.math.Matrix4f;
import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.MultiBufferSource;

public class ClientRadiationTooltip implements ClientTooltipComponent  {

    private final RadiationTooltip tooltip;

    public ClientRadiationTooltip(RadiationTooltip tooltip) {

        this.tooltip = tooltip;

    }

    @Override
    public int getHeight() {
        return tooltip.getHeight();
    }

    @Override
    public int getWidth(Font pFont) {
        return tooltip.getWidth();
    }

    @Override
    public void renderText(Font font, int posX, int posY, Matrix4f matrix4f, MultiBufferSource.BufferSource source) {
        int width = 0;
        final float scale = 1F;
        matrix4f.multiply(Matrix4f.createScaleMatrix(scale, scale, scale));

        final String pText = "Radiation : " + tooltip.getRadiation();
        final String text = pText;

        final int textWidth = font.width(text);

        font.drawInBatch(text, posX / scale, (posY + 1) / scale, -1, true, matrix4f, source, false, 130, 15728880);
        matrix4f.multiply(Matrix4f.createScaleMatrix(1 / scale, 1 / scale, 1 / scale));
        }


}
