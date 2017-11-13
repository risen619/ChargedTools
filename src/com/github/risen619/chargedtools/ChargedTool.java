package com.github.risen619.chargedtools;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public interface ChargedTool
{	
	public boolean isChargable();
	public String getSignature();
	public void use(Block b, BlockFace bf);
	public ShapedRecipe getRecipe();
	public ItemStack getTool();
}
