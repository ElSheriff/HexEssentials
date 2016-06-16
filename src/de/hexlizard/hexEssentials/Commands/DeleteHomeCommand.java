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

public class DeleteHomeCommand extends Command{
	
	public DeleteHomeCommand(Main main) {
		super(main);
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args){
		if(label.equalsIgnoreCase("deletehome") || label.equalsIgnoreCase("delhome")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(checkPerms(p, label, args)){
					if(args.length == 1){
						PlayerConfig pc = new PlayerConfig(main, p);
						HashMap<String, Location> homes = pc.getHomes();
						
						if(args[0].equalsIgnoreCase("default") == false || args[0].equalsIgnoreCase("bed") == false){
							if(homes.containsKey(args[0])){
								homes.remove(args[0]);
								pc.setHomes(homes);
							}else{
								sender.sendMessage(Colorize.colorize(language.getString("home_command_home_not_found_message").replaceAll("%home%", args[0])));
							}
						}else{
							sender.sendMessage(Colorize.colorize(language.getString("home_command_cant_delete_home_message")));
						}
						return true;										
					}else{
						sender.sendMessage(lang.invalidArgs(label));
						return false;
					}
				}else{
					noPerms(p, label, args);
				}
			}else{
				sender.sendMessage(lang.notForConsole(label));
			}
		}
		return false;
	}
	
	

}
