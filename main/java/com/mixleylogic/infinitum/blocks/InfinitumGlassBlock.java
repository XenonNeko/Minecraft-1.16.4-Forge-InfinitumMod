package com.mixleylogic.infinitum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

import java.util.function.ToIntFunction;

public class InfinitumGlassBlock extends Block {

    public InfinitumGlassBlock() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(100.0f, 2400.0f)
                .sound(SoundType.STONE)
                .harvestLevel(3)
                .setRequiresTool()
                .setLightLevel(lightLevel)
                .harvestTool(ToolType.PICKAXE)
        );
    }

    private static ToIntFunction<BlockState> lightLevel = (light) -> 7;
}
