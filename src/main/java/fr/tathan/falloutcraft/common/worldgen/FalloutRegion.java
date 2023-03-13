package fr.tathan.falloutcraft.common.worldgen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.List;
import java.util.function.Consumer;

import static terrablender.api.ParameterUtils.*;

public class FalloutRegion extends Region {

    public FalloutRegion(ResourceLocation name, int weight)
    {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {

        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            List<Climate.ParameterPoint> PlainsPoints = new ParameterPointListBuilder()
                    .temperature( Temperature.COOL)
                    .humidity(Humidity.NEUTRAL)
                    .continentalness(Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND))
                    .erosion(Erosion.EROSION_4)
                    .depth(Depth.SURFACE)
                    .weirdness(Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.PEAK_VARIANT, Weirdness.HIGH_SLICE_VARIANT_DESCENDING)
                    .build();

            List<Climate.ParameterPoint> ForestPoints = new ParameterPointListBuilder()
                    .temperature(Temperature.COOL)
                    .humidity(Humidity.NEUTRAL)
                    .continentalness(Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND))
                    .erosion(Erosion.EROSION_0, Erosion.EROSION_1)
                    .depth(Depth.SURFACE)
                    .weirdness(Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.PEAK_VARIANT, Weirdness.HIGH_SLICE_VARIANT_DESCENDING)
                    .build();

            List<Climate.ParameterPoint> JunglePoints = new ParameterPointListBuilder()
                    .temperature(Temperature.COOL)
                    .humidity(Humidity.HUMID)
                    .continentalness(Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND))
                    .erosion(Erosion.EROSION_0, Erosion.EROSION_1)
                    .depth(Depth.SURFACE)
                    .weirdness(Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.PEAK_VARIANT, Weirdness.HIGH_SLICE_VARIANT_DESCENDING)
                    .build();




            ForestPoints.forEach(point ->builder.replaceBiome(Biomes.FOREST, FalloutBiomes.RADIOACTIVE_FOREST));
            PlainsPoints.forEach(point ->builder.replaceBiome(Biomes.PLAINS, FalloutBiomes.RADIOACTIVE_PLAINS));
            JunglePoints.forEach(point ->builder.replaceBiome(Biomes.JUNGLE, FalloutBiomes.POISONED_JUNGLE));

        });
    }


}
