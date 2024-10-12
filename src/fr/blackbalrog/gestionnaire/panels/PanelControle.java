package fr.blackbalrog.gestionnaire.panels;

import java.awt.Color;

import javax.swing.JPanel;

import fr.blackbalrog.gestionnaire.panels.accounts.PanelAccounts;
import fr.blackbalrog.gestionnaire.panels.accounts.PanelControleActions;

@SuppressWarnings("serial")
public class PanelControle extends JPanel
{

	private PanelAccounts panelAccount;
	private PanelControleActions panelControleActions;

	public PanelControle()
	{
		this.setLayout(null);
		this.setBackground(new Color(39, 55, 70));
		
		this.panelAccount 					= new PanelAccounts();
		this.add(this.panelAccount);
		this.panelAccount.setBounds(0, 10, 385, 300);
		
		this.panelControleActions 				= new PanelControleActions();
		this.add(this.panelControleActions);
		this.panelControleActions.setBounds(0, 300, 400, 100);
	}
	
	public PanelAccounts getPanelAccounts()
	{
		return this.panelAccount;
	}

	public PanelControleActions getPanelControleActions()
	{
		return this.panelControleActions;
	}
}
