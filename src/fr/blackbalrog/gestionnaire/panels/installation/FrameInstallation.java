package fr.blackbalrog.gestionnaire.panels.installation;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class FrameInstallation extends JFrame
{
	
	private static FrameInstallation INSTANCE;
	public PanelInstallation panel;
	
	public FrameInstallation()
	{
		INSTANCE = this;
		
		this.setTitle("Gestionnaire des Comptes");
		this.setSize(300, 150);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(false);
		this.setLocationRelativeTo(null);
		this.setContentPane(this.panel = new PanelInstallation());
		
		this.setVisible(true);
	}
	
	public static void main()
	{
		SwingUtilities.invokeLater(() -> {
			INSTANCE = new FrameInstallation();
		});
	}
	
	public static FrameInstallation getInstance()
	{
		return INSTANCE;
	}
	
	public PanelInstallation getPanelInstallation()
	{
		return this.panel;
	}
}
