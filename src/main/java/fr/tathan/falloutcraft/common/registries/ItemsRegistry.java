package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.items.NukaCola;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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

    public static final RegistryObject<BlockItem> VAULT_TRAPDOOR_ITEM = ITEMS.register("vault_trapdoor", () -> new BlockItem(BlocksRegistry.VAULT_TRAPDOOR.get(), new Item.Properties().tab(TabsRegistry.TAB_DECORATION)));



}
