package fr.blackbalrog.gestionnaire.panels.users.create;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import fr.blackbalrog.gestionnaire.GestionnaireFrame;
import fr.blackbalrog.gestionnaire.GestionnairePanel;
import fr.blackbalrog.gestionnaire.components.button.Button;
import fr.blackbalrog.gestionnaire.components.label.LabelError;
import fr.blackbalrog.gestionnaire.instances.Instances;

@SuppressWarnings("serial")
public class PanelCreateUser extends JPanel implements ActionListener
{
	
	private JLabel utilisateur_label 			= new JLabel("<html><u>Utilisateur:</u></html>");
	private JTextField utilisateur_field 		= new JTextField();
	
	private Button validate_button 				= new Button("Créer");
	
	private LabelError error_label 				= new LabelError();
	
	public PanelCreateUser()
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
		
		this.validate_button.setColorHover();
		this.validate_button.setColorClick();
		this.validate_button.setBounds(305, 70, 60, 20);
		this.add(this.validate_button);
		this.validate_button.addActionListener(this);
		
		this.add(this.error_label);
		this.error_label.setBounds(5, 70, 300, 20);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == this.validate_button)
		{
			if (this.utilisateur_field.getText().isEmpty())
			{
				this.error_label.setErrorMessage("Veuillez remplir le champ de texte");
				System.out.println("Veuillez remplir le champ de texte");
				return;
			}
			
			File userFile = new File(GestionnaireFrame.INSTANCE.getUsersDirectory(), this.utilisateur_field.getText() + ".yml");
			
			if (userFile.exists())
			{
				this.error_label.setErrorMessage("Cette utilisateur éxiste déjà");
				System.out.println("Cette utilisateur éxiste déjà");
				return;
			}
			
			try
			{
				userFile.createNewFile();
			}
			catch (IOException exeption)
			{
				
			}
			
			System.out.println("L'utilisateur '" + this.utilisateur_field.getText() + "' a était créer avec succèes");
			FrameCreateUser.INSTANCE.dispose();
			
			GestionnairePanel gestionnairePanel = Instances.getGestionnairePanel();
			gestionnairePanel.getCreateUserButton().setEnabled(true);
			gestionnairePanel.getListModel().addElement(this.utilisateur_field.getText());
			
			Instances.getGestionnaireFrame().refresh();
		}
	}
}
