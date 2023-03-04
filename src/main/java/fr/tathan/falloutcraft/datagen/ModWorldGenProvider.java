package fr.tathan.falloutcraft.datagen;
import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.registries.BiomesRegistry;
import fr.tathan.falloutcraft.common.worldgen.FalloutOverworldBiomes;
import fr.tathan.falloutcraft.common.worldgen.features.FalloutConfiguredFeatures;
import fr.tathan.falloutcraft.common.worldgen.features.FalloutPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, FalloutConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, FalloutPlacedFeatures::bootstrap)
            .add(Registries.BIOME, BiomesRegistry::bootstrap);

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Collections.singleton(FalloutCraft.MODID));
    }

}
