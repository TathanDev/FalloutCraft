package fr.tathan.falloutcraft.mixin;

import fr.tathan.falloutcraft.common.registries.EffectsRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeLivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin (LivingEntity.class)
public abstract class GlowColorMixin extends Entity implements Attackable, IForgeLivingEntity {
    @Shadow public abstract boolean hasEffect(MobEffect pEffect);

    protected GlowColorMixin(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public int getTeamColor() {
        if (this.hasEffect(EffectsRegistry.RADIATION.get())) {
            return 0x00ff00;
        }

        return 0xffffff;
    }

}
