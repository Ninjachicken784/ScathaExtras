package ninjachicken784.scathaextras;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class ScathaExtras implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Register chat listener
        ChatListener.register();

        // Register HUD overlay
        HudRenderCallback.EVENT.register(new HudRenderer());
    }
}
