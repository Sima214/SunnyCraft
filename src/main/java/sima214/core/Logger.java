package sima214.core;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;

public class Logger {
	public static void log(Level lev,Object obj)
	{
		FMLLog.log(Constants.CORE_NAME, lev,obj.toString());
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
	public static void exception(Object message, Exception e) {
		LogManager.getLogger(Constants.CORE_NAME).fatal(message, e);
	}
	public static void exceptionFatal(Object message, Exception e) {
		exception(message, e);
		FMLCommonHandler.instance().exitJava(1, false);
	}
}
