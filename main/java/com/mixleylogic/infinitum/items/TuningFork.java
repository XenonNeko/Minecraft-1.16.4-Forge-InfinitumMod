package com.mixleylogic.infinitum.items;

import com.mixleylogic.infinitum.InfinitumMod;
import com.mixleylogic.infinitum.init.ModItems;
import net.minecraft.item.Item;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class TuningFork extends Item {

    public TuningFork() {
        super(new Properties().group(InfinitumMod.INFINITUM_TAB).maxStackSize(1));
    }

    public static TuningFork getInstance(int instanceId) {
        if (instanceId < 0 || instanceId > 25) {
            return ModItems.TUNING_FORK.get();
        }
        else {
            switch (instanceId) {
                default:
                    return ModItems.TUNING_FORK.get();
                case 1:
                    return ModItems.TUNING_FORK_FS1.get();
                case 2:
                    return ModItems.TUNING_FORK_G1.get();
                case 3:
                    return ModItems.TUNING_FORK_GS1.get();
                case 4:
                    return ModItems.TUNING_FORK_A1.get();
                case 5:
                    return ModItems.TUNING_FORK_AS1.get();
                case 6:
                    return ModItems.TUNING_FORK_B1.get();
                case 7:
                    return ModItems.TUNING_FORK_C1.get();
                case 8:
                    return ModItems.TUNING_FORK_CS1.get();
                case 9:
                    return ModItems.TUNING_FORK_D1.get();
                case 10:
                    return ModItems.TUNING_FORK_DS1.get();
                case 11:
                    return ModItems.TUNING_FORK_E1.get();
                case 12:
                    return ModItems.TUNING_FORK_F1.get();
                case 13:
                    return ModItems.TUNING_FORK_FS2.get();
                case 14:
                    return ModItems.TUNING_FORK_G2.get();
                case 15:
                    return ModItems.TUNING_FORK_GS2.get();
                case 16:
                    return ModItems.TUNING_FORK_A2.get();
                case 17:
                    return ModItems.TUNING_FORK_AS2.get();
                case 18:
                    return ModItems.TUNING_FORK_B2.get();
                case 19:
                    return ModItems.TUNING_FORK_C2.get();
                case 20:
                    return ModItems.TUNING_FORK_CS2.get();
                case 21:
                    return ModItems.TUNING_FORK_D2.get();
                case 22:
                    return ModItems.TUNING_FORK_DS2.get();
                case 23:
                    return ModItems.TUNING_FORK_E2.get();
                case 24:
                    return ModItems.TUNING_FORK_F2.get();
                case 25:
                    return ModItems.TUNING_FORK_FS3.get();
            }
        }
    };

    public static int getInstanceId(TuningFork tuningFork) {
        if (tuningFork == ModItems.TUNING_FORK.get()) {
            return 0;
        }
        else if (tuningFork == ModItems.TUNING_FORK_FS1.get()) {
            return 1;
        }
        else if (tuningFork == ModItems.TUNING_FORK_G1.get()) {
            return 2;
        }
        else if (tuningFork == ModItems.TUNING_FORK_GS1.get()) {
            return 3;
        }
        else if (tuningFork == ModItems.TUNING_FORK_A1.get()) {
            return 4;
        }
        else if (tuningFork == ModItems.TUNING_FORK_AS1.get()) {
            return 5;
        }
        else if (tuningFork == ModItems.TUNING_FORK_B1.get()) {
            return 6;
        }
        else if (tuningFork == ModItems.TUNING_FORK_C1.get()) {
            return 7;
        }
        else if (tuningFork == ModItems.TUNING_FORK_CS1.get()) {
            return 8;
        }
        else if (tuningFork == ModItems.TUNING_FORK_D1.get()) {
            return 9;
        }
        else if (tuningFork == ModItems.TUNING_FORK_DS1.get()) {
            return 10;
        }
        else if (tuningFork == ModItems.TUNING_FORK_E1.get()) {
            return 11;
        }
        else if (tuningFork == ModItems.TUNING_FORK_F1.get()) {
            return 12;
        }
        else if (tuningFork == ModItems.TUNING_FORK_FS2.get()) {
            return 13;
        }
        else if (tuningFork == ModItems.TUNING_FORK_G2.get()) {
            return 14;
        }
        else if (tuningFork == ModItems.TUNING_FORK_GS2.get()) {
            return 15;
        }
        else if (tuningFork == ModItems.TUNING_FORK_A2.get()) {
            return 16;
        }
        else if (tuningFork == ModItems.TUNING_FORK_AS2.get()) {
            return 17;
        }
        else if (tuningFork == ModItems.TUNING_FORK_B2.get()) {
            return 18;
        }
        else if (tuningFork == ModItems.TUNING_FORK_C2.get()) {
            return 19;
        }
        else if (tuningFork == ModItems.TUNING_FORK_CS2.get()) {
            return 20;
        }
        else if (tuningFork == ModItems.TUNING_FORK_D2.get()) {
            return 21;
        }
        else if (tuningFork == ModItems.TUNING_FORK_DS2.get()) {
            return 22;
        }
        else if (tuningFork == ModItems.TUNING_FORK_E2.get()) {
            return 23;
        }
        else if (tuningFork == ModItems.TUNING_FORK_F2.get()) {
            return 24;
        }
        else if (tuningFork == ModItems.TUNING_FORK_FS3.get())
        {
            return 25;
        }
        else {
            return -1;
        }
    }

    public static SoundEvent getSoundEventFromInstrument(NoteBlockInstrument instrument) {

        switch (instrument) {
            default:
                return SoundEvents.BLOCK_NOTE_BLOCK_HARP;
            case BASS:
                return SoundEvents.BLOCK_NOTE_BLOCK_BASS;
            case SNARE:
                return SoundEvents.BLOCK_NOTE_BLOCK_SNARE;
            case HAT:
                return SoundEvents.BLOCK_NOTE_BLOCK_HAT;
            case BASEDRUM:
                return SoundEvents.BLOCK_NOTE_BLOCK_BASEDRUM;
            case BELL:
                return SoundEvents.BLOCK_NOTE_BLOCK_BELL;
            case FLUTE:
                return SoundEvents.BLOCK_NOTE_BLOCK_FLUTE;
            case CHIME:
                return SoundEvents.BLOCK_NOTE_BLOCK_CHIME;
            case GUITAR:
                return SoundEvents.BLOCK_NOTE_BLOCK_GUITAR;
            case XYLOPHONE:
                return SoundEvents.BLOCK_NOTE_BLOCK_XYLOPHONE;
            case IRON_XYLOPHONE:
                return SoundEvents.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE;
            case COW_BELL:
                return SoundEvents.BLOCK_NOTE_BLOCK_COW_BELL;
            case DIDGERIDOO:
                return SoundEvents.BLOCK_NOTE_BLOCK_DIDGERIDOO;
            case BIT:
                return SoundEvents.BLOCK_NOTE_BLOCK_BIT;
            case BANJO:
                return SoundEvents.BLOCK_NOTE_BLOCK_BANJO;
            case PLING:
                return SoundEvents.BLOCK_NOTE_BLOCK_PLING;
        }
    }

    public static float getPitchFromNote(int note) {
        switch (note) {
            default:
                return 0.5f;
            case 1:
                return 0.529732f;
            case 2:
                return 0.561231f;
            case 3:
                return 0.594604f;
            case 4:
                return 0.629961f;
            case 5:
                return 0.667420f;
            case 6:
                return 0.707107f;
            case 7:
                return 0.749154f;
            case 8:
                return 0.793701f;
            case 9:
                return 0.840896f;
            case 10:
                return 0.890899f;
            case 11:
                return 0.943874f;
            case 12:
                return 1.0f;
            case 13:
                return 1.059463f;
            case 14:
                return 1.122462f;
            case 15:
                return 1.189207f;
            case 16:
                return 1.259921f;
            case 17:
                return 1.334840f;
            case 18:
                return 1.414214f;
            case 19:
                return 1.498307f;
            case 20:
                return 1.587401f;
            case 21:
                return 1.681793f;
            case 22:
                return 1.781797f;
            case 23:
                return 1.887749f;
            case 24:
                return 2.0f;
        }
    }
}
