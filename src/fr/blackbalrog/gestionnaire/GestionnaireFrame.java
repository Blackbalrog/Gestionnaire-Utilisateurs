package fr.blackbalrog.gestionnaire;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.blackbalrog.gestionnaire.yaml.YamlConfiguration;

@SuppressWarnings("serial")
public class GestionnaireFrame extends JFrame
{
	public static GestionnaireFrame INSTANCE;
	public GestionnairePanel panel;
	
	private File root;
	private File directoryUsers;
	private File[] usersFiles;
	
	private File defaultUser;
	
	public GestionnaireFrame()
	{
		INSTANCE 				= this;
		
		this.generateDefaultDirectorysAndFiles();
		//this.createDefautlUser();
		
		this.usersFiles 			= this.directoryUsers.listFiles();
		
		this.setTitle("Gestionnaire des Comptes");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(false);
		this.setLocationRelativeTo(null);
		this.setContentPane(this.panel = new GestionnairePanel());
		
		this.setVisible(true);
	}
	
	private void generateDefaultDirectorysAndFiles()
	{
		this.root 				= new File(System.getenv("APPDATA"), ".Gestionnaire");
		this.directoryUsers 			= new File(root, "Utilisateurs");
		this.defaultUser 			= new File(directoryUsers, "defaultUtilisateur.yml");

		this.generateDefaultDirs(root, directoryUsers);
		System.out.println();
		//this.generateDefaultFiles(defaultUser);
		System.out.println();
		System.out.println();
	}
	
	@SuppressWarnings("unused")
	private void createDefautlUser()
	{
		YamlConfiguration configurationUsers 	= new YamlConfiguration(this.defaultUser);
		
		configurationUsers.set("pterodactyl.mail", "pterodactyl@mail.fr");
		configurationUsers.set("pterodactyl.password", "pterodactyl");
		
		configurationUsers.set("rise.mail", "rise@mail.fr");
		configurationUsers.set("rise.password", "rise");
	}
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(() -> {
			INSTANCE = new GestionnaireFrame();
		});
	}
	
	public GestionnairePanel getPanel()
	{
		return this.panel;
	}
	
	public File getRootDirectory()
	{
		return this.root;
	}
	
	public File getUsersDirectory()
	{
		return this.directoryUsers;
	}
	
	public File[] getUsersFiles()
	{
		return this.usersFiles;
	}
	
	private void generateDefaultDirs(File... dirs)
	{
		System.out.println("Generation des dossiers par default:");
		for (File dir : dirs)
		{
			if (!dir.exists())
			{
				dir.mkdirs();
				System.out.println("Generation du dossier: " + dir.getAbsolutePath());
	        	}
		}
	}
	
	@SuppressWarnings("unused")
	private void generateDefaultFiles(File... files)
	{
		try
		{
			System.out.println("Generation des fichiers par default:");
			for (File file : files)
			{
				if (!file.exists())
		        	{
					file.createNewFile();
					System.out.println("Generation du fichier: " + file.getAbsolutePath());
		        	}
			}
        	}
		catch (IOException exeption)
		{
			
        	}
	}
	
	public void refresh()
	{
		this.revalidate();
		this.repaint();
	}
}
