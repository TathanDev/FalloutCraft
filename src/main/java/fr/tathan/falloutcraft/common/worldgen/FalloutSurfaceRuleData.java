package fr.tathan.falloutcraft.common.worldgen;

import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;


public class FalloutSurfaceRuleData {

    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);

    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);

    private static final SurfaceRules.RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource ASH_BLOCK = makeStateRule(BlocksRegistry.ASH_BLOCK.get());


    public static SurfaceRules.RuleSource makeRules()
    {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.ConditionSource isUnderWater = SurfaceRules.waterBlockCheck(1, 0);

        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, COARSE_DIRT), SurfaceRuleData.overworld());
        SurfaceRules.RuleSource ashSurface = SurfaceRules.sequence(ASH_BLOCK);

        return SurfaceRules.sequence(

                SurfaceRules.ifTrue(SurfaceRules.isBiome(FalloutBiomes.RADIOACTIVE_PLAINS), grassSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(FalloutBiomes.RADIOACTIVE_FOREST), grassSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(FalloutBiomes.POISONED_JUNGLE), SurfaceRuleData.overworld()),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(FalloutBiomes.ASH_LAND), ashSurface),

                // Default to a grass and dirt surface
                SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, SurfaceRuleData.overworld())
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block)
    {
        return SurfaceRules.state(block.defaultBlockState());
    }

}
