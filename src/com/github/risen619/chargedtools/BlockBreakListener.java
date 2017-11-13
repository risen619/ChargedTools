package com.github.risen619.chargedtools;

import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener
{
	public BlockBreakListener() { }
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{
		ItemStack inHand = e.getPlayer().getInventory().getItemInMainHand();
		ToolsManager tm = ToolsManager.getInstance();
		String signature = tm.getSignature(inHand);
		
		if(!signature.isEmpty() && tm.isTool(signature) && 
				tm.isCharged(e.getPlayer().getEntityId(), signature))
		{
			String face = "UP";
			double rotation = Math.abs(e.getPlayer().getLocation().getYaw());
			
			if(315 <= rotation || rotation < 45) face = "SOUTH";
			else if(45 <= rotation && rotation < 135) face = "EAST";
			else if(135 <= rotation && rotation < 225) face = "NORTH";
			else face = "WEST";
	         
			tm.useTool(signature, e.getBlock(), BlockFace.valueOf(face));
		}
	}
}
