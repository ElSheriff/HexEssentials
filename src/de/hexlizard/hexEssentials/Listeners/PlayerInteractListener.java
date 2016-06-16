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

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Door;

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Main;

public class PlayerInteractListener implements Listener{
	Main main;
	FileConfiguration language;	
	
	public PlayerInteractListener(Main main){
		this.main = main;
		this.language = main.getLanguage();
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){	
		//Elevator Up
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getState() instanceof Sign && event.getPlayer().isSneaking() == false && ((Sign) event.getClickedBlock().getState()).getLine(1).contentEquals("[Up]") || event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getState() instanceof Sign && event.getPlayer().isSneaking() == false){
			if(event.getPlayer().hasPermission(main.getDescription().getName().toLowerCase() + ".use.lift")){
				Sign eventSign = (Sign) event.getClickedBlock().getState();
				
				Location curLoc = event.getClickedBlock().getLocation();			
				float pPitch = event.getPlayer().getLocation().getPitch();
				float pYaw = event.getPlayer().getLocation().getYaw();
				boolean teleportIsSafe = false;
				
				if(eventSign.getLine(1).contentEquals("[Lift Up]")){
					System.out.println("DEBUG: Lift Up");
					//Up									
					for(short s = (short) event.getClickedBlock().getLocation().getY(); s < 254; s++){
						if(event.getClickedBlock().getWorld().getBlockAt(curLoc).getState() instanceof Sign){
							Sign tmpSign = (Sign) event.getClickedBlock().getWorld().getBlockAt(curLoc).getState(); 
							if(tmpSign.getLine(1).contentEquals("[Lift Down]")){
								if(isLocationSafe(curLoc)){									
									teleportIsSafe = true;
									liftTeleport(event.getPlayer(), curLoc, pYaw, pPitch);
									break;
								}else{									
									event.getPlayer().sendMessage(Colorize.colorize(language.getString("lift_error_message")));									
								}
							}						
						}
						curLoc.setY(s);
					}	
				}else if(eventSign.getLine(1).contentEquals("[Lift Down]")){
					//DOwn
					System.out.println("DEBUG: Lift Down");
					for(short s = (short) event.getClickedBlock().getLocation().getY(); s > 0; s--){
						if(event.getClickedBlock().getWorld().getBlockAt(curLoc).getState() instanceof Sign){
							Sign tmpSign = (Sign) event.getClickedBlock().getWorld().getBlockAt(curLoc).getState(); 
							if(tmpSign.getLine(1).contentEquals("[Lift Up]")){
								if(isLocationSafe(curLoc)){
									teleportIsSafe = true;
									liftTeleport(event.getPlayer(), curLoc, pYaw, pPitch);
									
									break;
								}else{
									event.getPlayer().sendMessage(Colorize.colorize(language.getString("lift_error_message")));
								}
							}						
						}
						curLoc.setY(s);
					}										
				}
				
			}else{
				event.getPlayer().sendMessage(language.getString("no_permissions_message").replaceAll("%command%", "usage of Lift Signs").replaceAll("%args%","").replaceAll("NULL", ""));
			}


			//Elevator Down
		}else if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getPlayer().isSneaking() && event.getClickedBlock().getState() instanceof Sign){				
			event.getPlayer().sendMessage(Colorize.colorize(language.getString("not_implemented_yet_message")));			
			
		}
		

		
		
		//Sign Elevator
		
		
	}
	
	private boolean isLocationSafe(Location signLocation){
		Location tmpLoc = signLocation;

		tmpLoc.setY(tmpLoc.getY() - 1);
		if(signLocation.getWorld().getBlockAt(tmpLoc).getType().equals(Material.AIR)){
			return true;
		}

		
		return false;
	}
	
	private void liftTeleport(Player player, Location curLoc, float yaw, float pitch){
		curLoc.setX(curLoc.getX()+0.5);
		curLoc.setZ(curLoc.getZ()+0.5);
		curLoc.setYaw(yaw);
		curLoc.setPitch(pitch);
		player.teleport(curLoc);
	}
	
	
}
