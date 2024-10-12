package fr.blackbalrog.gestionnaire.panels.accounts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import fr.blackbalrog.gestionnaire.components.ui.CustomScrollBarUI;
import fr.blackbalrog.gestionnaire.files.FileConfiguration;
import fr.blackbalrog.gestionnaire.utils.Utils;
import fr.blackbalrog.gestionnaire.yaml.ConfigurationSection;
import fr.blackbalrog.gestionnaire.yaml.YamlConfiguration;

@SuppressWarnings("serial")
public class PanelAccounts extends JScrollPane
{
	
	private Map<String, JTextField> mailFields 			= new HashMap<>();
	private Map<String, JTextField> passwordFields 		= new HashMap<>();
	private List<JTextField> listFields 				= new ArrayList<>();
	private List<JButton> listButtons 					= new ArrayList<>();

	private JPanel contentPanel 						= new JPanel();
	
	public PanelAccounts()
	{
		super();
		
		this.setBackground(new Color(39, 55, 70));
		
		this.contentPanel.setLayout(null);
		this.contentPanel.setBackground(new Color(39, 55, 70));
 
		this.setViewportView(this.contentPanel);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JScrollBar scrollBar 							= new JScrollBar();
		scrollBar.setUI(new CustomScrollBarUI());
		scrollBar.setPreferredSize(new Dimension(5, 0));
		this.setVerticalScrollBar(scrollBar);
		
		this.setBorder(null);
		this.setupPanel();
	}

	public void setupPanel()
	{
		this.contentPanel.removeAll();
		
		YamlConfiguration configurationUser 			= new YamlConfiguration(FileConfiguration.getFileUser());

		int y = 10;

		for (String key : configurationUser.getKeys(false))
		{
			ConfigurationSection section 				= configurationUser.getConfigurationSection(key);
			String mail 								= section.getString("mail");
			String password 							= section.getString("password");

			/*----------------*/
			
			JLabel accountLabel 						= new JLabel(key + ":");
			accountLabel.setBounds(10, y, 200, 20);
			accountLabel.setForeground(new Color(88, 214, 141));
			this.contentPanel.add(accountLabel);
			
			/*-------------------------------------*/
			
			y += 20;
			
			JLabel mailLabel 							= new JLabel("mail: ");
			mailLabel.setBounds(10, y, 50, 20);
			mailLabel.setForeground(new Color(88, 214, 141));
			this.contentPanel.add(mailLabel);

			/*----------------*/
			
			JTextField mailField 						= new JTextField();
			mailField.setText(mail);
			mailField.setBounds(40, y, 150, 20);
			this.setupField(mailField);
			this.contentPanel.add(mailField);
			
			this.mailFields.put(key, mailField);
			this.listFields.add(mailField);

			/*-------------------------------------*/
			
			y += 25;

			JLabel passwordLabel 						= new JLabel("Mot de passe: ");
			passwordLabel.setBounds(10, y, 100, 20);
			passwordLabel.setForeground(new Color(88, 214, 141));
			this.contentPanel.add(passwordLabel);

			/*----------------*/
			
			JTextField passwordField 					= new JTextField();
			passwordField.setText(password);
			passwordField.setBounds(95, y, 95, 20);
			this.setupField(passwordField);
			this.contentPanel.add(passwordField);
			
			this.passwordFields.put(key, passwordField);
			this.listFields.add(passwordField);
			
			/*----------------*/
			
			JButton generate_password_button 			= new JButton("<html><u>generer</u></html>");
			generate_password_button.setBounds(185, y +3, 60, 20);
			this.setupButton(generate_password_button, new Color(247, 220, 111));
			this.contentPanel.add(generate_password_button);
			generate_password_button.setVisible(false);
			generate_password_button.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent event)
				{
					passwordField.setText(Utils.generateRandomPassword());
				}
			});
			
			this.listButtons.add(generate_password_button);
			
			/*-------------------------------------*/
			
			y += 20;
			
			/*----------------*/
			
			JButton copy_button 						= new JButton("<html><u>copier</u></html");
			copy_button.setBounds(-1, y, 60, 20);
			this.setupButton(copy_button, new Color(88, 214, 141));
			this.contentPanel.add(copy_button);
			copy_button.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent event)
				{
					String toCopy 						= "Mail: " + mail + "\nMot de passe: " + passwordField.getText();
					StringSelection selection 			= new StringSelection(toCopy);
					Clipboard clipboard 				= Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(selection, null);
					System.out.println("Informations copiées: " + toCopy);
					
					Thread thread = new Thread(new Runnable()
					{
			            int i = 0;

			            @Override
			            public void run()
			            {
			                while (i < 5)
			                {
			                    i++;
			                    copy_button.setText("copier");
			                    copy_button.setForeground(new Color(247, 235, 111));
			                    try
			                    {
			                        Thread.sleep(100);
			                    }
			                    catch (InterruptedException exception)
			                    {
			                        System.out.println("Le thread a été interrompu.");
			                    }
			                    
			                    if (i == 5)
				                {
				                	copy_button.setText("<html><u>copier</u></html>");
				                	copy_button.setForeground(new Color(88, 214, 141));
				                }
			                }
			            }
			        });
					thread.start();
				}
			});
			
			/*-------------------------------------*/
			
			y += 40;
		}

		this.contentPanel.setPreferredSize(new Dimension(400, y + 10));
		
		this.contentPanel.revalidate();
		this.contentPanel.repaint();
	}
	
	private void setupField(JTextField field)
	{
		field.setEditable(false);
		field.setForeground(new Color(88, 214, 141));
		field.setOpaque(false);
		field.setBorder(new MatteBorder(0, 0, 1, 0, new Color(88, 214, 141)));
		field.setSelectionColor(new Color(0, 0, 0, 0));
		field.setSelectedTextColor(new Color(88, 214, 141));
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
	
	public List<JTextField> getListFields()
	{
		return this.listFields;
	}

	public Map<String, JTextField> getMailFields()
	{
		return this.mailFields;
	}

	public Map<String, JTextField> getPasswordFields()
	{
		return this.passwordFields;
	}

	public List<JButton> getListButtons()
	{
		return this.listButtons;
	}
}
