package ch.hatbe2113.obsidianhome;

import org.bukkit.plugin.java.JavaPlugin;

import ch.hatbe2113.obsidianhome.commands.DelHomeCommand;
import ch.hatbe2113.obsidianhome.commands.HomeCommand;
import ch.hatbe2113.obsidianhome.commands.SetHomeCommand;
import ch.hatbe2113.obsidianhome.io.ConfigHandler;
import ch.hatbe2113.obsidianhome.io.CustomConfigHandler;
import ch.hatbe2113.obsidianhome.io.TextOutput;

public class Main extends JavaPlugin {
	
	private ConfigHandler configHandler;
	private CustomConfigHandler homesConfig;

	@Override
	public void onEnable() {
		TextOutput.outputToConsole("Starting");
		
		configHandler = new ConfigHandler(this);
		homesConfig = new CustomConfigHandler(this, "homes.yaml");
		
		//configLoader.setDefaults(key, value);
		//homesConfig.setDefaults(key, value);
		
		this.getCommand("home").setExecutor(new HomeCommand(homesConfig));
		this.getCommand("setHome").setExecutor(new SetHomeCommand(homesConfig));
		this.getCommand("delHome").setExecutor(new DelHomeCommand(homesConfig));
	}
	
	@Override
	public void onDisable() {
		TextOutput.outputToConsole("Stopping");
	}
	
}
