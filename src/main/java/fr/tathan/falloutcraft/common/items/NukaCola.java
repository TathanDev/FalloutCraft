package fr.tathan.falloutcraft.common.items;

import fr.tathan.falloutcraft.common.registries.EffectsRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class NukaCola extends Item {

    MobEffect effect;

    public NukaCola(Properties pProperties, MobEffect effect) {
        super(pProperties);
        this.effect = effect;

    }


    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        pEntityLiving.addEffect(new MobEffectInstance(EffectsRegistry.RADIATION.get(), 200, 0));
        pEntityLiving.addEffect(new MobEffectInstance(effect, 200, 0, false, false, true));

        return super.finishUsingItem(pStack, pLevel, pEntityLiving);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return pStack.getItem().isEdible() ? UseAnim.DRINK: UseAnim.NONE;
    }




}
