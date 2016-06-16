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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class MailBox {
	Player player;
	Main main;
	File mailboxFile;
	FileConfiguration mailboxConfig;
	
	
	public MailBox(Player player){
		this.player = player;
		mailboxFile = new File(main.getDataFolder()+"/mailbox/", player.getUniqueId().toString()+".yml");
		mailboxConfig = YamlConfiguration.loadConfiguration(mailboxFile);
	}
	
	
	public void createDefaultMailbox(){		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		mailboxConfig.addDefault("owner", player.getUniqueId().toString());
		List<String> listOfString = Arrays.asList(timeStamp+"Welcome to " + Bukkit.getServer().getName());
		mailboxConfig.addDefault("mails.CONSOLE", listOfString);		
		
		mailboxConfig.options().copyDefaults(true);
		
		try {
			mailboxConfig.save(mailboxFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	

}
