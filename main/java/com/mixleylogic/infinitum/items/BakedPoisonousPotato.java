package com.mixleylogic.infinitum.items;

import com.mixleylogic.infinitum.InfinitumMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class BakedPoisonousPotato extends Item {
    public BakedPoisonousPotato() {
        super(new Item.Properties()
                .group(InfinitumMod.INFINITUM_TAB)
                .food(new Food.Builder()
                        .hunger(4)
                        .saturation(1.2f)
                        .effect(() -> new EffectInstance(Effects.INSTANT_DAMAGE, 20, 50), 0.05f)
                        .effect(() -> new EffectInstance(Effects.POISON, 20*15, 5), 1.0f)
                        .effect(() -> new EffectInstance(Effects.SLOWNESS, 20*15, 4), 1.0f)
                        .effect(() -> new EffectInstance(Effects.HUNGER, 20*15, 3), 1.0f)
                        .effect(() -> new EffectInstance(Effects.WEAKNESS, 20*15, 1), 1.0f)
                        .effect(() -> new EffectInstance(Effects.MINING_FATIGUE, 20*15, 3), 1.0f)
                        .effect(() -> new EffectInstance(Effects.BLINDNESS, 20*15, 1), 1.0f)
                        .setAlwaysEdible()
                        .build())
        );
    }
}
