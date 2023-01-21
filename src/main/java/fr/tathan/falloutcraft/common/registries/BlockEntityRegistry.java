package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.blocks.entity.NukaColaMachineBlockEntity;
import fr.tathan.falloutcraft.common.blocks.entity.RadiationRemoverBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FalloutCraft.MODID);



    public static final RegistryObject<BlockEntityType<NukaColaMachineBlockEntity>> NUKA_COLA_MACHINE =
            BLOCK_ENTITIES.register("nuka_cola_machine", () ->
                    BlockEntityType.Builder.of(NukaColaMachineBlockEntity::new,
                            BlocksRegistry.NUKA_COLA_MACHINE.get()).build(null));


    public static final RegistryObject<BlockEntityType<RadiationRemoverBlockEntity>> RADIATION_REMOVER =
            BLOCK_ENTITIES.register("radiation_remover", () ->
                    BlockEntityType.Builder.of(RadiationRemoverBlockEntity::new,
                            BlocksRegistry.RADIATION_REMOVER.get()).build(null));


}
