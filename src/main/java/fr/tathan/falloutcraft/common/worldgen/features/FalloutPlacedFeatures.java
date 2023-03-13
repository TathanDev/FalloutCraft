package fr.tathan.falloutcraft.common.worldgen.features;


import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.worldSurfaceSquaredWithCount;

public class FalloutPlacedFeatures {

    public static final ResourceKey<PlacedFeature> IRRADIATED_OAK_CHECKED_KEY = createKey("irradiated_oak_checked");
    public static final ResourceKey<PlacedFeature> IRRADIATED_OAK_PLACED_KEY = createKey("irradiated_oak_placed");

    public static final ResourceKey<PlacedFeature> POISONED_JUNGLE_TREE_CHECKED_KEY = createKey("poisoned_jungle_tree_checked");
    public static final ResourceKey<PlacedFeature> POISONED_JUNGLE_TREE_PLACED_KEY = createKey("poisoned_jungle_tree_placed");

    public static final ResourceKey<PlacedFeature> RADIOACTIVA_PLACED_KEY = createKey("radioactiva_placed");
    public static final ResourceKey<PlacedFeature> QUICKSAND_LAKE = createKey("quicksand_lake_placed");
    public static final ResourceKey<PlacedFeature> QUICKDIRT_LAKE = createKey("quickdirt_lake_placed");

    public static final ResourceKey<PlacedFeature> PATCH_POISONED_GRASS_JUNGLE = createKey("patch_poisoned_grass_jungle");




    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);


        register(context, IRRADIATED_OAK_CHECKED_KEY, configuredFeatures.getOrThrow(FalloutConfiguredFeatures.IRRADIATED_OAK_KEY),
                List.of(PlacementUtils.filteredByBlockSurvival(BlocksRegistry.IRRADIATED_OAK_SAPLING.get())));

        register(context, IRRADIATED_OAK_PLACED_KEY, configuredFeatures.getOrThrow(FalloutConfiguredFeatures.IRRADIATED_OAK_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2)));

        register(context, POISONED_JUNGLE_TREE_CHECKED_KEY, configuredFeatures.getOrThrow(FalloutConfiguredFeatures.POISONED_JUNGLE_TREE_KEY),
                List.of(PlacementUtils.filteredByBlockSurvival(BlocksRegistry.POISONED_JUNGLE_SAPLING.get())));

        register(context, POISONED_JUNGLE_TREE_PLACED_KEY, configuredFeatures.getOrThrow(FalloutConfiguredFeatures.POISONED_JUNGLE_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2)));



        register(context, RADIOACTIVA_PLACED_KEY, configuredFeatures.getOrThrow(FalloutConfiguredFeatures.RADIOACTIVA_KEY),
                List.of(RarityFilter.onAverageOnceEvery(16),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context, QUICKSAND_LAKE, configuredFeatures.getOrThrow(FalloutConfiguredFeatures.QUICKSAND_LAKE), RarityFilter.onAverageOnceEvery(89), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

        register(context, QUICKDIRT_LAKE, configuredFeatures.getOrThrow(FalloutConfiguredFeatures.QUICKDIRT_LAKE), RarityFilter.onAverageOnceEvery(89), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

        register(context, PATCH_POISONED_GRASS_JUNGLE, configuredFeatures.getOrThrow(FalloutConfiguredFeatures.PATCH_POISONED_GRASS_JUNGLE), worldSurfaceSquaredWithCount(25));


    }



    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(FalloutCraft.MODID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }


}
