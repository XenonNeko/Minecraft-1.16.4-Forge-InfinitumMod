package com.mixleylogic.infinitum;

import com.mixleylogic.infinitum.entities.HookshotEntity;
import com.mixleylogic.infinitum.events.HookshotEventListener;
import net.minecraft.util.SoundEvents;

public class SoundEventController implements HookshotEventListener {

    @Override
    public void onHookshotLatch(HookshotEntity hookshotEntity) {
        Debug.log("onHookshotLatch()");

        hookshotEntity.playSound(
                SoundEvents.BLOCK_CHAIN_PLACE,
                1.0f,
                1.0f);
        hookshotEntity.playSound(
                SoundEvents.ITEM_TRIDENT_HIT_GROUND,
                1.0f,
                1.0f);
    }

    @Override
    public void onHookshotReturn(HookshotEntity hookshotEntity) {
        Debug.log("onHookshotReturn()");

        hookshotEntity.playSound(
                SoundEvents.ITEM_ARMOR_EQUIP_IRON,
                1.0f,
                1.0f);
    }
}
