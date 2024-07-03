package fr.tathan.falloutcraft.common.util;

import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import fr.tathan.falloutcraft.common.radiation.ItemRadiationProvider;
import fr.tathan.falloutcraft.common.registries.DamageTypeRegistry;
import fr.tathan.falloutcraft.common.registries.ItemsRegistry;
import fr.tathan.falloutcraft.common.registries.TagsRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;

import static fr.tathan.falloutcraft.common.config.CommonConfig.radiationRainDamage;


public class Methods {

    //public static final DamageSource DAMAGE_SOURCE_RADIOACTIVE_RAIN = new DamageSource("radioactive_rain").bypassArmor();


    public static void addRadiation(ItemStack stack, float count) {
        ItemRadiation itemRadiation = stack.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElseThrow(() -> new IllegalStateException("Damn! An Error ?! This is Spooky !!"));
        itemRadiation.addRadiation(count);
    }

    public static void subRadiation(ItemStack stack, float count) {
        ItemRadiation itemRadiation = stack.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElseThrow(() -> new IllegalStateException("Damn! An Error ?! This is Spooky !!"));
        itemRadiation.subRadiation(count);
    }

    public static boolean hasHazmatSuit(Player player) {

        boolean legging = player.getItemBySlot(EquipmentSlot.LEGS).getItem().equals(ItemsRegistry.HAZMAT_LEGGINGS.get());
        boolean chestplate = player.getItemBySlot(EquipmentSlot.CHEST).getItem().equals(ItemsRegistry.HAZMAT_CHESTPLATE.get());
        boolean boots = player.getItemBySlot(EquipmentSlot.FEET).getItem().equals(ItemsRegistry.HAZMAT_BOOTS.get());
        boolean mask = player.getItemBySlot(EquipmentSlot.HEAD).getItem().equals(ItemsRegistry.HAZMAT_MASK.get());

        return legging && chestplate && boots && mask;
    }

    public static void radioactiveRain(LivingEntity entity, ResourceKey<Level> overworld) {
        if (!isLevel(entity.level(), overworld)) {

            return;
        }

        if(!radiationRainDamage.get()) {
            return;
        }

        if (entity instanceof Player) {
            Player player = (Player) entity;

            if (player.isSpectator() || player.getAbilities().instabuild) {
                return;
            }

            if(Methods.hasHazmatSuit(player)) {
                return;
            }
        }

        if (entity.getType().is(TagsRegistry.RADIATION_IMMUNISED)){
            return;
        }

        if (entity.level().getLevelData().isRaining() && entity.level().getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Math.floor(entity.getX()), (int) Math.floor(entity.getZ())) <= Math.floor(entity.getY()) + 1) {
            if (!entity.level().isClientSide) {
                entity.hurt(DamageTypeRegistry.of(entity.level(), DamageTypeRegistry.RADIOACTIVE_RAIN), 0.5f);
            }
        }
    }

    public static boolean isLevel(Level level, ResourceKey<Level> loc) {
        return level.dimension() == loc;
    }

    public static boolean canStacksCanStack(ItemStack stack1, ItemStack stack2) {

        if (stack1.getItem().equals(stack2.getItem())) {
            if (stack1.getCount() + stack2.getCount() <= stack1.getMaxStackSize()) {
                return true;
            }
        }
        return false;
    }

    public static boolean setRadiationFromStack(ItemStack stack1, ItemStack stack2) {
        ItemRadiation radiation1 = stack1.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElse(null);
        ItemRadiation radiation2 = stack2.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElse(null);


        if (radiation2.getRadiation() == radiation1.getRadiation()) {
            return false;
        }

        if (radiation1.getRadiation() > radiation2.getRadiation()) {

            stack1.setCount(stack1.getCount() + stack2.getCount());
            stack2.setCount(0);
            return false;

        }

        return false;

    }
}
