package com.mixleylogic.infinitum.blocks;

import com.mixleylogic.infinitum.InfinitumMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class BlockItemBase extends BlockItem {

    public BlockItemBase(Block blockIn) {
        super(blockIn, new Item.Properties().group(InfinitumMod.INFINITUM_TAB));
    }
}
