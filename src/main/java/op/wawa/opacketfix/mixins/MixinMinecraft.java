package op.wawa.opacketfix.mixins;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.main.GameConfiguration;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import op.wawa.opacketfix.OPacketFix;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.PixelFormat;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Skid or Made By WaWa
 *
 * @author WaWa
 * @date 2023/7/31 16:37
 */

@Mixin(value={Minecraft.class})
public class MixinMinecraft {
    @Shadow
    @Final
    private static Logger logger;

    @Shadow
    private boolean fullscreen;

    @Shadow
    private int leftClickCounter;

    @Shadow
    public EntityPlayerSP thePlayer;

    @Shadow
    public MovingObjectPosition objectMouseOver;

    @Shadow
    public PlayerControllerMP playerController;

    @Shadow
    public WorldClient theWorld;

    @Shadow
    private void updateDisplayMode() {}



    @Overwrite
    private void createDisplay() throws LWJGLException {
        Display.setResizable(true);
        Display.setTitle("Minecraft 1.8.9 - "+ OPacketFix.NAME+" v"+OPacketFix.VERSION+" By WaWa");
        try {
            Display.create((new PixelFormat()).withDepthBits(24));
        } catch (LWJGLException lwjglexception) {
            logger.error("Couldn't set pixel format", lwjglexception);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                logger.error("Error, InterruptedException");
            }
            if (this.fullscreen) {
                this.updateDisplayMode();
            }
            Display.create();
        }
    }
    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;checkGLError(Ljava/lang/String;)V", ordinal = 2, shift = At.Shift.AFTER))
    private void startGame(CallbackInfo callbackInfo) {
        OPacketFix.startGame();
    }
}
