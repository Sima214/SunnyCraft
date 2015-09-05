package sima214.core.client;

import java.util.ArrayList;

import sima214.core.Logger;
import sima214.core.common.CommonProxy;

public class ClientProxy extends CommonProxy{
	private static ArrayList<IResourcePackChangeListener> postRegistry=new ArrayList<IResourcePackChangeListener>();
	public ClientProxy() {
	}
	@Override
	public void post() {
		Logger.info("Reloading "+postRegistry.size()+" registered objects");
		for(IResourcePackChangeListener cur:postRegistry)
		{
			cur.onReload();
		}
	}
	public static void registerClient(IResourcePackChangeListener obj) {
		postRegistry.add(obj);
	}
}
