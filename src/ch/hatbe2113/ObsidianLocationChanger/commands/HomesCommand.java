package ch.hatbe2113.ObsidianLocationChanger.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ch.hatbe2113.ObsidianLocationChanger.Main;
import ch.hatbe2113.ObsidianLocationChanger.home.HomeHandler;
import ch.hatbe2113.ObsidianLocationChanger.io.TextOutput;

public class HomesCommand implements CommandExecutor {
	private Main main;
	private HomeHandler home;
	
	public HomesCommand(Main main) {
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
		
		home.displayList(p);
		
		return false;
	}
}
