package sima214.core.common.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property.Type;

public class DoubleConfigElement implements IConfigElement{
	public DoubleConfigElement(String category,String key,double defaultValue) {
		this.category=category;
		this.key=key;
		this.defaultValue=defaultValue;
	}
	public DoubleConfigElement(String category,String key,String comment,double defaultValue) {
		this.category=category;
		this.key=key;
		this.defaultValue=defaultValue;
		this.comment=comment+"[Default value: "+Double.toString(defaultValue)+"]";
	}
	public String category;
	public String key;
	public double defaultValue;
	public double value;
	public String comment;
	@Override
	public void load(Configuration forgeConfig) {
		value=forgeConfig.get(category, key, Double.toString(defaultValue), comment, Type.DOUBLE).getDouble();
	}
}
