package fr.blackbalrog.gestionnaire.panels.installation;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.blackbalrog.gestionnaire.GestionnaireFrame;
import fr.blackbalrog.gestionnaire.color.BaseColor;
import fr.blackbalrog.gestionnaire.managers.LecteurManager;
import fr.blackbalrog.gestionnaire.usb.Lecteur;

@SuppressWarnings("serial")
public class PanelInstallation extends JPanel implements ItemListener, ActionListener
{
	
	private JLabel label_lecteur 				= new JLabel("Lecteur: ");
	private JComboBox<String> box_lecteur 		= new JComboBox<String>();
	private JButton validate_button 			= new JButton("<html><u>Valider</u></html>");
	
	public PanelInstallation()
	{
		this.setLayout(null);
		this.setBackground(BaseColor.background);
		
		this.label_lecteur.setBounds(10, 20, 50, 20);
		this.label_lecteur.setForeground(BaseColor.gray);
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
		
		this.setupButton(this.validate_button, BaseColor.green);
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
	
	private void setupButton(JButton button, Color color)
	{
        button.setBackground(new Color(0, 0, 0, 0));
        button.setForeground(color);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setMargin(new Insets(0, 0, 0, 0));
    }
}
