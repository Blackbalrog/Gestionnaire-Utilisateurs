package fr.blackbalrog.gestionnaire.panels.accounts;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import fr.blackbalrog.gestionnaire.GestionnaireFrame;
import fr.blackbalrog.gestionnaire.components.button.Button;
import fr.blackbalrog.gestionnaire.components.label.LabelError;
import fr.blackbalrog.gestionnaire.instances.Instances;
import fr.blackbalrog.gestionnaire.managers.UserManager;
import fr.blackbalrog.gestionnaire.panels.accounts.create.FrameCreateAccount;
import fr.blackbalrog.gestionnaire.panels.accounts.delete.FrameDeleteAccount;
import fr.blackbalrog.gestionnaire.yaml.YamlConfiguration;

@SuppressWarnings("serial")
public class PanelControleActions extends JPanel implements ActionListener
{
	
	private Button button_create_account;
	
	private Button button_edit;
	private boolean etat_button 				= false;
	
	private Button button_delete;
	
	private LabelError error_label;
	
	public PanelControleActions()
	{
		this.setLayout(null);
		this.setBackground(new Color(39, 55, 70));
		this.setBorder(new MatteBorder(1, 0, 0, 0, new Color(93, 109, 126)));
		
		this.button_create_account 				= new Button("Ajouter");
        this.add(this.button_create_account);
        this.button_create_account.setBounds(0, 20, 60, 20);
        this.button_create_account.setColorHover();
        this.button_create_account.setColorClick();
        this.button_create_account.addActionListener(this);
		
		this.button_edit 						= new Button("Modifier");
        this.add(this.button_edit);
        this.button_edit.setBounds(60, 20, 70, 20);
        this.button_edit.setColorHover();
        this.button_edit.setColorClick();
        this.button_edit.addActionListener(this);
        
        this.button_delete 						= new Button("Supprimer");
        this.add(this.button_delete);
        this.button_delete.setBounds(130, 20, 70, 20);
        this.button_delete.setColorHover();
        this.button_delete.setColorClick();
        this.button_delete.addActionListener(this);
        
        this.error_label 						= new LabelError();
        this.add(this.error_label);
        this.error_label.setBounds(0, 35, 300, 20);
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		File userFile 							= new File(GestionnaireFrame.INSTANCE.getUsersDirectory(), UserManager.getUsername() + ".yml");
		if (!userFile.exists())
		{
			this.error_label.setErrorMessage("Le fichier de " + UserManager.getUsername() + " n'a pas pu être charger correctement");
			System.out.println("Le fichier de " + UserManager.getUsername() + " n'a pas pu être charger correctement");
			return;
		}
		
		PanelAccounts panelAccounts 			= Instances.getPanelControle().getPanelAccounts();
		
		YamlConfiguration configurationUser 	= new YamlConfiguration(userFile);
		
		if (event.getSource() == this.button_create_account)
		{
			FrameCreateAccount.main();
			this.button_create_account.setEnabled(false);
		}
		
		else if (event.getSource() == this.button_edit)
		{
			this.etat_button = !this.etat_button;
			this.button_edit.setText(this.etat_button ? this.editText("Valider") : this.editText("Modifier"));
			
			List<JTextField> listFields 			= panelAccounts.getListFields();
			List<JButton> listButtons				= panelAccounts.getListButtons();
            Map<String, JTextField> mailFields 		= panelAccounts.getMailFields();
            Map<String, JTextField> passwordFields 	= panelAccounts.getPasswordFields();
			
			if (this.etat_button == true)
			{
				for (JTextField fields : listFields)
				{
					fields.setEditable(true);
					fields.setCaretColor(new Color(93, 109, 126));
					fields.setSelectionColor(Color.gray);
				}
				
				for (JButton buttons : listButtons)
				{
					buttons.setVisible(true);
				}
			}
			
			else
			{
				for (JTextField fields : listFields)
				{
					fields.setEditable(false);
					fields.setSelectionColor(new Color(0, 0, 0, 0));
					fields.setCaretColor(Color.black);
				}
		        
		        for (String key : mailFields.keySet())
		        {
                    configurationUser.set(key + ".mail", mailFields.get(key).getText());
                    configurationUser.set(key + ".password", passwordFields.get(key).getText());
                }
		        
		        for (JButton buttons : listButtons)
				{
					buttons.setVisible(false);
				}
			}
		}
		
		else if (event.getSource() == this.button_delete)
		{
			FrameDeleteAccount.main();
			this.button_delete.setEnabled(false);
		}
	}
	
	public boolean getEtatButtonEdit()
	{
		return this.etat_button;
	}
	
	public void setEtatButtonEdit(boolean etat_edit)
	{
		this.etat_button = etat_edit;
	}
	
	public JButton getButtonCreateAccount()
	{
		return this.button_create_account;
	}
	
	public JButton getButtonDeleteAccount()
	{
		return this.button_delete;
	}
	
	private String editText(String text)
	{
		return "<html><u>" + text + "</u></html>";
	}
}
