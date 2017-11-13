package com.github.risen619.chargedtools;

import java.util.ArrayList;
import java.util.HashSet;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class ToolsManager {
	private static ToolsManager instance = null;
	
	public static String chargedMessage = ChatColor.GREEN + "" + ChatColor.BOLD + "Charged!";
	public static String dischargedMessage = ChatColor.YELLOW + "" + ChatColor.BOLD + "Discharged!";
	
	private HashSet<String> tools = null;
	private ArrayList<ChargedTool> toolList = null;
	
	private ToolsManager()
	{
		tools = new HashSet<String>();
		toolList = new ArrayList<ChargedTool>();
		
		toolList.add(new ChargedPickaxe());
		toolList.add(new ChargedShovel());
		toolList.add(new ChargedDispenser());
	}
	
	public static ToolsManager getInstance()
	{
		if(instance == null)
			instance = new ToolsManager();
		return instance;
	}

	public void registerRecipes(Server server)
	{
		for(ChargedTool ct : toolList)
			server.addRecipe(ct.getRecipe());
	}
	
	private String parseSignature(String signature)
	{
		if(signature.contains("Charged "))
			return "Charged " + signature.split("Charged ")[1];
		else return "";
	}
	
	public boolean isTool(String signature)
	{
		for(ChargedTool ct : toolList)
			if(ct.getSignature().equals(signature))
				return true;
		
		return false;
	}
	
	public boolean isChargable(String signature)
	{
		try
		{
			return (isTool(signature) && getTool(signature).isChargable());
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public void charge(int pid, String signature)
	{
		tools.add(pid + ":" + signature);
	}
	
	public void discharge(int pid, String signature)
	{
		tools.remove(pid + ":" + signature);
	}
	
	public boolean isCharged(int pid, String signature)
	{
		return tools.contains(pid + ":" + signature);
	}
	
	public String changeChargeStatus(int pid, String signature)
	{
		if(tools.contains(pid + ":" + signature))
		{
			discharge(pid,signature);
			return dischargedMessage;
		}
		else
		{
			charge(pid,signature);
			return chargedMessage;
		}
		
	}
	
	public void useTool(String signature, Block b, BlockFace bf)
	{
		for(ChargedTool ct : toolList)
		{
			if(signature.equals(ct.getSignature()))
			{
				ct.use(b, bf);
				return;
			}
		}
	}
	
	public ChargedTool getTool(String signature) throws InstantiationException, IllegalAccessException
	{
		for(ChargedTool ct : toolList)
			if(ct.getSignature().equals(signature))
				return ct.getClass().newInstance();
		
		return null;
	}
	
	public ChargedTool getToolByRecipe(ShapedRecipe sp) throws Exception
	{
		for(ChargedTool ct : toolList)
		{
			if(sp.getKey().equals(ct.getRecipe().getKey()))
				return ct;
		}
		throw new Exception("No such tool registered!");
	}
	
	public String getSignature(ItemStack is)
	{
		if(is != null && is.hasItemMeta() && is.getItemMeta().hasLore() && is.getItemMeta().getLore().get(0) != null)
			return parseSignature(is.getItemMeta().getLore().get(0));
		else return "";
	}
	
}
