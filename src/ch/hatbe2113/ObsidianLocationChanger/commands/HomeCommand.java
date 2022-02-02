package ch.hatbe2113.ObsidianLocationChanger.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ch.hatbe2113.ObsidianLocationChanger.Main;
import ch.hatbe2113.ObsidianLocationChanger.home.HomeHandler;
import ch.hatbe2113.ObsidianLocationChanger.io.TextOutput;

public class HomeCommand implements CommandExecutor {
	private Main main;
	private HomeHandler home;
	
	public HomeCommand(Main main) {
		this.main = main;
		home = main.getHome();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			TextOutput.outputToCommandSender(sender, "You have to be a player, to execute this command!");
			return false;
		}
		
		Player p = (Player) sender;
		
		if(!p.hasPermission(main.PERM_PREFIX + "use")) {
			TextOutput.outputToPlayer(p, "You don't have the permission to execute this command.");
			return false;
		}
		
		if(args.length == 0) {
			home.displayList(p);
		} else if(args.length == 1) {
			String name = args[0];
			
			if(!name.matches(main.NAME_REGEX)) {
				TextOutput.outputToPlayer(p, "Home name is not in the right format!");
				return false;
			}
			
			if(!home.exists(p, name)) {
				TextOutput.outputToPlayer(p, "This home does not exist!");
				return false;
			}
			
			Location loc = home.get(p, name);
			
			p.teleport(loc);
			
			TextOutput.outputToPlayer(p, "Successfully teleported!");
		} else {
			TextOutput.outputToPlayer(p, "Please use /home [name]");
		}
		
		return false;
	}
}
