package com.mixleylogic.infinitum;

import com.mixleylogic.infinitum.tileentity.QuarryTileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class NBTHelper {

    public static CompoundNBT toNBT(Object o) {
        if (o instanceof ItemStack) {
            return writeItemStack((ItemStack)o);
        }
        if (o instanceof QuarryTileEntity) {
            return writeQuarry((QuarryTileEntity)o);
        }

        return null;
    }

    private static CompoundNBT writeQuarry(QuarryTileEntity o) {
        CompoundNBT compound = new CompoundNBT();
        compound.putInt("x", o.x);
        compound.putInt("y", o.y);
        compound.putInt("z", o.z);
        compound.putInt("block", o.block);
        compound.putInt("tick", o.tick);
        compound.putInt("blocksMined", o.blocksMined);
        return compound;
    }

    private static CompoundNBT writeItemStack(ItemStack i) {
        CompoundNBT compound = new CompoundNBT();
        compound.putInt("count", i.getCount());
        compound.putString("item", i.getItem().getRegistryName().toString());
        compound.putByte("type", (byte)0);
        return compound;
    }

    private static ItemStack readItemStack(CompoundNBT compound) {
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(compound.getString("item")));
        int count = compound.getInt("count");
        return new ItemStack(item, count);
    }
}
