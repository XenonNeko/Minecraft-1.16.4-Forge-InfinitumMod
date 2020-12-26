package com.mixleylogic.infinitum.events;

import com.mixleylogic.infinitum.Debug;
import com.mixleylogic.infinitum.InfinitumMod;
import com.mixleylogic.infinitum.entities.HookshotEntity;
import com.mixleylogic.infinitum.init.ModItems;
import com.mixleylogic.infinitum.init.ModResources;
import com.mixleylogic.infinitum.items.TuningFork;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NoteBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = InfinitumMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE,
        value = Dist.CLIENT)
public class ModClientEvents {

    /*
    @SubscribeEvent
    public static void onBeeNestHeadBonk(LivingEvent.LivingJumpEvent event) {
        LivingEntity player = event.getEntityLiving();
        World world = player.getEntityWorld();
        BlockPos bonkLocation = player.getPosition().add(0, 2, 0);
        BlockState bonkingBlock = world.getBlockState(bonkLocation);
        if (bonkingBlock.getBlock() == Blocks.BEE_NEST) {
            //Debug.chat("You bonked your head on a bee's nest! How rude!");
            // Shut the Hell up, me
            if (player instanceof PlayerEntity) {
                BeeNestInterior beeNestInterior =
                        new BeeNestInterior((PlayerEntity)player, world, player.getPosition());
                beeNestInterior.conjure();
            }
        }

    }
     */



    /*
    @SubscribeEvent
    public static void onJumpWithStick(LivingEvent.LivingJumpEvent event) {
        LivingEntity player = event.getEntityLiving();
        if (player.getHeldItemMainhand().getItem() == Items.STICK) {
            InfinitumMod.LOGGER.info("Player tried to jump with a stick");
            World world = player.getEntityWorld();

            BlockPos playerLocation = player.getPosition();
            BlockPos chunkLocation = new BlockPos(
                    (playerLocation.getX() / 16) * 16,
                    0,
                    (playerLocation.getZ() / 16) * 16
            );

            int count = 0;
            int count2 = 0;
            int count3 = 0;
            for (int y = 0; y < playerLocation.getY(); y++) {
                for (int z = 0; z < 16; z++) {
                    for (int x = 0; x < 16; x++) {
                        BlockState blockState = world.getBlockState(
                                chunkLocation.add(x, y, z)
                        );
                        if (blockState.getBlock() == ModBlocks.INFINITUM_ORE_BLOCK.get()) {
                            count++;
                        }
                        if (blockState.getBlock() == Blocks.DIAMOND_ORE) {
                            count2++;
                        }
                        if (blockState.getBlock() == Blocks.IRON_ORE) {
                            count3++;
                        }
                    }
                }
            }

            Debug.chat(count + " Infinitum Ore, " + count2 + " Diamond Ore, " + count3 + " Iron Ore");
        }
    }
     */


