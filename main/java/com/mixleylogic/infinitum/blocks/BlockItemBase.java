package com.mixleylogic.infinitum.blocks;

import com.mixleylogic.infinitum.InfinitumMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BlockItemBase extends BlockItem {

    public BlockItemBase(Block blockIn, ItemGroup tab) {
        super(blockIn, new Item.Properties().group(tab));
    }
}
