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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

import de.hexlizard.hexEssentials.Commands.AfkCommand;
import de.hexlizard.hexEssentials.Commands.BackCommand;
import de.hexlizard.hexEssentials.Commands.BanCommand;
import de.hexlizard.hexEssentials.Commands.BlockCommand;
import de.hexlizard.hexEssentials.Commands.ClearCommand;
import de.hexlizard.hexEssentials.Commands.DayCommand;
import de.hexlizard.hexEssentials.Commands.DeleteHomeCommand;
import de.hexlizard.hexEssentials.Commands.FlyCommand;
import de.hexlizard.hexEssentials.Commands.GamemodeCommand;
import de.hexlizard.hexEssentials.Commands.GodCommand;
import de.hexlizard.hexEssentials.Commands.HatCommand;
import de.hexlizard.hexEssentials.Commands.HealCommand;
import de.hexlizard.hexEssentials.Commands.HomeCommand;
import de.hexlizard.hexEssentials.Commands.HomesCommand;
import de.hexlizard.hexEssentials.Commands.KillmobCommand;
import de.hexlizard.hexEssentials.Commands.MotdCommand;
import de.hexlizard.hexEssentials.Commands.MsgCommand;
import de.hexlizard.hexEssentials.Commands.MuteCommand;
import de.hexlizard.hexEssentials.Commands.NickCommand;
import de.hexlizard.hexEssentials.Commands.NightCommand;
import de.hexlizard.hexEssentials.Commands.OpCommand;
import de.hexlizard.hexEssentials.Commands.OpsCommand;
import de.hexlizard.hexEssentials.Commands.SethomeCommand;
import de.hexlizard.hexEssentials.Commands.SetspawnCommand;
import de.hexlizard.hexEssentials.Commands.SetwarpCommand;
import de.hexlizard.hexEssentials.Commands.SpawnCommand;
import de.hexlizard.hexEssentials.Commands.SpawnmobCommand;
import de.hexlizard.hexEssentials.Commands.TestCommand;
import de.hexlizard.hexEssentials.Commands.TopCommand;
import de.hexlizard.hexEssentials.Commands.UnbanCommand;
import de.hexlizard.hexEssentials.Commands.UnmuteCommand;
import de.hexlizard.hexEssentials.Commands.WarpCommand;
import de.hexlizard.hexEssentials.Commands.WarpsCommand;
import de.hexlizard.hexEssentials.Listeners.BlockBreakListener;
import de.hexlizard.hexEssentials.Listeners.BlockPlaceListener;
import de.hexlizard.hexEssentials.Listeners.PlayerAfkChangeListener;
import de.hexlizard.hexEssentials.Listeners.PlayerBedEnterListener;
import de.hexlizard.hexEssentials.Listeners.PlayerChatListener;
import de.hexlizard.hexEssentials.Listeners.PlayerDeathListener;
import de.hexlizard.hexEssentials.Listeners.PlayerInteractListener;
import de.hexlizard.hexEssentials.Listeners.PlayerJoinListener;
import de.hexlizard.hexEssentials.Listeners.PlayerMoveListener;
import de.hexlizard.hexEssentials.Listeners.PlayerQuitListener;
import de.hexlizard.hexEssentials.Listeners.ServerListPingListener;

public class Main extends JavaPlugin{
	
	private FileConfiguration languageConfig = null;
	private File languageConfigFile = null;
	private File defaultConf = new File(getDataFolder(), "config.yml");
	String language = "EN";
	HashMap<UUID, PermissionAttachment> permissionMap = new HashMap<UUID, PermissionAttachment>();
	
	@Override
	public void onEnable(){
		if(!defaultConf.exists() || this.getConfig().getBoolean("first_run")){
			createDefaultConfig();
			createDefaultLanguage();
		}
		System.out.println("load Configuration.");
		loadConfig();
		System.out.println("Done.");
		System.out.println("load Language: "+ this.getConfig().getString("language"));
		loadLanguage(this.getConfig().getString("language"));
		System.out.println("Done.");
		System.out.println("register Listeners.");
		registerListeners();
		System.out.println("Done.");
		System.out.println("register Commands.");
		registerCommands();
		System.out.println("Done.");
		System.out.println("register Events.");
		registerEvents();
		System.out.println("Done");
	}
	
	@Override
	public void onDisable(){
		
	}
	
