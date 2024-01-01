package fr.tathan.falloutcraft.common.worldgen.features.tree;

import fr.tathan.falloutcraft.common.worldgen.features.FalloutConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class BurntOakTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pLargeHive) {
        return FalloutConfiguredFeatures.BURNT_OAK.getHolder().get();
    }



}
