package ch.hatbe2113.ObsidianLocationChanger.io;

import org.bukkit.configuration.file.FileConfiguration;

import ch.hatbe2113.ObsidianLocationChanger.Main;

public class ConfigHandler {
	private Main main;
	protected FileConfiguration config;
	
	public ConfigHandler(Main main) {
		this.main = main;
		config = main.getConfig();
	}
	
	public void addDefault(String path, Object obj) {
		config.addDefault(path, obj);
		config.options().copyDefaults(true);
		
		save();
	}
		
	public FileConfiguration getConfig() {
		return config;
	}
	
	public void save() {
		main.saveConfig();
	}
	
	public void set(String path, Object obj) {
		config.set(path, obj);
	}
	
	public Object get(String path) {
		return config.get(path);
	}
	
	public void delete(String path) {
		config.set(path, null);
	}
	
	public String getString(String path) {
		return config.getString(path);
	}
	
	public Double getDouble(String path) {
		return config.getDouble(path);
	}
	
	public int getInt(String path) {
		return config.getInt(path);
	}		
}
