package ninjachicken784.scathaextras;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class HudRenderer implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;

        TextRenderer renderer = client.textRenderer;
        int x = 4, y = 4, color = 0xFFFFFF;

        context.drawTextWithShadow(renderer, Text.literal("Worms/h: " + ScathaTracker.count), x, y, color);
        y += 11;
        context.drawTextWithShadow(renderer, Text.literal("Time: " + ScathaTracker.getTimeRunning()), x, y, color);
        y += 11;
        context.drawTextWithShadow(renderer, Text.literal("Last: " + ScathaTracker.getTimeSinceLast()), x, y, 0xAAAAAA);
    }
}
