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
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Main;

public class SpawnCommand extends Command{
	
	
	public SpawnCommand(Main main){
		super(main);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("spawn")){
			if(sender instanceof Player){
				if(checkPerms((Player) sender, label, args)){
					Player p = (Player) sender;
					// TODO spawn aus config auslesen
					World w = Bukkit.getWorld(main.getConfig().getString("spawn.world"));
					Double x = main.getConfig().getDouble("spawn.x");
					Double y = main.getConfig().getDouble("spawn.y");
					Double z = main.getConfig().getDouble("spawn.z");
					
					
					
					
					p.teleport(new Location(w, x, y, z));
				}else{
					noPerms((Player) sender, label, args);
				}
			}else{
				//Console
				sender.sendMessage(Colorize.colorize(language.getString("not_for_console_message").replaceAll("%command%", label)));
			}
			return true;
		}
		
		// TODO Auto-generated method stub
		return false;
	}

}
