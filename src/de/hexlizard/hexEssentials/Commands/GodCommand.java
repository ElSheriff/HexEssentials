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
import org.bukkit.metadata.FixedMetadataValue;

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Main;

public class GodCommand extends Command{

	public GodCommand(Main main) {
		super(main);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args){
		////god_command_end_message
		//god_command_start_message
		if(label.equalsIgnoreCase("god")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(checkPerms(p, label, args)){
					if(p.hasMetadata("godmode")){
						p.removeMetadata("godmode", main);
						p.setInvulnerable(true);
						p.sendMessage(Colorize.colorize(language.getString("god_command_start_message")));
					}else{
						p.setMetadata("godmode", new FixedMetadataValue(main, true));
						p.setInvulnerable(false);
						p.sendMessage(Colorize.colorize(language.getString("god_command_end_message")));
					}
				}else{
					noPerms(p, label, args);
				}
			}else{
				notForConsole(sender, label);
			}
			return true;
		}
		return false;
	}

}
