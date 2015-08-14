package sima214.core.client;

import java.util.ArrayList;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import sima214.core.Logger;

public class ResourceReloader implements IResourceManagerReloadListener{
	private ArrayList<IResourcePackChangeListener> registry = new ArrayList<IResourcePackChangeListener>();
	public static void register(IResourcePackChangeListener obj){
		ClientProxy.reloadHandler.registry.add(obj);
	}
	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		Logger.info("Reloading registered objects");
		for(IResourcePackChangeListener cur:registry){
			cur.onReload();
		}
	}

}
