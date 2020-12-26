package com.mixleylogic.infinitum;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class Debug {
    private static final Minecraft minecraft = Minecraft.getInstance();
    private static final World world = minecraft.world;
    public static void log(String message) {
        InfinitumMod.LOGGER.info(message);
    }
    public static void logWarn(String message) {
        InfinitumMod.LOGGER.warn("{Server} " + message);
    }
    public static void logError(String message) {
        InfinitumMod.LOGGER.warn("{Server} " + message);
    }
    public static void chat(String message) {
        if (minecraft.player == null) {
            InfinitumMod.LOGGER.error("Unable to communicate with player");
        } else {
            minecraft.player.sendChatMessage(message);
        }
    }
}
