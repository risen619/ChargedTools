package com.github.risen619.chargedtools;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class ChargedShovel implements ChargedTool {

	private String signature = "Charged Spade";
	private boolean isChargable = true;
	private ShapedRecipe recipe;
	
	private String name;
	private ArrayList<String> lore;
	
	public ChargedShovel()
	{
		name = "Spade++";
		lore = new ArrayList<String>();
		lore.add(signature);
		
		NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class), "charged_spade");
		recipe = new ShapedRecipe(key, getTool());
		recipe.shape("ttt","dpd","ddd");
		recipe.setIngredient('t', Material.TNT);
		recipe.setIngredient('d', Material.DIAMOND);
		recipe.setIngredient('p', Material.DIAMOND_SPADE);
	}
	
	@Override
	public void use(Block b, BlockFace bf)
	{
		b.getRelative(bf, 1).breakNaturally();
		b.getRelative(bf, 2).breakNaturally();
		b.breakNaturally();
	}

	@Override
	public ShapedRecipe getRecipe() { return recipe; }

	@Override
	public ItemStack getTool()
	{
		ItemStack res = new ItemStack(Material.DIAMOND_SPADE, 1);
		
		ItemMeta im = res.getItemMeta();
		im.setDisplayName(name);
		im.setLore(lore);
		res.setItemMeta(im);
		
		return res;
	}

	@Override
	public String getSignature() { return signature; }

	@Override
	public boolean isChargable() { return isChargable; }

}
