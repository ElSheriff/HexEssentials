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
package de.hexlizard.hexEssentials.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Main;

public class PlayerChatListener implements Listener{
	Main main;
	FileConfiguration language;	
	
	public PlayerChatListener(Main main){
		this.main = main;
		this.language = main.getLanguage();
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		if(event.getMessage().startsWith("@")){
			String receiverName = "";
			String[] parts = event.getMessage().split(" ");
			receiverName = parts[0].replaceAll("@", "");
			
			if(main.playerOnline(receiverName)){
				Player target = Bukkit.getPlayer(receiverName);
				String msg = "";
				for(short s = 1; s<parts.length;s++){
					msg = msg + parts[s];
					if(s < parts.length-1){
						msg = msg + " ";
					}
				}
				
				target.sendMessage(Colorize.colorize(language.getString("msg_command.receiver").replaceAll("%sender%", event.getPlayer().getName()).replaceAll("%message%", "")));
				target.sendMessage(msg);
				event.getPlayer().sendMessage(Colorize.colorize(event.getMessage()));
			}else{
				event.getPlayer().sendMessage(Colorize.colorize(language.getString(("target_offline_message").replaceAll("%target%", receiverName))));

			}
			event.setCancelled(true);
			
		}
		event.setMessage(Colorize.colorize(event.getMessage()));
	}

}
