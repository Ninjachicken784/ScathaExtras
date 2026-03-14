package ninjachicken784.scathaextras;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class ScathaExtras implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ChatListener.register();
        HudRenderCallback.EVENT.register(new HudRenderer());
    }
}
