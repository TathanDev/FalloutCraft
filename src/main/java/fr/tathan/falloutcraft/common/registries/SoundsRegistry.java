package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.effects.RadiationsEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundsRegistry {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTs
            = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FalloutCraft.MODID);

    public static final RegistryObject<SoundEvent> AN_OLD_SONG = SOUND_EVENTs.register("an_old_song", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FalloutCraft.MODID, "an_old_song")));


}
