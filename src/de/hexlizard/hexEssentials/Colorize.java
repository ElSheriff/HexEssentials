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


import org.bukkit.ChatColor;

public class Colorize {
		
	
	public static String colorize(String string){
		String tmpString = string;
		tmpString = tmpString.replaceAll("&0", ChatColor.BLACK+"");		
		tmpString = tmpString.replaceAll("&1", ChatColor.DARK_BLUE+"");
		tmpString = tmpString.replaceAll("&2", ChatColor.DARK_GREEN+"");
		tmpString = tmpString.replaceAll("&3", ChatColor.DARK_AQUA+"");
		tmpString = tmpString.replaceAll("&4", ChatColor.DARK_RED+"");
		tmpString = tmpString.replaceAll("&5", ChatColor.DARK_PURPLE+"");
		tmpString = tmpString.replaceAll("&6", ChatColor.GOLD+"");
		tmpString = tmpString.replaceAll("&7", ChatColor.GRAY+"");
		tmpString = tmpString.replaceAll("&8", ChatColor.DARK_GRAY+"");
		tmpString = tmpString.replaceAll("&9", ChatColor.BLUE+"");
		tmpString = tmpString.replaceAll("&a", ChatColor.GREEN+"");
		tmpString = tmpString.replaceAll("&b", ChatColor.AQUA+"");
		tmpString = tmpString.replaceAll("&c", ChatColor.RED+"");
		tmpString = tmpString.replaceAll("&d", ChatColor.LIGHT_PURPLE+"");
		tmpString = tmpString.replaceAll("&e", ChatColor.YELLOW+"");
		tmpString = tmpString.replaceAll("&f", ChatColor.WHITE+"");
		tmpString = tmpString.replaceAll("&k", ChatColor.MAGIC+"");
		tmpString = tmpString.replaceAll("&r", ChatColor.RESET+"");
		tmpString = tmpString.replaceAll("&l", ChatColor.BOLD+"");
		tmpString = tmpString.replaceAll("&m", ChatColor.STRIKETHROUGH+"");
		tmpString = tmpString.replaceAll("&n", ChatColor.UNDERLINE+"");
		tmpString = tmpString.replaceAll("&o", ChatColor.ITALIC+"");
		
		
		
		return tmpString;
	}
	

}
