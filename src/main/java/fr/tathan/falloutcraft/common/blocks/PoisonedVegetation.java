package fr.tathan.falloutcraft.common.blocks;

import fr.tathan.falloutcraft.common.registries.EffectsRegistry;
import fr.tathan.falloutcraft.common.registries.TagsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PoisonedVegetation extends FlowerBlock {
    int effectDuration;
    MobEffect effect;

    public PoisonedVegetation(MobEffect effect, int effectDuration, Properties pProperties) {
        super(effect, effectDuration, pProperties);
        this.effectDuration = effectDuration;
        this.effect = effect;

    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return super.mayPlaceOn(pState, pLevel, pPos) || pState.is(Blocks.DIRT) || pState.is(Blocks.GRASS_BLOCK) || pState.is(Blocks.COARSE_DIRT);
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (!pLevel.isClientSide && pLevel.getDifficulty() != Difficulty.PEACEFUL) {
            if (pEntity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)pEntity;
                if (!livingentity.getType().is(TagsRegistry.RADIATION_IMMUNISED)) {
                    livingentity.addEffect(new MobEffectInstance(EffectsRegistry.RADIATION.get(), effectDuration));
                }
            }

        }
    }


}

