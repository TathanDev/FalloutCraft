package fr.tathan.falloutcraft.common.enchantment;

import fr.tathan.falloutcraft.common.registries.EffectsRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class RadioactiveBladeEnchantment extends Enchantment {
    public RadioactiveBladeEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }
    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        if (pTarget instanceof LivingEntity) {
            ((LivingEntity)pTarget).addEffect(new MobEffectInstance(EffectsRegistry.RADIATION.get(), 100, 2));
        }
    }
    @Override
    public int getMinCost(int pEnchantmentLevel) {
        return 10 + 20 * (pEnchantmentLevel - 1);
    }
    @Override

    public int getMaxCost(int pEnchantmentLevel) {
        return super.getMinCost(pEnchantmentLevel) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

}
