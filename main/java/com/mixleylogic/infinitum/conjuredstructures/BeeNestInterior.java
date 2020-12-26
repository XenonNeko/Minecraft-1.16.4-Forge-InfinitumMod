package com.mixleylogic.infinitum.conjuredstructures;

import com.mixleylogic.infinitum.init.ModBlocks;
import com.mixleylogic.infinitum.tileentity.StructureControllerTileEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class BeeNestInterior extends ConjurableStructure {
    public BeeNestInterior(PlayerEntity playerIn, World worldIn, BlockPos pos) {
        super(playerIn, worldIn);
        returnPosition = pos;
        size = 16;
        height = 16;
        position = new BlockPos(
                pos.getX() - (size / 2),
                255 - height,
                pos.getZ() - (size / 2)
        );
        entryPosition = position.add(size / 2, 1, size / 2);
    }

    @Override
    public void conjure() {
        world.setBlockState(position.add(-1, -1, -1),
                ModBlocks.STRUCTURE_CONTROLLER_BLOCK.get().getDefaultState());
        TileEntity tileEntity = world.getTileEntity(position.add(-1, -1, -1));
        StructureControllerTileEntity structureController =
                (StructureControllerTileEntity) tileEntity;
        if (structureController != null) {
            structureController.setStructure(this);

            for (int y = 0; y < height; y++) {
                for (int z = 0; z < size; z++) {
                    for (int x = 0; x < size; x++) {
                        BlockPos blockPos = position.add(x, y, z);
                        Vector3d offsetPosition = new Vector3d(x + 0.5, y + 0.5, z + 0.5);
                        double ringDistance = Math.max(
                                Math.abs(offsetPosition.x - (size / 2)),
                                Math.abs(offsetPosition.z - (size / 2)));
                        ringDistance -= 0.5;
                        int ring = (int) ringDistance;
                        switch (y) {
                            case 0: // Bottom
                                if (ring == 0 || ring == 2 || ring == 3 || ring == 5 || ring == 6) {
                                    world.setBlockState(blockPos, Blocks.WHITE_TERRACOTTA.getDefaultState());
                                } else {
                                    world.setBlockState(blockPos, Blocks.BROWN_TERRACOTTA.getDefaultState());
                                }
                                break;
                            case 15: // Top
                                if (ring == 0 || ring == 2 || ring == 3 || ring == 5 || ring == 6) {
                                    world.setBlockState(blockPos, Blocks.HONEYCOMB_BLOCK.getDefaultState());
                                } else {
                                    world.setBlockState(blockPos, Blocks.BROWN_TERRACOTTA.getDefaultState());
                                }
                                break;
                            case 4: // Rings
                            case 8:
                            case 12:
                                if (ring == 7) {
                                    world.setBlockState(blockPos, Blocks.BROWN_TERRACOTTA.getDefaultState());
                                }
                                break;
                            default: // Bands
                                if (ring == 7) {
                                    world.setBlockState(blockPos, Blocks.HONEYCOMB_BLOCK.getDefaultState());
                                }
                                break;
                        }
                    }
                }
            }

            if (!world.isRemote) summoner.setPosition(entryPosition.getX(), entryPosition.getY(), entryPosition.getZ());
        }
    }
}
