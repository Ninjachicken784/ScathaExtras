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
        if (client.player == null || client.world == null) {
            return;
        }

        // Optional: only show in Crystal Hollows (uncomment if wanted)
        // String dim = client.world.getRegistryKey().getValue().toString();
        // if (!dim.contains("crystal_hollows")) return;

        TextRenderer textRenderer = client.textRenderer;

        int x = 4;     // left padding
        int y = 4;     // top padding
        int color = 0xFFFFFF;   // white
        int shadowColor = 0x000000; // for shadow

        // Main line: Worms/h count
        String countText = "Worms/h: " + ScathaTracker.count;
        context.drawTextWithShadow(textRenderer, Text.literal(countText), x, y, color);
        y += 11;

        // Timer: time running
        String timerText = "Time: " + ScathaTracker.getTimeRunning();
        context.drawTextWithShadow(textRenderer, Text.literal(timerText), x, y, color);
        y += 11;

        // Time since last
        String lastText = "Last: " + ScathaTracker.getTimeSinceLast();
        context.drawTextWithShadow(textRenderer, Text.literal(lastText), x, y, 0xAAAAAA); // gray
    }
}
