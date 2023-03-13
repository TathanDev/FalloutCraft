package fr.tathan.falloutcraft.common.worldgen.features.tree;

import fr.tathan.falloutcraft.common.worldgen.features.FalloutConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class PoisonedJungleTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
        return FalloutConfiguredFeatures.IRRADIATED_OAK_KEY;
    }



}
