package fr.blackbalrog.gestionnaire.usb;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Lecteur
{
	private static char[] lettresLecteurs = "DEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private static File lecteurFind;
	
	public static void search()
	{
		for (char lettre : lettresLecteurs)
		{
			File lecteur = new File(lettre + ":\\");
			if (lecteur.exists() && lecteur.isDirectory())
			{
				System.out.println("Vérification du lecteur " + lettre + ":\\");
				File fichier = new File(lecteur, "gestionnaire.txt");

				if (fichier.exists())
				{
					lecteurFind = lecteur;

					System.out.println("Fichier trouvé sur le lecteur " + lettre + ":\\");
					System.out.println("Chemin complet : " + fichier.getAbsolutePath());
					break;
				}
				else
				{
					System.out.println("Le fichier n'a pas été trouver");
					break;
				}
			}
		}
	}
	
	public static List<String> getLecteurs()
	{
		List<String> list = new ArrayList<String>();

		for (char lettre : lettresLecteurs)
		{
			File fileRoot = new File(lettre + ":\\");
			if (fileRoot.exists() && fileRoot.isDirectory())
			{
				list.add(fileRoot.getAbsolutePath());
			}
		}
		return list;
	}
	
	public static List<String> getDisksAmovible()
	{
		List<String> list = new ArrayList<>();

		// Exécuter la commande WMIC pour obtenir les noms des volumes
		try
		{
			Process process = Runtime.getRuntime().exec("wmic logicaldisk get name, description");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
			String line;

			while ((line = reader.readLine()) != null)
			{
				line = line.trim();
				if (line.length() > 0 && !line.equalsIgnoreCase("Name") && !line.equalsIgnoreCase("Description"))
				{
					if (line.startsWith("Disque amovible"))
					{
						list.add(line);
					}
				}
			}
			reader.close();
			process.waitFor();
		}
		catch (IOException | InterruptedException exeption)
		{
			exeption.printStackTrace();
		}

		return list;
	}

	public static File getLecteur()
	{
		return lecteurFind;
	}
}
