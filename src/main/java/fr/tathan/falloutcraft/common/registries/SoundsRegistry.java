package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class SoundsRegistry {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FalloutCraft.MODID);

    /**
     * SOUNDS
     */
    public static final RegistryObject<SoundEvent> APOCALYSPE_MUSIC_1 = SOUNDS.register("apocalypse_music_1", () -> new SoundEvent(new ResourceLocation(FalloutCraft.MODID, "apocalypse_music_1")));


}
