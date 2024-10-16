package fr.blackbalrog.gestionnaire.utils;

public class Debug
{
	
	private static boolean enableDebug;
	
	public static void setEnable(boolean enable)
	{
		enableDebug = enable;
	}
	
	public static boolean isEnable()
	{
		return enableDebug ? true : false;
	}
}
