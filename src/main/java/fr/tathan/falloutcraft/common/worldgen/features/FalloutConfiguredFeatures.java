package fr.tathan.falloutcraft.common.worldgen.features;

import com.google.common.collect.ImmutableList;
import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.levelgen.feature.configurations.*;

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


    /** Poisoned Jungle Tree **/

    public static final RegistryObject<ConfiguredFeature<?, ?>> POISONED_JUNGLE_TREE =
            CONFIGURED_FEATURES.register("poisoned_jungle_tree", () ->
                    new ConfiguredFeature<>(Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                            new MegaJungleTrunkPlacer(10, 2, 19),
                            BlockStateProvider.simple(Blocks.AIR),
                            new MegaJungleFoliagePlacer(ConstantInt.of(0),
                                    ConstantInt.of(0), 0),
                            new TwoLayersFeatureSize(1, 1, 2)).decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE,
                            new LeaveVineDecorator(0.25F)))).build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> POISONED_JUNGLE_TREE_SPAWN_KEY =
            CONFIGURED_FEATURES.register("poisoned_jungle_tree_spawn", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                            FalloutPlacedFeatures.POISONED_JUNGLE_TREE_CHECKED.getHolder().get(),
                            0.5F)), FalloutPlacedFeatures.POISONED_JUNGLE_TREE_CHECKED.getHolder().get())));



    public static final RegistryObject<ConfiguredFeature<?, ?>> RADIOACTIVA = CONFIGURED_FEATURES.register("radioactiva",
            () -> new ConfiguredFeature<>(Feature.FLOWER,
                    new RandomPatchConfiguration(32, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(BlocksRegistry.RADIOACTIVA.get()))))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> POISONED_GRASS_PATCH = CONFIGURED_FEATURES.register("poisoned_grass_patch",
            () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH,
                    new RandomPatchConfiguration(16, 7, 3, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                                    .add(BlocksRegistry.POISONED_GRASS.get().defaultBlockState(), 5))),
            BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
            BlockPredicate.not(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.PODZOL)))))));






    public static final RegistryObject<ConfiguredFeature<?, ?>> RADIATED_WATER_LAKE = CONFIGURED_FEATURES.register("radiared_water_lake",
            () -> new ConfiguredFeature<>(Feature.LAKE,
                    new LakeFeature.Configuration(BlockStateProvider.simple(BlocksRegistry.RADIATED_WATER_BLOCK.get().defaultBlockState()), BlockStateProvider.simple(Blocks.COARSE_DIRT.defaultBlockState()))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> QUICKSAND_LAKE = CONFIGURED_FEATURES.register("quicksand_lake",
            () -> new ConfiguredFeature<>(Feature.LAKE,
                    new LakeFeature.Configuration(BlockStateProvider.simple(BlocksRegistry.QUICKSAND.get().defaultBlockState()), BlockStateProvider.simple(Blocks.COARSE_DIRT.defaultBlockState()))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> QUICKDIRT_LAKE = CONFIGURED_FEATURES.register("quickdirt_lake",
            () -> new ConfiguredFeature<>(Feature.LAKE,
                    new LakeFeature.Configuration(BlockStateProvider.simple(BlocksRegistry.QUICKDIRT.get().defaultBlockState()), BlockStateProvider.simple(Blocks.COARSE_DIRT.defaultBlockState()))));

}
