package fr.tathan.falloutcraft.common.registries;
/**
import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.entity.radroaches.RadroachEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FalloutCraft.MODID);

    public static final RegistryObject<EntityType<RadroachEntity>> RADROACH =
            ENTITY_TYPES.register("radroach",
                    () -> EntityType.Builder.of(RadroachEntity::new, MobCategory.MONSTER)
                            .sized(0.4f, 0.6f)
                            .build(new ResourceLocation(FalloutCraft.MODID, "radroach").toString()));




}
*/