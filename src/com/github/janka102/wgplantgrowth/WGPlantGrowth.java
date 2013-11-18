package com.github.janka102.wgplantgrowth;

import com.mewin.WGCustomFlags.WGCustomFlagsPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.StateFlag;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class WGPlantGrowth extends JavaPlugin {
    public static StateFlag PLANT_GROWTH_FLAG = new StateFlag("plant-growth", true);

    private WorldGuardPlugin wgPlugin;
    private WGCustomFlagsPlugin wgCustPlugin;
    private GrowListener listener;
    
    public static custLogger logger = new custLogger("Plant Growth");
    public static custLogger getCustLogger() {
            return logger;
    }

    public void onEnable() {
        wgPlugin = getWorldGuard();
        wgCustPlugin = getWGCustomFlags();

        if (wgPlugin == null) {
            getLogger().warning("This plugin requires WorldGuard, disabling.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if (wgCustPlugin == null) {
            getLogger().warning("This plugin requires WorldGuard Custom Flags, disabling.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        listener = new GrowListener(wgPlugin);
        getServer().getPluginManager().registerEvents(listener, this);

        wgCustPlugin.addCustomFlag(PLANT_GROWTH_FLAG);
    }

    private WorldGuardPlugin getWorldGuard() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

        if ((plugin == null) || (!(plugin instanceof WorldGuardPlugin))) {
            return null;
        }

        return (WorldGuardPlugin)plugin;
    }

    private WGCustomFlagsPlugin getWGCustomFlags() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WGCustomFlags");

        if ((plugin == null) || (!(plugin instanceof WGCustomFlagsPlugin))) {
            return null;
        }

        return (WGCustomFlagsPlugin)plugin;
    }
}
