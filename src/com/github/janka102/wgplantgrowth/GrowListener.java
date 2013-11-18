package com.github.janka102.wgplantgrowth;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.StructureGrowEvent;

public class GrowListener implements Listener {
    private WorldGuardPlugin wgPlugin;

    public GrowListener(WorldGuardPlugin wgPlugin) {
        this.wgPlugin = wgPlugin;
    }

    @EventHandler
    public void onGrow(BlockGrowEvent event) {
        Block block = event.getBlock();
        if (!wgPlugin.getGlobalRegionManager().allows(WGPlantGrowth.PLANT_GROWTH_FLAG, block.getLocation())) {
//        	block.setType(Material.GLASS);
            event.setCancelled(true);
        }
//        WGPlantGrowth.getCustLogger().info((event.isCancelled()?"Denied ":"Allowed ") + block.getType() + " at x:" + block.getLocation().getX() + " y:" + block.getLocation().getY() + " z:" + block.getLocation().getZ());
    }
    
    @EventHandler
    public void onUseBoneMeal(PlayerInteractEvent event) {
	if (event.getAction() == Action.RIGHT_CLICK_BLOCK
				&& event.getMaterial() == Material.INK_SACK
				&& event.getItem().getDurability() == 15
				&& !wgPlugin.getGlobalRegionManager().allows(WGPlantGrowth.PLANT_GROWTH_FLAG, event.getClickedBlock().getLocation())) {
			event.getPlayer().sendMessage(ChatColor.RED + "Cannot use Bone Meal here!");
			event.setCancelled(true);
		}
    }
    
    @EventHandler
    public void onTreeGrow(StructureGrowEvent event) {
    	if (!wgPlugin.getGlobalRegionManager().allows(WGPlantGrowth.PLANT_GROWTH_FLAG, event.getLocation())) {
//    		event.getLocation().getWorld().getBlockAt(event.getLocation()).setType(Material.GLASS);
            event.setCancelled(true);
        }
//        WGPlantGrowth.getCustLogger().info((event.isCancelled()?"Denied ":"Allowed ") +  event.getSpecies() + " at x:" + event.getLocation().getX() + " y:" + event.getLocation().getY() + " z:" + event.getLocation().getZ());
    }
}
