/*
 * 
 * 	HexLizard - HexEssentials Plugin
 * 	Copyleft (ɔ) Freedom 3
 * 	GPLv3
 *	
 * 	Feel free to study, modify, and distribute my Sourcecode
 *  under the Terms of the GNU General Public License Version 3
 * 
 * 	More Information about the GNU General Public License
 * 	can be found here <https://www.gnu.org/licenses/gpl.txt>
 * 
 * 	Want to know what Copyleft is? <https://www.wikiwand.com/en/Copyleft>
 * 
 * 
 * */
package de.hexlizard.hexEssentials.Commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Language;
import de.hexlizard.hexEssentials.Main;

public class Command implements CommandExecutor{
	Main main;
	FileConfiguration language;
	Language lang;
	
	public Command(Main main){
		this.main = main;
		this.language = main.getLanguage();
		this.lang = new Language(main);
	}
	


	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	protected Language getMessages(){
		return this.lang;
	}
	
	protected boolean checkPerms(Player sender, String label, String[] args){		
		if(main.getConfig().getBoolean("use_permission_system") == false){
			return true;
		}else{
			if(sender.hasPermission(main.getDescription().getName().toLowerCase() + ".commands."+label.toLowerCase())){
				return true;
			}
		}
		return false;
	}
	
	protected boolean checkPerms(CommandSender sender, String label, String[] args){		
		if(main.getConfig().getBoolean("use_permission_system") == false){
			return true;
		}else{
			if(sender.hasPermission(main.getDescription().getName().toLowerCase() + ".commands."+label.toLowerCase())){
				return true;
			}
		}
		return false;
	}
	
	protected void noPerms(Player sender, String label, String[] args){
		if(args.length == 0){
			sender.sendMessage(Colorize.colorize(language.getString("no_permissions_message").replaceAll("%command%", label).replaceAll("%args%", "").replaceAll("NULL", "")));
		}else if(args.length == 1){
			sender.sendMessage(Colorize.colorize(language.getString("no_permissions_message").replaceAll("%command%", label).replaceAll("%args%", args[0]).replaceAll("NULL", "")));
		}else{
			sender.sendMessage(Colorize.colorize(language.getString("no_permissions_message").replaceAll("%command%", label).replaceAll("%args%", "").replaceAll("NULL", "")));
		}
		
	}
	protected void noPerms(CommandSender sender, String label, String[] args){
		if(args.length == 0){
			sender.sendMessage(Colorize.colorize(language.getString("no_permissions_message").replaceAll("%command%", label).replaceAll("%args%", "").replaceAll("NULL", "")));
		}else if(args.length == 1){
			sender.sendMessage(Colorize.colorize(language.getString("no_permissions_message").replaceAll("%command%", label).replaceAll("%args%", args[0]).replaceAll("NULL", "")));
		}else{
			sender.sendMessage(Colorize.colorize(language.getString("no_permissions_message").replaceAll("%command%", label).replaceAll("%args%", "").replaceAll("NULL", "")));
		}
		
	}
	
	protected void notForConsole(CommandSender sender, String label){
		sender.sendMessage(lang.notForConsole(label));				
	}



}
