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

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.hexlizard.hexEssentials.Main;
import de.hexlizard.hexEssentials.PlayerConfig;

public class PlayerDeathListener implements Listener{
	Main main;
	
	public PlayerDeathListener(Main main){
		this.main = main;
	}
	
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){
		PlayerConfig pc = new PlayerConfig(main, event.getEntity());
		pc.setLatestDeath(event.getEntity().getLocation());		
		
	}

}
