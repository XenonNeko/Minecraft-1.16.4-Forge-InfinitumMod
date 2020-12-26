package com.mixleylogic.infinitum.client.model;

import com.mixleylogic.infinitum.entities.HookshotEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class HookshotModel<T extends HookshotEntity> extends EntityModel<T> {
    private final ModelRenderer hook;
    private final ModelRenderer stickY_r1;
    private final ModelRenderer left_r1;
    private final ModelRenderer right_r1;

    public HookshotModel() {
        textureWidth = 8;
        textureHeight = 8;

        hook = new ModelRenderer(this);
        hook.setRotationPoint(0.0F, 22.0F, 0.0F);
        hook.setTextureOffset(0, 4).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 0.0F, 2.0F, 0.0F, false);

        stickY_r1 = new ModelRenderer(this);
        stickY_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        hook.addChild(stickY_r1);
        setRotationAngle(stickY_r1, 0.0F, 0.0F, 1.5708F);
        stickY_r1.setTextureOffset(0, 4).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 0.0F, 2.0F, 0.0F, false);

        left_r1 = new ModelRenderer(this);
        left_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        hook.addChild(left_r1);
        setRotationAngle(left_r1, 0.0F, 0.7854F, 0.0F);
        left_r1.setTextureOffset(0, 2).addBox(0.0F, -0.5F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        right_r1 = new ModelRenderer(this);
        right_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        hook.addChild(right_r1);
        setRotationAngle(right_r1, 0.0F, -0.7854F, 0.0F);
        right_r1.setTextureOffset(0, 0).addBox(-2.0F, -0.5F, 0.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.hook.rotateAngleX = (headPitch - 180.0f) * ((float)Math.PI / 180F);
        this.hook.rotateAngleY = (netHeadYaw - 180.0f) * ((float)Math.PI / 180F);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        hook.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
        //InfinitumMod.LOGGER.info("setRotationAngle(?, " + x + ", " + y + ", " + z + ")");
    }
}
