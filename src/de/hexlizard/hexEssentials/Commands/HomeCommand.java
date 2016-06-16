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

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Main;
import de.hexlizard.hexEssentials.PlayerConfig;
import de.hexlizard.hexEssentials.Commands.Command;

public class HomeCommand extends Command{
	
	
	public HomeCommand(Main main){
		super(main);
		
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("home") || label.equalsIgnoreCase("h")){
			if(sender instanceof Player){
				//home [name]
				if(checkPerms((Player) sender, label, args)){
					PlayerConfig pc = new PlayerConfig(main, (Player) sender);					
					HashMap<String, Location> homes = pc.getHomes();					
					if(args.length == 0){
						//teleport playa to default home
						((Player) sender).teleport(homes.get("default"));
						sender.sendMessage(Colorize.colorize(language.getString("home_command_home_teleported_to_home_message").replaceAll("%home%", "default")));
					}else if(args.length == 1){
						if(homes.containsKey(args[0])){
							((Player) sender).teleport(homes.get(args[0]));
							sender.sendMessage(Colorize.colorize(language.getString("home_command_home_teleported_to_home_message").replaceAll("%home%", args[0])));
						}else{
							sender.sendMessage(Colorize.colorize(language.getString("home_command_home_not_found_message").replaceAll("%home%", args[0])));
						}
						return true;
					}else{
						sender.sendMessage(Colorize.colorize(language.getString("invalid_arguments_message")));
						return false;
					}
					sender.sendMessage(Colorize.colorize(language.getString("not_implemented_yet_message")));
					return true;
				}else{
					noPerms((Player)sender, label, args);
					return true;
				}				
			}else{
				sender.sendMessage(Colorize.colorize(language.getString("not_for_console_message").replaceAll("%command%", label)));
			}
			
			return true;
		}							
		return false;
	}

}
