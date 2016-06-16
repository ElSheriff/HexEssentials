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

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BlockBreakListener implements Listener{
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		//TODO remove XP DROP
		
		if(event.getBlock().getType().equals(Material.MOB_SPAWNER)){			
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getState();
			String mob = spawner.getCreatureTypeName();
			if(event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)){
				event.setExpToDrop(0);
				ItemStack spawnerItem = new ItemStack(Material.MOB_SPAWNER);
				spawnerItem.setAmount(1);
				ItemMeta im = spawnerItem.getItemMeta();
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(mob);
				im.setLore(lore);
				spawnerItem.setItemMeta(im);
				event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), spawnerItem);
			}
			
		}
	}

}
