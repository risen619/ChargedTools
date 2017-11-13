package com.github.risen619.chargedtools;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable()
	{
		ToolsManager.getInstance().registerRecipes(getServer());
		
		getServer().getPluginManager().registerEvents(new CraftListener(), this);
		getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
		getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
		
		getLogger().info("ChargedTools enabled.");
	}
	
	@Override
	public void onDisable()
	{
		getLogger().info("ChargedTools disabled.");
	}
}
