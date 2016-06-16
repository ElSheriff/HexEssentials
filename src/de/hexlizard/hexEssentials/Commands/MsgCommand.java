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

public class MsgCommand extends Command{

	
	public MsgCommand(Main main){
		super(main);

	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("msg")){
			if(checkPerms(sender, label, args)){
				if(args.length >= 2){
					
					if(main.playerOnline(args[0])){
						String msg = "";
						Player target = Bukkit.getPlayer(args[0]);
						for(short s = 1; s<=args.length-1;s++){
							msg+=args[s];
							if(s!=args.length-1){
								msg+=" ";
							}
							
						}
						//	this.languageConfig.addDefault("msg_command.sender", "You sent %target% a Message!");
						//this.languageConfig.addDefault("msg_command.receiver", "Message from %sender%: %message%");
						sender.sendMessage(Colorize.colorize(language.getString("msg_command.sender").replaceAll("%target%", target.getName())));
						
						target.sendMessage(Colorize.colorize(language.getString("msg_command.receiver").replaceAll("%sender%", sender.getName()).replaceAll("%message%", "")));
						target.sendMessage(Colorize.colorize(msg));
						
						
					}else{
						sender.sendMessage(Colorize.colorize(language.getString(("target_offline_message").replaceAll("%target%", args[0]))));
					}
					
				}else{
					sender.sendMessage(Colorize.colorize(language.getString("invalid_arguments_message").replaceAll("%command%", label)));
					return false;
				}				
			}else{
				//NO PERMS
				noPerms((Player) sender, label, args);
			}
			return true;
			
		}
		
		
		
		
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

}
