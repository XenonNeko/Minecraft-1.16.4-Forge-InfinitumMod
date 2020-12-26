package com.mixleylogic.infinitum.init;

import com.mixleylogic.infinitum.InfinitumMod;
import com.mixleylogic.infinitum.entities.HookshotEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(
            ForgeRegistries.ENTITIES,
            InfinitumMod.MOD_ID);

    public static final RegistryObject<EntityType<HookshotEntity>> HOOKSHOT = ENTITY_TYPES.register(
            "hookshot",
            () -> EntityType.Builder.create(HookshotEntity::new, EntityClassification.MISC)
                    .size(0.2f, 0.2f)
                    .build(new ResourceLocation(InfinitumMod.MOD_ID, "hookshot").toString())
    );
}
