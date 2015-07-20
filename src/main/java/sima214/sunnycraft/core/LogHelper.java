package sima214.sunnycraft.core;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

public class LogHelper {
	public static void log(Level lev,Object obj)
	{
		FMLLog.log("sunnycraft", lev,String.valueOf(obj));
	}
	public static void debug(Object obj)
	{
		log(Level.DEBUG, obj);
	}
	public static void info(Object obj)
	{
		log(Level.INFO, obj);
	}
	public static void warn(Object obj)
	{
		log(Level.WARN, obj);
	}
	public static void error(Object obj)
	{
		log(Level.ERROR, obj);
	}
	public static void trace(Object obj)
	{
		log(Level.TRACE, obj);
	}
	public static void all(Object obj)
	{
		log(Level.ALL, obj);
	}
}
