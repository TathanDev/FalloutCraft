package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.worldgen.FalloutBiomes;
import fr.tathan.falloutcraft.common.worldgen.FalloutOverworldBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
public class BiomesRegistry {

    public static final DeferredRegister<Biome> BIOME_REGISTER =
            DeferredRegister.create(ForgeRegistries.BIOMES, FalloutCraft.MODID);

    public static void registerBiomes()
    {
        register(FalloutBiomes.RADIOACTIVE_PLAINS, FalloutOverworldBiomes::radioactivePlains);
        register(FalloutBiomes.RADIOACTIVE_FOREST, FalloutOverworldBiomes::radioactiveForest);
        register(FalloutBiomes.POISONED_JUNGLE, FalloutOverworldBiomes::poisonedJungle);
        register(FalloutBiomes.ASH_LAND, FalloutOverworldBiomes::ashLand);

    }

    public static RegistryObject<Biome> register(ResourceKey<Biome> key, Supplier<Biome> biomeSupplier)
    {
        return BIOME_REGISTER.register(key.location().getPath(), biomeSupplier);
    }

}
