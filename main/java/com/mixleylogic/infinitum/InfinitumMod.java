package com.mixleylogic.infinitum;

import com.mixleylogic.infinitum.entities.HookshotEntity;
import com.mixleylogic.infinitum.init.ModBlocks;
import com.mixleylogic.infinitum.init.ModEntityTypes;
import com.mixleylogic.infinitum.init.ModItems;
import com.mixleylogic.infinitum.init.ModTileEntityTypes;
import com.mixleylogic.infinitum.world.gen.ModOreGen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("mxl_inf")
public class InfinitumMod
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "mxl_inf";

    private static final Minecraft minecraft = Minecraft.getInstance();

    private static SoundEventController soundEventController;

    public InfinitumMod() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);

        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);

        soundEventController = new SoundEventController();
        HookshotEntity.addHookshotEventListener(soundEventController);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ModOreGen.registerOres();

        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntityTypes.HOOKSHOT.get(),
                    HookshotEntity.setCustomAttributes().create());
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }

    public static final ItemGroup INFINITUM_TAB = new ItemGroup("infinitumMod") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.INFINITUM_INGOT.get());
        }
    };


}
