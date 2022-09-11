package me.sigmaclientwastaken.client;

import com.google.common.eventbus.EventBus;
import me.sigmaclientwastaken.client.command.CommandHandler;
import me.sigmaclientwastaken.client.module.ModuleManager;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client implements ModInitializer {

	private static Client instance;

	private final String clientName = "Client";
	private final long clientBuild = 220911L;

	private final Logger logger = LoggerFactory.getLogger("client");
	private final EventBus eventBus = new EventBus();
	private ModuleManager moduleManager;
	private CommandHandler commandHandler;

	public Client() {
		instance = this;
	}

	@Override
	public void onInitialize() {

		logger.info("Starting {}, build {}.", clientName, clientBuild);

		moduleManager = new ModuleManager();
		commandHandler = new CommandHandler();

	}

	public String getClientName() {
		return clientName;
	}

	public long getClientBuild() {
		return clientBuild;
	}

	public Logger getLogger() {
		return logger;
	}

	public EventBus getEventBus() {
		return eventBus;
	}

	public static Client getInstance() {
		return instance;
	}

	public ModuleManager getModuleManager() {
		return moduleManager;
	}

}
