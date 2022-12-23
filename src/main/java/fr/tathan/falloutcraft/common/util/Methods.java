package fr.tathan.falloutcraft.common.util;

import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import fr.tathan.falloutcraft.common.radiation.ItemRadiationProvider;
import fr.tathan.falloutcraft.common.registries.ItemsRegistry;
import fr.tathan.falloutcraft.common.registries.TagsRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;



public class Methods {

    public static final DamageSource DAMAGE_SOURCE_RADIOACTIVE_RAIN = new DamageSource("radioactive_rain").bypassArmor();


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
        if (!isLevel(entity.level, overworld)) {

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

        if (entity.level.getLevelData().isRaining() && entity.level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Math.floor(entity.getX()), (int) Math.floor(entity.getZ())) <= Math.floor(entity.getY()) + 1) {
            if (!entity.level.isClientSide) {
                entity.hurt(DAMAGE_SOURCE_RADIOACTIVE_RAIN, 0.5F);
            }
        }
    }

    public static boolean isLevel(Level level, ResourceKey<Level> loc) {
        return level.dimension() == loc;
    }




}
