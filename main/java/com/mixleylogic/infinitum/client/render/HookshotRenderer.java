package com.mixleylogic.infinitum.client.render;

import com.mixleylogic.infinitum.InfinitumMod;
import com.mixleylogic.infinitum.client.model.HookshotModel;
import com.mixleylogic.infinitum.entities.HookshotEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class HookshotRenderer extends MobRenderer<HookshotEntity, HookshotModel<HookshotEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(
            InfinitumMod.MOD_ID,
            "textures/entity/hookshot.png"
    );

    public HookshotRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HookshotModel<>(), 0.2f);
    }

    @Override
    public ResourceLocation getEntityTexture(HookshotEntity entity) {
        return TEXTURE;
    }
}
