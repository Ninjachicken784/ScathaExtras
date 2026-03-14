package net.scatha;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft; // Name changed from MinecraftClient
import java.util.ArrayList;
import java.util.List;

public class ScathaTracker implements ClientModInitializer {
    private static final List<Long> kills = new ArrayList<>();

    @Override
    public void onInitializeClient() {
        ClientReceiveMessageEvents.CHAT.register((message, signed, sender, params, time) -> {
            if (message.getString().contains("You hear the sound of something approaching...")) {
                kills.add(System.currentTimeMillis());
            }
        });

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            Minecraft client = Minecraft.getInstance();
            if (client.font == null) return; // Name changed from textRenderer

            long hourAgo = System.currentTimeMillis() - 3600000;
            kills.removeIf(t -> t < hourAgo);
            
            // Fixed for Mojang Mappings 1.21.10
            drawContext.drawText(client.font, "Worms/H: " + kills.size(), 10, 10, 0xFFAA00, true);
        });
    }
}
