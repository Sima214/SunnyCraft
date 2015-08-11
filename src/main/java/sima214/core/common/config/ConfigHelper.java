package sima214.core.common.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Loader;

public class ConfigHelper {
	public Configuration forgeConfig;
	private IConfigElement[] elements;
	public ConfigHelper(String modid) {
		forgeConfig=new Configuration(new File(Loader.instance().getConfigDir(),modid+".cfg"));
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
		for(IConfigElement curElement:elements){
			curElement.load(forgeConfig);
		}
		if(forgeConfig.hasChanged())forgeConfig.save();
	}
}
