package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.items.*;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemsRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FalloutCraft.MODID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, FalloutCraft.MODID);

    public static final RegistryObject<Item> NUKA_COLA_CLASSIC = ITEMS.register("nuka_cola",
            () -> new NukaCola(new Item.Properties().food(new FoodProperties.Builder().fast().nutrition(2).saturationMod(0.2F).build()).stacksTo(16), MobEffects.MOVEMENT_SPEED));


    public static final RegistryObject<Item> NUKA_COLA_BERRY = ITEMS.register("nuka_cola_berry",
            () -> new NukaCola(new Item.Properties().food(new FoodProperties.Builder().fast().nutrition(2).saturationMod(0.2F).build()).stacksTo(16), MobEffects.REGENERATION ));

    public static final RegistryObject<Item> NUKA_COLA_MIXTURE = ITEMS.register("nuka_cola_mixture",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUKA_COLA_BERRY_MIXTURE = ITEMS.register("nuka_cola_berry_mixture",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RADIATED_WATER_BUCKET = ITEMS.register("radiated_water_bucket",
            () -> new BucketItem(FluidsRegistry.SOURCE_RADIATED_WATER,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<BlockItem> IRRADIATED_OAK_SAPLING_ITEM = ITEMS.register("irradiated_oak_sapling", () -> new BlockItem(BlocksRegistry.IRRADIATED_OAK_SAPLING.get(), new Item.Properties()));
    public static final RegistryObject<Item> STIMPAK = ITEMS.register("stimpak", () -> new Stimpak(new Item.Properties()));

    public static final RegistryObject<Item> RADAWAY = ITEMS.register("radaway", () -> new Radaway(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> GEIGER_COUNTER = ITEMS.register("geiger_counter", () -> new GeigerCounter(new Item.Properties()));
    public static final RegistryObject<BlockItem> PAPERS_ON_THE_GROUND_ITEM = ITEMS.register("papers_on_the_ground", () -> new BlockItem(BlocksRegistry.PAPERS_ON_THE_GROUND.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> NUKA_COLA_MACHINE_ITEM = ITEMS.register("nuka_cola_machine", () -> new BlockItem(BlocksRegistry.NUKA_COLA_MACHINE.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> RADIOACTIVA = ITEMS.register("radioactiva", () -> new BlockItem(BlocksRegistry.RADIOACTIVA.get(), new Item.Properties()));

    //public static final RegistryObject<BlockItem> VAULT_TRAPDOOR_ITEM = ITEMS.register("vault_trapdoor", () -> new BlockItem(BlocksRegistry.VAULT_TRAPDOOR.get(), new Item.Properties().tab(TabsRegistry.TAB_DECORATION)));

    //public static final RegistryObject<BlockItem> RADIATION_REMOVER_ITEM = ITEMS.register("radiation_remover", () -> new BlockItem(BlocksRegistry.RADIATION_REMOVER.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> VAULT_BUTTONS = ITEMS.register("vault_buttons", () -> new BlockItem(BlocksRegistry.VAULT_BUTTONS.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> QUICKSAND = ITEMS.register("quicksand", () -> new BlockItem(BlocksRegistry.QUICKSAND.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> QUICKDIRT = ITEMS.register("quickdirt", () -> new BlockItem(BlocksRegistry.QUICKDIRT.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> POISONED_GRASS = ITEMS.register("poisoned_grass", () -> new BlockItem(BlocksRegistry.POISONED_GRASS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> ASH = ITEMS.register("ash", () -> new BlockItem(BlocksRegistry.ASH.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> ASH_BLOCK = ITEMS.register("ash_block", () -> new BlockItem(BlocksRegistry.ASH_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> BURNT_OAK_LOG = ITEMS.register("burnt_oak_log", () -> new BlockItem(BlocksRegistry.BURNT_OAK_LOG.get(), new Item.Properties()));

    public static final RegistryObject<Item> PIP_BOY = ITEMS.register("pip_boy", () -> new PipBoy(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> PIMP_BOY = ITEMS.register("pimp_boy", () -> new PipBoy(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> HAZMAT_MASK = ITEMS.register("hazmat_mask",
            () -> new ArmorItem(ArmorMaterials.HAZMAT, ArmorItem.Type.HELMET,
                    new Item.Properties()));
    public static final RegistryObject<Item> HAZMAT_CHESTPLATE = ITEMS.register("hazmat_chestplate",
            () -> new ArmorItem(ArmorMaterials.HAZMAT, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties()));
    public static final RegistryObject<Item> HAZMAT_LEGGINGS = ITEMS.register("hazmat_leggings",
            () -> new ArmorItem(ArmorMaterials.HAZMAT, ArmorItem.Type.LEGGINGS,
                    new Item.Properties()));
    public static final RegistryObject<Item> HAZMAT_BOOTS = ITEMS.register("hazmat_boots",
            () -> new ArmorItem(ArmorMaterials.HAZMAT, ArmorItem.Type.BOOTS,
                    new Item.Properties()));

}
