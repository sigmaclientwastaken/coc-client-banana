package me.sigmaclientwastaken.client.command;

import com.google.common.eventbus.Subscribe;
import me.sigmaclientwastaken.client.Client;
import me.sigmaclientwastaken.client.command.impl.BindCommand;
import me.sigmaclientwastaken.client.command.impl.ConfigCommand;
import me.sigmaclientwastaken.client.event.impl.ChatEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.MessageType;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {

    private final List<Command> commands = new ArrayList<>();

    public CommandHandler() {

        // add commands here
        commands.add(new BindCommand());
        commands.add(new ConfigCommand());

        Client.getInstance().getEventBus().register(this);
    }

    @Subscribe
    public void onChat(ChatEvent e) {
        if(e.getMessage().startsWith(".")) {
            e.setCancelled(true);

            String cmdString = e.getMessage().substring(1).split(" ")[0];

            Command cmd = commands.stream().filter(c -> c.getName().equalsIgnoreCase(cmdString)).findFirst().orElse(null);

            System.out.println("cmd " + cmdString);

            if(cmd == null) {
                message(Formatting.RED + "Invalid command.");
            } else {
                if(!cmd.handle(e.getMessage().substring(1).split(" "))) {
                    message(Formatting.RED + "Invalid Syntax: " + cmd.getSyntax());
                }
            }
        }
    }

    public static void message(String message) {
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText(message), MinecraftClient.getInstance().player.getUuid());
    }

}
