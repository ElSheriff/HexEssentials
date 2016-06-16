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

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Main;
import de.hexlizard.hexEssentials.PlayerConfig;

public class PlayerJoinListener implements Listener{
	Main main;
	FileConfiguration language;
	
	public PlayerJoinListener(Main main){
		this.main = main;
		this.language = main.getLanguage();
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		PermissionAttachment attachment = event.getPlayer().addAttachment(main);
		HashMap<UUID, PermissionAttachment> permMap = main.getPermissionMap();
		permMap.put(event.getPlayer().getUniqueId(), attachment);
		main.setPermissionMap(permMap);
		PlayerConfig pc = new PlayerConfig(main, event.getPlayer());
		//pc.loadOrCreate();
		
		
		event.getPlayer().setCustomName(pc.getNickname());
		event.getPlayer().setDisplayName(pc.getNickname());
		event.getPlayer().setPlayerListName(pc.getNickname());
		
		if(main.getConfig().getBoolean("custom_join_message")){
			event.setJoinMessage(Colorize.colorize(language.getString("player_join_message").replaceAll("%player%", event.getPlayer().getCustomName())));
		}			
	}	
}
