package fr.tathan.falloutcraft.common.worldgen.features;

import fr.tathan.falloutcraft.FalloutCraft;
import net.minecraft.core.Registry;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class FalloutConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, FalloutCraft.MODID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> IRRADIATED_OAK =
            CONFIGURED_FEATURES.register("irradiated_oak", () ->
                    new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(Blocks.OAK_WOOD),
                            new FancyTrunkPlacer(4, 7, 3),
                            BlockStateProvider.simple(Blocks.AIR),
                            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                            new TwoLayersFeatureSize(1, 0, 2)).build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> IRRADIATED_OAK_SPAWN =
            CONFIGURED_FEATURES.register("irradiated_oak_spawn", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                            FalloutPlacedFeatures.IRRADIATED_OAK_CHECKED.getHolder().get(),
                            0.5F)), FalloutPlacedFeatures.IRRADIATED_OAK_CHECKED.getHolder().get())));


}
