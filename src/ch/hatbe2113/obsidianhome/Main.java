package ch.hatbe2113.obsidianhome;

import org.bukkit.plugin.java.JavaPlugin;

import ch.hatbe2113.obsidianhome.commands.DelHomeCommand;
import ch.hatbe2113.obsidianhome.commands.HomeCommand;
import ch.hatbe2113.obsidianhome.commands.SetHomeCommand;
import io.ConfigHandler;
import io.TextOutput;

public class Main extends JavaPlugin {
	
	private ConfigHandler configLoader;

	@Override
	public void onEnable() {
		TextOutput.outputToConsole("Starting");
		
		configLoader = new ConfigHandler(this);
		//configLoader.setDefaults();
		
		this.getCommand("home").setExecutor(new HomeCommand(configLoader));
		this.getCommand("setHome").setExecutor(new SetHomeCommand(configLoader));
		this.getCommand("delHome").setExecutor(new DelHomeCommand(configLoader));
	}
	
	@Override
	public void onDisable() {
		TextOutput.outputToConsole("Stopping");
	}
	
}
