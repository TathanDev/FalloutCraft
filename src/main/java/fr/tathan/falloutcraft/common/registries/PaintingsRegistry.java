package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PaintingsRegistry {

    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, FalloutCraft.MODID);

    public static final RegistryObject<PaintingVariant> NUKA_COLA_POSTER = PAINTING_VARIANTS.register("nuka_cola_poster",
            () -> new PaintingVariant(32, 32));

    public static final RegistryObject<PaintingVariant> SURFACE_NEVER_POSTER = PAINTING_VARIANTS.register("surface_never_poster",
            () -> new PaintingVariant(32, 32));


}
