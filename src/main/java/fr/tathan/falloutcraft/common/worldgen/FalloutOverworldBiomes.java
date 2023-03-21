package fr.tathan.falloutcraft.common.worldgen;

import fr.tathan.falloutcraft.common.worldgen.features.FalloutPlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import javax.annotation.Nullable;


public class FalloutOverworldBiomes {

    @Nullable
    private static final Music NORMAL_MUSIC = null;

    protected static int calculateSkyColor(float color)
    {
        float $$1 = color / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }



    private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor,int foliageColor,int grassColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
    {
        return (new Biome.BiomeBuilder()).precipitation(precipitation).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(calculateSkyColor(temperature)).foliageColorOverride(foliageColor).grassColorOverride(grassColor).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
    {
        return (new Biome.BiomeBuilder()).precipitation(precipitation).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder)
    {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
        addQuickDirtLake(builder);

    }

    public static Biome radioactivePlains(HolderGetter<PlacedFeature> pPlacedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> pConfiguredWorldCarverGetter)
    {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(pPlacedFeatureGetter, pConfiguredWorldCarverGetter);
        BiomeDefaultFeatures.addFossilDecoration(biomeBuilder);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        //BiomeDefaultFeatures.addDefaultFlowers(biomeBuilder);
        BiomeDefaultFeatures.addDefaultGrass(biomeBuilder);
        BiomeDefaultFeatures.addDesertVegetation(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDesertExtraVegetation(biomeBuilder);
        BiomeDefaultFeatures.addDesertExtraDecoration(biomeBuilder);
        BiomeDefaultFeatures.addAncientDebris(biomeBuilder);

        calculateSkyColor(6415715);
        return biome(Biome.Precipitation.NONE, 2.0F, 0.0F, 6415715, 6415715, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome radioactiveForest(HolderGetter<PlacedFeature> pPlacedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> pConfiguredWorldCarverGetter)
    {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(pPlacedFeatureGetter, pConfiguredWorldCarverGetter);
        BiomeDefaultFeatures.addFossilDecoration(biomeBuilder);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDesertVegetation(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDesertExtraVegetation(biomeBuilder);
        BiomeDefaultFeatures.addDesertExtraDecoration(biomeBuilder);
        BiomeDefaultFeatures.addAncientDebris(biomeBuilder);

        return biome(Biome.Precipitation.NONE, 2.0F, 0.0F, 6415715, 6415715, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome poisonedJungle(HolderGetter<PlacedFeature> pPlacedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> pConfiguredWorldCarverGetter)
    {

        MobSpawnSettings.Builder mobspawnsetting = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.baseJungleSpawns(mobspawnsetting);


        BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder(pPlacedFeatureGetter, pConfiguredWorldCarverGetter);
        globalOverworldGeneration(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultOres(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomegenerationsettings$builder);


        //BiomeDefaultFeatures.addJungleGrass(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addJungleVines(biomegenerationsettings$builder);
        addQuickSandLake(biomegenerationsettings$builder);
        poisonedJungleDefault(biomegenerationsettings$builder);




        return biome(Biome.Precipitation.NONE, 2.0F, 0.0F, 6415715, 6415715, mobspawnsetting, biomegenerationsettings$builder, NORMAL_MUSIC);
    }

    public static void addQuickSandLake(BiomeGenerationSettings.Builder pBuilder) {
        pBuilder.addFeature(GenerationStep.Decoration.LAKES, FalloutPlacedFeatures.QUICKSAND_LAKE);
    }

    public static void addQuickDirtLake(BiomeGenerationSettings.Builder pBuilder) {

        pBuilder.addFeature(GenerationStep.Decoration.LAKES, FalloutPlacedFeatures.QUICKDIRT_LAKE);
    }
    public static void poisonedJungleDefault(BiomeGenerationSettings.Builder pBuilder) {
        pBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FalloutPlacedFeatures.POISONED_JUNGLE_TREE_PLACED_KEY);
        pBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FalloutPlacedFeatures.PATCH_POISONED_GRASS_JUNGLE);
    }

}



