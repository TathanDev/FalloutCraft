package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.items.NukaCola;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemsRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FalloutCraft.MODID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, FalloutCraft.MODID);


    public static final RegistryObject<Item> NUKA_COLA = ITEMS.register("nuka_cola",
            () -> new NukaCola(new Item.Properties().tab(TabsRegistry.TAB).food(new FoodProperties.Builder().fast().nutrition(2).saturationMod(0.2F).build()).stacksTo(16)));


    public static final RegistryObject<Item> NUKA_COLA_MIXTURE = ITEMS.register("nuka_cola_mixture",
            () -> new NukaCola(new Item.Properties().tab(TabsRegistry.TAB)));

    public static final RegistryObject<Item> RADIATED_WATER_BUCKET = ITEMS.register("radiated_water_bucket",
            () -> new BucketItem(FluidsRegistry.SOURCE_RADIATED_WATER,
                    new Item.Properties().tab(TabsRegistry.TAB).craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<BlockItem> IRRADIATED_OAK_SAPLING_ITEM = ITEMS.register("irradiated_oak_sapling", () -> new BlockItem(BlocksRegistry.IRRADIATED_OAK_SAPLING.get(), new Item.Properties().tab(TabsRegistry.TAB)));

    public static final RegistryObject<BlockItem> PAPERS_ON_THE_GROUND_ITEM = ITEMS.register("papers_on_the_ground", () -> new BlockItem(BlocksRegistry.PAPERS_ON_THE_GROUND.get(), new Item.Properties().tab(TabsRegistry.TAB_DECORATION)));
    public static final RegistryObject<BlockItem> NUKA_COLA_MACHINE_ITEM = ITEMS.register("nuka_cola_machine", () -> new BlockItem(BlocksRegistry.NUKA_COLA_MACHINE.get(), new Item.Properties().tab(TabsRegistry.TAB_DECORATION)));

    public static final RegistryObject<BlockItem> RADIOACTIVA = ITEMS.register("radioactiva", () -> new BlockItem(BlocksRegistry.RADIOACTIVA.get(), new Item.Properties().tab(TabsRegistry.TAB)));

    //public static final RegistryObject<BlockItem> VAULT_TRAPDOOR_ITEM = ITEMS.register("vault_trapdoor", () -> new BlockItem(BlocksRegistry.VAULT_TRAPDOOR.get(), new Item.Properties().tab(TabsRegistry.TAB_DECORATION)));

    public static final RegistryObject<BlockItem> RADIATION_REMOVER_ITEM = ITEMS.register("radiation_remover", () -> new BlockItem(BlocksRegistry.RADIATION_REMOVER.get(), new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_MASK = ITEMS.register("hazmat_mask",
            () -> new ArmorItem(ArmorMaterials.HAZMAT, EquipmentSlot.HEAD,
                    new Item.Properties().tab(TabsRegistry.TAB)));
    public static final RegistryObject<Item> HAZMAT_CHESTPLATE = ITEMS.register("hazmat_chestplate",
            () -> new ArmorItem(ArmorMaterials.HAZMAT, EquipmentSlot.CHEST,
                    new Item.Properties().tab(TabsRegistry.TAB)));
    public static final RegistryObject<Item> HAZMAT_LEGGINGS = ITEMS.register("hazmat_leggings",
            () -> new ArmorItem(ArmorMaterials.HAZMAT, EquipmentSlot.LEGS,
                    new Item.Properties().tab(TabsRegistry.TAB)));
    public static final RegistryObject<Item> HAZMAT_BOOTS = ITEMS.register("hazmat_boots",
            () -> new ArmorItem(ArmorMaterials.HAZMAT, EquipmentSlot.FEET,
                    new Item.Properties().tab(TabsRegistry.TAB)));


}