	private void createDefaultConfig(){
		this.getConfig().addDefault("version", this.getDescription().getVersion());
		this.getConfig().addDefault("first_run", true);
		this.getConfig().addDefault("language", "EN");		
		this.getConfig().addDefault("motd", Bukkit.getServer().getMotd());
		this.getConfig().addDefault("custom_join_message", true);
		this.getConfig().addDefault("use_permission_system", false);
		this.getConfig().addDefault("custom_quit_message", true);
		this.getConfig().addDefault("silktouch_spawner", false);
		this.getConfig().addDefault("afk_nametag", true);
		this.getConfig().addDefault("players_need_to_sleep_percent", 50);
		this.getConfig().addDefault("spawn.world", Bukkit.getWorlds().get(0).getName());
		this.getConfig().addDefault("spawn.x", Bukkit.getWorlds().get(0).getSpawnLocation().getX());
		this.getConfig().addDefault("spawn.y", Bukkit.getWorlds().get(0).getSpawnLocation().getY());
		this.getConfig().addDefault("spawn.z", Bukkit.getWorlds().get(0).getSpawnLocation().getZ());
		this.getConfig().addDefault("enable_chatcolor", true);
		this.getConfig().addDefault("sign_edit", true);
		this.getConfig().addDefault("sneak_right_rename_mob", false);
		
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	private void createDefaultLanguage(){
		loadLanguage(language);
		this.languageConfig.addDefault("no_permissions_message", "No permissions for: %command% %args%!");
		this.languageConfig.addDefault("target_offline_message", "%target% is offline!");
		this.languageConfig.addDefault("ops_message", "Operators: %ops%");
		this.languageConfig.addDefault("player_join_message", " [+] %player%");
		this.languageConfig.addDefault("player_quit_message", " [-] %player%");
		this.languageConfig.addDefault("player_bed_enter_message", "The bed is nice and comfy...");
		this.languageConfig.addDefault("player_bed_sleep_message", "You are sleeping");
		this.languageConfig.addDefault("invalid_arguments_message", "Invalid arguments for %command%! (wrong, too few or to many)");
		this.languageConfig.addDefault("not_for_console_message", "%command% isn't supposed to be used on the Console");
		this.languageConfig.addDefault("not_implemented_yet_message", "Function is not implemented yet!");
		this.languageConfig.addDefault("fly_command.start_fly", "Fly! You are free like a bird now!");
		this.languageConfig.addDefault("fly_command.stop_fly", "You are bound to the ground now. Again.");
		this.languageConfig.addDefault("msg_command.sender", "You sent %target% a Message!");
		this.languageConfig.addDefault("msg_command.receiver", "Message from %sender%: %message%");
		this.languageConfig.addDefault("lift_error_message", "The Lift is not usable!");
		this.languageConfig.addDefault("afk_command_message_start", "%player% is now AFK");
		this.languageConfig.addDefault("afk_command_message_end", "%player% is no longer AFK");
		this.languageConfig.addDefault("motd_command_current_motd_message", "Current Message of the Day:");
		this.languageConfig.addDefault("motd_command_set_motd_message", "MOTD set to:");
		this.languageConfig.addDefault("killmob_command_result_message", "Killed mobs: ");
		this.languageConfig.addDefault("god_command_start_message", "You are a God now.");
		this.languageConfig.addDefault("god_command_end_message", "Now you only are a ordinary human again.");
		this.languageConfig.addDefault("gamemode_command_change_message", "Gamemode changed to: %gamemode%");
		this.languageConfig.addDefault("gamemode_command_change_message_admin", "%player% changed to gamemode: %gamemode%");
		this.languageConfig.addDefault("home_command_home_not_found_message", "Can't find Home %home%!");
		this.languageConfig.addDefault("home_command_home_teleported_to_home_message", "Teleported to %home%! Welcome Home.");
		this.languageConfig.addDefault("home_command_cant_delete_home_message", "You cant delete your bed and default home!");
		
		this.languageConfig.options().copyDefaults(true);
		
		try {
			this.languageConfig.save(languageConfigFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadLanguage(String language){
		if(languageConfigFile == null){			
			languageConfigFile = new File(getDataFolder()+"/lib/lang/", language+".yml");
		}
		languageConfig = YamlConfiguration.loadConfiguration(languageConfigFile);
		
		
	}
	
	private void loadConfig(){
		this.language = this.getConfig().getString("language");
	}
	
	
	private void registerListeners(){
		//TODO alphabetisch sortieren
		this.getServer().getPluginManager().registerEvents(new PlayerAfkChangeListener(this), this);
		this.getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
		this.getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerBedEnterListener(this), this);
		this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
		this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
		this.getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
		this.getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
		this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
		this.getServer().getPluginManager().registerEvents(new PlayerChatListener(this), this);
		this.getServer().getPluginManager().registerEvents(new ServerListPingListener(this), this);
	}
	
	private void registerCommands(){
		//TODO alphabetisch sortieren
		this.getCommand("afk").setExecutor(new AfkCommand(this));//
		this.getCommand("back").setExecutor(new BackCommand(this));
		this.getCommand("ban").setExecutor(new BanCommand(this));//
		this.getCommand("block").setExecutor(new BlockCommand(this));
		this.getCommand("clear").setExecutor(new ClearCommand(this));
		this.getCommand("day").setExecutor(new DayCommand(this));
		this.getCommand("deletehome").setExecutor(new DeleteHomeCommand(this));
		this.getCommand("fly").setExecutor(new FlyCommand(this));
		this.getCommand("gamemode").setExecutor(new GamemodeCommand(this));
		this.getCommand("god").setExecutor(new GodCommand(this));
		this.getCommand("hat").setExecutor(new HatCommand(this));
		this.getCommand("heal").setExecutor(new HealCommand(this));//
		this.getCommand("home").setExecutor(new HomeCommand(this));//
		this.getCommand("homes").setExecutor(new HomesCommand(this));//
		this.getCommand("killmob").setExecutor(new KillmobCommand(this));
		this.getCommand("msg").setExecutor(new MsgCommand(this));
		this.getCommand("motd").setExecutor(new MotdCommand(this));
		this.getCommand("mute").setExecutor(new MuteCommand(this));//
		this.getCommand("nick").setExecutor(new NickCommand(this));//
		this.getCommand("night").setExecutor(new NightCommand(this));
		this.getCommand("ops").setExecutor(new OpsCommand(this));
		this.getCommand("op").setExecutor(new OpCommand(this));//
		this.getCommand("sethome").setExecutor(new SethomeCommand(this));//
		this.getCommand("setspawn").setExecutor(new SetspawnCommand(this));
		this.getCommand("setwarp").setExecutor(new SetwarpCommand(this));//
		this.getCommand("spawn").setExecutor(new SpawnCommand(this));
		this.getCommand("spawnmob").setExecutor(new SpawnmobCommand(this));//
		this.getCommand("top").setExecutor(new TopCommand(this));
		this.getCommand("unmute").setExecutor(new UnmuteCommand(this));//
		this.getCommand("unban").setExecutor(new UnbanCommand(this));//
		this.getCommand("warp").setExecutor(new WarpCommand(this));//
		this.getCommand("warps").setExecutor(new WarpsCommand(this));//
		
		
		
		
		this.getCommand("test").setExecutor(new TestCommand(this));//
		
						
	}
	
	private void registerEvents(){
		
	}
	
	public HashMap<UUID, PermissionAttachment> getPermissionMap(){
		return this.permissionMap;
	}
	
	public void loadPermissions(){
		File f = new File(getDataFolder()+"/permission/", "global.yml");
		FileConfiguration fConf = YamlConfiguration.loadConfiguration(f);
		
		fConf.addDefault("group", Arrays.asList("", ""));
		
		
	}
	
	
	
	
	public void setPermissionMap(HashMap<UUID, PermissionAttachment> permissionMap){
		this.permissionMap = permissionMap;
	}
	
	private void reloadLanguage(){
		loadLanguage(language);
		
	}
	
	
	public FileConfiguration getLanguage(){
		if(languageConfig == null){
			reloadLanguage();			
		}
		return languageConfig;
	}
	
	
	
	public boolean playerOnline(String name){
		if(Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getPlayer(name))){
			return true;
		}
		
		return false;
	}
	
	public Location deserializeLocation(String raw){
		raw = raw.substring(1);
		raw = raw.substring(0 ,raw.length()-1);
		String[] locRaw = raw.split(",");
		
		
		for(short s = 0; s<=5; s++){
			locRaw[s] = locRaw[s].replaceAll("x=", "").replaceAll("y=", "").replaceAll("z=", "").replaceAll("pitch=", "").replaceAll("yaw=", "").replaceAll(" ", "").replaceAll("world=", "");
		}
		World w = Bukkit.getWorld(locRaw[0]);
		Double x = Double.parseDouble(locRaw[1]);
		Double y = Double.parseDouble(locRaw[2]);
		Double z = Double.parseDouble(locRaw[3]);
		float pitch = Float.parseFloat(locRaw[4]);
		float yaw = Float.parseFloat(locRaw[5]);
		
		return new Location(w, x, y, z, yaw, pitch);
	}
	

}
