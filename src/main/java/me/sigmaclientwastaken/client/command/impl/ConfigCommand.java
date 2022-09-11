package me.sigmaclientwastaken.client.command.impl;

import com.google.gson.*;
import me.sigmaclientwastaken.client.Client;
import me.sigmaclientwastaken.client.command.Command;
import me.sigmaclientwastaken.client.command.CommandHandler;
import me.sigmaclientwastaken.client.module.Module;
import net.minecraft.util.Formatting;

import java.io.*;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ConfigCommand extends Command {

    public ConfigCommand() {
        super("Config", ".config <save|load> <config>");
    }

    @Override
    public boolean handle(String[] args) {
        if(args.length != 3)
            return false;

        switch (args[1].toLowerCase()) {
            case "save" -> {
                File saveTo = Paths.get("Client", "config", args[2] + ".json").toFile();
                saveTo.mkdirs();
                JsonObject obj = new JsonObject();
                for (Module module : Client.getInstance().getModuleManager().getModules()) {
                    JsonObject moduleObj = new JsonObject();

                    moduleObj.add("key", new JsonPrimitive(module.getKey()));
                    moduleObj.add("state", new JsonPrimitive(module.isEnabled()));

                    obj.add(module.getName(), moduleObj);
                }
                String jsonStr = new Gson().toJson(obj);
                try {
                    FileWriter writer = new FileWriter(saveTo);
                    writer.write(jsonStr);
                    writer.close();
                } catch (Exception e) {
                    CommandHandler.message(Formatting.RED + "Failed to save config.");
                    e.printStackTrace();
                    return true;
                }
                CommandHandler.message(Formatting.YELLOW + "Saved config.");
                return true;
            }
            case "load" -> {
                File loadFrom = Paths.get("Client", "config", args[2] + ".json").toFile();
                if (!loadFrom.exists()) {
                    CommandHandler.message(Formatting.RED + "Config not found.");
                    return true;
                }
                String jsonStr = "";
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(loadFrom));

                    JsonElement el = new JsonParser().parse(fromLines(reader.lines()));

                    JsonObject obj = el.getAsJsonObject();

                    obj.entrySet().forEach((entry) -> {
                        Module module = Client.getInstance().getModuleManager().getModule(entry.getKey());

                        if (module != null) {
                            module.setKey(entry.getValue().getAsJsonObject().get("key").getAsInt());
                            module.setEnabled(entry.getValue().getAsJsonObject().get("state").getAsBoolean());
                        }
                    });

                    CommandHandler.message(Formatting.YELLOW + "Loaded config.");
                    return true;
                } catch (Exception e) {
                    CommandHandler.message(Formatting.RED + "Failed to load config.");
                    e.printStackTrace();
                    return true;
                }
            }
        }

        return false;
    }

    private String fromLines(Stream<String> lines) {
        StringBuilder sb = new StringBuilder();
        lines.forEach(line -> sb.append(line).append("\n"));
        return sb.toString();
    }

}
