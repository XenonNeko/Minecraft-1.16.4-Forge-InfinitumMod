package com.mixleylogic.infinitum.items;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class FoodEffect {
    public FoodEffect(Effect potionIn,
                      int durationIn,
                      int amplifierIn,
                      float probabilityIn) {
        effectInstance = new EffectInstance(potionIn, durationIn, amplifierIn);
        probability = probabilityIn;
    }

    private final EffectInstance effectInstance;

    public EffectInstance getEffectInstance() {
        return effectInstance;
    }

    private final float probability;

    public float getProbability() {
        return probability;
    }
}
