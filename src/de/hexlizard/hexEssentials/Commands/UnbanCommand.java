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
package de.hexlizard.hexEssentials.Commands;

import org.bukkit.command.CommandSender;

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Main;

public class UnbanCommand extends Command{

	public UnbanCommand(Main main) {
		super(main);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args){
		if(label.equalsIgnoreCase("unban")){
			sender.sendMessage(Colorize.colorize(language.getString("not_implemented_yet_message")));
		}
		return false;
	}

}
