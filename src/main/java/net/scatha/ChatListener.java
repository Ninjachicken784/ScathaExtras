package ninjachicken784.scathaextras;

import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.text.Text;

public class ChatListener {

    public static void register() {
        ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
            if (message == null) return;

            String text = message.getString();
            // Hypixel SkyBlock worm/scatha spawn warning (exact or partial match)
            if (text.contains("You hear the sound of something approaching...")) {
                ScathaTracker.addDetection();
            }
        });
    }
}
