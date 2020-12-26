package com.mixleylogic.infinitum.conjuredstructures;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ConjurableStructure {
    public ConjurableStructure(PlayerEntity playerIn, World worldIn) {
        summoner = playerIn;
        world = worldIn;
    }

    protected PlayerEntity summoner;
    protected World world;
    protected BlockPos returnPosition; // In world coordinates
    protected BlockPos entryPosition; // Relative to the structure
    protected static BlockPos position;
    protected static int size;
    protected static int height;

    public PlayerEntity getSummoner() {
        return summoner;
    }
    public BlockPos getReturnPosition() {
        return returnPosition;
    }
    public BlockPos getPosition() {
        return position;
    }
    public int getSize() {
        return size;
    }
    public int getHeight() {
        return height;
    }

    public abstract void conjure();

    public void dissolve() {
        for (int y = 0; y < height; y++) {
            for (int z = 0; z < size; z++) {
                for (int x = 0; x < size; x++) {
                    BlockPos outPos = position.add(x, y, z);
                    world.removeBlock(outPos, false);
                    world.removeBlock(position.add(-1, -1, -1), false);
                }
            }
        }
    }
}
