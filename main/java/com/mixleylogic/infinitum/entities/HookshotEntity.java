package com.mixleylogic.infinitum.entities;

import com.mixleylogic.infinitum.Debug;
import com.mixleylogic.infinitum.VecMath;
import com.mixleylogic.infinitum.events.HookshotEventListener;
import com.mixleylogic.infinitum.events.ModClientEvents;
import com.mixleylogic.infinitum.events.PlayerDeathListener;
import com.mixleylogic.infinitum.init.ModItems;
import com.mixleylogic.infinitum.items.HookshotItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class HookshotEntity extends MobEntity implements PlayerDeathListener {
    public HookshotEntity(EntityType<? extends  MobEntity> type, World worldIn) {
        super(type, worldIn);
        direction = new Vector3d(0.0d, 0.0d, 0.0d);
        trajectory = new Vector2f(0.0f, 0.0f);
        // This makes it unable to move
        //this.setNoAI(true);
        this.setSilent(true);
        this.setInvulnerable(true);
        this.preventDespawn();
        ModClientEvents.addPlayerDeathListener(this);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, Double.POSITIVE_INFINITY)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 10000.0d);
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    public boolean canCollide(Entity entity) {
        return (entity != owner) && (super.canCollide(entity));
    }

    private boolean moving = false;
    private Vector3d direction;
    public void setDirection(Vector3d direction) {
        this.direction = direction;
    }
    private Vector2f trajectory;
    public void setTrajectory(Vector2f trajectory) {
        this.trajectory = trajectory;
    }

    private PlayerEntity owner;
    public void setOwner(PlayerEntity owner) {
        Debug.log("Hook's owner was set to " + owner.getScoreboardName());

        this.owner = owner;

        setLeashHolder(owner, true);

        if (!isInitialized && launcher != null) {
            isInitialized = true;
            state = HookshotState.Firing;
            Debug.log("Hook is initialized (via setOwner)!");
        }
    }

    private ItemStack launcher = null;
    private HookshotItem hookshotItem = null;
    public void setLauncher(ItemStack launcher) {
        Debug.log("Hook's launcher was set to " + launcher.getItem().getRegistryName());

        this.launcher = launcher;
        this.hookshotItem = (HookshotItem) launcher.getItem();
        launcherIsAccountedFor = true;
        if (!isInitialized && owner != null) {
            isInitialized = true;
            state = HookshotState.Firing;
            Debug.log("Hook is initialized (via setLauncher)!");
        }
    }

    enum HookshotState {
        Holstered,
        Firing,
        Latched,
        Retracting
    };
    private HookshotState state;

    public void retrieve() {
        state = HookshotState.Retracting;
        Debug.log("(Hook) Request to retract hook received.");
    }

    private boolean isInitialized = false;
    private boolean launcherIsAccountedFor;
    private boolean launcherIsHeld;
    private int uninitializedTicks = 0;
    private int brokenTicks = 0;
    private static final double LAUNCH_SPEED = 1.7;
    private static final double PULL_FORCE = 1.0;
    // Distance below which the pull force is reduced
    private static final double BRAKE_DISTANCE = 2.0;
    // Distance below which the pull force is stopped
    private static final double LATCH_DISTANCE = 0.6;
    // Distance above which the hook will be forced to retract
    private static final double MAX_DISTANCE = 70.0;
    private boolean hasLatchedEntity = false;
    private Entity latchedEntity;

    private boolean firstTick = true;

    // Also update, I guess
    //@Override
    //public void livingTick() {
    //    super.livingTick();
    //    setDirection(direction);
    //}

    // Update
    @Override
    public void tick() {
        super.tick();

        //if (!world.isRemote) {
            Vector2f pitchAndYaw = VecMath.getPitchAndYaw(direction);
            //Debug.chat("Yaw: " + pitchAndYaw.y);
            setAIMoveSpeed(1000.0f);
            if (pitchAndYaw.y != 90.0) {
                setRotation(pitchAndYaw.y + 90.0f, 0.0f);
            }
        //}

        if (firstTick) {
            Debug.log("Hook is alive!");
            firstTick = false;
        }

        if (!isInitialized) {
            uninitializedTicks++;
            if (uninitializedTicks >= 6000) {
                despawn(true);
            }
            return;
        }
        else {
            uninitializedTicks = 0;
        }

        if (!getLeashed()) {
            brokenTicks++;
            if (brokenTicks >= 100) {
                despawn(true);
            }
        }
        else {
            brokenTicks = 0;
        }

        if (hookshotItem != null) {
            if (hookshotItem.isMissingHook(this)) {
                hookshotItem.setHook(this);
            }
        }
        else {
            Debug.logError("Launcher suddenly disappeared!");
            despawn(true);
            return;
        }

        // Check if the launcher is accounted for.
        launcherIsAccountedFor = (
                owner.inventory.hasItemStack(ModItems.HOOKSHOT_EMPTY.get().getDefaultInstance())
                || owner.inventory.hasItemStack(ModItems.HOOKSHOT_ITEM.get().getDefaultInstance())
        );
        if (!launcherIsAccountedFor) {
            Debug.logError("Launcher is suddenly unaccounted for!");
            despawn(true);
            return;
        }
        int launcherSlot = -1;
        launcherIsHeld = (owner.inventory.getCurrentItem() == launcher);
        if (launcherIsAccountedFor) {
            launcherSlot = owner.inventory.getSlotFor(launcher);
        }

        if (hasLatchedEntity) {
            Vector3d grabPosition = getPositionVec();
            latchedEntity.setPosition(grabPosition.x, grabPosition.y, grabPosition.z);
        }

        Vector3d velocity = direction.scale(LAUNCH_SPEED);

        if (state == HookshotState.Holstered) {
            setMotion(0.0d, 0.0d, 0.0d);
            Vector3d position = owner.getPositionVec().add(0.0d, 2.5d, 0.0d);
            setPosition(position.x, position.y, position.z);

        }
        else if (state == HookshotState.Firing) {
            if (!launcherIsHeld) {
                Debug.log("Launcher is not being held; retracting.");
                state = HookshotState.Retracting;
            }

            setMotion(velocity);

            // onHookshotLatch
            if (collidedVertically || collidedHorizontally) {
                broadcastHookshotLatch(this);
                state = HookshotState.Latched;

                Debug.log("Hook has latched.");
            }

            // onHookshotReturn
            if (getDistance(owner) >= MAX_DISTANCE) {
                broadcastHookshotReturn(this);
                state = HookshotState.Retracting;

                Debug.log("Hook has reached its max distance; retracting.");
            }
        }
        else if (state == HookshotState.Latched) {
            if (!launcherIsHeld) {
                state = HookshotState.Retracting;

                Debug.log("Launcher is not being held; retracting.");
            }

            setMotion(0.0, 0.0, 0.0);

            Vector3d pull = getPositionVec()
                    .subtract(owner.getPositionVec().add(0.0, 1.5, 0.0))
                    .normalize();
            double pullFactor;
            double distance = getPositionVec()
                    .subtract(owner.getPositionVec().add(0.0, 1.5, 0.0)).length();
            if (distance >= BRAKE_DISTANCE) {
                pullFactor = PULL_FORCE;
            }
            else if (distance >= LATCH_DISTANCE) {
                pullFactor = ((distance - LATCH_DISTANCE) / (BRAKE_DISTANCE - LATCH_DISTANCE))
                        * PULL_FORCE;
            }
            else {
                pullFactor = 0.0;
            }
            pull = pull.scale(pullFactor);

            ClientPlayerEntity player = Minecraft.getInstance().player;
            if (player != null) {
                player.setMotion(pull.x, pull.y, pull.z);
                //player.addVelocity(pull.x, pull.y, pull.z);

                player.velocityChanged = true;
            }
        }
        // Returning
        else {

            Vector3d returnVelocity = owner.getPositionVec().subtract(getPositionVec()).normalize()
                    .scale(LAUNCH_SPEED);
            setMotion(returnVelocity);

            // onHookshotReturn
            if (getDistance(owner) < 1.0f) {
                broadcastHookshotReturn(this);
                state = HookshotState.Holstered;
                clearLeashed(true, false);
                hasLatchedEntity = false;
                if (launcherSlot != -1) {
                    owner.inventory.setInventorySlotContents(
                            launcherSlot,
                            ModItems.HOOKSHOT_ITEM.get().getDefaultInstance()
                    );
                }
                else {
                    Debug.logWarn("Unable to locate launcher within inventory.");
                }
                despawn(false);

                Debug.log("Hook has returned.");
            }
        }

        this.noClip = (state == HookshotState.Retracting);
    }

    @Override
    public void onDeath(DamageSource cause) {
        Debug.chat("Hook died.");

        clearLeashed(true, true);
        super.onDeath(cause);
    }

    @Override
    public boolean preventDespawn() {
        return launcherIsAccountedFor || !isInitialized;
    }

    private void despawn(boolean dropSelf) {
        Debug.log("Hook despawned.");

        clearLeashed(true, dropSelf);
        if (dropSelf) {
            entityDropItem(ModItems.HOOKSHOT_HOOK.get().getDefaultInstance());
        }

        ModClientEvents.removePlayerDeathListener(this);
        remove();
    }

    @Override
    public void applyEntityCollision(Entity entityIn) {
        super.applyEntityCollision(entityIn);

        if (entityIn != owner && (!hasLatchedEntity || (entityIn != latchedEntity))) {
            state = HookshotState.Retracting;
            broadcastHookshotLatch(this);
            hasLatchedEntity = true;
            latchedEntity = entityIn;

            // Automatically ignite Creepers.
            if (entityIn instanceof CreeperEntity) {
                CreeperEntity creeperEntity = (CreeperEntity) entityIn;
                creeperEntity.ignite();
            }
            // Hurt normal things
            else {
                entityIn.attackEntityFrom(DamageSource.causePlayerDamage(owner), 2.0f);
            }
        }
    }

    @Override
    public void onPlayerDeath(PlayerEntity playerEntity) {
        if (playerEntity == owner) {
            Debug.chat("Hookshot successfully detected owner's death!");
            despawn(true);
        }
    }

    private static final float EYE_HEIGHT = 0.0f;
    @Override
    public float getEyeHeight(Pose pose) {
        return EYE_HEIGHT;
    }

    private static List<HookshotEventListener> hookshotEventListeners = new ArrayList<>();
    public static void addHookshotEventListener(HookshotEventListener listener) {
        hookshotEventListeners.add(listener);
    }
    public static void removeHookshotEventListener(HookshotEventListener listener) {
        hookshotEventListeners.remove(listener);
    }
    public static void broadcastHookshotLatch(HookshotEntity hookshotEntity) {
        for (HookshotEventListener hel : hookshotEventListeners)
            hel.onHookshotLatch(hookshotEntity);
    }
    public static void broadcastHookshotReturn(HookshotEntity hookshotEntity) {
        for (HookshotEventListener hel : hookshotEventListeners)
            hel.onHookshotReturn(hookshotEntity);
    }
}
