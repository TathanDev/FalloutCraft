package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.enchantment.RadioactiveBladeEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EnchantmentRegistry {

    public static final DeferredRegister<Enchantment> ENCHANTMENT =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, FalloutCraft.MODID);


    public static final RegistryObject<Enchantment> RADIOACTIVE_BLADE = ENCHANTMENT.register("radioactive_blade",
            () -> new RadioactiveBladeEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));



}
