package ch.hatbe2113.obsidianhome.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import ch.hatbe2113.obsidianhome.home.HomeHandler;
import ch.hatbe2113.obsidianhome.io.CustomConfigHandler;
import ch.hatbe2113.obsidianhome.io.TextOutput;

public class HomeCommand implements CommandExecutor, TabCompleter {
	
	private CustomConfigHandler configHandler;
	private HomeHandler home;
	
	public HomeCommand(CustomConfigHandler configHandler) {
		this.configHandler = configHandler;
		this.home = new HomeHandler(this.configHandler);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			TextOutput.outputToCommandSender(sender, "You have to be a Player, to execute this command.");
			return false;
		}
		
		Player p = (Player) sender;
		
		if(!p.hasPermission("obsidian.home.use")) {
			TextOutput.outputToPlayer(p, "You don't have the permission to execute this command.");
			return false;
		}
		
		if(args.length == 0) {
			
			if(configHandler.getConfig().get("homes." + p.getUniqueId()) == null) {
				TextOutput.outputToPlayer(p, "You don't have any Homes right now.");
				return false;
			}
			
			Object[] fields = configHandler.getConfig().getConfigurationSection("homes." + p.getUniqueId()).getKeys(false).toArray();

			if(fields.length == 0) {
				TextOutput.outputToPlayer(p, "You don't have any Homes right now.");
				return false;
			}
			
			String homes = "";
			int c = 1;
			
			TextOutput.outputToPlayer(p, "Your Homes");
			
			for (Object key : fields){
				homes += key;
				if(c != fields.length) {
					homes += ", ";
				}
				c++;
			}
			
			TextOutput.outputToPlayer(p, homes, false);
			
		} else if(args.length == 1) {
			String name = args[0];
			
			Location location = home.get(p, name);
			
			if(location == null) {
				TextOutput.outputToPlayer(p, "This Home does not exist.");
				return false;
			}
			
			p.teleport(location);
			
			TextOutput.outputToPlayer(p, "Teleported to Home " + name + ".");
			
		} else {
			TextOutput.outputToPlayer(p, "Please use /home *[name]");
			return false;
		}
		
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			return null;
		}
		
		Player p = (Player) sender;
		
		List<String> completions = new ArrayList<>();
		
		if(configHandler.getConfig().get("homes." + p.getUniqueId()) == null) {
			return null;
		}
		
		Object[] fields = configHandler.getConfig().getConfigurationSection("homes." + p.getUniqueId()).getKeys(false).toArray();
		
		if(fields.length <= 0) {
			return null;
		}
		
		for (Object key : fields){
			completions.add((String) key);
		}
		
		Collections.sort(completions);
		
		return completions;
	}

}
