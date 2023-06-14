package fr.tathan.falloutcraft.common.events;


import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.commands.RadiationItemCommand;
import fr.tathan.falloutcraft.common.config.CommonConfig;
//import fr.tathan.falloutcraft.common.entity.radroaches.RadroachEntity;
import fr.tathan.falloutcraft.common.fluid.ModFluidTypes;
import fr.tathan.falloutcraft.common.network.ModMessages;
import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import fr.tathan.falloutcraft.common.radiation.ItemRadiationProvider;
import fr.tathan.falloutcraft.common.registries.*;
import fr.tathan.falloutcraft.common.util.Methods;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import java.util.List;

import static fr.tathan.falloutcraft.common.util.Methods.radioactiveRain;

@Mod.EventBusSubscriber(modid = FalloutCraft.MODID)
public class Events {


    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {

        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            Level level = player.level;
            GameRules gamerules = level.getLevelData().getGameRules();

            if (player.isInFluidType(ModFluidTypes.RADIATED_WATER_FLUID_TYPE.get()) || player.isInWater()) {
                if(!Methods.hasHazmatSuit(player)) {
                player.addEffect(new MobEffectInstance(EffectsRegistry.RADIATION.get(), 10));
                }
            }

            if (!player.isCreative() && CommonConfig.itemRadiationDamage.get()) {

                if(event.player.getRandom().nextFloat() < 0.005f) {

                for (ItemStack itemStack : player.getInventory().items) {
                    ItemRadiation radiation = itemStack.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElse(null);
                    radiation.loadNBTData(itemStack.getOrCreateTagElement("radiation"));
                    Double itemRadiation = radiation.getRadiation();


                    if (itemRadiation >= 2 && itemRadiation < 3) {
                        player.addEffect(new MobEffectInstance(EffectsRegistry.RADIATION.get(), 30));
                        player.hurt(DamageTypeRegistry.of(level, DamageTypeRegistry.RADIOACTIVE_RAIN), 1);
                    } else if (itemRadiation >= 3 && itemRadiation < 4) {
                        player.addEffect(new MobEffectInstance(EffectsRegistry.RADIATION.get(), 40, 1));
                        player.hurt(DamageTypeRegistry.of(level, DamageTypeRegistry.RADIOACTIVE_RAIN), 1.5F);
                    } else if (itemRadiation >= 4 && itemRadiation < 5) {
                        player.addEffect(new MobEffectInstance(EffectsRegistry.RADIATION.get(), 50, 1));
                        player.hurt(DamageTypeRegistry.of(level, DamageTypeRegistry.RADIOACTIVE_RAIN), 2);
                    } else if (itemRadiation >= 5 && itemRadiation < 6) {
                        player.addEffect(new MobEffectInstance(EffectsRegistry.RADIATION.get(), 60, 2));
                        player.hurt(DamageTypeRegistry.of(level, DamageTypeRegistry.RADIOACTIVE_RAIN), 2.5F);
                    } else if (itemRadiation >= 7 || itemStack.is(TagsRegistry.VERY_RADIOACTIVE )) {
                        player.addEffect(new MobEffectInstance(EffectsRegistry.RADIATION.get(), 70, 2));
                        player.hurt(DamageTypeRegistry.of(level, DamageTypeRegistry.RADIOACTIVE_RAIN), 3);

                    }
                  }
                }
            }

        }
    }

    @SubscribeEvent
    public static void livingEntityTick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();


        event.getPhase();


        radioactiveRain(livingEntity, Level.OVERWORLD);

        if (livingEntity.isInFluidType(ModFluidTypes.RADIATED_WATER_FLUID_TYPE.get())) {
            if(livingEntity instanceof Player player && Methods.hasHazmatSuit(player)) {
                return;
            }
            livingEntity.addEffect(new MobEffectInstance(EffectsRegistry.RADIATION.get(), 10, 1));
        }

    }

    @SubscribeEvent
    public static void itemPickupEvent(EntityItemPickupEvent event) {

        ItemStack itemStack = event.getItem().getItem();
        ItemRadiation itemRadiation = itemStack.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElseThrow(() -> new IllegalStateException("Damn! An Error ?! This is Spooky !!"));

        //When a play will pick up an item, the item will have one more radiation point
        if (itemRadiation.getRadiation() < 7) {

            itemRadiation.addRadiation( 0.5);
            itemRadiation.saveNBTData(itemStack.getOrCreateTagElement("radiation"));


        }

        FalloutCraft.LOGGER.info("Radiation of the item : " + itemRadiation.getRadiation());
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath() && !event.getEntity().getInventory().contains(ItemsRegistry.PIMP_BOY.get().getDefaultInstance())) {
            event.getEntity().addItem(ItemsRegistry.PIP_BOY.get().getDefaultInstance());
        }
    }

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event)
    {

        if(event.getType() == VillagerRegistry.SURVIVALIST.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            ItemStack nuka_cola_classic = new ItemStack(ItemsRegistry.NUKA_COLA_CLASSIC.get(), 2);
            ItemStack nuka_cola_berry = new ItemStack(ItemsRegistry.NUKA_COLA_BERRY.get(), 2);
            ItemStack pip_boy = new ItemStack(ItemsRegistry.PIP_BOY.get(), 1);
            ItemStack pimp_boy = new ItemStack(ItemsRegistry.PIMP_BOY.get(), 1);

            ItemStack geiger_counter = new ItemStack(ItemsRegistry.GEIGER_COUNTER.get(), 1);
            ItemStack radaway = new ItemStack(ItemsRegistry.RADAWAY.get(), 3);
            ItemStack stimpak = new ItemStack(ItemsRegistry.STIMPAK.get(), 5);



            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    pimp_boy,32,12,0.09F));

            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    nuka_cola_classic,32,12,0.09F));
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    nuka_cola_berry,32,12,0.09F));
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    stimpak,25,12,0.09F));


            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    pip_boy,1,32,0.05f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    geiger_counter,200,1,0.05f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    radaway,200,32,0.05f));


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


/**
    @Mod.EventBusSubscriber(modid = FalloutCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(EntityTypes.RADROACH.get(), RadroachEntity.setAttributes());
        }
    }
    */
}
