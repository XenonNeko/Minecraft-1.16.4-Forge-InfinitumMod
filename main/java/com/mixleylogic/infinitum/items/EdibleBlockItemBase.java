package com.mixleylogic.infinitum.items;

import com.mixleylogic.infinitum.InfinitumMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class EdibleBlockItemBase extends BlockItem {
    public EdibleBlockItemBase(Block blockIn, int hunger, float saturation, FoodEffect[] effects) {
        super(blockIn, new Item.Properties()
                .group(InfinitumMod.INFINITUM_TAB)
                .food(BuildFood(hunger, saturation, effects))
        );
    }

    public static Food BuildFood(int hunger, float saturation, FoodEffect[] effects) {
        Food.Builder builder = new Food.Builder();
        for (FoodEffect effect: effects) {
            builder.effect(effect::getEffectInstance, effect.getProbability());
        }
        return builder.build();
    }
}

