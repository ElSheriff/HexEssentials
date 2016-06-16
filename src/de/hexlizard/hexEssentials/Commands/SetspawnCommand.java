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
import de.hexlizard.hexEssentials.Main;

public class SetspawnCommand extends Command{

	
	public SetspawnCommand(Main main){
		super(main);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		/*
		 * this.getConfig().addDefault("spawn.world", Bukkit.getWorlds().get(0).getName());
		this.getConfig().addDefault("spawn.x", Bukkit.getWorlds().get(0).getSpawnLocation().getX());
		this.getConfig().addDefault("spawn.y", Bukkit.getWorlds().get(0).getSpawnLocation().getY());
		this.getConfig().addDefault("spawn.z", Bukkit.getWorlds().get(0).getSpawnLocation().getZ());
		 * */
		
		if(label.equalsIgnoreCase("setspawn")){
			if(sender instanceof Player){
				if(checkPerms((Player) sender, label, args)){
					Player p = (Player) sender;
					main.getConfig().set("spawn.", p.getLocation().serialize().toString());
					p.getWorld().setSpawnLocation((int) p.getLocation().getX(), (int) p.getLocation().getY(), (int) p.getLocation().getZ());
					
					main.saveConfig();
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
