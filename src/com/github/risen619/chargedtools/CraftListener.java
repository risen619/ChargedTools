package com.github.risen619.chargedtools;

import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

import org.bukkit.ChatColor;

public class CraftListener implements Listener
{
	
	public CraftListener() { }

	@EventHandler
	public void onCraftItem(CraftItemEvent e)
	{	
		if(e.getInventory() instanceof CraftingInventory &&
			e.getRecipe() instanceof ShapedRecipe)
		{
			CraftingInventory inv = (CraftingInventory)e.getInventory();
			ShapedRecipe sp = (ShapedRecipe)e.getRecipe();
			ChargedTool ct = null;
			
			try {
				ct = ToolsManager.getInstance().getToolByRecipe(sp);
			} catch (Exception e1) {}
			
			if(ct != null && sp.getKey().equals(ct.getRecipe().getKey()))
			{
				e.getWhoClicked().sendMessage(ChatColor.GREEN + "You crafted " + ct.getSignature() + "!");
				inv.setResult(ct.getTool());
			}
		}
	}
	
}
