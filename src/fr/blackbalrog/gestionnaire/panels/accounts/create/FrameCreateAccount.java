package fr.blackbalrog.gestionnaire.panels.accounts.create;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.blackbalrog.gestionnaire.instances.Instances;

@SuppressWarnings("serial")
public class FrameCreateAccount extends JFrame
{
	
	private static FrameCreateAccount INSTANCE;
	private PanelCreateAccount panel;
	
	public FrameCreateAccount()
	{
		INSTANCE = this;
		
		this.setTitle("Gestionnaire des Comptes - Création de compte");
		this.setSize(400, 200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(false);
		this.setLocationRelativeTo(null);
		this.setContentPane(this.panel = new PanelCreateAccount());
		
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent event)
			{
				Instances.getPanelControleActions().getButtonCreateAccount().setEnabled(true);
			}
		});
		
		this.setVisible(true);
	}
	
	public static void main()
	{
		SwingUtilities.invokeLater(() -> {
			INSTANCE = new FrameCreateAccount();
		});
	}
	
	public PanelCreateAccount getPanelCreateAccount()
	{
		return this.panel;
	}
	
	public static FrameCreateAccount getInstance()
	{
		return INSTANCE;
	}
}
