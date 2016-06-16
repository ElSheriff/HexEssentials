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

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Main;
import de.hexlizard.hexEssentials.Events.PlayerAfkChangeEvent;

public class PlayerQuitListener implements Listener{
	Main main;
	FileConfiguration language;
	
	public PlayerQuitListener(Main main){
		this.main = main;
		this.language = main.getLanguage();
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		HashMap<UUID, PermissionAttachment> permMap = main.getPermissionMap();
		event.getPlayer().removeAttachment(permMap.get(event.getPlayer().getUniqueId()));
		permMap.remove(event.getPlayer().getUniqueId());
		
		
		
		
		
		if(main.getConfig().getBoolean("custom_quit_message")){
			event.setQuitMessage(Colorize.colorize(language.getString("player_quit_message").replaceAll("%player%", event.getPlayer().getName())));
		}
		
		
		//Remove Godmode from Player
		event.getPlayer().removeMetadata("godmode", main);
		if(event.getPlayer().isInvulnerable()){
			event.getPlayer().setInvulnerable(false);
		}
		//Remove afk mode from Player
		event.getPlayer().removeMetadata("afk", main);									
		main.getServer().getPluginManager().callEvent(new PlayerAfkChangeEvent(event.getPlayer()));
	}
	

}
