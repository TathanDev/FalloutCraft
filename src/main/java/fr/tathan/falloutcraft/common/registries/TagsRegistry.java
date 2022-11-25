package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class TagsRegistry {

    public static final TagKey<EntityType<?>> RADIATION_IMMUNISED;

    public TagsRegistry() {
    }
    static {
        RADIATION_IMMUNISED = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(FalloutCraft.MODID, "radiation_immunised"));
    }


}
