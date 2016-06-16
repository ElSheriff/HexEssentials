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

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Language;
import de.hexlizard.hexEssentials.Main;
import de.hexlizard.hexEssentials.PlayerConfig;

public class SethomeCommand extends Command{
	Language lang;
	
	public SethomeCommand(Main main){
		super(main);
		lang = new Language(main);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("sethome")){
			if(sender instanceof Player){
				if(checkPerms((Player) sender, label, args)){
					Player p = (Player)sender;
					PlayerConfig pc = new PlayerConfig(main, p);
					if(args.length == 0){
						pc.setHome("default", p.getLocation());
						sender.sendMessage(Colorize.colorize("home_command_created_message").replaceAll("%home%", "default"));
					}else if(args.length == 1){
						if(args[0].equalsIgnoreCase("bed") == false){
							pc.setHome(args[0], p.getLocation());
							sender.sendMessage(Colorize.colorize("home_command_created_message").replaceAll("%home%", args[0]));
						}
					}else{
						//invalid args
						sender.sendMessage(lang.invalidArgs(label));
					}
				}else{
					noPerms((Player) sender, label, args);
				}
			}else{
				sender.sendMessage(lang.notForConsole(label));
			}
			
			return true;
		}							
		// TODO Auto-generated method stub
		return false;
	}

}
