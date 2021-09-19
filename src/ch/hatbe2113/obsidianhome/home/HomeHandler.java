package ch.hatbe2113.obsidianhome.home;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import ch.hatbe2113.obsidianhome.io.ConfigHandler;

public class HomeHandler {
	
	private ConfigHandler configLoader;
	
	public HomeHandler(ConfigHandler configLoader) {
		this.configLoader = configLoader;
	}
	
	public boolean exists(Player p, String name) {
		String path = "homes." + p.getUniqueId() + "." + name;
		
		if(configLoader.getString(path) == null) {
			return false;
		}
		
		return true;
	}
	
	public Location get(Player p, String name) {
		
		String path = "homes." + p.getUniqueId() + "." + name;

		if(configLoader.getString(path) == null) {
			return null;
		}
		
		World world = Bukkit.getWorld(configLoader.getString(path + ".world"));
		double x = configLoader.getDouble(path + ".x");
		double y = configLoader.getDouble(path + ".y");
		double z = configLoader.getDouble(path + ".z");
		double pitch = configLoader.getDouble(path + ".pitch");
		double yaw = configLoader.getDouble(path + ".yaw");
		
		Location location = new Location(world, x, y, z, (float) yaw, (float) pitch);
		
		return location;
	}
	
	public void delete(Player p, String name) {
		String path = "homes." + p.getUniqueId() + "." + name;
		configLoader.delete(path);
		
		configLoader.save();
	}
	
	public void add(Player p, String name) {
		String world = p.getLocation().getWorld().getName();
		double x = p.getLocation().getX();
		double y = p.getLocation().getY();
		double z = p.getLocation().getZ();
		double pitch = p.getLocation().getPitch();
		double yaw = p.getLocation().getYaw();
		
		String path = "homes." + p.getUniqueId() + "." + name;
		
		configLoader.set(path + ".world", world);
		configLoader.set(path + ".x", x);
		configLoader.set(path + ".y", y);
		configLoader.set(path + ".z", z);
		configLoader.set(path + ".pitch", pitch);
		configLoader.set(path + ".yaw", yaw);
		
		configLoader.save();
	}
	
}
