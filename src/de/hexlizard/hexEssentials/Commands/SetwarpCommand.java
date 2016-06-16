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
import de.hexlizard.hexEssentials.Language;
import de.hexlizard.hexEssentials.Main;

public class SetwarpCommand extends Command{
	Language lang;
	
	public SetwarpCommand(Main main){
		super(main);
		lang = new Language(main);
		
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("setwarp")){
			HashMap<String, Location> warps = main.getWarpPoints();			
			if(sender instanceof Player){
				if(checkPerms((Player) sender, label, args)){
					if(args.length == 1){
						if(args[0].equalsIgnoreCase("--list") == false){
							main.addWarpPoint(args[0], (((Player) sender).getLocation()));
							sender.sendMessage(Colorize.colorize(language.getString("warp_command_created_message").replaceAll("%warp%", args[0])));
						}else{
							sender.sendMessage(Colorize.colorize(language.getString("warp_command_blacklist_name_message").replaceAll("%warp%", args[0])));
						}						
					}else{
						sender.sendMessage(lang.invalidArgs(label));
						return false;
					}
				}else{
					noPerms((Player) sender, label, args);
				}
			}else{
				sender.sendMessage(lang.notForConsole(label));
			}
			
			return true;
		}							
		return false;
	}

}
