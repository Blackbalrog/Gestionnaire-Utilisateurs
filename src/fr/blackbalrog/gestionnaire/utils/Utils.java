package fr.blackbalrog.gestionnaire.utils;

import java.util.Random;

public class Utils
{
	public static String generateRandomPassword()
	{
		int longueur 				= 15;
		StringBuilder chaine 		= new StringBuilder();
		Random rand 				= new Random();
		
		String caracteresSpeciaux 	= "!@#$%^&*()-_=+[]{}|;:,.<>?";
		
		for (int i = 0; i < longueur; i++)
		{
			int typeDeCaractere 	= rand.nextInt(4);
			char caractere = ' ';
			
			switch (typeDeCaractere)
			{
				case 0:
					// Majuscules A-Z
					caractere 		= (char) (rand.nextInt(26) + 'A');
					break;
				case 1:
					// Minuscules a-z
					caractere 		= (char) (rand.nextInt(26) + 'a');
					break;
				case 2:
					// Chiffres 0-9
					caractere 		= (char) (rand.nextInt(10) + '0');
					break;
				case 3:
					// Caractère spécial
					caractere 		= caracteresSpeciaux.charAt(rand.nextInt(caracteresSpeciaux.length()));
					break;
			}
			chaine.append(caractere);
		}
		return chaine.toString();
	}
	
}
