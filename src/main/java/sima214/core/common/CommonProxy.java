package sima214.core.common;

import sima214.core.client.IResourcePackChangeListener;

public abstract class CommonProxy {

	public abstract void post();

	public abstract void registerClient(IResourcePackChangeListener obj);

}
