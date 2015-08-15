package sima214.core.client;

import java.util.ArrayList;

import sima214.core.common.CommonProxy;

public class ClientProxy extends CommonProxy{
	ArrayList<IResourcePackChangeListener> postRegistry=new ArrayList<IResourcePackChangeListener>();
	public ClientProxy() {
	}
	@Override
	public void post() {
		for(IResourcePackChangeListener cur:postRegistry)
		{
			cur.onReload();
		}
	}
	@Override
	public void registerClient(IResourcePackChangeListener obj) {
		postRegistry.add(obj);
	}
}
