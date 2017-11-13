package com.github.risen619.chargedtools;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class ChargedDispenser implements ChargedTool {

	private String signature = "Charged Dispenser"; 
	private boolean isChargable = false;
	private ShapedRecipe recipe;
	private int depth = 20;
	private int diameter = 15;
	
	private String name;
	private ArrayList<String> lore;
	
	public ChargedDispenser()
	{
		name = "Dispenser++";
		lore = new ArrayList<String>();
		lore.add(signature);
		
		NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class), "charged_dispenser");
		recipe = new ShapedRecipe(key, getTool());
		recipe.shape("ttt","dpd","rrr");
		recipe.setIngredient('t', Material.TNT);
		recipe.setIngredient('d', Material.DIAMOND_BLOCK);
		recipe.setIngredient('p', Material.DISPENSER);
		recipe.setIngredient('r', Material.REDSTONE_BLOCK);
	}
	
	@Override
	public String getSignature() { return signature; }

	@Override
	public boolean isChargable() { return isChargable; }
	
	private ArrayList<Block> takeAroundX(Block b, int radius)
	{
		ArrayList<Block> result = new ArrayList<Block>();
		int edge = radius/2;

		for(int i=-edge; i<=edge; i++)
			for(int j=-edge; j<=edge; j++)
				result.add(b.getLocation().add(0,i,j).getBlock());
		
		return result;
	}
	
	private ArrayList<Block> takeAroundY(Block b, int radius)
	{
		ArrayList<Block> result = new ArrayList<Block>();
		int edge = radius/2;

		for(int i=-edge; i<=edge; i++)
			for(int j=-edge; j<=edge; j++)
				result.add(b.getLocation().add(i,0,j).getBlock());
		
		return result;
	}
	
	private ArrayList<Block> takeAroundZ(Block b, int radius)
	{
		ArrayList<Block> result = new ArrayList<Block>();
		int edge = radius/2;
		
		for(int i=-edge; i<=edge; i++)
			for(int j=-edge; j<=edge; j++)
				result.add(b.getLocation().add(i,j,0).getBlock());
		
		return result;
	}
	
	@Override
	public void use(Block b, BlockFace bf)
	{
		ArrayList<Block> blocksToDestroy = new ArrayList<Block>();
		Block center = b;
		
		if(!(bf.equals(BlockFace.UP) || bf.equals(BlockFace.DOWN)))
			center = b.getRelative(BlockFace.UP, diameter/2);
		
		for(int i=0; i<depth; i++)
		{
			if(bf.equals(BlockFace.NORTH) || bf.equals(BlockFace.SOUTH))
				blocksToDestroy.addAll(takeAroundZ(center.getRelative(bf, i), diameter));
			else if(bf.equals(BlockFace.WEST) || bf.equals(BlockFace.EAST))
				blocksToDestroy.addAll(takeAroundX(center.getRelative(bf, i), diameter));
			else blocksToDestroy.addAll(takeAroundY(center.getRelative(bf, i), diameter));
		}
		
		for(Block block : blocksToDestroy)
			block.breakNaturally();
		
	}

	@Override
	public ShapedRecipe getRecipe() { return recipe; }

	@Override
	public ItemStack getTool()
	{
		ItemStack res = new ItemStack(Material.DISPENSER, 1);
		
		ItemMeta im = res.getItemMeta();
		im.setDisplayName(name);		
		im.setLore(lore);
		res.setItemMeta(im);
		
		return res;
	}

}
