package com.github.risen619.chargedtools;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class BlockPlaceListener implements Listener {

	public BlockPlaceListener() {}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e)
	{
		ToolsManager tm = ToolsManager.getInstance();
		Main plugin = Main.getPlugin(Main.class);
		
		if(tm.isTool(tm.getSignature(e.getItemInHand())))
		{
			String signature = tm.getSignature(e.getItemInHand());
			if(signature.equals(new ChargedDispenser().getSignature()))
				e.getBlockPlaced().setMetadata("signature", new FixedMetadataValue(plugin, signature));
		}
	}
	
}
