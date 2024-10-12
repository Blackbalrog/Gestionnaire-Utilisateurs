package fr.blackbalrog.gestionnaire.panels.users.create;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.blackbalrog.gestionnaire.instances.Instances;

@SuppressWarnings("serial")
public class FrameCreateUser extends JFrame
{
	
	public static FrameCreateUser INSTANCE;
	public PanelCreateUser panel;
	
	public FrameCreateUser()
	{
		INSTANCE = this;
		
		this.setTitle("Gestionnaire des Comptes - Création d'utilisateur");
		this.setSize(400, 150);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(false);
		this.setLocationRelativeTo(null);
		this.setContentPane(this.panel = new PanelCreateUser());
		
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent event)
			{
				Instances.getGestionnairePanel().getCreateUserButton().setEnabled(true);
			}
		});
		
		this.setVisible(true);
	}
	
	public static void main()
	{
		SwingUtilities.invokeLater(() -> {
			INSTANCE = new FrameCreateUser();
		});
	}
	
	public PanelCreateUser getPanel()
	{
		return this.panel;
	}
}
