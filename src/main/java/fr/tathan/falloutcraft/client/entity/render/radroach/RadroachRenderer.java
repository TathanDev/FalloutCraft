package fr.tathan.falloutcraft.client.entity.render.radroach;
/**
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.entity.radroaches.RadroachEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RadroachRenderer extends GeoEntityRenderer<RadroachEntity> {
    public RadroachRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RadroachModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(RadroachEntity instance) {
        return new ResourceLocation(FalloutCraft.MODID, "textures/entity/radroach.png");
    }

    @Override
    public RenderType getRenderType(RadroachEntity animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer,
                                    @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(0.8f, 0.8f, 0.8f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }

}
*/