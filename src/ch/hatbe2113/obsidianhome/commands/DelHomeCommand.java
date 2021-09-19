package ch.hatbe2113.obsidianhome.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ch.hatbe2113.obsidianhome.home.HomeHandler;
import ch.hatbe2113.obsidianhome.io.CustomConfigHandler;
import ch.hatbe2113.obsidianhome.io.TextOutput;

public class DelHomeCommand implements CommandExecutor {
	
	private CustomConfigHandler configHandler;
	private HomeHandler home;
	
	public DelHomeCommand(CustomConfigHandler configHandler) {
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
		
		if(!p.hasPermission("obsidian.home.del")) {
			TextOutput.outputToPlayer(p, "You don't have the permission to execute this command.");
			return false;
		}
		
		if(args.length != 1) {
			TextOutput.outputToPlayer(p, "Please use /delhome [name]");
			return false;
		}
		
		String name = args[0];
		
		if(home.exists(p, name)) {
			home.delete(p, name);
			TextOutput.outputToPlayer(p, "Home " + name + " successfully deleted.");
		} else {
			TextOutput.outputToPlayer(p, "This Home does not exist.");
			return false;
		}
		
		return false;
	}

}
