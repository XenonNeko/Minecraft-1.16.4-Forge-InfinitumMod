package com.mixleylogic.infinitum.init;

import com.mixleylogic.infinitum.InfinitumMod;
import com.mixleylogic.infinitum.tileentity.QuarryTileEntity;
import com.mixleylogic.infinitum.tileentity.StructureControllerTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(
            ForgeRegistries.TILE_ENTITIES,
            InfinitumMod.MOD_ID);

    public static final RegistryObject<TileEntityType<StructureControllerTileEntity>> STRUCTURE_CONTROLLER = TILE_ENTITY_TYPES.register(
            "structure_controller",
            () -> TileEntityType.Builder.create(
                    StructureControllerTileEntity::new,
                    ModBlocks.STRUCTURE_CONTROLLER_BLOCK.get()
            ).build(null)
    );

    public static final RegistryObject<TileEntityType<QuarryTileEntity>> QUARRY = TILE_ENTITY_TYPES.register(
            "quarry",
            () -> TileEntityType.Builder.create(
                    QuarryTileEntity::new,
                    ModBlocks.QUARRY_BLOCK.get()
            ).build(null)
    );
}
