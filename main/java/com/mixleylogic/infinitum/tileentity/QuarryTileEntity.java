package com.mixleylogic.infinitum.tileentity;

import com.mixleylogic.infinitum.NBTHelper;
import com.mixleylogic.infinitum.blocks.QuarryBlock;
import com.mixleylogic.infinitum.init.ModTileEntityTypes;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeFluidState;

import javax.annotation.Nullable;
import java.util.Random;

public class QuarryTileEntity extends TileEntity implements ITickableTileEntity {

    public int x, y, z, block, tick, blocksMined;
    boolean initialized = false;

    public QuarryTileEntity(final TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public QuarryTileEntity()
    {
        this(ModTileEntityTypes.QUARRY.get());
    }

    @Override
    public void tick() {
        if (!initialized) init();
        tick++;
        if (tick == 10) {
            tick = 0;
            if (y > 2) execute();
            else {
                if (!this.world.isRemote) {
                    this.world.setBlockState(this.pos,
                            this.world.getBlockState(this.pos)
                                    .with(QuarryBlock.LIT, false), 3);
                }
            }
        }
    }

    private void init() {
        initialized = true;
        x = this.pos.getX();
        y = this.pos.getY() - 2;
        z = this.pos.getZ();
    }

    private void execute() {
        getConfigurationIsValid();
        if (!configurationIsValid) return;
        updateFuel();
        if (!canRun) return;

        BlockPos offset;
        switch (block) {
            default:
                offset = new BlockPos(0, 0, 0);
                break;
            case 1:
                offset = new BlockPos(0, 0, 1);
                break;
            case 2:
                offset = new BlockPos(1, 0, 0);
                break;
            case 3:
                offset = new BlockPos(0, 0, -1);
                break;
            case 4:
                offset = new BlockPos(-1, 0, 0);
                break;
            case 5:
                offset = new BlockPos(1, 0, 1);
                break;
            case 6:
                offset = new BlockPos(-1, 0, 1);
                break;
            case 7:
                offset = new BlockPos(-1, 0, -1);
                break;
            case 8:
                offset = new BlockPos(1, 0, -1);
                break;
        }
        BlockPos posToBreak = new BlockPos(this.x + offset.getX(),
                this.y + offset.getY(),
                this.z + offset.getZ());
        destroyBlock(posToBreak, true, null);
        block++;
        if (block == 9) {
            block = 0;
            this.y--;
        }
    }

    private HopperTileEntity feedingHopper;
    private boolean configurationIsValid;
    private void getConfigurationIsValid() {
        configurationIsValid = false;

        BlockPos location = new BlockPos(this.pos);
        TileEntity above = world.getTileEntity(location.add(0, 1, 0));
        if (above instanceof HopperTileEntity) {
            //Debug.log("Above TileEntity is hopper");
            HopperTileEntity hopper = (HopperTileEntity)above;
            BlockState hopperState = world.getBlockState(location.add(0, 1, 0));
            Direction direction = hopperState.get(HopperBlock.FACING);
            if (direction == Direction.DOWN) {
                //Debug.log("Hopper is facing down");
                feedingHopper = hopper;
                if (hopperState.get(HopperBlock.ENABLED)) {
                    //Debug.log("Hopper is enabled");
                    configurationIsValid = true;
                }
            }
        }

        if (!this.world.isRemote && !configurationIsValid) {
            this.world.setBlockState(this.pos,
                    this.world.getBlockState(this.pos)
                            .with(QuarryBlock.LIT, false), 3);
        }
    }

    private boolean canRun = false;
    private void updateFuel() {
        int size = feedingHopper.getSizeInventory();
        boolean hasFuel = false;
        int consumeFuelSlot = -1;
        int stackBucketSlot = -1;
        for (int i = 0; i < size; i++) {
            ItemStack slot = feedingHopper.getStackInSlot(i);
            if (consumeFuelSlot == -1 && slot.getItem() == Items.LAVA_BUCKET) {
                consumeFuelSlot = i;
                hasFuel = true;
            }
            if (stackBucketSlot == -1 && slot.getItem() == Items.BUCKET && slot.getCount() < 16) {
                stackBucketSlot = i;
            }
            if (stackBucketSlot == -1 && slot.isEmpty()) {
                stackBucketSlot = i;
            }
        }
        canRun = hasFuel;

        if (!this.world.isRemote) {
            this.world.setBlockState(this.pos,
                    this.world.getBlockState(this.pos)
                            .with(QuarryBlock.LIT, canRun), 3);
        }

        BlockState blockState = this.getBlockState();
        if (canRun) {
            if (blocksMined >= 100) {
                blocksMined = 0;
                if (stackBucketSlot != -1) {
                    feedingHopper.removeStackFromSlot(consumeFuelSlot);
                    if (!feedingHopper.getStackInSlot(stackBucketSlot).isEmpty()) {
                        int count = feedingHopper.getStackInSlot(stackBucketSlot).getCount();
                        ItemStack newStack = feedingHopper.getStackInSlot(stackBucketSlot);
                        newStack.setCount(count + 1);
                        feedingHopper.setInventorySlotContents(stackBucketSlot, newStack);
                    } else {
                        feedingHopper.setInventorySlotContents(stackBucketSlot,
                                Items.BUCKET.getDefaultInstance());
                    }
                } else {
                    feedingHopper.setInventorySlotContents(consumeFuelSlot,
                            Items.BUCKET.getDefaultInstance());
                }
            }
        }
    }

    private static final BlockPos[] DROP_LOCATIONS = {
            new BlockPos(0, 0, 1),
            new BlockPos(1, 0, 0),
            new BlockPos(0, 0, -1),
            new BlockPos(-1, 0, 0),
            new BlockPos(1, 0, 1),
            new BlockPos(1, 0, -1),
            new BlockPos(-1, 0, -1),
            new BlockPos(-1, 0, 1)
    };
    private boolean destroyBlock(BlockPos pos, boolean dropBlock, @Nullable Entity entity) {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.isAir(world, pos)) return false;
        else {
            blocksMined++;
            IForgeFluidState iFluidState = world.getFluidState(pos);
            world.playSound(x, y, z,
                    blockState.getSoundType().getBreakSound(),
                    SoundCategory.BLOCKS,
                    blockState.getSoundType().getVolume(),
                    blockState.getSoundType().getPitch(),
                    false);
            if (dropBlock) {
                TileEntity tileEntity = blockState.hasTileEntity() ? world.getTileEntity(pos) : null;
                Random rng = new Random();
                BlockPos dropLocation = this.pos.add(DROP_LOCATIONS[rng.nextInt(8)]);
                Block.spawnDrops(blockState, world, dropLocation, tileEntity, entity, ItemStack.EMPTY);
            }
            return world.setBlockState(pos, iFluidState.getFluidState().getBlockState(), 3);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("initvalues", NBTHelper.toNBT(this));
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        CompoundNBT initValues = nbt.getCompound("initvalues");
        if (initValues != null) {
            this.x = initValues.getInt("x");
            this.y = initValues.getInt("y");
            this.z = initValues.getInt("z");
            this.block = initValues.getInt("block");
            this.tick = initValues.getInt("tick");
            this.blocksMined = initValues.getInt("blocksMined");
            initialized = true;
        } else {
            init();
        }
    }
}
