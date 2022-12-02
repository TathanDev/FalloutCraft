package fr.tathan.falloutcraft.common.effects;

import net.minecraft.world.effect.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class RadiationsEffect extends MobEffect {

    public RadiationsEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 5, 0, false, false, false));
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 5, 0, false, false, false));
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 5, 0, false, false, false));

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }


    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {

        return true;
    }

}
