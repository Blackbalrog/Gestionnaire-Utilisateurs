package fr.blackbalrog.gestionnaire.instances;

import fr.blackbalrog.gestionnaire.GestionnaireFrame;
import fr.blackbalrog.gestionnaire.GestionnairePanel;
import fr.blackbalrog.gestionnaire.panels.PanelControle;
import fr.blackbalrog.gestionnaire.panels.accounts.PanelAccounts;
import fr.blackbalrog.gestionnaire.panels.accounts.PanelControleActions;

public class Instances
{
	
	public static GestionnaireFrame getGestionnaireFrame()
	{
		return GestionnaireFrame.INSTANCE;
	}
	
	public static GestionnairePanel getGestionnairePanel()
	{
		return GestionnaireFrame.INSTANCE.getPanel();
	}
	
	public static PanelControle getPanelControle()
	{
		return GestionnaireFrame.INSTANCE.getPanel().getPanelControle();
	}
	
	public static PanelAccounts getPanelAccounts()
	{
		return GestionnaireFrame.INSTANCE.getPanel().getPanelControle().getPanelAccounts();
	}
	
	public static PanelControleActions getPanelControleActions()
	{
		return GestionnaireFrame.INSTANCE.getPanel().getPanelControle().getPanelControleActions();
	}
}
