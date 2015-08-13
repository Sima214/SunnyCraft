package sima214.core.client;

import java.util.ArrayList;

import net.minecraftforge.client.event.TextureStitchEvent;
import sima214.core.Logger;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ResourceReloader {
	private ArrayList<IResourcePackChangeListener> registry = new ArrayList<IResourcePackChangeListener>();
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
		ClientProxy.reloadHandler.registry.add(obj);
	}
}
