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

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Main;

public class GamemodeCommand extends Command{

	
	
	public GamemodeCommand(Main main){
		super(main);
	}
		

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("gamemode") || label.equalsIgnoreCase("gm")){
			if(sender instanceof Player){
				if(args.length == 2){
					if(checkPerms((Player) sender, label, args)){
						if(main.playerOnline(args[1])){
							
							Player p = (Player) sender;

							p.setGameMode(changeGameMode(args[0]));
							p.sendMessage(Colorize.colorize(language.getString("gamemode_command_change_message").replaceAll("%gamemode%", p.getGameMode().toString())));
							
						}else{
							sender.sendMessage(Colorize.colorize(language.getString("target_offline_message").replaceAll("%target%", args[1])));
						}
					}else{
						//NO PERMS
						noPerms((Player) sender, label, args);
					}
					return true;
				}else if(args.length == 1){
					if(checkPerms((Player) sender, label, args)){
						Player p = (Player) sender;

						p.setGameMode(changeGameMode(args[0]));
						p.sendMessage(Colorize.colorize(language.getString("gamemode_command_change_message").replaceAll("%gamemode%", p.getGameMode().toString())));
						
					}else{
						//NO PERMS
						noPerms((Player) sender, label, args);
					}
					return true;
				}else{
					return false;
				}
			}else{
				//Not a Player
				sender.sendMessage(Colorize.colorize(language.getString("not_for_console_message").replaceAll("%command%", label)));
			}

		}
				// TODO Auto-generated method stub
		return false;
	}
	
	private GameMode changeGameMode(String mode){
		GameMode gm = GameMode.SURVIVAL;
		if(mode.equalsIgnoreCase("s") || mode.equalsIgnoreCase("0") || mode.equalsIgnoreCase("survival")){
			gm = GameMode.SURVIVAL;
		}else if(mode.equalsIgnoreCase("c") || mode.equalsIgnoreCase("1") || mode.equalsIgnoreCase("creative")){
			gm = GameMode.CREATIVE;
		}else if(mode.equalsIgnoreCase("a") || mode.equalsIgnoreCase("2") || mode.equalsIgnoreCase("adventure")){
			gm = GameMode.ADVENTURE;
		}else if(mode.equalsIgnoreCase("sp") || mode.equalsIgnoreCase("3") || mode.equalsIgnoreCase("spectator")){
			gm = GameMode.SPECTATOR;
		}
		return gm;
	}

}
