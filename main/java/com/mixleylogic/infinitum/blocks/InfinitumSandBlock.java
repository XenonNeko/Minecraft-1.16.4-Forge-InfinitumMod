package com.mixleylogic.infinitum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class InfinitumSandBlock extends Block {

    public InfinitumSandBlock() {
        super(Block.Properties.create(Material.CLAY)
                .hardnessAndResistance(0.6f, 600.0f)
                .sound(SoundType.GROUND)
                .harvestLevel(2)
                .setRequiresTool()
                .harvestTool(ToolType.SHOVEL)
        );
    }
}
