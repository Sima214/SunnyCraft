package sima214.core.common.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property.Type;

public class IntConfigElement implements IConfigElement {
	public IntConfigElement(String category,String key,int defaultValue) {
		this.category=category;
		this.key=key;
		this.defaultValue=defaultValue;
	}
	public IntConfigElement(String category,String key,String comment,int defaultValue) {
		this.category=category;
		this.key=key;
		this.defaultValue=defaultValue;
		this.comment=comment+"[Default value: "+Integer.toString(defaultValue)+"]";
	}
	public String category;
	public String key;
	public int defaultValue;
	public int value;
	public String comment;
	@Override
	public void load(Configuration forgeConfig) {
		value=forgeConfig.get(category, key, Integer.toString(defaultValue), comment, Type.INTEGER).getInt();
	}
}
