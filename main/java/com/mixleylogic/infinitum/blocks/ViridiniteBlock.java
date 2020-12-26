package com.mixleylogic.infinitum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class ViridiniteBlock extends Block {

    public ViridiniteBlock() {
        super(Properties.create(Material.CLAY)
                .hardnessAndResistance(0.6f, 0.6f)
                .sound(SoundType.GROUND)
                .harvestLevel(1)
                .setRequiresTool()
                .harvestTool(ToolType.SHOVEL)
        );
    }
}
