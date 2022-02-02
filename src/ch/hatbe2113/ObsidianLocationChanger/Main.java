package ch.hatbe2113.ObsidianLocationChanger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ch.hatbe2113.ObsidianLocationChanger.commands.DelHomeCommand;
import ch.hatbe2113.ObsidianLocationChanger.commands.HomeCommand;
import ch.hatbe2113.ObsidianLocationChanger.commands.HomesCommand;
import ch.hatbe2113.ObsidianLocationChanger.commands.SetHomeCommand;
import ch.hatbe2113.ObsidianLocationChanger.home.HomeHandler;
import ch.hatbe2113.ObsidianLocationChanger.io.CustomConfigHandler;
import ch.hatbe2113.ObsidianLocationChanger.io.TextOutput;

public class Main extends JavaPlugin {
	
	// 01.02.2022 HATBE2113
	
	private PluginManager plmanager;
	private HomeHandler home;
	private CustomConfigHandler homeConfig;
	
	public final String NAME_REGEX = "^[A-Za-z0-9_-]{1,16}$";
	public final String PERM_PREFIX = "obsidian.lc.";
	
	public void onEnable() {
		enableMessage();
		
		plmanager = Bukkit.getPluginManager();
		homeConfig = new CustomConfigHandler(this, "homes");
		home = new HomeHandler(homeConfig);
		
		registerCommands();
		registerEvents();
	}
	
	public void onDisable() {
		disableMessage();
	}
	
	public void registerEvents() {
		
	}
	
	public void registerCommands() {
		getCommand("home").setExecutor(new HomeCommand(this));
		getCommand("homes").setExecutor(new HomesCommand(this));
		getCommand("sethome").setExecutor(new SetHomeCommand(this));
		getCommand("delhome").setExecutor(new DelHomeCommand(this));
	}
	
	public void enableMessage() {
		TextOutput.outputToConsole("", false);
		TextOutput.outputToConsole("---------------------------------", false);
		TextOutput.outputToConsole("", false);
		TextOutput.outputToConsole("Startup", true);
		TextOutput.outputToConsole("", false);
		TextOutput.outputToConsole("---------------------------------", false);
		TextOutput.outputToConsole("", false);
	}
	
	public void disableMessage() {
		TextOutput.outputToConsole("", false);
		TextOutput.outputToConsole("---------------------------------", false);
		TextOutput.outputToConsole("", false);
		TextOutput.outputToConsole("Shutdown", true);
		TextOutput.outputToConsole("", false);
		TextOutput.outputToConsole("---------------------------------", false);
		TextOutput.outputToConsole("", false);
	}
	
	public HomeHandler getHome() {
		return home;
	}
}
