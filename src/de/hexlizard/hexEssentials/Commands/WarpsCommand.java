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

public class WarpsCommand extends Command{
	
	public WarpsCommand(Main main){
		super(main);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("warps")){
			if(checkPerms(sender, label, args)){
				HashMap<String, Location> warps = main.getWarpPoints();
				String warpsMsg = "";								
				if(warps.size() != 0){
					for(Entry<String, Location>  e : warps.entrySet()){
						warpsMsg = warpsMsg + e.getKey() + ", ";						
					}
					warpsMsg = warpsMsg.substring(0, warpsMsg.length()-2);
					sender.sendMessage(warpsMsg);
					
				}	
				sender.sendMessage(Colorize.colorize(language.getString("warp_command_warps_message").replaceAll("%warps%", warpsMsg)));
			}else{
				noPerms(sender, label, args);
			}
			
			
			return true;
		}							
		// TODO Auto-generated method stub
		return false;
	}


}
