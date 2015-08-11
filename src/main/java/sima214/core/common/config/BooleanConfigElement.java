package sima214.core.common.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property.Type;

public class BooleanConfigElement implements IConfigElement {
	public BooleanConfigElement(String category,String key,boolean defaultValue) {
		this.category=category;
		this.key=key;
		this.defaultValue=defaultValue;
	}
public BooleanConfigElement(String category,String key,String comment,boolean defaultValue) {
	this.category=category;
	this.key=key;
	this.defaultValue=defaultValue;
	this.comment=comment+"[Default value: "+Boolean.toString(defaultValue)+"]";
}
	public String category;
	public String key;
	public boolean defaultValue;
	public boolean value;
	public String comment;
	@Override
	public void load(Configuration forgeConfig) {
		value=forgeConfig.get(category, key, Boolean.toString(defaultValue), comment, Type.BOOLEAN).getBoolean();
	}
}
