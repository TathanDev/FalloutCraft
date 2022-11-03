package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.blocks.PapersOnTheGround;
import fr.tathan.falloutcraft.common.blocks.RadiatedWaterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
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



    /** Decorations Blocks */
    public static final RegistryObject<Block>  PAPERS_ON_THE_GROUND = BLOCKS.register("papers_on_the_ground",
            () -> new PapersOnTheGround(BlockBehaviour.Properties.copy(Blocks.REDSTONE_WIRE).noCollission().instabreak()));





}
