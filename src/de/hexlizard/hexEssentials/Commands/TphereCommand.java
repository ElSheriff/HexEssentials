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

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Main;

public class TphereCommand extends Command{

	public TphereCommand(Main main) {
		super(main);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override 
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args){
		if(label.equalsIgnoreCase("tphere")){
			if(sender instanceof Player){
				if(checkPerms((Player) sender, label, args)){
					
					if(main.playerOnline(args[1])){
						
						Player p = (Player) sender;
						Player target = Bukkit.getServer().getPlayer(args[1]);
						
						target.teleport(p);
						
						
					}else{
						sender.sendMessage(Colorize.colorize(language.getString("target_offline_message").replaceAll("%target%", args[1])));
					}
					
				}
			}else{
				sender.sendMessage(Colorize.colorize(language.getString("not_for_console_message").replaceAll("%command%", label)));
			}	
			return true;
		}
		
		
		return false;
		
	}

}
