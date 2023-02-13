package fr.tathan.falloutcraft.common.loot;

import com.mojang.serialization.Codec;
import fr.tathan.falloutcraft.FalloutCraft;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, FalloutCraft.MODID);

    public static RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEM_MODIFIER = LOOT_MODIFIER_SERIALIZERS.register("add_item", AddItemModifier.CODEC);
    public static RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEMS_MODIFIER = LOOT_MODIFIER_SERIALIZERS.register("add_items", AddItemsModifier.CODEC);

}
