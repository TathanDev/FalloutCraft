package fr.tathan.falloutcraft.common.worldgen;

import fr.tathan.falloutcraft.FalloutCraft;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class FalloutBiomes {


    public static final ResourceKey<Biome> RADIOACTIVE_PLAINS = register("radioactive_plains");

    private static ResourceKey<Biome> register(String name)
    {
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(FalloutCraft.MODID, name));
    }

}
