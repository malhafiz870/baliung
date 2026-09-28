package com.baliung.mod.mixin;
import com.baliung.mod.BaliungSpinMod;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {
    @Inject(method = "renderFirstPersonItem", at = @At("HEAD"))
    private void baliung$spin(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (BaliungSpinMod.spinning && hand == Hand.MAIN_HAND && !item.isEmpty()) {
            matrices.translate(0, 0, 0.2f);
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(BaliungSpinMod.spinAngle));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(BaliungSpinMod.spinAngle * 0.5f));
        }
    }
}
