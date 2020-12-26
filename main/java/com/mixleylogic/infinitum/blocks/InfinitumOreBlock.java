package com.mixleylogic.infinitum.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

import java.util.function.ToIntFunction;

public class InfinitumOreBlock extends OreBlock {

    public InfinitumOreBlock() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(18_000_000.0f, 3_600_000.0f)
                .sound(SoundType.STONE)
                .harvestLevel(5)
                .setRequiresTool()
                .setLightLevel(lightLevel)
                .harvestTool(ToolType.PICKAXE)
        );
    }

    private static ToIntFunction<BlockState> lightLevel = (light) -> 7;

    @Override
    public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silkTouch) {
        return 5;
    }
}
