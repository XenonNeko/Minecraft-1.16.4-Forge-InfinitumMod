package com.mixleylogic.infinitum.blocks;

import com.mixleylogic.infinitum.init.ModTileEntityTypes;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.function.ToIntFunction;

public class StructureControllerBlock extends Block implements ITileEntityProvider {

    public StructureControllerBlock() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(18_000_000.0f, 3_600_000.0f)
                .sound(SoundType.STONE)
                .harvestLevel(3)
                .setRequiresTool()
                .setLightLevel(lightLevel)
                .harvestTool(ToolType.PICKAXE)
        );
    }

    private static ToIntFunction<BlockState> lightLevel = (light) -> 7;

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn)
    {
        return ModTileEntityTypes.STRUCTURE_CONTROLLER.get().create();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (placer instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity)placer;
            /*
            if (!player.isSpectator() && !player.isCreative()) {
                player.setGameType(GameType.ADVENTURE);
                //player.inventory.dropAllItems();

                World world = player.getEntityWorld();
                BlockPos chestPosition = pos.add(0, 2, 0);
                //world.setBlockState(chestPosition,
                //        Blocks.CHEST.getDefaultState());
                TileEntity tileEntity1 = world.getTileEntity(chestPosition);
                TileEntity tileEntity2 = world.getTileEntity(chestPosition.add(-1, 0, 0));
                if (tileEntity1 != null) {
                    if (tileEntity2 != null) {
                        if (tileEntity1 instanceof ChestTileEntity) {
                            if (tileEntity2 instanceof ChestTileEntity) {
                                ChestTileEntity chest1= (ChestTileEntity)tileEntity1;
                                ChestTileEntity chest2 = (ChestTileEntity)tileEntity2;
                                int chestSlot = 0;
                                for (int i = 0; i < player.inventory.getSizeInventory(); i++)
                                {
                                    ItemStack item;
                                    item = player.inventory.removeStackFromSlot(i);
                                    if (!item.isEmpty())
                                    {
                                        if (chestSlot < 27) {
                                            chest1.setInventorySlotContents(chestSlot, item);
                                        }
                                        else
                                        {
                                            chest2.setInventorySlotContents(chestSlot - 27, item);
                                        }
                                        chestSlot++;
                                    }
                                }
                                //chestTileEntity.setInventorySlotContents();
                            } else {
                                Debug.chat("Tile entity (2) isn't a chest tile entity!");
                            }
                        } else {
                            Debug.chat("Tile entity (1) isn't a chest tile entity!");
                        }
                    } else {
                        Debug.chat("Didn't find a tile entity (2)!");
                    }
                } else {
                    Debug.chat("Didn't find a tile entity (1)!");
                }
            }
             */
        }
    }
}
