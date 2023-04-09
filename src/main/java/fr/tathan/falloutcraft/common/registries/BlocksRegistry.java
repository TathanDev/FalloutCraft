package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.blocks.*;
import fr.tathan.falloutcraft.common.blocks.entity.RadiationRemoverBlockEntity;
import fr.tathan.falloutcraft.common.worldgen.features.tree.IrradiatedOakGrower;
import fr.tathan.falloutcraft.common.worldgen.features.tree.PoisonedJungleTreeGrower;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlocksRegistry {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FalloutCraft.MODID);


    public static final RegistryObject<LiquidBlock>  RADIATED_WATER_BLOCK= BLOCKS.register("radiated_water_block",
            () -> new RadiatedWaterBlock(FluidsRegistry.SOURCE_RADIATED_WATER, BlockBehaviour.Properties.copy(Blocks.WATER).lightLevel((p_50872_) -> {
                return 12;
            })));


    public static final RegistryObject<Block> IRRADIATED_OAK_SAPLING = BLOCKS.register("irradiated_oak_sapling",
            () -> new SaplingBlock(new IrradiatedOakGrower(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> POISONED_JUNGLE_SAPLING = BLOCKS.register("poisoned_jungle_sapling",
            () -> new SaplingBlock(new PoisonedJungleTreeGrower(),
                    BlockBehaviour.Properties.copy(Blocks.JUNGLE_SAPLING)));


    public static final RegistryObject<Block> NUKA_COLA_MACHINE = BLOCKS.register("nuka_cola_machine",
            () -> new NukaColaMachine(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));


    public static final RegistryObject<Block> RADIATION_REMOVER = BLOCKS.register("radiation_remover",
            () -> new RadiationRemover(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    //May Fix the Cave Block issue
    public static final RegistryObject<Block> RADIOACTIVA = BLOCKS.register("radioactiva",
            () -> new RadioactivaFlower(MobEffects.GLOWING,  BlockBehaviour.Properties.copy(Blocks.DANDELION)));



    public static final RegistryObject<Block> POTTED_RADIOACTIVA = BLOCKS.register("potted_radioactiva",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), BlocksRegistry.RADIOACTIVA,
                    BlockBehaviour.Properties.copy(Blocks.DANDELION)));

    public static final RegistryObject<Block> QUICKSAND = BLOCKS.register("quicksand",
            () -> new QuickBlock(BlockBehaviour.Properties.of(Material.SAND).noCollission().requiresCorrectToolForDrops().strength(0.5F)));

    public static final RegistryObject<Block> QUICKDIRT = BLOCKS.register("quickdirt",
            () -> new QuickBlock(BlockBehaviour.Properties.of(Material.SAND).noCollission().requiresCorrectToolForDrops().strength(0.5F)));

    public static final RegistryObject<Block> POISONED_GRASS = BLOCKS.register("poisoned_grass",
            () -> new PoisonedVegetation(MobEffects.GLOWING, 40, BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.GRASS).noCollission()));



    /** Decorations Blocks */
    public static final RegistryObject<Block>  PAPERS_ON_THE_GROUND = BLOCKS.register("papers_on_the_ground",
            () -> new PapersOnTheGround(BlockBehaviour.Properties.copy(Blocks.CYAN_CARPET).instabreak().noOcclusion().noCollission()));


    public static final RegistryObject<Block>  VAULT_BUTTONS = BLOCKS.register("vault_buttons",
            () -> new VaultButtons(true, BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).instabreak()));





}
