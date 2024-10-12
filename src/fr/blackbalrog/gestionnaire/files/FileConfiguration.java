package fr.blackbalrog.gestionnaire.files;

import java.io.File;

import fr.blackbalrog.gestionnaire.GestionnaireFrame;
import fr.blackbalrog.gestionnaire.managers.UserManager;

public class FileConfiguration
{
	public static File getFileUser()
	{
		File userFile = new File(GestionnaireFrame.INSTANCE.getUsersDirectory(), UserManager.getUsername() + ".yml");
		if (!userFile.exists())
		{
			System.out.println("Le fichier de " + UserManager.getUsername() + " n'a pas pu être charger correctement");
			return null;
		}
		return userFile;
	}
}
