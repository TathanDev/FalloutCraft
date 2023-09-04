package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;

public class DamageTypeRegistry {

    public static final DeferredRegister<DamageType> DAMAGE_TYPE_REGISTER =
            DeferredRegister.create(Registries.DAMAGE_TYPE, FalloutCraft.MODID);

    public static final ResourceKey<DamageType> RADIOACTIVE_RAIN = registerKey("radioactive_rain");

    private static ResourceKey<DamageType> registerKey(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(FalloutCraft.MODID, name));
    }

    public static DamageSource of(Level level, ResourceKey<DamageType> key) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key));
    }



}
