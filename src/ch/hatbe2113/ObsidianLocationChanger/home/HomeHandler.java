package ch.hatbe2113.ObsidianLocationChanger.home;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import ch.hatbe2113.ObsidianLocationChanger.io.CustomConfigHandler;
import ch.hatbe2113.ObsidianLocationChanger.io.TextOutput;

public class HomeHandler {
	private CustomConfigHandler config;
	
	public HomeHandler(CustomConfigHandler config) {
		this.config = config;
	}
	
	public Location get(Player p, String name) {
		String path = "homes." + p.getUniqueId() + "." + name + ".";
		
		World world = Bukkit.getWorld(config.getString(path + "world"));
		double x = config.getDouble(path + "x");
		double y = config.getDouble(path + "y");
		double z = config.getDouble(path + "z");
		double pitch = config.getDouble(path + "pitch");
		double yaw = config.getDouble(path + "yaw");
		
		Location loc = new Location(world, x, y, z, (float) yaw, (float) pitch);
		
		return loc;
	}
	
	public void add(Player p, String name) {
		Location loc = p.getLocation();
		String world = loc.getWorld().getName();
		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();
		double pitch= loc.getPitch();
		double yaw = loc.getYaw();
		
		String path = "homes." + p.getUniqueId() + "." + name + ".";
		
		config.set(path + "world", world);
		config.set(path + "x", x);
		config.set(path + "y", y);
		config.set(path + "z", z);
		config.set(path + "pitch", pitch);
		config.set(path + "yaw", yaw);
		
		config.save();
	}
	
	public void delete(Player p, String name) {
		String path = "homes." + p.getUniqueId() + "." + name;
		
		if(count(p) <= 1) {
			config.delete("homes." + p.getUniqueId());
		} else {
			config.delete(path);	
		}
		
		config.save();
	}
	
	public boolean exists(Player p, String name) {
		String path = "homes." + p.getUniqueId() + "." + name;
		
		if(config.getString(path) == null) {
			return false;
		}
		
		return true;
	}
	
	public boolean hasPlayerHomes(Player p) {
		String path = "homes." + p.getUniqueId();
		
		if(config.get(path) == null || config.get(path) == "") {
			return false;
		}
		
		return true;
	}
	
	public Object[] getHomes(Player p) {
		String path = "homes." + p.getUniqueId();
		
		if(!hasPlayerHomes(p)) {
			return null;
		}
		
		return config.getConfig().getConfigurationSection(path).getKeys(false).toArray();
	}
	
	public int count(Player p) {
		if(!hasPlayerHomes(p)) {
			return 0;
		}
		
		return getHomes(p).length;
	}
	
	public void displayList(Player p) {
		if(!hasPlayerHomes(p)) {
			TextOutput.outputToPlayer(p, "You don't have any homes right now!");
			return;
		}
		
		Object[] homes = getHomes(p);
		
		String homesStr = "";
		for(int i = 0; i < homes.length; i++) {
			homesStr += homes[i] + (i < homes.length - 1 ? ", ":"");
		}
		
		TextOutput.outputToPlayer(p, "Homes: " + homesStr);
	}
}
