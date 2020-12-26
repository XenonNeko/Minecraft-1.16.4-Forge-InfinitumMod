package com.mixleylogic.infinitum.init;

import com.mixleylogic.infinitum.InfinitumMod;
import com.mixleylogic.infinitum.blocks.*;
import com.mixleylogic.infinitum.items.EdibleBlockItemBase;
import com.mixleylogic.infinitum.items.FoodEffect;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            InfinitumMod.MOD_ID);

    // Blocks
    public static final RegistryObject<Block> INFINITUM_ORE_BLOCK = BLOCKS.register(
            "infinitum_ore_block",
            InfinitumOreBlock::new);
    public static final RegistryObject<Block> INFINITUM_BLOCK = BLOCKS.register(
            "infinitum_block",
            InfinitumBlock::new);
    public static final RegistryObject<Block> ANCIENT_INFINITUM_BLOCK = BLOCKS.register(
            "ancient_infinitum_block",
            AncientInfinitumBlock::new);

    public static final RegistryObject<Block> STRUCTURE_CONTROLLER_BLOCK = BLOCKS.register(
            "structure_controller_block",
            StructureControllerBlock::new);

    public static final RegistryObject<Block> QUARRY_BLOCK = BLOCKS.register(
            "quarry_block",
            QuarryBlock::new);

    public static final RegistryObject<Block> LAVA_SPONGE_BLOCK = BLOCKS.register(
            "lava_sponge_block",
            LavaSponge::new);
    public static final RegistryObject<Block> MOLTEN_LAVA_SPONGE_BLOCK = BLOCKS.register(
            "molten_lava_sponge_block",
            MoltenLavaSponge::new);

    public static final RegistryObject<Block> POTATO_BLOCK = BLOCKS.register(
            "potato_block",
            PotatoBlock::new);
    public static final RegistryObject<Block> POISONOUS_POTATO_BLOCK = BLOCKS.register(
            "poisonous_potato_block",
            PoisonousPotatoBlock::new);
    public static final RegistryObject<Block> BAKED_POTATO_BLOCK = BLOCKS.register(
            "baked_potato_block",
            BakedPotatoBlock::new);

    // Block Items
    public static final RegistryObject<Item> INFINITUM_ORE_BLOCK_ITEM = ModItems.ITEMS.register(
            "infinitum_ore_block",
            () -> new BlockItemBase(INFINITUM_ORE_BLOCK.get(),
                    InfinitumMod.INFINITUM_TAB));
    public static final RegistryObject<Item> INFINITUM_BLOCK_ITEM = ModItems.ITEMS.register(
            "infinitum_block",
            () -> new BlockItemBase(INFINITUM_BLOCK.get(),
                    InfinitumMod.INFINITUM_TAB));
    public static final RegistryObject<Item> ANCIENT_INFINITUM_BLOCK_ITEM = ModItems.ITEMS.register(
            "ancient_infinitum_block",
            () -> new BlockItemBase(ANCIENT_INFINITUM_BLOCK.get(),
                    InfinitumMod.UNUSED_TAB));

    public static final RegistryObject<Item> STRUCTURE_CONTROLLER_BLOCK_ITEM = ModItems.ITEMS.register(
            "structure_controller_block",
            () -> new BlockItemBase(STRUCTURE_CONTROLLER_BLOCK.get(),
                    InfinitumMod.UNUSED_TAB));

    public static final RegistryObject<Item> QUARRY_BLOCK_ITEM = ModItems.ITEMS.register(
            "quarry_block",
            () -> new BlockItemBase(QUARRY_BLOCK.get(),
                    InfinitumMod.INFINITUM_TAB));

    public static final RegistryObject<Item> LAVA_SPONGE_BLOCK_ITEM = ModItems.ITEMS.register(
            "lava_sponge_block",
            () -> new BlockItemBase(LAVA_SPONGE_BLOCK.get(),
                    InfinitumMod.INFINITUM_TAB));
    public static final RegistryObject<Item> MOLTEN_LAVA_SPONGE_BLOCK_ITEM = ModItems.ITEMS.register(
            "molten_lava_sponge_block",
            () -> new BlockItemBase(MOLTEN_LAVA_SPONGE_BLOCK.get(),
                    InfinitumMod.INFINITUM_TAB));

    // Simply multiplied the hunger and saturation values by nine.
    public static final RegistryObject<Item> POTATO_BLOCK_ITEM = ModItems.ITEMS.register(
            "potato_block",
            () -> new EdibleBlockItemBase(POTATO_BLOCK.get(), 9, 5.4f,
                    new FoodEffect[0]));
    public static final RegistryObject<Item> POISONOUS_POTATO_BLOCK_ITEM = ModItems.ITEMS.register(
            "poisonous_potato_block",
            () -> new EdibleBlockItemBase(POISONOUS_POTATO_BLOCK.get(), 18, 10.8f,
                    new FoodEffect[]{
                            new FoodEffect(Effects.INSTANT_DAMAGE,5, 1, 0.6f)
                    }));
    public static final RegistryObject<Item> BAKED_POTATO_BLOCK_ITEM = ModItems.ITEMS.register(
            "baked_potato_block",
            () -> new EdibleBlockItemBase(BAKED_POTATO_BLOCK.get(), 45, 54.0f,
                    new FoodEffect[0]));
}
