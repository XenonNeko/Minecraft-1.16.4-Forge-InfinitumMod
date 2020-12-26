package com.mixleylogic.infinitum.tileentity;

import com.mixleylogic.infinitum.Debug;
import com.mixleylogic.infinitum.conjuredstructures.ConjurableStructure;
import com.mixleylogic.infinitum.init.ModTileEntityTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class StructureControllerTileEntity extends TileEntity implements ITickableTileEntity {

    private boolean isInitialized = false;
    private boolean isDissolved = false;
    private ConjurableStructure structure;
    private BlockPos returnPosition;
    private BlockPos structurePosition;
    private int structureSize;
    private int structureHeight;
    private PlayerEntity occupant;
    private AxisAlignedBB structureBounds;

    public void setStructure(ConjurableStructure structure) {
        this.structure = structure;
        occupant = structure.getSummoner();
        returnPosition = structure.getReturnPosition();
        structurePosition = structure.getPosition();
        structureSize = structure.getSize();
        structureHeight = structure.getHeight();
    }

    public StructureControllerTileEntity(final TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public StructureControllerTileEntity()
    {
        this(ModTileEntityTypes.STRUCTURE_CONTROLLER.get());
    }

    private boolean hasStructure;
    private void init() {
        isInitialized = true;
        if (structure != null) {
            structure.conjure();
        } else {
            Debug.logWarn("No structure assigned!");
        }
    }

    private int kickCooldownTicks = 0;
    @Override
    public void tick() {
        if (!isInitialized) {
            init();
        }
        if (isDissolved) {
            return;
        }

        structureBounds = (new AxisAlignedBB(this.pos
                .add(1, 1, 1)
                .add(structureSize / 2, structureHeight / 2, structureSize / 2)))
                .grow(structureSize / 2, structureHeight / 2, structureSize / 2);

        execute();

        kickCooldownTicks++;
    }

    private void execute() {
        occupant.addPotionEffect(new EffectInstance(
                Effects.BLINDNESS,
                20
        ));

        // If the occupant leaves the structure bounds, dissolve the structure
        if (kickCooldownTicks > 20 && !structureBounds.contains(occupant.getPositionVec())) {
            occupant.setPosition(returnPosition.getX(),
                    returnPosition.getY(),
                    returnPosition.getZ());
            structure.dissolve();
            isDissolved = true;

            occupant.clearActivePotions();
        }
    }
}
