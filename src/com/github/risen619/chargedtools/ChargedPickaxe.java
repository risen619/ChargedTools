package com.github.risen619.chargedtools;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class ChargedPickaxe implements ChargedTool
{	
	private String signature = "Charged Pickaxe";
	private boolean isChargable = true;
	private ShapedRecipe recipe;
	
	private String name;
	private ArrayList<String> lore;
	
	public ChargedPickaxe()
	{
		name = "Pickaxe++";
		lore = new ArrayList<String>();
		lore.add(signature);
		
		NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class), "charged_pickaxe");
		recipe = new ShapedRecipe(key, getTool());
		recipe.shape("ttt", "dpd", "ddd");
		recipe.setIngredient('d', Material.DIAMOND);
		recipe.setIngredient('p', Material.DIAMOND_PICKAXE);
		recipe.setIngredient('t', Material.TNT);		
	}
	
	public ShapedRecipe getRecipe() { return recipe; }
	
	public void use(Block b, BlockFace bf)
	{
		b.breakNaturally();
		b.getRelative(BlockFace.UP, 1).breakNaturally();
		b.getRelative(BlockFace.DOWN, 1).breakNaturally();
	}
	
	public ItemStack getTool()
	{
		ItemStack res = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		
		ItemMeta im = res.getItemMeta();
		im.setDisplayName(name);		
		im.setLore(lore);
		res.setItemMeta(im);
		
		return res;
	}

	@Override
	public String getSignature() { return signature; }
	
	@Override
	public boolean isChargable() {return isChargable; }
	
}
