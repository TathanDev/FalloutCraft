package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.worldgen.FalloutBiomes;
import fr.tathan.falloutcraft.common.worldgen.FalloutOverworldBiomes;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
public class BiomesRegistry {

    public static final DeferredRegister<Biome> BIOME_REGISTER =
            DeferredRegister.create(ForgeRegistries.BIOMES, FalloutCraft.MODID);

    public static void bootstrap(BootstapContext<Biome> pContext){

    HolderGetter<PlacedFeature> holdergetter = pContext.lookup(Registries.PLACED_FEATURE);
    HolderGetter<ConfiguredWorldCarver<?>> holdergetter1 = pContext.lookup(Registries.CONFIGURED_CARVER);


        pContext.register(FalloutBiomes.RADIOACTIVE_PLAINS, FalloutOverworldBiomes.radioactiveForest(holdergetter, holdergetter1));
        pContext.register(FalloutBiomes.RADIOACTIVE_FOREST, FalloutOverworldBiomes.radioactivePlains(holdergetter, holdergetter1));
        pContext.register(FalloutBiomes.POISONED_JUNGLE, FalloutOverworldBiomes.poisonedJungle(holdergetter, holdergetter1));
    }


}
