package com.ryr15.scathaextras;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;

public class ScathaTracker implements ModInitializer {
    private final List<Long> kills = new ArrayList<>();

    @Override
    public void onInitialize() {
        ClientReceiveMessageEvents.CHAT.register((message, signed, sender, params, time) -> {
            if (message.getString().contains("You hear the sound of something approaching...")) {
                kills.add(System.currentTimeMillis());
            }
        });

        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.textRenderer == null) return;

            long hourAgo = System.currentTimeMillis() - 3600000;
            kills.removeIf(t -> t < hourAgo);

            drawContext.drawText(client.textRenderer, "Worms/H: " + kills.size(), 10, 10, 0xFFAA00, true);
        });
    }
}
