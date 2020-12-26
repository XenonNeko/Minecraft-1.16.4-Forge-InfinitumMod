package com.mixleylogic.infinitum.events;

import net.minecraft.entity.player.PlayerEntity;

public interface PlayerDeathListener {
    void onPlayerDeath(PlayerEntity playerEntity);
}
