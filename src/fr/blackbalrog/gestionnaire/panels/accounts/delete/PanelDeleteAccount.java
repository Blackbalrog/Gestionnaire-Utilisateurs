package fr.blackbalrog.gestionnaire.panels.accounts.delete;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import fr.blackbalrog.gestionnaire.files.FileConfiguration;
import fr.blackbalrog.gestionnaire.instances.Instances;
import fr.blackbalrog.gestionnaire.yaml.YamlConfiguration;

@SuppressWarnings("serial")
public class PanelDeleteAccount extends JPanel implements ActionListener
{
	
	private JLabel delete_label 				= new JLabel("<html><u>Compte:</u></html>");
	private JTextField delete_field 			= new JTextField();
	
	private JButton validate_button 			= new JButton("<html><u>Valider</u></html>");
	
	public PanelDeleteAccount()
	{
		this.setLayout(null);
		this.setBackground(new Color(39, 55, 70));
		
		this.add(this.delete_label);
		this.delete_label.setBounds(5, 10, 150, 20);
		this.delete_label.setForeground(new Color(93, 109, 126));
		
		this.add(this.delete_field);
		this.delete_field.setBounds(60, 10, 150, 20);
		this.delete_field.setForeground(new Color(93, 109, 126));
		this.delete_field.setOpaque(false);
		this.delete_field.setBorder(new MatteBorder(0, 0, 1, 0, new Color(93, 109, 126)));
		this.delete_field.setSelectionColor(new Color(0, 0, 0, 0));
		this.delete_field.setCaretColor(Color.gray);
		this.delete_field.setSelectionColor(Color.gray);
		
		this.add(this.validate_button);
		this.validate_button.setBounds(305, 70, 60, 20);
		this.validate_button.setBackground(new Color(0, 0, 0, 0));
		this.validate_button.setForeground(new Color(88, 214, 141));
		this.validate_button.setBorderPainted(false);
		this.validate_button.setContentAreaFilled(false);
		this.validate_button.setFocusPainted(false);
		this.validate_button.setMargin(new Insets(0, 0, 0, 0));
		this.validate_button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == this.validate_button)
		{
			if (this.delete_field.getText().isEmpty())
			{
				System.out.println("Veuillez remplir le champ de texte");
				return;
			}
			
			YamlConfiguration configurationUser 		= new YamlConfiguration(FileConfiguration.getFileUser());
			
			if (!configurationUser.contains(this.delete_field.getText()))
			{
				System.out.println("Cette clée n'éxiste pas");
				return;
			}
				
			configurationUser.remove(this.delete_field.getText());
			
			Instances.getPanelControle().getPanelAccounts().setupPanel();
			Instances.getPanelControle().getPanelControleActions().getButtonDeleteAccount().setEnabled(true);
			
			FrameDeleteAccount.getInstance().dispose();
		}
	}
}
