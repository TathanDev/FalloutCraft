package fr.tathan.falloutcraft.common.worldgen;

import fr.tathan.falloutcraft.common.worldgen.features.FalloutPlacedFeatures;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import javax.annotation.Nullable;

import static fr.tathan.falloutcraft.common.registries.SoundsRegistry.APOCALYSPE_MUSIC_1;

public class FalloutOverworldBiomes {

    private static final Music APOCALYPTIC_MUSIC = Musics.createGameMusic(APOCALYSPE_MUSIC_1.get());

    protected static int calculateSkyColor(float color)
    {
        float $$1 = color / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
    {
        return biome(precipitation, temperature, downfall, 4159204, 329011, spawnBuilder, biomeBuilder, music);
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
    }

    public static Biome radioactivePlains()
    {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
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
        addLakes(biomeBuilder);

        calculateSkyColor(6415715);
        return biome(Biome.Precipitation.RAIN, 2.0F, 0.0F, 6415715, 6415715, spawnBuilder, biomeBuilder, APOCALYPTIC_MUSIC);
    }

    public static Biome radioactiveForest()
    {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        BiomeDefaultFeatures.addFossilDecoration(biomeBuilder);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultGrass(biomeBuilder);
        BiomeDefaultFeatures.addDesertVegetation(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDesertExtraVegetation(biomeBuilder);
        BiomeDefaultFeatures.addDesertExtraDecoration(biomeBuilder);
        BiomeDefaultFeatures.addAncientDebris(biomeBuilder);
        addLakes(biomeBuilder);


        return biome(Biome.Precipitation.RAIN, 2.0F, 0.0F, 6415715, 6415715, spawnBuilder, biomeBuilder, APOCALYPTIC_MUSIC);
    }

    public static Biome poisonedJungle()
    {

        MobSpawnSettings.Builder mobspawnsetting = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.baseJungleSpawns(mobspawnsetting);


        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);


        //BiomeDefaultFeatures.addJungleGrass(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        BiomeDefaultFeatures.addJungleVines(biomeBuilder);
        jungleFeatures(biomeBuilder);
        addLakes(biomeBuilder);






        return biome(Biome.Precipitation.NONE, 2.0F, 0.0F, 6415715, 6415715, mobspawnsetting, biomeBuilder, APOCALYPTIC_MUSIC);
    }
    public static Biome ashLand()
    {

        MobSpawnSettings.Builder mobspawnsetting = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.baseJungleSpawns(mobspawnsetting);


        BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultOres(biomegenerationsettings$builder);
        //biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FalloutPlacedFeatures.BURNT_OAK_PLACED.getHolder().get());
        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.LAKES, FalloutPlacedFeatures.RADIATED_WATER_LAKE_ASH.getHolder().get());
        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, FalloutPlacedFeatures.ASH_TOP_LAYER.getHolder().get());


        return biome(Biome.Precipitation.NONE, 2.0F, 0.0F, 6415715, 6415715, mobspawnsetting, biomegenerationsettings$builder, APOCALYPTIC_MUSIC);
    }

    public static void addLakes(BiomeGenerationSettings.Builder biomeBuilder) {
        biomeBuilder.addFeature(GenerationStep.Decoration.LAKES, FalloutPlacedFeatures.QUICKSAND_LAKE.getHolder().get());
        biomeBuilder.addFeature(GenerationStep.Decoration.LAKES, FalloutPlacedFeatures.QUICKDIRT_LAKE.getHolder().get());
        biomeBuilder.addFeature(GenerationStep.Decoration.LAKES, FalloutPlacedFeatures.RADIATED_WATER_LAKE.getHolder().get());

   }

   public static void jungleFeatures(BiomeGenerationSettings.Builder biomeBuilder) {
       biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FalloutPlacedFeatures.POISONED_JUNGLE_TREE_PLACED_KEY.getHolder().get());
       biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FalloutPlacedFeatures.POISONED_GRASS_PATCH.getHolder().get());
       biomeBuilder.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, FalloutPlacedFeatures.ASH_TOP_LAYER.getHolder().get());
   }

}
