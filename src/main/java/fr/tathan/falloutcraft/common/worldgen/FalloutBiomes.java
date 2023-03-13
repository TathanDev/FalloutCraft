package fr.tathan.falloutcraft.common.worldgen;

import fr.tathan.falloutcraft.FalloutCraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;

public class FalloutBiomes {

    public static final ResourceKey<Biome> RADIOACTIVE_PLAINS = register("radioactive_plains");
    public static final ResourceKey<Biome> RADIOACTIVE_FOREST = register("radioactive_forest");
    public static final ResourceKey<Biome> POISONED_JUNGLE = register("poisoned_jungle");





    private static ResourceKey<Biome> register(String name)
    {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(FalloutCraft.MODID, name));
    }

}
