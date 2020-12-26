package com.mixleylogic.infinitum.events;

import com.mixleylogic.infinitum.entities.HookshotEntity;

public interface HookshotEventListener {
    void onHookshotLatch(HookshotEntity hookshotEntity);
    void onHookshotReturn(HookshotEntity hookshotEntity);
}
