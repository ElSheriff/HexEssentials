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
import org.bukkit.ChatColor;
import org.bukkit.GameMode;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Main;
import de.hexlizard.hexEssentials.Commands.Command;
import de.hexlizard.hexEssentials.Events.PlayerAfkChangeEvent;

public class AfkCommand extends Command{

	public AfkCommand(Main main) {
		super(main);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			if(checkPerms((Player) sender, label, args)){				
				Player p = (Player) sender;
				//afk_command_message
				if(!p.hasMetadata("afk")){				
					p.setMetadata("afk", new FixedMetadataValue(main, true));					
					main.getServer().getPluginManager().callEvent(new PlayerAfkChangeEvent(p));					
				}else{
					p.removeMetadata("afk", main);									
					main.getServer().getPluginManager().callEvent(new PlayerAfkChangeEvent(p));
				}								
				
			}else{
				noPerms((Player) sender, label, args);
			}
			return true;
		}else{
			notForConsole(sender, label);			
		}
		
		
		
		// TODO Auto-generated method stub
		return false;
	}

}
