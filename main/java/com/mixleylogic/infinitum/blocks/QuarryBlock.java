package com.mixleylogic.infinitum.blocks;

import com.mixleylogic.infinitum.init.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import java.util.function.ToIntFunction;

public class QuarryBlock extends Block implements ITileEntityProvider {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public QuarryBlock() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(25.0f, 1200.0f)
                .sound(SoundType.NETHERITE)
                .harvestLevel(3)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
                .setLightLevel(lightLevel)
        );
        this.setDefaultState(this.stateContainer.getBaseState().with(LIT, false));
    }

    private static final ToIntFunction<BlockState> lightLevel = QuarryBlock::getLightLevel;
    private static int getLightLevel(BlockState blockState) {
        if (blockState.hasProperty(QuarryBlock.LIT)) {
            return blockState.get(QuarryBlock.LIT) ? 13 : 0;
        } else {
            return 0;
        }
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn)
    {
        return ModTileEntityTypes.QUARRY.get().create();
    }
}
