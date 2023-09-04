package fr.tathan.falloutcraft.client.gui.nuka_cola_machine;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.client.gui.nuka_cola_machine.renderer.FluidTankRenderer;
import fr.tathan.falloutcraft.common.blocks.entity.NukaColaMachineBlockEntity;
import fr.tathan.falloutcraft.common.util.MouseUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.SmithingScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NukaColaMachineScreen extends AbstractContainerScreen<NukaColaMachineMenu.GuiContainer> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(FalloutCraft.MODID,"textures/gui/nuka_cola_machine_gui.png");

    private FluidTankRenderer fluidTankRenderer;
    public NukaColaMachineScreen(NukaColaMachineMenu.GuiContainer menu, Inventory inventory, Component component) {
        super(menu, inventory, component);

        this.imageWidth = 177;
        this.imageHeight = 168;
        this.titleLabelY -= 2;

        this.inventoryLabelY += 3;



    }



    @Override
    protected void init() {
        super.init();
        assignFluidRenderer();
    }


    @Override
    protected void renderBg(GuiGraphics graphics, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        fluidTankRenderer.render(graphics.pose(), x + 55, y + 13, menu.getFluidStack());

    }

    private void assignFluidRenderer() {
        fluidTankRenderer = new FluidTankRenderer(64000, true, 16, 61);
    }


    /**
    private void renderProgressArrow(GuiGraphics pPoseStack, int x, int y) {
        if(menu.isCrafting()) {
            blit(pPoseStack, x + 105, y + 33, 176, 0, 8, menu.getScaledProgress());

        }
    }

    */

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        this.renderTooltip(guiGraphics, mouseX, mouseY);

        NukaColaMachineBlockEntity blockEntity = menu.getBlockEntity();

        renderFluidAreaTooltips(guiGraphics, mouseX, mouseY, blockEntity.getBlockPos().getX(), blockEntity.getBlockPos().getY());


    }

    private void renderFluidAreaTooltips(GuiGraphics graphics, int pMouseX, int pMouseY, int x, int y) {
        if(isMouseAboveArea(pMouseX, pMouseY, x, y, 55, 15)) {
            FalloutCraft.LOGGER.debug("Mouse is above fluid area");
            this.setTooltipForNextRenderPass(fluidTankRenderer.getTooltip(menu.getFluidStack()).get(0));
            this.renderTooltip(graphics, pMouseX, pMouseY);
        }
    }


    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, fluidTankRenderer.getWidth(), fluidTankRenderer.getHeight());
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }
}
