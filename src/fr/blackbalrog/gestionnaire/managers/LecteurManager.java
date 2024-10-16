package fr.blackbalrog.gestionnaire.managers;

public class LecteurManager
{
	
	private static String LECTEUR;
	
	public static void setLecteur(String lecteur)
	{
		LECTEUR = lecteur;
	}
	
	public static String getLecteur()
	{
		return LECTEUR;
	}
}
