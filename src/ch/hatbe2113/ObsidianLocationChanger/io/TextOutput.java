package ch.hatbe2113.ObsidianLocationChanger.io;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TextOutput {
	public static final String PREFIX = ChatColor.GRAY + "[" + ChatColor.DARK_PURPLE + "OLC" + ChatColor.GRAY + "]" + ChatColor.RESET + " ";
	public static final String PLAIN_PREFIX = "[OLC] ";
	
	public static void outputToConsole(String msg) {
		System.out.println(PLAIN_PREFIX + msg);
	}
	
	public static void outputToConsole(String msg, boolean prefix) {
		if(prefix) {
			System.out.println(PLAIN_PREFIX + msg);
		} else {
			System.out.println(msg);
		}
	}
	
	public static void outputToPlayer(Player p, String msg) {
		p.sendMessage(PREFIX + msg);
	}
	
	public static void outputToPlayer(Player p, String msg, boolean prefix) {
		if(prefix) {
			p.sendMessage(PREFIX + msg);
		} else {
			p.sendMessage(msg);
		}
	}
	
	public static void outputToCommandSender(CommandSender s, String msg) {
		s.sendMessage(PLAIN_PREFIX + msg);
	}
	
	public static void outputToCommandSender(CommandSender s, String msg, boolean prefix) {
		if(prefix) {
			s.sendMessage(PLAIN_PREFIX + msg);
		} else {
			s.sendMessage(msg);
		}
	}
}
