package fr.blackbalrog.gestionnaire.panels.users.delete;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import fr.blackbalrog.gestionnaire.GestionnaireFrame;
import fr.blackbalrog.gestionnaire.GestionnairePanel;
import fr.blackbalrog.gestionnaire.instances.Instances;

@SuppressWarnings("serial")
public class PanelDeleteUser extends JPanel implements ActionListener
{
	
	private JLabel utilisateur_label 			= new JLabel("<html><u>Utilisateur:</u></html>");
	private JTextField utilisateur_field 			= new JTextField();
	
	private JButton validate_button 			= new JButton("<html><u>Supprimer</u></html>");
	
	public PanelDeleteUser()
	{
		this.setLayout(null);
		this.setBackground(new Color(39, 55, 70));
		
		this.add(this.utilisateur_label);
		this.utilisateur_label.setBounds(5, 10, 150, 20);
		this.utilisateur_label.setForeground(new Color(93, 109, 126));
		
		this.add(this.utilisateur_field);
		this.utilisateur_field.setBounds(70, 10, 150, 20);
		this.utilisateur_field.setForeground(new Color(93, 109, 126));
		this.utilisateur_field.setOpaque(false);
		this.utilisateur_field.setBorder(new MatteBorder(0, 0, 1, 0, new Color(93, 109, 126)));
		this.utilisateur_field.setSelectionColor(new Color(0, 0, 0, 0));
		this.utilisateur_field.setCaretColor(Color.gray);
		this.utilisateur_field.setSelectionColor(Color.gray);
		
		this.add(this.validate_button);
		this.validate_button.setBounds(295, 70, 70, 20);
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
			if (this.utilisateur_field.getText().isEmpty())
			{
				System.out.println("Veuillez remplir le champ de texte");
				return;
			}
			
			File userFile = new File(GestionnaireFrame.INSTANCE.getUsersDirectory(), this.utilisateur_field.getText() + ".yml");
			
			if (!userFile.exists())
			{
				System.out.println("Cette utilisateur n'éxiste pas");
				return;
			}
			
			userFile.delete();
			System.out.println("L'utilisateur '" + this.utilisateur_field.getText() + "' a était supprimer avec succèes");
			FrameDeleteUser.INSTANCE.dispose();
			
			GestionnairePanel gestionnairePanel = Instances.getGestionnairePanel();
			gestionnairePanel.getDeleteUserButton().setEnabled(true);
			gestionnairePanel.getListModel().removeElement(this.utilisateur_field.getText());
			
			Instances.getGestionnaireFrame().refresh();
		}
	}
}
