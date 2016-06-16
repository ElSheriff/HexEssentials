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

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Main;

public class HatCommand extends Command{
	
	public HatCommand(Main main){
		super(main);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("hat")){
			if(sender instanceof Player){
				if(checkPerms((Player) sender, label, args)){
					Player p = (Player) sender;					
					ItemStack old = p.getInventory().getHelmet();
					p.getInventory().setHelmet(p.getInventory().getItemInMainHand());
					p.getInventory().setItemInMainHand(old);
					//p.getItemInHand().setAmount(0);					
				}else{
					noPerms((Player) sender, label, args);
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
