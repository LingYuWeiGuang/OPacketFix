/*
 * LiquidBounce++ Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/PlusPlusMC/LiquidBouncePlusPlus/
 */
package op.wawa.opacketfix.mixins;

import de.florianmichael.viaforge.ViaForge;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockLadder.class)
public abstract class MixinBlockLadder extends MixinBlock {

    @Shadow
    @Final
    public static PropertyDirection FACING;

    /**
     * /@author CCBlueX
     */
    @Overwrite
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
        final IBlockState iblockstate = worldIn.getBlockState(pos);

        if(iblockstate.getBlock() instanceof BlockLadder) {
            final float f = ViaForge.targetVersion.getVersion() <= ViaForge.NATIVE_VERSION.getVersion() ? 0.125f : 0.1875f;
            switch(iblockstate.getValue(FACING)) {
                case NORTH:
                    this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
                    break;
                case SOUTH:
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
                    break;
                case WEST:
                    this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                    break;
                case EAST:
                default:
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
            }
        }
    }
    
}
