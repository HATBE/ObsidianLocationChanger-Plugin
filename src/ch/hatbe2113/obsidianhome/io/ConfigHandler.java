package ch.hatbe2113.obsidianhome.io;

import org.bukkit.configuration.file.FileConfiguration;

import ch.hatbe2113.obsidianhome.Main;

public class ConfigHandler {
	
	private Main main;
	private FileConfiguration config;
	
	public ConfigHandler(Main main) {
		this.main = main;
		this.config = main.getConfig();
	}
	
	public void setDefaults(String path, Object obj) {
		config.addDefault(path, obj);
		config.options().copyDefaults(true);
		main.saveConfig();
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
	
	public void delete(String path) {
		config.set(path, null);
	}
	
	public String getString(String path) {
		String str = config.getString(path);
		return str;
	}
	
	public Double getDouble(String path) {
		double d = config.getDouble(path);
		return d;
	}
	
	public int getInt(String path) {
		int i = config.getInt(path);
		return i;
	}
	
}
