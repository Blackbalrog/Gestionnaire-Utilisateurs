package fr.blackbalrog.gestionnaire.panels.accounts.delete;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.blackbalrog.gestionnaire.instances.Instances;

@SuppressWarnings("serial")
public class FrameDeleteAccount extends JFrame
{
	
	private static FrameDeleteAccount INSTANCE;
	private PanelDeleteAccount panel;
	
	public FrameDeleteAccount()
	{
		INSTANCE = this;
		
		this.setTitle("Gestionnaire des Comptes - Suppression de compte");
		this.setSize(400, 150);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(false);
		this.setLocationRelativeTo(null);
		this.setContentPane(this.panel = new PanelDeleteAccount());
		
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent event)
			{
				Instances.getPanelControleActions().getButtonDeleteAccount().setEnabled(true);
			}
		});
		
		this.setVisible(true);
	}
	
	public static void main()
	{
		SwingUtilities.invokeLater(() -> {
			INSTANCE = new FrameDeleteAccount();
		});
	}
	
	public static FrameDeleteAccount getInstance()
	{
		return INSTANCE;
	}
	
	public PanelDeleteAccount getPanelDeleteAccount()
	{
		return this.panel;
	}
}
