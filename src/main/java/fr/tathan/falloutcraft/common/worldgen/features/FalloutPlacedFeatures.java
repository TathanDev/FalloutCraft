package fr.tathan.falloutcraft.common.worldgen.features;


import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import fr.tathan.falloutcraft.common.registries.FeatureRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.worldSurfaceSquaredWithCount;

public class FalloutPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, FalloutCraft.MODID);

    /** Irradiated oak Tree */
    public static final RegistryObject<PlacedFeature> IRRADIATED_OAK_CHECKED = PLACED_FEATURES.register("irradiated_oak_checked",
            () -> new PlacedFeature(FalloutConfiguredFeatures.IRRADIATED_OAK.getHolder().get(),
                    List.of(PlacementUtils.filteredByBlockSurvival(BlocksRegistry.IRRADIATED_OAK_SAPLING.get()))));

    public static final RegistryObject<PlacedFeature> IRRADIATED_OAK_PLACED = PLACED_FEATURES.register("irradiated_oak_placed",
            () -> new PlacedFeature(FalloutConfiguredFeatures.IRRADIATED_OAK_SPAWN.getHolder().get(), VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(3, 0.1f, 2))));

    /** Poisoned Jungle Tree **/
    public static final RegistryObject<PlacedFeature> POISONED_JUNGLE_TREE_CHECKED = PLACED_FEATURES.register("poisoned_jungle_tree_checked",
            () -> new PlacedFeature(FalloutConfiguredFeatures.POISONED_JUNGLE_TREE.getHolder().get(),
                    List.of(PlacementUtils.filteredByBlockSurvival(BlocksRegistry.POISONED_JUNGLE_SAPLING.get()))));

    public static final RegistryObject<PlacedFeature> POISONED_JUNGLE_TREE_PLACED_KEY = PLACED_FEATURES.register("poisoned_jungle_tree_placed",
            () -> new PlacedFeature(FalloutConfiguredFeatures.POISONED_JUNGLE_TREE_SPAWN_KEY.getHolder().get(), VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(3, 0.1f, 2))));

    /** Burnt Oak Log */
    public static final RegistryObject<PlacedFeature> BURNT_OAK_CHECKED = PLACED_FEATURES.register("burnt_oak_checked",
            () -> new PlacedFeature(FalloutConfiguredFeatures.BURNT_OAK.getHolder().get(),
                    List.of(PlacementUtils.filteredByBlockSurvival(BlocksRegistry.BURNT_OAK_SAPLING.get()))));

    public static final RegistryObject<PlacedFeature> BURNT_OAK_PLACED = PLACED_FEATURES.register("burnt_oak_placed",
            () -> new PlacedFeature(FalloutConfiguredFeatures.BURNT_OAK_SPAWN.getHolder().get(), VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(3, 0.1f, 2))));


    public static final RegistryObject<PlacedFeature> RADIOACTIVA_PLACED = PLACED_FEATURES.register("radioactiva_placed",
            () -> new PlacedFeature(FalloutConfiguredFeatures.RADIOACTIVA.getHolder().get(), List.of(RarityFilter.onAverageOnceEvery(16),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> RADIATED_WATER_LAKE = PLACED_FEATURES.register("radiated_water_lake",
            () -> new PlacedFeature(FalloutConfiguredFeatures.RADIATED_WATER_LAKE.getHolder().get(), List.of(RarityFilter.onAverageOnceEvery(70),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> RADIATED_WATER_LAKE_ASH = PLACED_FEATURES.register("radiated_water_lake_ash",
            () -> new PlacedFeature(FalloutConfiguredFeatures.RADIATED_WATER_LAKE_ASH.getHolder().get(), List.of(RarityFilter.onAverageOnceEvery(70),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> QUICKSAND_LAKE = PLACED_FEATURES.register("quicksand_lake",
            () -> new PlacedFeature(FalloutConfiguredFeatures.RADIATED_WATER_LAKE.getHolder().get(), List.of(RarityFilter.onAverageOnceEvery(70),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> QUICKDIRT_LAKE = PLACED_FEATURES.register("quickdirt_lake",
            () -> new PlacedFeature(FalloutConfiguredFeatures.RADIATED_WATER_LAKE.getHolder().get(), List.of(RarityFilter.onAverageOnceEvery(70),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> POISONED_GRASS_PATCH = PLACED_FEATURES.register("poisoned_grass_patch_placed",
            () -> new PlacedFeature(FalloutConfiguredFeatures.POISONED_GRASS_PATCH.getHolder().get(), worldSurfaceSquaredWithCount(25)));

    public static final RegistryObject<PlacedFeature> ASH_TOP_LAYER = PLACED_FEATURES.register("ash_top_layer",
            () -> new PlacedFeature(FalloutConfiguredFeatures.ASH_TOP_LAYER.getHolder().get(), List.of(BiomeFilter.biome())));


    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }


}
