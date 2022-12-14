package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class TagsRegistry {

    public static final TagKey<EntityType<?>> RADIATION_IMMUNISED;
    public static final TagKey<Item> VERY_RADIOACTIVE;


    public TagsRegistry() {
    }


    static {
        VERY_RADIOACTIVE = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(FalloutCraft.MODID, "very_radioactive"));

        RADIATION_IMMUNISED = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(FalloutCraft.MODID, "radiation_immunised"));
    }


}
