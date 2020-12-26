package com.mixleylogic.infinitum.items;

import com.mixleylogic.infinitum.Debug;
import com.mixleylogic.infinitum.InfinitumMod;
import com.mixleylogic.infinitum.VecMath;
import com.mixleylogic.infinitum.entities.HookshotEntity;
import com.mixleylogic.infinitum.init.ModEntityTypes;
import com.mixleylogic.infinitum.init.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class HookshotItem extends Item {

    public HookshotItem(boolean loaded) {
        super(new Properties().group(InfinitumMod.INFINITUM_TAB).maxStackSize(1));
        this.loaded = loaded;
    }

    private boolean loaded;

    private HookshotEntity hook = null;
    public boolean isMissingHook(HookshotEntity hookshotEntity) {
        if (hook == null) {
            return true;
        }
        else {
            return hook != hookshotEntity;
        }
    }
    public void setHook(HookshotEntity hookshotEntity) {
        hook = hookshotEntity;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        // Retrieve
        if (!loaded) {
            Debug.log("(Launcher) Attempting to retract hook...");
            if (hook == null) {
                Debug.logError("(Launcher) Unable to locate hook.");
                return ActionResult.resultFail(itemstack);
            }
            else {
                Debug.log("(Launcher) Retrieving hook.");
                hook.retrieve();
                return ActionResult.resultPass(playerIn.getHeldItem(handIn));
            }
        }
        // Launch
        else {
            worldIn.playSound(playerIn,
                    playerIn.getPosition(),
                    SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
                    SoundCategory.BLOCKS,
                    1.0f,
                    1.0f);
            worldIn.playSound(playerIn,
                    playerIn.getPosition(),
                    SoundEvents.ITEM_TRIDENT_THROW,
                    SoundCategory.BLOCKS,
                    1.0f,
                    1.0f);

            Vector3d playerPosition = playerIn.getPositionVec();
            Vector3d fireDirection = playerIn.getLookVec();
            Vector2f fireTrajectory = new Vector2f(
                    playerIn.getYaw(1.0f),
                    playerIn.getPitch(1.0f)
            );
            Vector3d spawnPosition = playerPosition.add(new Vector3d(0.0d, 1.5d, 0.0d));
            spawnPosition = fireDirection.scale(0.5).add(spawnPosition);

            EntityType<HookshotEntity> hookshotEntityType = ModEntityTypes.HOOKSHOT.get();
            HookshotEntity hookshotEntity = new HookshotEntity(
                    hookshotEntityType,
                    worldIn
            );
            hookshotEntity.setPosition(
                    spawnPosition.getX(),
                    spawnPosition.getY(),
                    spawnPosition.getZ());

            hookshotEntity.setDirection(fireDirection);
            hookshotEntity.setTrajectory(fireTrajectory);

            hookshotEntity.setInvisible(false);

            // Replace item
            playerIn.setHeldItem(handIn, ModItems.HOOKSHOT_EMPTY.get().getDefaultInstance());

            // Configuration
            hookshotEntity.setOwner(playerIn);
            hookshotEntity.setLauncher(playerIn.getHeldItem(handIn));


            VecMath.getPitchAndYaw(new Vector3d(0.0, 0.0, 0.0));
            // Spawn
            if (!worldIn.isRemote) {
                worldIn.addEntity(hookshotEntity);
            }

            return ActionResult.resultConsume(itemstack);
        }
    }


}
