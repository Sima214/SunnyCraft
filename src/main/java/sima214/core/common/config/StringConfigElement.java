package sima214.core.common.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property.Type;

public class StringConfigElement implements IConfigElement {
	public StringConfigElement(String category,String key,String defaultValue) {
		this.category=category;
		this.key=key;
		this.defaultValue=defaultValue;
	}
	public StringConfigElement(String category,String key,String comment,String defaultValue) {
		this.category=category;
		this.key=key;
		this.defaultValue=defaultValue;
		this.comment=comment+"[Default value: "+defaultValue+"]";
	}
	public String category;
	public String key;
	public String defaultValue;
	public String value;
	public String comment;
	@Override
	public void load(Configuration forgeConfig) {
		value=forgeConfig.get(category, key, defaultValue, comment, Type.STRING).getString();
	}
}
