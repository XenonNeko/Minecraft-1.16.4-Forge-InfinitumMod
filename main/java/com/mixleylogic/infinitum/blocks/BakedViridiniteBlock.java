package com.mixleylogic.infinitum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BakedViridiniteBlock extends Block {

    public BakedViridiniteBlock() {
        super(Properties.create(Material.ROCK)
                .hardnessAndResistance(4.2f, 1.25f)
                .sound(SoundType.STONE)
                .harvestLevel(1)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
        );
    }
}
