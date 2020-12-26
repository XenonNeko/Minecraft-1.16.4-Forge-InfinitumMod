package com.mixleylogic.infinitum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BakedPotatoBlock extends Block {
    public BakedPotatoBlock() {
        super(Properties.create(Material.GOURD)
                .hardnessAndResistance(1.0f, 1.0f)
                .sound(SoundType.FUNGUS)
                .harvestTool(ToolType.AXE)
        );
    }
}
