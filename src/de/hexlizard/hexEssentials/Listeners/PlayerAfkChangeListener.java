package de.hexlizard.hexEssentials.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Language;
import de.hexlizard.hexEssentials.Main;
import de.hexlizard.hexEssentials.Events.PlayerAfkChangeEvent;

public class PlayerAfkChangeListener implements Listener{
	Main main;
	Language lang;
	FileConfiguration language;
	
	public PlayerAfkChangeListener(Main main){
		this.main = main;
		this.lang = new Language(main);
		language = main.getLanguage();
	}
	
	
	@EventHandler
	public void onPlayerAfkChange(PlayerAfkChangeEvent event){
		Player p = event.getPlayer();
		if(event.isAfk()){
			
			Bukkit.broadcastMessage(Colorize.colorize(language.getString("afk_command_message_start").replaceAll("%player%", p.getDisplayName())));
			p.setPlayerListName(ChatColor.DARK_GRAY+ "[AFK] "+ChatColor.RESET+p.getDisplayName());
			p.setGameMode(GameMode.SPECTATOR);	
		}else{
			Bukkit.broadcastMessage(Colorize.colorize(language.getString("afk_command_message_end").replaceAll("%player%", p.getDisplayName())));
			p.setPlayerListName(p.getDisplayName());
			p.setGameMode(GameMode.SURVIVAL);
		}
	}

}
