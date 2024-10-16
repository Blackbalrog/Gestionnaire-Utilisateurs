package fr.blackbalrog.gestionnaire;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.blackbalrog.gestionnaire.managers.LecteurManager;
import fr.blackbalrog.gestionnaire.panels.installation.FrameInstallation;
import fr.blackbalrog.gestionnaire.usb.Lecteur;
import fr.blackbalrog.gestionnaire.utils.Debug;

@SuppressWarnings("serial")
public class GestionnaireFrame extends JFrame
{
	public static GestionnaireFrame INSTANCE;
	public GestionnairePanel panel;
	
	private File lecteur;
	
	private File root;
	private File directoryUsers;
	private File[] usersFiles;
	
	public GestionnaireFrame()
	{
		INSTANCE 					= this;
		
		Debug.setEnable(false);
		
<<<<<<< HEAD
		if (LecteurManager.getLecteur() == null)
		{
			FrameInstallation.main();
		}
		else
		{
			Lecteur.search();
			
			this.generateDefaultDirectorysAndFiles();
			
			this.usersFiles 			= this.directoryUsers.listFiles();
			
			this.setTitle("Gestionnaire des Comptes");
			this.setSize(600, 400);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setUndecorated(false);
			this.setLocationRelativeTo(null);
			this.setContentPane(this.panel = new GestionnairePanel());
			
			this.setVisible(true);
			
			Thread thread = new Thread(new Runnable()
			{
	            @Override
	            public void run()
	            {
	                while (true)
	                {
	                	if (Lecteur.getLecteur() == null) return;
	                	
	                	if (!Lecteur.getLecteur().exists())
	    				{
	    					System.exit(0);
	    				}
	                    try
	                    {
	                        Thread.sleep(100);
	                    }
	                    catch (InterruptedException exception)
	                    {
	                        System.out.println("Le thread a été interrompu.");
	                    }
	                }
	            }
	        });
			thread.start();
		}
=======
		this.usersFiles 			= this.directoryUsers.listFiles();
		
		this.setTitle("Gestionnaire des Comptes");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(false);
		this.setLocationRelativeTo(null);
		this.setContentPane(this.panel = new GestionnairePanel());
		
		this.setVisible(true);
>>>>>>> fb11cdb932f6a7c32ae16abc1e456373b4c91a07
	}

	private void generateDefaultDirectorysAndFiles()
	{
<<<<<<< HEAD
		
		if (Debug.isEnable())
		{
			this.root 				= new File(System.getenv("APPDATA"), ".Gestionnaire");
			this.directoryUsers 	= new File(root, "Utilisateurs");
	
			this.generateDefaultDirs(root, directoryUsers);
		}
		else
		{
			this.root 				= new File(Lecteur.getLecteur(), ".Gestionnaire");
			this.directoryUsers 	= new File(root, "Utilisateurs");
			
			this.generateDefaultDirs(root, directoryUsers);
		}
=======
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
>>>>>>> fb11cdb932f6a7c32ae16abc1e456373b4c91a07
		
		System.out.println();
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
	
	public File getLecteur()
	{
<<<<<<< HEAD
		return this.lecteur;
=======
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
>>>>>>> fb11cdb932f6a7c32ae16abc1e456373b4c91a07
	}
	
	public void refresh()
	{
		this.revalidate();
		this.repaint();
	}
}
