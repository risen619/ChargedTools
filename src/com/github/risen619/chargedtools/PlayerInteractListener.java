package com.github.risen619.chargedtools;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dispenser;

public class PlayerInteractListener implements Listener
{
	public PlayerInteractListener() {}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		ToolsManager tm = ToolsManager.getInstance();
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			Block button = e.getClickedBlock();
			if(button.getType() == Material.WOOD_BUTTON)
				if(button.getRelative(BlockFace.DOWN, 1).getType() == Material.DISPENSER)
				{
					Block dispenser = button.getRelative(BlockFace.DOWN, 1);
					String signature = "";
					if(dispenser.hasMetadata("signature"))
						signature = dispenser.getMetadata("signature").get(0).asString();
					else return;
					
					Dispenser dispMaterial = (Dispenser)dispenser.getState().getData();
					tm.useTool(signature, dispenser, dispMaterial.getFacing());
				}
				
		}
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)
		{
			ItemStack inHand = e.getItem();
			String signature = tm.getSignature(inHand);
			
			if(!signature.isEmpty() && tm.isTool(signature) && tm.isChargable(signature))
				e.getPlayer().sendMessage(tm.changeChargeStatus(e.getPlayer().getEntityId(), signature));
		}
	}
}
