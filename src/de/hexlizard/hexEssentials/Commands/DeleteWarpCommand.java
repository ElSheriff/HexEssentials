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

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Language;
import de.hexlizard.hexEssentials.Main;

public class DeleteWarpCommand extends Command{
	Language lang;

	public DeleteWarpCommand(Main main) {
		super(main);
		this.lang = new Language(main);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args){
		if(label.equalsIgnoreCase("deletewarp") || label.equalsIgnoreCase("delwarp")){
			HashMap<String, Location> warps = main.getWarpPoints();
			if(checkPerms(sender, label, args)){
				if(args.length == 1){
					if(warps.containsKey(args[0])){
						warps.remove(args[0]);
						main.setWarpPoints(warps);
						sender.sendMessage(language.getString("warp_command_deleted_message").replaceAll("%warp%", args[0]));
					}else{
						sender.sendMessage(Colorize.colorize(language.getString("warp_command_warp_not_found_message").replaceAll("%warp%", args[0])));
						
					}
					return true;
				}else{
					sender.sendMessage(lang.invalidArgs(label));
					return false;
				}
			}else{
				noPerms(sender, label, args);
				return true;
			}
		}
		return false;
	}
	

}
