package fr.blackbalrog.gestionnaire.components.button;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import fr.blackbalrog.gestionnaire.color.ColorComponent;

@SuppressWarnings("serial")
public class Button extends JButton
{
	public Button(String text)
	{
		this.setText("<html><u>" + text + "</u></html>");
		this.setBackground(new Color(0, 0, 0, 0));
		this.setForeground(ColorComponent.default_button);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setMargin(new Insets(0, 0, 0, 0));
	}
	
	public Button setColorHover()
	{
		this.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent event)
			{
				Button.this.setForeground(ColorComponent.hover_button);
			}
			
			@Override
			public void mouseExited(MouseEvent event)
			{
				Button.this.setForeground(ColorComponent.default_button);
			}
		});
		return this;
	}
	
	public Button setColorClick()
	{
		this.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent event)
			{
				Button.this.setForeground(ColorComponent.clicked_button);
			}
			
			@Override
			public void mouseExited(MouseEvent event)
			{
				Button.this.setForeground(ColorComponent.default_button);
			}
		});
		return this;
	}
	
	
}
