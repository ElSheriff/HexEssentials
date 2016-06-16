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

public class MailCommand extends Command{
	//Offline Messages

	public MailCommand(Main main) {
		super(main);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args){
		if(label.equalsIgnoreCase("mail")){
			sender.sendMessage(Colorize.colorize(language.getString("not_implemented_yet_message")));

			if(checkPerms(sender, label, args)){
				//mail send <receiver>, mail read <sender> <msgnumber>, mail box
				if(args.length == 0){
					
				}
			}else{
				noPerms(sender, label, args);
			}
		}
		return false;
	}
	
	private void showMails(CommandSender sender, Player messager){
		//From <Player> <DATETIME>
		
	}
	
	private void sendMail(CommandSender sender, Player receiver, String message){
		String senderName = "";
		if(sender instanceof Player == false){
			senderName = "CONSOLE";
		}else{
			senderName = sender.getName();
		}
		
		
	}


}