    @SubscribeEvent
    public static void onDamageEntity(LivingHurtEvent event) {
        float damage = event.getAmount();
        DamageSource source = event.getSource();
        LivingEntity entity = event.getEntityLiving();
        Iterable<ItemStack> armor = entity.getArmorInventoryList();
        int requirementsMet = 0;
        // What are you wearing?
        for (ItemStack itemStack:
             armor) {
            Item item = itemStack.getItem();
            if (item == ModItems.INFINITUM_HELMET.get()) {
                requirementsMet++;
            }
            if (item == ModItems.INFINITUM_CHESTPLATE.get()) {
                requirementsMet++;
            }
            if (item == ModItems.INFINITUM_LEGGINGS.get()) {
                requirementsMet++;
            }
            if (item == ModItems.INFINITUM_BOOTS.get()) {
                requirementsMet++;
            }
        }
        // If you're wearing a full set of Infinitum
        // BUT you were hit with an Infinitum Sword,
        // then treat the Infinitum Sword like a diamond sword.
        // Get the entity that dealt the damage.
        Entity immediateSource = source.getImmediateSource();
        LivingEntity attacker = null;
        if (immediateSource != null) {
            // Attempt to derive a living entity from the source entity.
            if (immediateSource instanceof LivingEntity) {
                attacker = (LivingEntity) immediateSource;
            }
        }
        if (requirementsMet == 4 && attacker != null
                && attacker.getHeldItemMainhand().getItem() == ModItems.INFINITUM_SWORD.get()) {
            event.setAmount(7.0f);
        }
        // Otherwise negate all damage
        else if (requirementsMet == 4) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onUseTuningFork(PlayerInteractEvent.RightClickBlock event) {
        PlayerEntity player = event.getPlayer();
        World world = player.world;
        BlockState blockState = world.getBlockState(event.getPos());
        // If we are holding a tuning fork
        if (player.getHeldItemMainhand().getItem() instanceof TuningFork) {
            // If we are looking at a note block
            if (blockState.getBlock() == Blocks.NOTE_BLOCK) {
                // Copy
                if (player.isCrouching()) {
                    // Properties
                    int note = blockState.get(NoteBlock.NOTE);
                    NoteBlockInstrument instrument = blockState.get(NoteBlock.INSTRUMENT);
                    // Tune Tuning Fork
                    TuningFork tuningFork = TuningFork.getInstance(note + 1);
                    player.setHeldItem(Hand.MAIN_HAND, new ItemStack(tuningFork, 1));
                    // Play sound
                    SoundEvent soundEvent = new SoundEvent(ModResources.TUNING_FORK_RING);
                    float pitch = TuningFork.getPitchFromNote(note);
                    world.playSound(player,
                            event.getPos(),
                            soundEvent,
                            SoundCategory.BLOCKS,
                            0.75f,
                            pitch);
                }
                // Paste
                else {
                    TuningFork tuningFork = (TuningFork) player.getHeldItemMainhand().getItem();
                    // Properties
                    int note = TuningFork.getInstanceId(tuningFork);
                    if (note > 0) {
                        NoteBlockInstrument instrument = blockState.get(NoteBlock.INSTRUMENT);
                        // Replace block
                        BlockState replaceBlockState = Blocks.NOTE_BLOCK.getDefaultState();
                        replaceBlockState = replaceBlockState.with(NoteBlock.NOTE, note - 1);
                        world.setBlockState(event.getPos(), replaceBlockState);
                        // Stop event
                        event.setCanceled(true);
                        // Play sound
                        SoundEvent soundEvent = TuningFork.getSoundEventFromInstrument(instrument);
                        float pitch = TuningFork.getPitchFromNote(note - 1);
                        world.playSound(player,
                                event.getPos(),
                                soundEvent,
                                SoundCategory.BLOCKS,
                                1.0f,
                                pitch);
                    } else {
                        Debug.logWarn("Can't tune a note block with this tuning fork!");
                    }
                }
            }
            // If we are targeting something other than a note block
            else {
                TuningFork tuningFork = (TuningFork) player.getHeldItemMainhand().getItem();
                // Properties
                int note = TuningFork.getInstanceId(tuningFork);
                SoundEvent soundEvent = (note > 0) ?
                        new SoundEvent(ModResources.TUNING_FORK_RING) :
                        new SoundEvent(ModResources.TUNING_FORK_CLANK);
                float pitch = (note > 0) ?
                        TuningFork.getPitchFromNote(note - 1) :
                        1.0f;
                world.playSound(player,
                        event.getPos(),
                        soundEvent,
                        SoundCategory.BLOCKS,
                        0.7f,
                        pitch);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) event.getEntity();
            broadcastPlayerDeath(playerEntity);
        }
    }

    @SubscribeEvent
    public static ActionResultType onTryHookshotInteract(PlayerInteractEvent.EntityInteractSpecific event) {
        if (event.getTarget() instanceof HookshotEntity) {
            event.setCanceled(true);
            HookshotEntity hookshotEntity = (HookshotEntity) event.getTarget();
            hookshotEntity.retrieve();
            // This does not prevent unleashing
            return ActionResultType.FAIL;
        }
        else {
            return event.getTarget().applyPlayerInteraction(
                    event.getPlayer(),
                    event.getLocalPos(),
                    event.getHand());
        }
    }

    /*
    @SubscribeEvent
    public static void onUseLectern(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        BlockPos blockPos = event.getPos();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.getBlock() instanceof LecternBlock) {
            LecternTileEntity lecternTileEntity = (LecternTileEntity) world.getTileEntity(blockPos);
            if (lecternTileEntity != null) {
                ItemStack bookItemStack = lecternTileEntity.getBook();
                CompoundNBT nbt = bookItemStack.serializeNBT();
                // Typical NBT looks like this:
                // {id:"minecraft:writable_book",Count:1b,tag:{pages:["Page 1","Page 2","Page 3","Page 4","Page 5"]}}
                // So, look for the first opening square bracket and the last closing square bracket, and get the
                // substring between them. For the sake of simplicity, only one page will be supported. From there,
                // just remove the double/single quotes on each side and process the resulting string as desired.
                String nbtString = nbt.getString();
                Debug.log(nbtString);
                int dataStart = nbtString.indexOf('[');
                int dataEnd = nbtString.lastIndexOf(']');
                if (dataStart != -1 && dataEnd != -1) {
                    String pageString = nbtString.substring(dataStart + 1, dataEnd);
                    String codePage = pageString.substring(1, pageString.length() - 1);
                    // BEGIN CODE PROCESSING

                    Debug.log(codePage);
                    String[] components = codePage.split(",");
                    double x = 0.0;
                    double y = 0.0;
                    double z = 0.0;
                    boolean vectorIsValid = true;
                    if (components.length == 3) {
                        for (int i = 0; i < 3; i++) {
                            try {
                                double component = Double.parseDouble(components[i]);
                                switch (i) {
                                    case 0:
                                        x = component;
                                        break;
                                    case 1:
                                        y = component;
                                        break;
                                    case 2:
                                        z = component;
                                        break;
                                }
                            }
                            catch(Exception e) {
                                vectorIsValid = false;
                                Debug.chat("[ERROR] " + e.toString());
                            }
                        }
                    }
                    else {
                        vectorIsValid = false;
                    }

                    if (vectorIsValid) {
                        Vector3d vector3d = new Vector3d(x, y, z);

                        Debug.chat("Successfully parsed x: " + x);
                        Debug.chat("Successfully parsed y: " + y);
                        Debug.chat("Successfully parsed z: " + z);
                        Debug.chat("Successfully parsed vector (" + vector3d.x + ", " + vector3d.y + ", " + vector3d.z + ")");
                        Vector2f pitchAndYaw = VecMath.getPitchAndYaw(vector3d);
                        Debug.chat("Pitch calculated as " + pitchAndYaw.x + "; Yaw calculated as " + pitchAndYaw.y);
                    }
                    else {
                        Debug.chat("[ERROR] Invalid vector.");
                    }

                    // END CODE PROCESSING
                }
            }
        }
    }
    public static float broadcastFloat_1;
    */

    private static List<PlayerDeathListener> playerDeathListeners = new ArrayList<PlayerDeathListener>();
    public static void addPlayerDeathListener(PlayerDeathListener listener) {
        playerDeathListeners.add(listener);
    }
    public static void removePlayerDeathListener(PlayerDeathListener listener) {
        playerDeathListeners.remove(listener);
    }
    public static void broadcastPlayerDeath(PlayerEntity playerEntity) {
        // Notify everybody that may be interested.
        for (PlayerDeathListener pdl : playerDeathListeners)
            pdl.onPlayerDeath(playerEntity);
    }

}
