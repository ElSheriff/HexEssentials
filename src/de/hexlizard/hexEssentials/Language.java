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

import org.bukkit.configuration.file.FileConfiguration;

public class Language {
	Main main;
	FileConfiguration language;
	
	public Language(Main main){
		this.main = main;
		this.language = main.getLanguage();
	}
	
	
	public String noPerms(String label, String[] args){
		//I need to to this better
		return Colorize.colorize(language.getString("no_permissions_message").replaceAll("%command%", label).replaceAll("%args%", args[0]));
	}
	
	public String targetOffline(String name){
		return Colorize.colorize(language.getString("target_offline_message").replaceAll("%target%", name));
	}
	
	public String invalidArgs(String label){
		return Colorize.colorize(language.getString("invalid_arguments_message").replaceAll("%command%", label));
	}
	
	public String notForConsole(String label){
		return Colorize.colorize(language.getString("not_for_console_message").replaceAll("%command%", label));
	}
	
	public String notImplementedYet(){
		return Colorize.colorize(language.getString("not_implemented_yet_message"));
	}
	

}
