package net.scatha;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import java.util.ArrayList;
import java.util.List;

public class ScathaTracker implements ClientModInitializer {
    private static final List<Long> kills = new ArrayList<>();

    @Override
    public void onInitializeClient() {
        // Detects the chat message
        ClientReceiveMessageEvents.CHAT.register((message, signed, sender, params, time) -> {
            if (message.getString().contains("You hear the sound of something approaching...")) {
                kills.add(System.currentTimeMillis());
            }
        });

        // Displays the Kills/H on your screen
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.textRenderer == null) return;

            long hourAgo = System.currentTimeMillis() - 3600000;
            kills.removeIf(t -> t < hourAgo);
            
            // Fixed for 1.21.1: Removed getMatrices() and added textRenderer
            drawContext.drawText(client.textRenderer, "Worms/H: " + kills.size(), 10, 10, 0xFFAA00, true);
        });
    }
}
