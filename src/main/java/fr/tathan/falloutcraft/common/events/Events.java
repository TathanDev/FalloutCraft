package fr.tathan.falloutcraft.common.events;


import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.commands.RadiationItemCommand;
import fr.tathan.falloutcraft.common.fluid.ModFluidTypes;
import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import fr.tathan.falloutcraft.common.radiation.ItemRadiationProvider;
import fr.tathan.falloutcraft.common.registries.EffectsRegistry;
import fr.tathan.falloutcraft.common.registries.ItemsRegistry;
import fr.tathan.falloutcraft.common.registries.TagsRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import java.util.List;

@Mod.EventBusSubscriber(modid = FalloutCraft.MODID)
public class Events {


    public static final DamageSource DAMAGE_SOURCE_RADIOACTIVE_RAIN = new DamageSource("radioactive_rain").bypassArmor();


    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            Level level = player.level;



            if (player.isInFluidType(ModFluidTypes.RADIATED_WATER_FLUID_TYPE.get()) || player.isInWater()) {
                player.addEffect(new MobEffectInstance(EffectsRegistry.RADIATION.get(), 10));
            }

                if(event.player.getRandom().nextFloat() < 005f) {
               // if(event.player.getRandom().nextFloat() < 0.005f) {
                for (ItemStack itemStack : player.getInventory().items) {
                    ItemRadiation radiation = itemStack.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElse(null);

                   Double itemRadiation = radiation.getRadiation();

                    if (itemRadiation <= 1 && itemRadiation > 0) {
                        player.addEffect(new MobEffectInstance(EffectsRegistry.RADIATION.get(), 20));
                    }

                    if (itemRadiation >= 2 && itemRadiation < 3) {
                        player.addEffect(new MobEffectInstance(EffectsRegistry.RADIATION.get(), 30));
                    }

                    if (itemRadiation >= 3 && itemRadiation < 4) {
                        player.addEffect(new MobEffectInstance(EffectsRegistry.RADIATION.get(), 40));
                    }
                    if (itemRadiation >= 4 && itemRadiation < 5) {
                        player.addEffect(new MobEffectInstance(EffectsRegistry.RADIATION.get(), 50));
                    }

                    if (itemRadiation >= 5 && itemRadiation < 6) {
                        player.addEffect(new MobEffectInstance(EffectsRegistry.RADIATION.get(), 60));
                    }

                    if (itemRadiation >= 7 ) {
                        player.addEffect(new MobEffectInstance(EffectsRegistry.RADIATION.get(), 70));
                    }

                }
            }
        }
    }


    @SubscribeEvent
    public static void livingEntityTick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();

        radioactiveRain(livingEntity, Level.OVERWORLD);

    }

    @SubscribeEvent
    public static void itemPickupEvent(EntityItemPickupEvent event) {

       ItemStack itemStack = event.getItem().getItem();
       ItemRadiation itemRadiation = itemStack.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElseThrow(() -> new IllegalStateException("Damn! An Error ?! This is Spooky !!"));

       //When a play will pick up an item, the item will have one more radiation point
       if (itemRadiation.getRadiation() < 7) {
           itemRadiation.addRadiation(0.5);

       }
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesItemStack(AttachCapabilitiesEvent<ItemStack> event) {
            event.addCapability(new ResourceLocation(FalloutCraft.MODID, "item_radiation"), new ItemRadiationProvider());
    }
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
        event.register(ItemRadiation.class);
    }


    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new RadiationItemCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());

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
        }

        if (entity.getType().is(TagsRegistry.RADIATION_IMMUNISED)){
            return;
        }

        if (entity.level.getLevelData().isRaining() && entity.level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Math.floor(entity.getX()), (int) Math.floor(entity.getZ())) <= Math.floor(entity.getY()) + 1) {
            if (!entity.level.isClientSide) {
                entity.hurt(DAMAGE_SOURCE_RADIOACTIVE_RAIN, 1.0F);
            }
        }
    }

    public static boolean isLevel(Level level, ResourceKey<Level> loc) {
        return level.dimension() == loc;
    }

}
