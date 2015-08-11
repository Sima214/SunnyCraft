package sima214.core.common.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property.Type;

public class FloatConfigElement implements IConfigElement {
	public FloatConfigElement(String category,String key,float defaultValue) {
		this.category=category;
		this.key=key;
		this.defaultValue=defaultValue;
	}
	public FloatConfigElement(String category,String key,String comment,float defaultValue) {
		this.category=category;
		this.key=key;
		this.defaultValue=defaultValue;
		this.comment=comment+"[Default value: "+Float.toString(defaultValue)+"]";
	}
	public String category;
	public String key;
	public float defaultValue;
	public float value;
	public String comment;
	@Override
	public void load(Configuration forgeConfig) {
		value=(float) forgeConfig.get(category, key, Float.toString(defaultValue), comment, Type.DOUBLE).getDouble();
	}
}
