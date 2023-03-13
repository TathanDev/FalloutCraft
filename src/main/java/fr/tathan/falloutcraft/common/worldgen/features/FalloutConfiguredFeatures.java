package fr.tathan.falloutcraft.common.worldgen.features;

import com.google.common.collect.ImmutableList;
import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
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
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.levelgen.feature.configurations.*;

import java.util.List;

public class FalloutConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registries.CONFIGURED_FEATURE, FalloutCraft.MODID);

    public static final ResourceKey<ConfiguredFeature<?, ?>> IRRADIATED_OAK_KEY = registerKey("irradiated_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> IRRADIATED_OAK_SPAWN_KEY = registerKey("irradiated_oak_spawn");

    public static final ResourceKey<ConfiguredFeature<?, ?>> POISONED_JUNGLE_TREE_KEY = registerKey("poisoned_jungle_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> POISONED_JUNGLE_TREE_SPAWN_KEY = registerKey("poisoned_jungle_tree_spawn");


    public static final ResourceKey<ConfiguredFeature<?, ?>> RADIOACTIVA_KEY = registerKey("radioactiva");

    public static final ResourceKey<ConfiguredFeature<?, ?>> QUICKSAND_LAKE = registerKey("quicksand_lake");

    public static final ResourceKey<ConfiguredFeature<?, ?>> QUICKDIRT_LAKE = registerKey("quickdirt_lake");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_POISONED_GRASS_JUNGLE = registerKey("patch_poisoned_grass_jungle");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);



        register(context, IRRADIATED_OAK_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_WOOD),
                new FancyTrunkPlacer(4, 7, 3),
                BlockStateProvider.simple(Blocks.AIR),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, IRRADIATED_OAK_SPAWN_KEY, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                        placedFeatures.getOrThrow(FalloutPlacedFeatures.IRRADIATED_OAK_CHECKED_KEY),
                        0.5F)), placedFeatures.getOrThrow(FalloutPlacedFeatures.IRRADIATED_OAK_CHECKED_KEY)));

        register(context, POISONED_JUNGLE_TREE_KEY, Feature.TREE,
                (new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new MegaJungleTrunkPlacer(10, 2, 19),
                        BlockStateProvider.simple(Blocks.AIR),
                        new MegaJungleFoliagePlacer(ConstantInt.of(0),
                                ConstantInt.of(0), 0),
                        new TwoLayersFeatureSize(1, 1, 2)).decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE,
                        new LeaveVineDecorator(0.25F)))).build());

        register(context, POISONED_JUNGLE_TREE_SPAWN_KEY, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                        placedFeatures.getOrThrow(FalloutPlacedFeatures.POISONED_JUNGLE_TREE_CHECKED_KEY),
                        0.5F)), placedFeatures.getOrThrow(FalloutPlacedFeatures.POISONED_JUNGLE_TREE_CHECKED_KEY)));


        register(context, RADIOACTIVA_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(32, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(BlocksRegistry.RADIOACTIVA.get())))));;

        register(context, QUICKSAND_LAKE, Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(BlocksRegistry.QUICKSAND.get().defaultBlockState()), BlockStateProvider.simple(Blocks.COARSE_DIRT.defaultBlockState())));

        register(context, QUICKDIRT_LAKE, Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(BlocksRegistry.QUICKDIRT.get().defaultBlockState()), BlockStateProvider.simple(Blocks.COARSE_DIRT.defaultBlockState())));

        register(context, PATCH_POISONED_GRASS_JUNGLE, Feature.RANDOM_PATCH, new RandomPatchConfiguration(16, 7, 3, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                .add(BlocksRegistry.POISONED_GRASS.get().defaultBlockState(), 5))),
                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.not(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.PODZOL))))));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(FalloutCraft.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
