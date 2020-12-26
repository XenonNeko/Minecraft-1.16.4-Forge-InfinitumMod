package com.mixleylogic.infinitum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class AncientInfinitumBlock extends Block {

    public AncientInfinitumBlock() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(100.0f, 2400.0f)
                .sound(SoundType.STONE)
                .harvestLevel(4)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
        );
    }
}
