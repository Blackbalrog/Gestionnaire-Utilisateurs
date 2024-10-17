package fr.blackbalrog.gestionnaire.panels.installation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.blackbalrog.gestionnaire.GestionnaireFrame;
import fr.blackbalrog.gestionnaire.color.ColorComponent;
import fr.blackbalrog.gestionnaire.components.button.Button;
import fr.blackbalrog.gestionnaire.managers.LecteurManager;
import fr.blackbalrog.gestionnaire.usb.Lecteur;

@SuppressWarnings("serial")
public class PanelInstallation extends JPanel implements ItemListener, ActionListener
{
	
	private JLabel label_lecteur 				= new JLabel("Lecteur: ");
	private JComboBox<String> box_lecteur 		= new JComboBox<String>();
	private Button validate_button 				= new Button("Valider");
	
	public PanelInstallation()
	{
		this.setLayout(null);
		this.setBackground(ColorComponent.background);
		
		this.label_lecteur.setBounds(10, 20, 50, 20);
		this.label_lecteur.setForeground(ColorComponent.default_label);
		this.add(this.label_lecteur);
		
		this.box_lecteur.addItem("");
		for (String lecteur : Lecteur.getDisksAmovible())
		{
			this.box_lecteur.addItem(lecteur);
			System.out.println(lecteur);
		}
		
		this.box_lecteur.setBounds(65, 20, 160, 20);
		this.add(this.box_lecteur);
		this.box_lecteur.addItemListener(this);
		
		this.validate_button.setColorHover();
		this.validate_button.setColorClick();
		this.validate_button.setBounds(0, 50, 60, 20);
		this.validate_button.addActionListener(this);
		this.add(this.validate_button);
	}

	@Override
	public void itemStateChanged(ItemEvent event)
	{
		if (this.box_lecteur.getSelectedItem() == event.getItem())
		{
			LecteurManager.setLecteur(String.valueOf(this.box_lecteur.getSelectedItem()));
		}
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == this.validate_button)
		{
			if (LecteurManager.getLecteur() != null)
			{
				GestionnaireFrame.main(null);
				FrameInstallation.getInstance().dispose();
			}
		}
	}
}
