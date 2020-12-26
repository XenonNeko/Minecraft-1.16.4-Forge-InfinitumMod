package com.mixleylogic.infinitum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class MoltenLavaSponge extends Block {
    public MoltenLavaSponge() {
        super(Block.Properties.create(Material.SPONGE)
                .hardnessAndResistance(0.6f, 0.6f)
                .sound(SoundType.STONE)
                .harvestTool(ToolType.HOE)
        );
    }
}
