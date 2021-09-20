package ch.hatbe2113.obsidianhome.home;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import ch.hatbe2113.obsidianhome.io.CustomConfigHandler;

public class HomeHandler {
	
	private CustomConfigHandler configHandler;
	
	public HomeHandler(CustomConfigHandler configHandler) {
		this.configHandler = configHandler;
	}
	
	public boolean exists(UUID uuid, String name) {
		String path = "homes." + uuid + "." + name;
		
		if(configHandler.getString(path) == null) {
			return false;
		}
		
		return true;
	}
	
	public boolean playerHasHomes(UUID uuid) {

		if(configHandler.get("homes." + uuid) == null) {
			return false;
		}
		
		return true;
	}
	
	public Location get(UUID uuid, String name) {
		
		String path = "homes." + uuid + "." + name;

		if(configHandler.getString(path) == null) {
			return null;
		}
		
		World world = Bukkit.getWorld(configHandler.getString(path + ".world"));
		double x = configHandler.getDouble(path + ".x");
		double y = configHandler.getDouble(path + ".y");
		double z = configHandler.getDouble(path + ".z");
		double pitch = configHandler.getDouble(path + ".pitch");
		double yaw = configHandler.getDouble(path + ".yaw");
		
		Location location = new Location(world, x, y, z, (float) yaw, (float) pitch);
		
		return location;
	}
	
	public void delete(UUID uuid, String name) {
		String path = "homes." + uuid + "." + name;
		configHandler.delete(path);
		
		configHandler.save();
	}
	
	public void add(Player p, String name) {
		String world = p.getLocation().getWorld().getName();
		double x = p.getLocation().getX();
		double y = p.getLocation().getY();
		double z = p.getLocation().getZ();
		double pitch = p.getLocation().getPitch();
		double yaw = p.getLocation().getYaw();
		
		String path = "homes." + p.getUniqueId() + "." + name;
		
		configHandler.set(path + ".world", world);
		configHandler.set(path + ".x", x);
		configHandler.set(path + ".y", y);
		configHandler.set(path + ".z", z);
		configHandler.set(path + ".pitch", pitch);
		configHandler.set(path + ".yaw", yaw);
		
		configHandler.save();
	}
	
	public Object[] getHomeList(UUID uuid) {
		Object[] fields = configHandler.getConfig().getConfigurationSection("homes." + uuid).getKeys(false).toArray();
		return fields;
	}
	
}
