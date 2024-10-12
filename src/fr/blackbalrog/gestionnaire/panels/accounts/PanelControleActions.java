package fr.blackbalrog.gestionnaire.panels.accounts;

import java.awt.Color;
import java.awt.Insets;
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
import fr.blackbalrog.gestionnaire.instances.Instances;
import fr.blackbalrog.gestionnaire.managers.UserManager;
import fr.blackbalrog.gestionnaire.panels.accounts.create.FrameCreateAccount;
import fr.blackbalrog.gestionnaire.panels.accounts.delete.FrameDeleteAccount;
import fr.blackbalrog.gestionnaire.yaml.YamlConfiguration;

@SuppressWarnings("serial")
public class PanelControleActions extends JPanel implements ActionListener
{
	
	private JButton button_create_account;
	
	private JButton button_edit;
	private boolean etat_button 						= false;
	
	private JButton button_delete;
	
	public PanelControleActions()
	{
		this.setLayout(null);
		this.setBackground(new Color(39, 55, 70));
		this.setBorder(new MatteBorder(1, 0, 0, 0, new Color(93, 109, 126)));
		
		this.button_create_account 					= new JButton("<html><u>Ajouter</u></html>");
        	this.add(this.button_create_account);
        	this.button_create_account.setBounds(0, 20, 60, 20);
        	this.setupButton(this.button_create_account);
		
		this.button_edit 						= new JButton("<html><u>Modifier</u></html>");
        	this.add(this.button_edit);
        	this.button_edit.setBounds(60, 20, 70, 20);
        	this.setupButton(this.button_edit);
        
        	this.button_delete 						= new JButton("<html><u>Supprimer</u></html>");
        	this.add(this.button_delete);
        	this.button_delete.setBounds(130, 20, 70, 20);
        	this.setupButton(this.button_delete);
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		File userFile 							= new File(GestionnaireFrame.INSTANCE.getUsersDirectory(), UserManager.getUsername() + ".yml");
		if (!userFile.exists())
		{
			System.out.println("Le fichier de " + UserManager.getUsername() + " n'a pas pu être charger correctement");
			return;
		}
		
		PanelAccounts panelAccounts 					= Instances.getPanelControle().getPanelAccounts();
		
		YamlConfiguration configurationUser 				= new YamlConfiguration(userFile);
		
		if (event.getSource() == this.button_create_account)
		{
			FrameCreateAccount.main();
			this.button_create_account.setEnabled(false);
		}
		
		else if (event.getSource() == this.button_edit)
		{
			this.etat_button = !this.etat_button;
			this.button_edit.setText(this.etat_button ? this.editText("Valider") : this.editText("Modifier"));
			
			List<JTextField> listFields 				= panelAccounts.getListFields();
			List<JButton> listButtons				= panelAccounts.getListButtons();
            		Map<String, JTextField> mailFields 			= panelAccounts.getMailFields();
            		Map<String, JTextField> passwordFields 			= panelAccounts.getPasswordFields();
			
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
	
	private void setupButton(JButton button)
	{
        button.setBackground(new Color(0, 0, 0, 0));
        button.setForeground(new Color(88, 214, 141));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.addActionListener(this);
    }
}
