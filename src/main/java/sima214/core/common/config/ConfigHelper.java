package sima214.core.common.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import sima214.core.Constants;
import sima214.core.Logger;
import sima214.core.SimaCoreMain;
import cpw.mods.fml.common.Loader;

public class ConfigHelper {
	public Configuration forgeConfig;
	private IConfigElement[] elements;
	public ConfigHelper(String modid) {
		forgeConfig=new Configuration(new File(Loader.instance().getConfigDir()+"/"+Constants.CORE_NAME,modid+".cfg"));
		SimaCoreMain.registerConfig(this);
	}
	public ConfigHelper(String modid,IConfigElement[] elements) {
		this(modid);
		addElements(elements);
	}
	public void addElements(IConfigElement[] elements){
		this.elements=elements;
	}
	public void init(){
		forgeConfig.load();
		try {
			for(IConfigElement curElement:elements){
				curElement.load(forgeConfig);
			}
		} catch (NullPointerException e) {
			Logger.exception("Somebody forgot to register some stuff. Guess who?", e);
		}
		if(forgeConfig.hasChanged()) {
			forgeConfig.save();
		}
	}
}
