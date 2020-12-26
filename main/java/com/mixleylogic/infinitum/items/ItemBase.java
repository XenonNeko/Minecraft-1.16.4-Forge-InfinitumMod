package com.mixleylogic.infinitum.items;

import com.mixleylogic.infinitum.InfinitumMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBase extends Item {

    public ItemBase(ItemGroup tab) {
        super(new Item.Properties().group(tab));
    }
}
