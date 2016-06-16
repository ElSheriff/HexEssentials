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

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Main;
import de.hexlizard.hexEssentials.PlayerConfig;


public class PlayerBedEnterListener implements Listener{
	Main main;
	FileConfiguration language;
	
	public PlayerBedEnterListener(Main main){
		this.main = main;
		this.language = main.getLanguage();
	}
	
	
	@EventHandler
	public void onPlayerBedEnter(PlayerBedEnterEvent event){	
		PlayerConfig pc = new PlayerConfig(main, event.getPlayer());

		pc.setBedLocation();
					
		
		event.getPlayer().sendMessage(Colorize.colorize(language.getString("player_bed_enter_message")));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 130, 4));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 400, 4));
		Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() 	{
			public void run() {
				event.getPlayer().sendMessage(Colorize.colorize(language.getString("player_bed_sleep_message")));
			}
		}, 40);	
		Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() 	{
			public void run() {
			event.getPlayer().getWorld().setTime(0);
			if(event.getPlayer().getWorld().hasStorm()){
				event.getPlayer().getWorld().setStorm(false);;
			}
			}
		}, 200);
	}

}
