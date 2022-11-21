package fr.tathan.falloutcraft.common.worldgen.features;


import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class FalloutPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, FalloutCraft.MODID);

    public static final RegistryObject<PlacedFeature> IRRADIATED_OAK_CHECKED = PLACED_FEATURES.register("irradiated_oak_checked",
            () -> new PlacedFeature(FalloutConfiguredFeatures.IRRADIATED_OAK.getHolder().get(),
                    List.of(PlacementUtils.filteredByBlockSurvival(BlocksRegistry.IRRADIATED_OAK_SAPLING.get()))));

    public static final RegistryObject<PlacedFeature> IRRADIATED_OAK_PLACED = PLACED_FEATURES.register("irradiated_oak_placed",
            () -> new PlacedFeature(FalloutConfiguredFeatures.IRRADIATED_OAK_SPAWN.getHolder().get(), VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(3, 0.1f, 2))));


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
