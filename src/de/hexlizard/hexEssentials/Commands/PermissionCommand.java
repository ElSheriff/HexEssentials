package de.hexlizard.hexEssentials.Commands;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import de.hexlizard.hexEssentials.Main;

public class PermissionCommand extends Command{

	public PermissionCommand(Main main) {
		super(main);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args){
		if(label.equalsIgnoreCase("permission")){
			if(checkPerms(sender, label, args)){
				if(args.length == 3){
					//permission add player permission
					HashMap<UUID, PermissionAttachment> permMap = main.getPermissionMap();
					if(args[0].equalsIgnoreCase("grant")){
						if(main.playerOnline(args[1])){
							Player target = Bukkit.getServer().getPlayer(args[1]);
							PermissionAttachment pa = permMap.get(target.getUniqueId());
							pa.setPermission(args[2], true);
							
						}
					}else if(args[0].equalsIgnoreCase("revoke")){
						if(main.playerOnline(args[1])){
							Player target = Bukkit.getServer().getPlayer(args[1]);
							PermissionAttachment pa = permMap.get(target.getUniqueId());
							pa.unsetPermission(args[2]);
							
						}
					}
				}
			}else{
				
			}
		}
		return false;
		
	}

}
