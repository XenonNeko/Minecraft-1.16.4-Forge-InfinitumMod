package com.mixleylogic.infinitum.world.gen;

import com.mixleylogic.infinitum.InfinitumMod;
import com.mixleylogic.infinitum.init.ModBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

/**
 * Ore generation
 * @author TechOFreak
 * Add OreGenerationExample.registerOres(); to your main class's setup method
 */

@Mod.EventBusSubscriber
public class ModOreGen {

    private static final ArrayList<ConfiguredFeature<?, ?>> overworldOres = new ArrayList<ConfiguredFeature<?, ?>>();
    private static final ArrayList<ConfiguredFeature<?, ?>> netherOres = new ArrayList<ConfiguredFeature<?, ?>>();
    private static final ArrayList<ConfiguredFeature<?, ?>> endOres = new ArrayList<ConfiguredFeature<?, ?>>();

    public static void registerOres(){
        //field_241882_a is for generating in stone, granite, diorite, and andesite
        //field_241883_b is for generating in netherrack
        //field_241884_c is for generating in netherrack, basalt and blackstone

        //Overworld Ore Register
        overworldOres.add(register("infinitum_ore_overworld", Feature.ORE.withConfiguration(
                new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                ModBlocks.INFINITUM_ORE_BLOCK.get().getDefaultState(), 3)) //Vein Size
                .range(64).square() //Spawn height start
                .func_242731_b(6))); //Chawn Spunk frequency

        //Nether Ore Register
        //netherOres.add(register("example_nether_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(
        //        OreFeatureConfig.FillerBlockType.NETHERRACK, RegistryHandler.INFINITUM_ORE_BLOCK.get().getDefaultState(), 4)) //Vein Size
        //        .range(64).square() //Spawn height start
        //        .func_242731_b(64))); //Chunk spawn frequency

        //The End Ore Register
        //endOres.add(register("example_end_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(
        //        new BlockMatchRuleTest(Blocks.END_STONE), RegistryHandler.INFINITUM_ORE_BLOCK.get().getDefaultState(), 4)) //Vein Size
        //        .range(128).square() //Spawn height start
        //        .func_242731_b(64))); //Chunk spawn frequency
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void gen(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        if(event.getCategory().equals(Biome.Category.NETHER)){
            for(ConfiguredFeature<?, ?> ore : netherOres){
                if (ore != null) generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
            }
        }
        if(event.getCategory().equals(Biome.Category.THEEND)){
            for(ConfiguredFeature<?, ?> ore : endOres){
                if (ore != null) generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
            }
        }
        if(event.getCategory().equals(Biome.Category.JUNGLE)) {
            for (ConfiguredFeature<?, ?> ore : overworldOres) {
                if (ore != null) generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
            }
        }
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, InfinitumMod.MOD_ID + ":" + name, configuredFeature);
    }

}