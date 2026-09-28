package com.baliung.mod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import net.minecraft.text.Text;
public class BaliungSpinMod implements ClientModInitializer {
    public static KeyBinding toggleKey;
    public static boolean spinning = false;
    public static float spinAngle = 0;
    public static int spinTicks = 0;
    @Override
    public void onInitializeClient() {
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.baliung.spin", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F9, "category.baliung"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.wasPressed()) {
                spinning = !spinning; spinTicks = 0; spinAngle = 0;
                if (client.player != null) client.player.sendMessage(Text.literal(spinning ? "BALIUNG ON!" : "BALIUNG OFF"), true);
            }
            if (spinning) {
                spinTicks++; spinAngle += 25f;
                if (spinAngle > 720f) spinAngle -= 720f;
                if (spinTicks > 60) { spinning = false; spinTicks = 0; spinAngle = 0; }
            }
        });
    }
}
