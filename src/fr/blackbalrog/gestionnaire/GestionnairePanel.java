package fr.blackbalrog.gestionnaire;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.blackbalrog.gestionnaire.components.button.Button;
import fr.blackbalrog.gestionnaire.components.renders.ListRenderer;
import fr.blackbalrog.gestionnaire.instances.Instances;
import fr.blackbalrog.gestionnaire.managers.UserManager;
import fr.blackbalrog.gestionnaire.panels.PanelControle;
import fr.blackbalrog.gestionnaire.panels.users.create.FrameCreateUser;
import fr.blackbalrog.gestionnaire.panels.users.delete.FrameDeleteUser;

@SuppressWarnings("serial")
public class GestionnairePanel extends JPanel implements ListSelectionListener, ActionListener
{
	
	private File[] USERS_FILE 						= GestionnaireFrame.INSTANCE.getUsersFiles();
	private DefaultListModel<String> listModel;
	
	private JLabel utilisateurs_label 				= new JLabel("Liste des utilisateurs");
	private ListRenderer listRenderer;
	private JList<String> list;
	
	private Button create_user_button 				= new Button("Nouveau");
	private Button delete_user_button 				= new Button("Supprimer");
	
	private PanelControle panelControle;
	
	
	public GestionnairePanel()
	{
		this.setLayout(null);
		
		this.setBackground(new Color(39, 55, 70));
		
		this.add(this.utilisateurs_label);
		this.utilisateurs_label.setBounds(0, 0, 180, 20);
		this.utilisateurs_label.setBorder(BorderFactory.createCompoundBorder(
			    new MatteBorder(0, 0, 1, 1, new Color(93, 109, 126)),
			    new EmptyBorder(0, 5, 0, 0)
			));
		this.utilisateurs_label.setForeground(new Color(93, 109, 126));
		
		this.add(this.create_user_button);
		this.create_user_button.setBounds(20, 319, 60, 20);
		this.create_user_button.setColorHover();
		this.create_user_button.setColorClick();
		this.create_user_button.addActionListener(this);
		
		this.add(this.delete_user_button);
		this.delete_user_button.setBounds(70, 319, 100, 20);
		this.delete_user_button.setColorHover();
		this.delete_user_button.setColorClick();
		this.delete_user_button.addActionListener(this);
		
		this.listRenderer 		= new ListRenderer();
		this.listModel 			= new DefaultListModel<>();
		this.list 				= new JList<>(this.listModel);
		
		this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.list.setLayoutOrientation(JList.VERTICAL_WRAP);
		this.list.setVisibleRowCount(-1);
		this.list.setCellRenderer(this.listRenderer);
		this.listRenderer.setHover(this.list);
		this.list.addListSelectionListener(this);
		this.list.setBackground(new Color(39, 55, 70));
		
		JScrollPane scrollPane 	= new JScrollPane(this.list);
		this.add(scrollPane);
		scrollPane.setBounds(0, 20, 180, 280);
		scrollPane.setBorder(new MatteBorder(0, 0, 1, 1, new Color(93, 109, 126)));
		
		if (USERS_FILE.length == 0) return;
		
		if (this.USERS_FILE != null && this.USERS_FILE.length != 0)
		{
			for (File fileName : this.USERS_FILE)
			{
				this.listModel.addElement(fileName.getName().replace(".yml", ""));
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent event)
	{
		if (!event.getValueIsAdjusting())
		{
			if (event.getSource().equals(this.list))
			{
				String userSelected = this.list.getSelectedValue();
				UserManager.setUsername(userSelected);
				
				if (this.panelControle != null) this.remove(this.panelControle);
				
				this.panelControle = new PanelControle();
				
				this.add(this.panelControle);
				this.panelControle.setBounds(190, -1, 385, 400);
				
				Instances.getGestionnaireFrame().refresh();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == this.create_user_button)
		{
			FrameCreateUser.main();
			this.create_user_button.setEnabled(false);
		}
		
		else if (event.getSource() == this.delete_user_button)
		{
			FrameDeleteUser.main();
			this.delete_user_button.setEnabled(false);
		}
	}
	
	public PanelControle getPanelControle()
	{
		return this.panelControle;
	}
	
	public JButton getCreateUserButton()
	{
		return this.create_user_button;
	}
	
	public JButton getDeleteUserButton()
	{
		return this.delete_user_button;
	}
	
	public DefaultListModel<String> getListModel()
	{
		return this.listModel;
	}
}
