package fr.tathan.falloutcraft.client.gui.radiation_remover;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.client.gui.radiation_remover.renderer.FluidTankRenderer;
import fr.tathan.falloutcraft.common.util.MouseUtil;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.TooltipFlag;

import java.util.Optional;

public class RadiationRemoverScreen extends AbstractContainerScreen<RadiationRemoverMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(FalloutCraft.MODID,"textures/gui/radiation_remover.png");

    private FluidTankRenderer renderer;
    private FluidTankRenderer renderer2;

    public RadiationRemoverScreen(RadiationRemoverMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
        assignFluidRenderer();
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        renderWaterAreaTooltips(pPoseStack, pMouseX, pMouseY, x, y);
        renderRadiatedWaterAreaTooltips(pPoseStack, pMouseX, pMouseY, x, y);

    }



    private void assignFluidRenderer() {
        renderer = new FluidTankRenderer(64000, true, 16, 61);
        renderer2 = new FluidTankRenderer(64000, true, 16, 61);
    }

    private void renderWaterAreaTooltips(PoseStack pPoseStack, int pMouseX, int pMouseY, int x, int y) {
        if(isMouseAboveArea(pMouseX, pMouseY, x, y, 55, 15)) {

            renderTooltip(pPoseStack, renderer.getTooltip(menu.getWaterStack(), TooltipFlag.Default.NORMAL),
                    Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }

    private void renderRadiatedWaterAreaTooltips(PoseStack pPoseStack, int pMouseX, int pMouseY, int x, int y) {
        if(isMouseAboveArea(pMouseX, pMouseY, x, y, 100, 15)) {

            renderTooltip(pPoseStack, renderer.getTooltip(menu.getRadiatedWaterStack(), TooltipFlag.Default.NORMAL),
                    Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }



    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

        renderer.render(pPoseStack, x + 55, y + 15, menu.getWaterStack());
        renderer2.render(pPoseStack, x + 100, y + 15, menu.getRadiatedWaterStack());


    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);


    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, renderer.getWidth(), renderer.getHeight());
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }
}
