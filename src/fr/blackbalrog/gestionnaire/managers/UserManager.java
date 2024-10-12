package fr.blackbalrog.gestionnaire.managers;

public class UserManager
{
	private static String USER;
	
	public static void setUsername(String username)
	{
		USER = username;
	}
	
	public static String getUsername()
	{
		return USER;
	}
}
