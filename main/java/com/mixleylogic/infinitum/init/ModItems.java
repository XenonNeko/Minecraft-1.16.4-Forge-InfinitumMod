package com.mixleylogic.infinitum.init;

import com.mixleylogic.infinitum.InfinitumMod;
import com.mixleylogic.infinitum.armor.ModArmorMaterial;
import com.mixleylogic.infinitum.items.*;
import com.mixleylogic.infinitum.tools.InfinitumItemTier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            InfinitumMod.MOD_ID);

    // Items
    public static final RegistryObject<Item> INFINITUM_INGOT = ITEMS.register("infinitum_ingot",
            ItemBase::new);
    public static final RegistryObject<Item> INFINITUM_POWDER = ITEMS.register("infinitum_powder",
            ItemBase::new);
    public static final RegistryObject<Item> REFINED_INFINITUM_POWDER = ITEMS.register("refined_infinitum_powder",
            ItemBase::new);
    public static final RegistryObject<Item> ANCIENT_INFINITUM_INGOT = ITEMS.register("ancient_infinitum_ingot",
            ItemBase::new);
    public static final RegistryObject<Item> HOOKSHOT_ITEM = ITEMS.register("hookshot_item",
            () -> new HookshotItem(true));
    public static final RegistryObject<Item> HOOKSHOT_EMPTY = ITEMS.register("hookshot_empty",
            () -> new HookshotItem(false));
    public static final RegistryObject<Item> HOOKSHOT_HOOK = ITEMS.register("hookshot_hook",
            ItemBase::new);

    // Tuning Fork
    public static final RegistryObject<TuningFork> TUNING_FORK = ITEMS.register("tuning_fork",
            TuningFork::new);
    // Tuning Fork Tunings
    public static final RegistryObject<TuningFork> TUNING_FORK_FS1 = ITEMS.register("tuning_fork_fs1",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_G1 = ITEMS.register("tuning_fork_g1",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_GS1 = ITEMS.register("tuning_fork_gs1",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_A1 = ITEMS.register("tuning_fork_a1",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_AS1 = ITEMS.register("tuning_fork_as1",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_B1 = ITEMS.register("tuning_fork_b1",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_C1 = ITEMS.register("tuning_fork_c1",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_CS1 = ITEMS.register("tuning_fork_cs1",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_D1 = ITEMS.register("tuning_fork_d1",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_DS1 = ITEMS.register("tuning_fork_ds1",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_E1 = ITEMS.register("tuning_fork_e1",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_F1 = ITEMS.register("tuning_fork_f1",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_FS2 = ITEMS.register("tuning_fork_fs2",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_G2 = ITEMS.register("tuning_fork_g2",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_GS2 = ITEMS.register("tuning_fork_gs2",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_A2 = ITEMS.register("tuning_fork_a2",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_AS2 = ITEMS.register("tuning_fork_as2",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_B2 = ITEMS.register("tuning_fork_b2",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_C2 = ITEMS.register("tuning_fork_c2",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_CS2 = ITEMS.register("tuning_fork_cs2",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_D2 = ITEMS.register("tuning_fork_d2",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_DS2 = ITEMS.register("tuning_fork_ds2",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_E2 = ITEMS.register("tuning_fork_e2",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_F2 = ITEMS.register("tuning_fork_f2",
            TuningFork::new);
    public static final RegistryObject<TuningFork> TUNING_FORK_FS3 = ITEMS.register("tuning_fork_fs3",
            TuningFork::new);

    // Tools
    public static final RegistryObject<SwordItem> INFINITUM_SWORD = ITEMS.register(
            "infinitum_sword",
            () -> new SwordItem(InfinitumItemTier.INFINITUM, 0x7FFFFFFF, 0.0f, new Item.Properties().group(InfinitumMod.INFINITUM_TAB).isImmuneToFire()));
    public static final RegistryObject<PickaxeItem> INFINITUM_PICKAXE = ITEMS.register(
            "infinitum_pickaxe",
            () -> new PickaxeItem(InfinitumItemTier.INFINITUM, 0, -2.8f, new Item.Properties().group(InfinitumMod.INFINITUM_TAB).isImmuneToFire()));
    public static final RegistryObject<AxeItem> INFINITUM_AXE = ITEMS.register(
            "infinitum_axe",
            () -> new AxeItem(InfinitumItemTier.INFINITUM, 0, -2.8f, new Item.Properties().group(InfinitumMod.INFINITUM_TAB).isImmuneToFire()));
    public static final RegistryObject<ShovelItem> INFINITUM_SHOVEL = ITEMS.register(
            "infinitum_shovel",
            () -> new ShovelItem(InfinitumItemTier.INFINITUM, 0, -2.8f, new Item.Properties().group(InfinitumMod.INFINITUM_TAB).isImmuneToFire()));
    public static final RegistryObject<HoeItem> INFINITUM_HOE = ITEMS.register(
            "infinitum_hoe",
            () -> new HoeItem(InfinitumItemTier.INFINITUM, 0, -2.8f, new Item.Properties().group(InfinitumMod.INFINITUM_TAB).isImmuneToFire()));

    // Armor
    public static final RegistryObject<ArmorItem> INFINITUM_HELMET = ITEMS.register(
            "infinitum_helmet",
            () -> new ArmorItem(ModArmorMaterial.INFINITUM, EquipmentSlotType.HEAD, new Item.Properties().group(InfinitumMod.INFINITUM_TAB).isImmuneToFire()));
    public static final RegistryObject<ArmorItem> INFINITUM_CHESTPLATE = ITEMS.register("" +
                    "infinitum_chestplate",
            () -> new ArmorItem(ModArmorMaterial.INFINITUM, EquipmentSlotType.CHEST, new Item.Properties().group(InfinitumMod.INFINITUM_TAB).isImmuneToFire()));
    public static final RegistryObject<ArmorItem> INFINITUM_LEGGINGS = ITEMS.register(
            "infinitum_leggings",
            () -> new ArmorItem(ModArmorMaterial.INFINITUM, EquipmentSlotType.LEGS, new Item.Properties().group(InfinitumMod.INFINITUM_TAB).isImmuneToFire()));
    public static final RegistryObject<ArmorItem> INFINITUM_BOOTS = ITEMS.register(
            "infinitum_boots",
            () -> new ArmorItem(ModArmorMaterial.INFINITUM, EquipmentSlotType.FEET, new Item.Properties().group(InfinitumMod.INFINITUM_TAB).isImmuneToFire()));

    // Extras
    public static final RegistryObject<BakedPoisonousPotato> BAKED_POISONOUS_POTATO = ITEMS.register(
            "baked_poisonous_potato",
            BakedPoisonousPotato::new);
}
