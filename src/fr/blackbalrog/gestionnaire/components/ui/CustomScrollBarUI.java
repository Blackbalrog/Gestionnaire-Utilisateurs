package fr.blackbalrog.gestionnaire.components.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomScrollBarUI extends BasicScrollBarUI
{

	// Couleurs personnalisées pour le pouce et le track
	private Color thumbColor 			= new Color(93, 109, 126); // Rouge
	private Color trackColor 			= new Color(39, 55, 70); // Couleur de fond du track

	public CustomScrollBarUI()
	{
		super();
	}

	@Override
	protected void configureScrollBarColors()
	{
		// Cette méthode configure les couleurs du pouce et du track
		this.thumbColor 			= new Color(93, 109, 126); // Couleur rouge pour le pouce
		this.trackColor 			= new Color(39, 55, 70); // Couleur de fond
	}

	@Override
	public void paint(Graphics graphic, JComponent component)
	{
		// Dessiner le track
		graphic.setColor(trackColor);
		graphic.fillRect(trackRect.x, trackRect.y, trackRect.width, trackRect.height); // Dessiner le fond

		// Dessiner le pouce
		graphic.setColor(thumbColor);
		if (thumbRect.height > 0 && thumbRect.width > 0)
		{
			graphic.fillRect(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height); // Dessiner le pouce
		}
	}

	@Override
	protected void setThumbBounds(int x, int y, int width, int height)
	{
		super.setThumbBounds(x, y, width, height);
		// Mettre à jour les dimensions du pouce
		this.thumbRect.setBounds(x, y, width, height);
	}

	@Override
	protected JButton createIncreaseButton(int orientation)
	{
		JButton button 				= new JButton();
		button.setPreferredSize(new Dimension(0, 0));
		button.setBorder(null); // Pas de bordure
		button.setBackground(new Color(0, 0, 0, 0));
		return button;
	}

	@Override
	protected JButton createDecreaseButton(int orientation)
	{
		JButton button 				= new JButton();
		button.setPreferredSize(new Dimension(0, 0));
		button.setBorder(null);
		button.setBackground(new Color(0, 0, 0, 0));
		return button;
	}
}
