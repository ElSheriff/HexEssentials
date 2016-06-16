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

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Main;

public class SpawnmobCommand extends Command{

	public SpawnmobCommand(Main main) {
		super(main);	
	}
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("spawnmob")){
			//TODO
			//-b for baby, -n <name>, -c <count>, -o <owner>
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(checkPerms(p, label, args)){					
					if(args.length >= 1){
						EntityType entity = null;
						if(args[0].equalsIgnoreCase("sheep")){
							entity = EntityType.SHEEP;
						}else if(args[0].equalsIgnoreCase("chicken")){
							entity = EntityType.CHICKEN;
						}else if(args[0].equalsIgnoreCase("creeper")){
							entity = EntityType.CREEPER;
						}else if(args[0].equalsIgnoreCase("skeleton")){
							entity = EntityType.SKELETON;
						}else if(args[0].equalsIgnoreCase("spider")){
							entity = EntityType.SPIDER;
						}else if(args[0].equalsIgnoreCase("zombie")){
							entity = EntityType.ZOMBIE;
						}else if(args[0].equalsIgnoreCase("slime")){
							entity = EntityType.SLIME;
						}else if(args[0].equalsIgnoreCase("ghast")){
							entity = EntityType.GHAST;
						}else if(args[0].equalsIgnoreCase("pigman")){
							entity = EntityType.PIG_ZOMBIE;
						}else if(args[0].equalsIgnoreCase("enderman")){
							entity = EntityType.ENDERMAN;
						}else if(args[0].equalsIgnoreCase("enderdragon") || args[0].equalsIgnoreCase("dragon")){
							entity = EntityType.ENDER_DRAGON;
						}else if(args[0].equalsIgnoreCase("cavespider")){
							entity = EntityType.CAVE_SPIDER;
						}else if(args[0].equalsIgnoreCase("silverfish")){
							entity = EntityType.SILVERFISH;
						}else if(args[0].equalsIgnoreCase("blaze")){
							entity = EntityType.BLAZE;
						}else if(args[0].equalsIgnoreCase("magmacube")){
							entity = EntityType.MAGMA_CUBE;
						}else if(args[0].equalsIgnoreCase("bat")){
							entity = EntityType.BAT;
						}else if(args[0].equalsIgnoreCase("witch")){
							entity = EntityType.WITCH;
						}else if(args[0].equalsIgnoreCase("endermite")){
							entity = EntityType.ENDERMITE;
						}else if(args[0].equalsIgnoreCase("guardian")){
							entity = EntityType.GUARDIAN;
						}else if(args[0].equalsIgnoreCase("shulker")){
							entity = EntityType.SHULKER;
						}else if(args[0].equalsIgnoreCase("pig")){
							entity = EntityType.PIG;
						}else if(args[0].equalsIgnoreCase("sheep")){
							entity = EntityType.SHEEP;
						}else if(args[0].equalsIgnoreCase("cow")){
							entity = EntityType.COW;
						}else if(args[0].equalsIgnoreCase("squid")){
							entity = EntityType.SQUID;
						}else if(args[0].equalsIgnoreCase("wolf")){
							entity = EntityType.WOLF;
						}else if(args[0].equalsIgnoreCase("mooshroom") || args[0].equalsIgnoreCase("redcow")){
							entity = EntityType.MUSHROOM_COW;
						}else if(args[0].equalsIgnoreCase("ocelot")){
							entity = EntityType.OCELOT;
						}else if(args[0].equalsIgnoreCase("horse")){
							entity = EntityType.HORSE;
						}else if(args[0].equalsIgnoreCase("rabbit")){
							entity = EntityType.RABBIT;
						}else if(args[0].equalsIgnoreCase("polarbear")){
							entity = EntityType.POLAR_BEAR;
						}else if(args[0].equalsIgnoreCase("villager")){
							entity = EntityType.VILLAGER;
						}else if(args[0].equalsIgnoreCase("snowman")){
							entity = EntityType.SNOWMAN;
						}else if(args[0].equalsIgnoreCase("golem") || args[0].equalsIgnoreCase("irongolem")){
							entity = EntityType.IRON_GOLEM;
						}else if(args[0].equalsIgnoreCase("guardian")){
							entity = EntityType.GUARDIAN;
						}else{
							sender.sendMessage(Colorize.colorize(language.getString("not_implemented_yet_message")));
						}
						if(entity != null){
							Location loc = p.getTargetBlock((Set<Material>)null, 200).getLocation();
							loc.setY(loc.getY()+1);
							LivingEntity e = (LivingEntity) p.getWorld().spawnEntity(loc, entity);
							if(args.length == 2){
								e.setCustomName(args[1]);
								e.setCustomNameVisible(true);																	
							}
							
							
						}
						
					}else{
						sender.sendMessage(Colorize.colorize(language.getString("invalid_arguments_message").replaceAll("%command%", label)));
					}
				}else{
					noPerms(p,label,args);
				}
				
				
			}else{
				sender.sendMessage(Colorize.colorize(language.getString("not_for_console_message")));
			}
			return true;
		}
		return false;
	} 

}
