package sima214.core.client;

import java.util.ArrayList;

import sima214.core.Logger;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;

public class ResourseReloader {
	public static ResourseReloader reloadHandler;
	private final ArrayList<IResourcePackChangeListener> registry = new ArrayList<IResourcePackChangeListener>();
	public ResourseReloader() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	@SubscribeEvent//I could not find anything better.
	public void onReload(TextureStitchEvent.Post ev){
		if(ev.map.getTextureType()==1){
			Logger.info("Reloading registered objects");
			for(IResourcePackChangeListener cur:registry){
				cur.onReload();
			}
		}
	}
	public static void register(IResourcePackChangeListener obj){
		reloadHandler.registry.add(obj);
	}
	public static void init() {
		reloadHandler=new ResourseReloader();
	}
}
