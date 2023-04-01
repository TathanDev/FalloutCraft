package fr.tathan.falloutcraft.common.fluid;

import fr.tathan.falloutcraft.FalloutCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;


public class ModFluidTypes {

    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation RADIATED_WATER_OVERLAY_RL = new ResourceLocation(FalloutCraft.MODID, "misc/in_radiated_water_fluid");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, FalloutCraft.MODID);

    public static final RegistryObject<FluidType> RADIATED_WATER_FLUID_TYPE = register("radiated_water_fluid",
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).lightLevel(12).supportsBoating(true));

    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, RADIATED_WATER_OVERLAY_RL,
                0x0eff00, new Vector3f(100f / 255f, 221f / 255f, 23f / 255f), properties));
                        //Fix the color
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }

}
