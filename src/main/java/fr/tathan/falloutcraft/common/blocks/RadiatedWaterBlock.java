package fr.tathan.falloutcraft.common.blocks;

import fr.tathan.falloutcraft.common.registries.FluidsRegistry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class RadiatedWaterBlock extends LiquidBlock {

    public RadiatedWaterBlock() {
        super(FluidsRegistry.SOURCE_RADIATED_WATER, BlockBehaviour.Properties.copy(Blocks.WATER));
    }

    
}

