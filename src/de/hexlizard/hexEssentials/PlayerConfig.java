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
package de.hexlizard.hexEssentials;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;


import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PlayerConfig {
	private Player player;
	Main main;
	File dataFile;
	FileConfiguration dataConfigFile;
	
	public PlayerConfig(Main main, Player player){
		this.player = player;
		this.main = main;
		dataFile = new File(main.getDataFolder()+"/players/", player.getUniqueId().toString()+".yml");
		dataConfigFile = YamlConfiguration.loadConfiguration(dataFile);
		loadOrCreate();
		
	}
	
		
	
	public void loadOrCreate(){
		if(dataFile.exists() == false){			
			createDefaultPlayerConfig();
		}
	}
	
	public void setNickname(String nickname){
		dataConfigFile.set("nickname", nickname);
		savePlayerconfig();
	}
	public String getNickname(){
		return Colorize.colorize(dataConfigFile.getString("nickname"));
	}
	
	public void setBedLocation(){
		HashMap<String, Location> homes = this.getHomes();
		homes.put("bed", player.getLocation());
		List<String> returnList = new ArrayList<String>();
		
		for(Entry<String, Location> es : homes.entrySet()){
			returnList.add(es.getKey()+";"+es.getValue().serialize().toString());
		}			
		getPlayerConfig().set("homes", returnList);
		savePlayerconfig();
	}
	
	
	public void setHome(String name, Location location){
		HashMap<String, Location> homes = this.getHomes();
		homes.put(name, location);
		setHomes(homes);
	}
	
	public void setHomes(HashMap<String, Location> homes){
		List<String> homesToSafe = new ArrayList<String>();
		for(Entry<String, Location> e : homes.entrySet()){
			homesToSafe.add(e.getKey()+";"+e.getValue().serialize().toString());
		}
		
		
		getPlayerConfig().set("homes", homesToSafe);
		savePlayerconfig();
		
	}
	
	public Location getLatestDeath(){
		return main.deserializeLocation(this.getPlayerConfig().getString("latest_death"));
	}
	
	public void setLatestDeath(Location location){
		getPlayerConfig().set("latest_death", location.serialize().toString());
		savePlayerconfig();
	}
	
	public void addHome(String name, Location location){
		HashMap<String, Location> homes = this.getHomes();
		homes.put(name, location);
		setHomes(homes);
	}


	public HashMap<String, Location> getHomes(){
		HashMap<String, Location> homes = new HashMap<String, Location>();
		
		List<String> homeList = (List<String>) dataConfigFile.getList("homes");
		List<String[]> data = new ArrayList<String[]>();
		
		for(String s : homeList){
			data.add(s.split(";"));
		}

		for(String[] sA0 : data){
			homes.put(sA0[0], main.deserializeLocation(sA0[1]));						
		}
		
		
		return homes;
	}


	
	

	public FileConfiguration getPlayerConfig(){				
		return this.dataConfigFile;
	}
	

	
	public void savePlayerconfig(){		
		try {
			dataConfigFile.save(dataFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void createDefaultPlayerConfig(){		
		dataConfigFile.addDefault("nickname", player.getName());
		dataConfigFile.addDefault("latest_death", player.getLocation().serialize().toString());		
		dataConfigFile.addDefault("blocked_playerlayers", new ArrayList<String>());
		dataConfigFile.addDefault("homes", Arrays.asList("default;"+player.getLocation().serialize().toString(), "bed;"+player.getLocation().serialize().toString()));		
		//dataConfigFile.addDefault("homes.default", player.getLocation().serialize().toString());		
		
		dataConfigFile.options().copyDefaults(true);
		
		try {
			//dataConfigFile.save(dataFile);
			dataConfigFile.save(dataFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		

		
	}


}
