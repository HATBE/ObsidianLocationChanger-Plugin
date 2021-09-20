package ch.hatbe2113.obsidianhome.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
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
		
		if(args.length == 0) {
			if(!p.hasPermission("obsidian.home.use")) {
				TextOutput.outputToPlayer(p, "You don't have the permission to execute this command.");
				return false;
			}
			
			if(configHandler.getConfig().get("homes." + p.getUniqueId()) == null) {
				TextOutput.outputToPlayer(p, "You don't have any Homes right now.");
				return false;
			}
			
			Object[] homes = home.getHomeList(p.getUniqueId());
			if(homes.length == 0) {
				TextOutput.outputToPlayer(p, "You don't have any Homes right now.");
				return false;
			}
			String homesString = "";
			int c = 1;
			TextOutput.outputToPlayer(p, "Your Homes");
			
			for (Object key : homes){
				homesString += key;
				if(c != homes.length) {
					homesString += ", ";
				}
				c++;
			}
			
			TextOutput.outputToPlayer(p, homesString, false);
			
		} else if(args.length == 1) {
			if(!p.hasPermission("obsidian.home.use")) {
				TextOutput.outputToPlayer(p, "You don't have the permission to execute this command.");
				return false;
			}
			
			String name = args[0];
			Location location = home.get(p.getUniqueId(), name);
			if(location == null) {
				TextOutput.outputToPlayer(p, "This Home does not exist.");
				return false;
			}
			
			p.teleport(location);
			
			TextOutput.outputToPlayer(p, "Teleported to Home " + name + ".");
			
		} else if(args.length == 2) {
			if(!p.hasPermission("obsidian.home.use.other")) {
				TextOutput.outputToPlayer(p, "You don't have the permission to execute this command.");
				return false;
			}
			
			UUID uuid;
			
			String name = args[0];
			
			Player t = Bukkit.getPlayer(args[0]);
			
			if(t == null) {
				@SuppressWarnings("deprecation")
				OfflinePlayer ot = Bukkit.getOfflinePlayer(args[0]);
				if(ot == null) {
					TextOutput.outputToPlayer(p, "Player does not exist.");
					return false;
				}
				uuid = ot.getUniqueId();
			} else {
				uuid = t.getUniqueId();
			}
			
			Location location = home.get(uuid, name);
			
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
		
		List<String> completions = new ArrayList<>();
		if(!(sender instanceof Player)) {
			return null;
		}
		
		Player p = (Player) sender;
		
		if(args.length == 1) {
			if(!p.hasPermission("obsidian.home.use")) {
				return null;
			}
			
			if(home.playerHasHomes(p.getUniqueId())) {
				return null;
			}
			
			Object[] homes = home.getHomeList(p.getUniqueId());
			
			if(homes.length <= 0) {
				return null;
			}
			
			for (Object key : homes){
				completions.add((String) key);
			}
		} else if(args.length == 2) {
			if(!p.hasPermission("obsidian.home.use.other")) {
				return null;
			}

			UUID uuid;
			
			Player t = Bukkit.getPlayer(args[0]);
			
			if(t == null) {
				@SuppressWarnings("deprecation")
				OfflinePlayer ot = Bukkit.getOfflinePlayer(args[0]);
				if(ot == null) {
					return null;
				}
				uuid = ot.getUniqueId();
			} else {
				uuid = t.getUniqueId();
			}
			
			if(home.playerHasHomes(uuid)) {
				return null;
			}
			
			Object[] homes = home.getHomeList(uuid);
			
			if(homes.length <= 0) {
				return null;
			}
			
			for (Object key : homes){
				completions.add((String) key);
			}
			
		}
		
		Collections.sort(completions);
		
		return completions;
	}

}