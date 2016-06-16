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
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Main;
import de.hexlizard.hexEssentials.PlayerConfig;

public class HomesCommand extends Command{
	
	public HomesCommand(Main main){
		super(main);
	}
 
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("homes")){
			if(sender instanceof Player){
				if(checkPerms((Player) sender, label, args)){
					PlayerConfig pc = new PlayerConfig(main, (Player) sender);
					HashMap<String, Location> homes = pc.getHomes();
					String homesMsg = "Homes: ";									
					for(Entry<String, Location>  e : homes.entrySet()){
						homesMsg = homesMsg + e.getKey() + ", ";						
					}
					homesMsg = homesMsg.substring(0, homesMsg.length()-2);
					sender.sendMessage(homesMsg);			
				}else{
					sender.sendMessage(Colorize.colorize(language.getString("no_permissions_message").replaceAll("%command%", label).replaceAll("%args%", args[0]).replaceAll("NULL", "")));
				}
				
			}else{
				sender.sendMessage(Colorize.colorize(language.getString("not_for_console_message").replaceAll("%command%", label)));
			}
			
			return true;
		}							
		// TODO Auto-generated method stub
		return false;
	}

}
