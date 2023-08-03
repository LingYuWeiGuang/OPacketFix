/*
 * LiquidBounce+ Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/WYSI-Foundation/LiquidBouncePlus/
 */
package op.wawa.opacketfix.mixins;

import de.florianmichael.viaforge.ViaForge;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends MixinEntity {

    @ModifyConstant(method = "onLivingUpdate", constant = @Constant(doubleValue = 0.005D))
    private double refactor1_9MovementThreshold(double constant) {
        if (ViaForge.targetVersion.getVersion() <= ViaForge.NATIVE_VERSION.getVersion())
            return 0.005D;
        return 0.003D;
    }
}
