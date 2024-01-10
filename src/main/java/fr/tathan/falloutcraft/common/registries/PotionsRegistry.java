package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PotionsRegistry {

    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, FalloutCraft.MODID);

    public static final RegistryObject<Potion> RADIATION_POTION = POTIONS.register("radiation_potion",
            () -> new Potion(new MobEffectInstance(EffectsRegistry.RADIATION.get(), 1000, 0)));



}
