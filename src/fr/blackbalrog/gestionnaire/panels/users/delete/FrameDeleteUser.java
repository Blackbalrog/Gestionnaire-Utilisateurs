package fr.blackbalrog.gestionnaire.panels.users.delete;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.blackbalrog.gestionnaire.instances.Instances;

@SuppressWarnings("serial")
public class FrameDeleteUser extends JFrame
{
	
	public static FrameDeleteUser INSTANCE;
	private PanelDeleteUser panel;
	
	public FrameDeleteUser()
	{
		INSTANCE = this;
		
		this.setTitle("Gestionnaire des Comptes - Supression d'utilisateur");
		this.setSize(400, 150);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(false);
		this.setLocationRelativeTo(null);
		this.setContentPane(this.panel = new PanelDeleteUser());
		
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent event)
			{
				Instances.getGestionnairePanel().getDeleteUserButton().setEnabled(true);
			}
		});
		
		this.setVisible(true);
	}
	
	public static void main()
	{
		SwingUtilities.invokeLater(() -> {
			INSTANCE = new FrameDeleteUser();
		});
	}
	
	public PanelDeleteUser getPanel()
	{
		return this.panel;
	}
}
