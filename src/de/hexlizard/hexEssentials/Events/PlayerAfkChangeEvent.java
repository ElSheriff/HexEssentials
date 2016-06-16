package de.hexlizard.hexEssentials.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerAfkChangeEvent extends Event{
	Player player;
	boolean afk;
    private static final HandlerList handlers = new HandlerList();    

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
	
	public PlayerAfkChangeEvent(Player player){
		this.player = player;
		if(player.hasMetadata("afk")){
			this.afk = true;
		}else{
			this.afk = false;
		}
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public boolean isAfk(){
		return afk;
	}

}
