package ch.hatbe2113.obsidianhome.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ch.hatbe2113.obsidianhome.home.HomeHandler;
import io.ConfigHandler;
import io.TextOutput;

public class SetHomeCommand implements CommandExecutor {
	
	private ConfigHandler configHandler;
	private HomeHandler home;
	
	public SetHomeCommand(ConfigHandler configHandler) {
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
		
		if(args.length != 1) {
			TextOutput.outputToPlayer(p, "Please use /sethome [name]");
			return false;
		}

		String name = args[0];
		
		home.add(p, name);
		
		TextOutput.outputToPlayer(p, "Home " + name + " successfully added.");
		
		return false;
	}

}
