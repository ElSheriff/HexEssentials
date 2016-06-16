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
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Main;
import de.hexlizard.hexEssentials.Commands.Command;

public class OpsCommand extends Command{

	
	public OpsCommand(Main main){
		super(main);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("ops")){
			if(sender instanceof Player){
				if(checkPerms((Player) sender, label, args)){
					printOps(sender);
				}else{
					noPerms((Player) sender, label, args);				
				}
			}else{				
				printOps(sender);
			}

			return true;
		}					
		return false;
		// TODO Auto-generated method stub
		
	}
	private void printOps(CommandSender sender){
		String test = "";
		for(OfflinePlayer s : Bukkit.getServer().getOperators()){
			if(main.playerOnline(s.getName())){
				//Online
				test+=ChatColor.GREEN+s.getName()+ChatColor.RESET+", ";
			}else{
				test+=ChatColor.RED+s.getName()+ChatColor.RESET+", ";
			}					
		}
		test = test.substring(0, test.length()-2);
		sender.sendMessage(Colorize.colorize(language.getString("ops_message").replaceAll("%ops%", test)));
	}

}
