package sima214.tweaks;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
@Mod(modid = "simatweaks",name="Sima Tweaks",version="1")
public class SimaTweaksMain {
			@Mod.Instance("simatweaks")
			public static SimaTweaksMain instance;
			//Registration-Startup events
			@Mod.EventHandler
			public void preinit(FMLPreInitializationEvent event)
			{
			}

			@Mod.EventHandler
			public void init(FMLInitializationEvent event)
			{
				Item theWand=GameRegistry.findItem("ExtraUtilities","builderswand");
				if(theWand!=null){
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(theWand), "EGE","GDG","EGE",'D',Items.diamond_pickaxe,'G',"blockGold",'E',Items.emerald));}
			}

			@Mod.EventHandler
			public void postinit(FMLPostInitializationEvent event)
			{
			}
	}
