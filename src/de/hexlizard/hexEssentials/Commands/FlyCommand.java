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

public class FlyCommand extends Command{
	
	public FlyCommand(Main main){
		super(main);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("fly")){
			if(sender instanceof Player){
				if(args.length == 0){
					Player p = (Player) sender;
					if(checkPerms(p, label, args)){
						changeFly(p);
					}else{
						noPerms(p, label, args);
					}								
					return true;
				}else if(args.length == 1){
					///other players
					if(checkPerms((Player) sender, label, args)){					
						if(Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))){
							Player target = Bukkit.getPlayer(args[0]);
							changeFly(target);
						}else{
							sender.sendMessage(Colorize.colorize(language.getString("target_offline_message").replaceAll("%target%", args[0])));
						}
					}else{
						noPerms((Player) sender, label, args);
					}
					return true;
				}else{
				}			
			}else{
				lang.notForConsole(label);
			}			
		}
		// TODO Auto-generated method stub
		return false;
	}
	
	private void changeFly(Player p){
		if(p.getAllowFlight()){
			
			p.setFlying(false);
			p.setAllowFlight(false);
			p.sendMessage(Colorize.colorize(language.getString("fly_command.stop_fly")));
			
		}else{
			p.setAllowFlight(true);
			p.setFlying(true);
			p.sendMessage(Colorize.colorize(language.getString("fly_command.start_fly")));
		}
	}

}
