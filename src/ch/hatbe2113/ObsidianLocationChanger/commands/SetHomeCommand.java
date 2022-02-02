package ch.hatbe2113.ObsidianLocationChanger.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ch.hatbe2113.ObsidianLocationChanger.Main;
import ch.hatbe2113.ObsidianLocationChanger.home.HomeHandler;
import ch.hatbe2113.ObsidianLocationChanger.io.TextOutput;

public class SetHomeCommand implements CommandExecutor {
	private Main main;
	private HomeHandler home;
	
	public SetHomeCommand(Main main) {
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
		
		if(!p.hasPermission(main.PERM_PREFIX + "set")) {
			TextOutput.outputToPlayer(p, "You don't have the permission to execute this command.");
			return false;
		}
		
		if(args.length != 1) {
			TextOutput.outputToPlayer(p, "Please use /sethome [name]");
			return false;
		}
		
		String name = args[0];
		
		if(!name.matches(main.NAME_REGEX)) {
			TextOutput.outputToPlayer(p, "Home name is not in the right format!");
			return false;
		}
		
		if(home.exists(p, name)) {
			TextOutput.outputToPlayer(p, "Home location successfully changed!");
		} else {
			TextOutput.outputToPlayer(p, "Home location successfully created!");
		}
		
		home.add(p, name);
		
		return false;
	}
}
