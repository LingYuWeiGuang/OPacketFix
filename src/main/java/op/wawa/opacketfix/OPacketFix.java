package op.wawa.opacketfix;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import op.wawa.opacketfix.features.hytpacket.PacketManager;
import org.lwjgl.opengl.Display;

import java.io.File;
import java.util.Objects;

@Mod(modid = OPacketFix.MOD_ID, name = OPacketFix.NAME, version = OPacketFix.VERSION)
public class OPacketFix
{
    // Mod info
    public static final String MOD_ID = "opacketfix";
    public static final String NAME = "OPacketFix";
    public static final String VERSION = "0.2";


    public static void startGame(){// when Minecraft start end
        new PacketManager().init();
        String title = "Minecraft 1.8.9 - "+ OPacketFix.NAME+" v"+OPacketFix.VERSION+" By WaWa";
        if (!Objects.equals(Display.getTitle(), title))
            Display.setTitle(title);
    }
}
