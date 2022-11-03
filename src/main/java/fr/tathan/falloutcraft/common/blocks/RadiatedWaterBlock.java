package fr.tathan.falloutcraft.common.blocks;

import fr.tathan.falloutcraft.common.registries.EffectsRegistry;
import fr.tathan.falloutcraft.common.registries.FluidsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

public class RadiatedWaterBlock extends LiquidBlock {

    public RadiatedWaterBlock(java.util.function.Supplier<? extends FlowingFluid> pFluid, BlockBehaviour.Properties pProperties) {
        super(FluidsRegistry.SOURCE_RADIATED_WATER, BlockBehaviour.Properties.copy(Blocks.WATER));
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {

        if(pEntity instanceof LivingEntity pEntityLiving) {
            pEntityLiving.addEffect(new MobEffectInstance(EffectsRegistry.RADIATION.get(), 200, 0));
        }

        super.stepOn(pLevel, pPos, pState, pEntity);

    }



}

