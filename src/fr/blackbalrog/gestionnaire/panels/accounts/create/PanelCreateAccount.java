package fr.blackbalrog.gestionnaire.panels.accounts.create;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import fr.blackbalrog.gestionnaire.color.ColorComponent;
import fr.blackbalrog.gestionnaire.components.button.Button;
import fr.blackbalrog.gestionnaire.components.label.LabelError;
import fr.blackbalrog.gestionnaire.files.FileConfiguration;
import fr.blackbalrog.gestionnaire.instances.Instances;
import fr.blackbalrog.gestionnaire.utils.Utils;
import fr.blackbalrog.gestionnaire.yaml.YamlConfiguration;

@SuppressWarnings("serial")
public class PanelCreateAccount extends JPanel implements ActionListener
{
	
	private JLabel information_label 			= new JLabel("<html><u>Création de compte</u></html>");
	
	private JLabel account_label 				= new JLabel("Compte:");
	private JTextField account_field			= new JTextField();
	
	private JLabel mail_label 					= new JLabel("Mail:");
	private JTextField mail_field 				= new JTextField();
	
	private JLabel password_label 				= new JLabel("Password:");
	private JTextField password_field 			= new JTextField();
	private Button generate_password_button 	= new Button("Generer");
	
	private Button validate_button 				= new Button("Valider");
	
	private LabelError error_label 				= new LabelError();
	
	public PanelCreateAccount()
	{
		this.setLayout(null);
		this.setBackground(new Color(39, 55, 70));
		
		int y = 0;
		int x = 5;
		
		this.add(this.information_label);
		this.information_label.setBounds(x, y, 150, 20);
		this.setupLabel(this.information_label);
		
		y = y + 30;
		
		this.add(this.account_label);
		this.account_label.setBounds(x, y, 60, 20);
		this.setupLabel(this.account_label);
		
		this.add(this.account_field);
		this.account_field.setBounds(x + 50, y, 150, 20);
		this.setupField(this.account_field);
		
		y = y + 30;
		
		this.add(this.mail_label);
		this.mail_label.setBounds(x, y, 60, 20);
		this.setupLabel(this.mail_label);
		
		this.add(this.mail_field);
		this.mail_field.setBounds(x + 30, y, 150, 20);
		this.setupField(this.mail_field);
		
		y = y + 30;
		
		this.add(this.password_label);
		this.password_label.setBounds(x, y, 100, 20);
		this.setupLabel(this.password_label);
		
		this.add(this.password_field);
		this.password_field.setBounds(x + 65, y, 150, 20);
		this.setupField(this.password_field);
		
		this.add(this.generate_password_button);
		this.generate_password_button.setBounds(x + 150 + 65, y +3, 60, 20);
		this.generate_password_button.setForeground(ColorComponent.clicked_button);
		this.generate_password_button.addActionListener(this);
		
		y = y + 40;
		
		this.add(this.error_label);
		this.error_label.setBounds(x, y, 300, 20);
		
		x = x + 300;
		
		this.add(this.validate_button);
		this.validate_button.setColorHover();
		this.validate_button.setColorClick();
		this.validate_button.setBounds(x, y, 60, 20);
		this.validate_button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == this.generate_password_button)
		{
			this.password_field.setText(Utils.generateRandomPassword());
		}
		
		if (event.getSource() == this.validate_button)
		{
			if (this.account_field.getText().isEmpty() || this.mail_field.getText().isEmpty() || this.password_field.getText().isEmpty())
			{
				this.error_label.setErrorMessage("Veuillez remplir tous les champs de texte");
				System.out.println("Veuillez remplir tous les champs de texte");
				return;
			}
			
			YamlConfiguration configurationUser 		= new YamlConfiguration(FileConfiguration.getFileUser());
			
			if (configurationUser.contains(this.account_field.getText()))
			{
				this.error_label.setErrorMessage("Cette clée éxiste déjà");
				System.out.println("Cette clée éxiste déjà");
				return;
			}
			
			configurationUser.set(this.account_field.getText() + ".mail", this.mail_field.getText());
			configurationUser.set(this.account_field.getText() + ".password", this.password_field.getText());
			
			Instances.getPanelControleActions().getButtonCreateAccount().setEnabled(true);
			Instances.getPanelAccounts().setupPanel();
			
			FrameCreateAccount.getInstance().dispose();
		}
	}
	
	private void setupField(JTextField textField)
	{
		textField.setForeground(new Color(93, 109, 126));
		textField.setOpaque(false);
		textField.setBorder(new MatteBorder(0, 0, 1, 0, new Color(93, 109, 126)));
		textField.setSelectionColor(new Color(0, 0, 0, 0));
		textField.setCaretColor(Color.gray);
		textField.setSelectionColor(Color.gray);
	}
	
	private void setupLabel(JLabel label)
	{
		label.setForeground(new Color(93, 109, 126));
	}
}
