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

        if (pAmplifier == 0) {
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 5, 0, false, false, false));
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 5, 0, false, false, false));

            super.applyEffectTick(pLivingEntity, pAmplifier);
        } else if (pAmplifier == 1) {
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 5, 1, false, false, false));
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 5, 1, false, false, false));
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 5, 1, false, false, false));
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 5, 0, false, false, false));


            super.applyEffectTick(pLivingEntity, pAmplifier);
        } else if (pAmplifier == 2) {
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 5, 0, false, false, false));
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 5, 2, false, false, false));

            super.applyEffectTick(pLivingEntity, pAmplifier);
        }
    }
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {

        return true;
    }

}
