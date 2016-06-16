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

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Main;

public class MotdCommand extends Command{
	
	public MotdCommand(Main main){
		super(main);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args){
		if(label.equalsIgnoreCase("motd")){
			if(checkPerms(sender, label, args)){
				if(args.length == 0){
					sender.sendMessage(Colorize.colorize(language.getString("motd_command_current_motd_message")));
					sender.sendMessage(main.getConfig().getString("motd"));
				}else if(args.length >= 2){
					if(args[0].equalsIgnoreCase("set")){
						String newMotd = "";
						for(short s = 1; s<args.length;s++){
							newMotd = newMotd + (args[s]+" ");
						}
						sender.sendMessage(Colorize.colorize(language.getString("motd_command_set_motd_message")));
						sender.sendMessage(Colorize.colorize(newMotd));
						main.getConfig().set("motd", newMotd);
						main.saveConfig();						
					}else{
						return false;
					}
				}
			}else{
				noPerms(sender, label, args);
			}
			return true;
			
		}
		return false;
	}

}
