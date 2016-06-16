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

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import de.hexlizard.hexEssentials.Colorize;
import de.hexlizard.hexEssentials.Main;

public class KillmobCommand extends Command{

	public KillmobCommand(Main main) {
		super(main);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args){
		if(label.equalsIgnoreCase("killmob")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(checkPerms(p, label, args)){
					List<Entity> eRawList = p.getWorld().getEntities();
					List<LivingEntity> eList = new ArrayList<LivingEntity>();
					List<LivingEntity> toBeKilledList = new ArrayList<LivingEntity>();
					for(Entity e : eRawList){
						if(e instanceof LivingEntity){
							eList.add((LivingEntity)e);
						}
					}
					eRawList.clear();//I did this to free some memory
					if(args.length == 1){
						//Check if any of all mobs are the one we want, and then kill it!
						if(args[0].equalsIgnoreCase("all")){
							//Kill every Single Mob
							for(Entity e : eList){
								if(e instanceof LivingEntity){	
									if(e instanceof Player == false){
										toBeKilledList.add((LivingEntity) e);
									}									
								}																							
							}
						}else if(args[0].equalsIgnoreCase("hostile")){
							//Kill Hostile Mobs
							for(LivingEntity e : getHostileMobs(eList)){
								toBeKilledList.add(e);
							}
						}else if(args[0].equalsIgnoreCase("friendly")){
							//Kill friendly Mobs
							for(LivingEntity e : getFriendlyMobs(eList)){
								toBeKilledList.add(e);
							}
						}else{
							//Choosen MobType
							for(Entity e : eList){								
								if(e instanceof LivingEntity){
									LivingEntity toBeKilled = (LivingEntity) e;
									if(guessEntityType(args[0]) != null){
										if(toBeKilled.getType().equals(guessEntityType(args[0]))){
											toBeKilledList.add(toBeKilled);
										}
									}															
								}
							}							
						}
						killMobs(toBeKilledList, p);
					}else if(args.length == 0){
						//keine args alle hostiles killen
						for(LivingEntity e : getHostileMobs(eList)){
							toBeKilledList.add(e);
						}
					}else{
						p.sendMessage(Colorize.colorize(language.getString("invalid_arguments_message")));
						return false;
					}
					killMobs(getHostileMobs(eList), p);
					eList.clear();//also for memory reasons
					toBeKilledList.clear();//here as well
					return true;
				}else{
					noPerms(p, label, args);
					return true;
				}				
			}else{
				notForConsole(sender, label);
				return true;
			}
		}
		return false;
	}
	
	private List<LivingEntity> getHostileMobs(List<LivingEntity> mobList){
		List<LivingEntity> hostileList = new ArrayList<LivingEntity>();		
		for(LivingEntity e : mobList){
			if(e instanceof Zombie){				
				hostileList.add(e);
			}else if(e instanceof Creeper){				
				hostileList.add(e);
			}else if(e instanceof EnderDragon){				
				hostileList.add(e);
			}else if(e instanceof Skeleton){				
				hostileList.add(e);
			}else if(e instanceof Enderman){				
				hostileList.add(e);
			}else if(e instanceof Endermite){				
				hostileList.add(e);
			}else if(e instanceof Slime){				
				hostileList.add(e);
			}else if(e instanceof PigZombie){				
				hostileList.add(e);
			}else if(e instanceof Ghast){				
				hostileList.add(e);
			}else if(e instanceof Spider){
				hostileList.add(e);
			}else if(e instanceof CaveSpider){
				hostileList.add(e);
			}else if(e instanceof Silverfish){
				hostileList.add(e);
			}else if(e instanceof Shulker){
				hostileList.add(e);
			}else if(e instanceof Blaze){
				hostileList.add(e);
			}else if(e instanceof Witch){
				hostileList.add(e);
			}else if(e  instanceof MagmaCube){
				hostileList.add(e);
			}else if(e instanceof Guardian){
				hostileList.add(e);
			}
			
		}
		return hostileList;
	}
	
	private List<LivingEntity> getFriendlyMobs(List<LivingEntity> mobList){
		List<LivingEntity> friendlyList = new ArrayList<LivingEntity>();		
		for(LivingEntity e : mobList){
			if(e instanceof Ocelot){				
				friendlyList.add(e);
			}else if(e instanceof Sheep){				
				friendlyList.add(e);
			}else if(e instanceof Rabbit){				
				friendlyList.add(e);
			}else if(e instanceof Wolf){				
				friendlyList.add(e);
			}else if(e instanceof Cow){				
				friendlyList.add(e);
			}else if(e instanceof MushroomCow){				
				friendlyList.add(e);
			}else if(e instanceof Squid){				
				friendlyList.add(e);
			}else if(e instanceof Bat){				
				friendlyList.add(e);
			}else if(e instanceof Chicken){				
				friendlyList.add(e);
			}else if(e instanceof Pig){
				friendlyList.add(e);
			}else if(e instanceof Villager){
				friendlyList.add(e);
			}else if(e instanceof PolarBear){
				friendlyList.add(e);
			}else if(e instanceof Horse){
				friendlyList.add(e);
			}else if(e instanceof IronGolem){
				friendlyList.add(e);
			}else if(e instanceof Snowman){
				friendlyList.add(e);
			}
			
		}
		return friendlyList;		
	}
	
	private void killMobs(List<LivingEntity> mob, Player sender){
		int killCount = 0;
		for(LivingEntity le : mob){
			le.setHealth(0);
			killCount++;
		}
		sender.sendMessage(Colorize.colorize(language.getString("killmob_command_result_message") + killCount));
	}
	
	private EntityType guessEntityType(String mobType){
		EntityType et = null;
		if(mobType.equalsIgnoreCase("sheep")){
			et = EntityType.SHEEP;
		}else if(mobType.equalsIgnoreCase("chicken")){
			et = EntityType.CHICKEN;
		}else if(mobType.equalsIgnoreCase("creeper")){
			et = EntityType.CREEPER;
		}else if(mobType.equalsIgnoreCase("skeleton")){
			et = EntityType.SKELETON;
		}else if(mobType.equalsIgnoreCase("spider")){
			et = EntityType.SPIDER;
		}else if(mobType.equalsIgnoreCase("zombie")){
			et = EntityType.ZOMBIE;
		}else if(mobType.equalsIgnoreCase("slime")){
			et = EntityType.SLIME;
		}else if(mobType.equalsIgnoreCase("ghast")){
			et = EntityType.GHAST;
		}else if(mobType.equalsIgnoreCase("pigman")){
			et = EntityType.PIG_ZOMBIE;
		}else if(mobType.equalsIgnoreCase("enderman")){
			et = EntityType.ENDERMAN;
		}else if(mobType.equalsIgnoreCase("enderdragon") || mobType.equalsIgnoreCase("dragon")){
			et = EntityType.ENDER_DRAGON;
		}else if(mobType.equalsIgnoreCase("cavespider")){
			et = EntityType.CAVE_SPIDER;
		}else if(mobType.equalsIgnoreCase("silverfish")){
			et = EntityType.SILVERFISH;
		}else if(mobType.equalsIgnoreCase("blaze")){
			et = EntityType.BLAZE;
		}else if(mobType.equalsIgnoreCase("magmacube")){
			et = EntityType.MAGMA_CUBE;
		}else if(mobType.equalsIgnoreCase("bat")){
			et = EntityType.BAT;
		}else if(mobType.equalsIgnoreCase("witch")){
			et = EntityType.WITCH;
		}else if(mobType.equalsIgnoreCase("endermite")){
			et = EntityType.ENDERMITE;
		}else if(mobType.equalsIgnoreCase("guardian")){
			et = EntityType.GUARDIAN;
		}else if(mobType.equalsIgnoreCase("shulker")){
			et = EntityType.SHULKER;
		}else if(mobType.equalsIgnoreCase("pig")){
			et = EntityType.PIG;
		}else if(mobType.equalsIgnoreCase("sheep")){
			et = EntityType.SHEEP;
		}else if(mobType.equalsIgnoreCase("cow")){
			et = EntityType.COW;
		}else if(mobType.equalsIgnoreCase("squid")){
			et = EntityType.SQUID;
		}else if(mobType.equalsIgnoreCase("wolf")){
			et = EntityType.WOLF;
		}else if(mobType.equalsIgnoreCase("mooshroom") || mobType.equalsIgnoreCase("redcow")){
			et = EntityType.MUSHROOM_COW;
		}else if(mobType.equalsIgnoreCase("ocelot")){
			et = EntityType.OCELOT;
		}else if(mobType.equalsIgnoreCase("horse")){
			et = EntityType.HORSE;
		}else if(mobType.equalsIgnoreCase("rabbit")){
			et = EntityType.RABBIT;
		}else if(mobType.equalsIgnoreCase("polarbear")){
			et = EntityType.POLAR_BEAR;
		}else if(mobType.equalsIgnoreCase("villager")){
			et = EntityType.VILLAGER;
		}else if(mobType.equalsIgnoreCase("snowman")){
			et = EntityType.SNOWMAN;
		}
		
		return et;
	}

}
