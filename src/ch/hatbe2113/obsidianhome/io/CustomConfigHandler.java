package ch.hatbe2113.obsidianhome.io;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import ch.hatbe2113.obsidianhome.Main;

public class CustomConfigHandler {

	private Main main;
	private FileConfiguration config;
	File configFile;
	
	public CustomConfigHandler(Main main, String name) {
		this.main = main;
		this.configFile = new File(main.getDataFolder(), name);
		this.config = YamlConfiguration.loadConfiguration(configFile);
		
		try {
			config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setDefaults(String path, Object obj) {
		config.addDefault(path, obj);
		config.options().copyDefaults(true);
		main.saveConfig();
	}
	
	public FileConfiguration getConfig() {
		return this.config;
	}
    
	public void save() {
		try {
			this.config.save(this.configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void set(String path, Object obj) {
		config.set(path, obj);
	}
	
	public void delete(String path) {
		config.set(path, null);
	}
	
	public Object get(String path) {
		Object o = config.get(path);
		return o;
	}
	
	public String getString(String path) {
		String s = config.getString(path);
		return s;
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
