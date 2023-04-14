package fr.tathan.falloutcraft.common.registries;
import com.google.common.collect.ImmutableSet;
import fr.tathan.falloutcraft.FalloutCraft;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.lang.reflect.InvocationTargetException;

public class VillagerRegistry {

    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, FalloutCraft.MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, FalloutCraft.MODID);

    public static final RegistryObject<PoiType> NUKA_COLA_MACHINE_POI = POI_TYPES.register("nuka_cola_machine_poi",
            () -> new PoiType(ImmutableSet.copyOf(BlocksRegistry.NUKA_COLA_MACHINE.get().getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final RegistryObject<VillagerProfession> SURVIVALIST = VILLAGER_PROFESSIONS.register("survivalist",
            () -> new VillagerProfession("survivalist", x -> x.get() == NUKA_COLA_MACHINE_POI.get(),
                    x -> x.get() == NUKA_COLA_MACHINE_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_ARMORER));
    //TODO FIX VILLAGER ZOMBIE

    public static void registerPOIs() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates", PoiType.class).invoke(null, NUKA_COLA_MACHINE_POI.get());
        } catch (InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }

}
