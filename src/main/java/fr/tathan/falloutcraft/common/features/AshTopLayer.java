package fr.tathan.falloutcraft.common.features;

import com.mojang.serialization.Codec;
import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class AshTopLayer extends Feature<NoneFeatureConfiguration> {
    public AshTopLayer(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        BlockPos blockpos = pContext.origin();
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

        for(int i = 0; i < 16; ++i) {
            for(int j = 0; j < 16; ++j) {
                int k = blockpos.getX() + i;
                int l = blockpos.getZ() + j;
                int i1 = worldgenlevel.getHeight(Heightmap.Types.MOTION_BLOCKING, k, l);
                blockpos$mutableblockpos.set(k, i1, l);
                blockpos$mutableblockpos1.set(blockpos$mutableblockpos).move(Direction.DOWN, 1);
                Biome biome = worldgenlevel.getBiome(blockpos$mutableblockpos).value();

                BlockState blockstate = worldgenlevel.getBlockState(blockpos$mutableblockpos1);
                if (blockstate.is(BlocksRegistry.ASH_BLOCK.get())) {
                    Random random = new Random();
                    int layer = random.nextInt(2, 4);
                    worldgenlevel.setBlock(blockpos$mutableblockpos, BlocksRegistry.ASH.get().defaultBlockState(), layer);
                }
            }
        }

        return true;
    }

}

