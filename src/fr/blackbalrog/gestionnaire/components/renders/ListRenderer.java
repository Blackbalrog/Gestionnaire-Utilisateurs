package fr.blackbalrog.gestionnaire.components.renders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ListRenderer extends DefaultListCellRenderer
{
	private int hoveredIndex = -1;
	
	public void setHover(JList<?> list)
	{
		list.addMouseMotionListener(new MouseMotionAdapter()
		{
			@Override
			public void mouseMoved(MouseEvent event)
			{
				int index = list.locationToIndex(event.getPoint());
				if (index != hoveredIndex)
				{
					hoveredIndex = index;
					list.repaint();
				}
			}
		});
		
		list.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseExited(MouseEvent event)
			{
				hoveredIndex = -1;
				list.repaint();
			}
		});
	}
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
	    Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

	    FontMetrics metrics = list.getFontMetrics(this.getFont());
	    int width = metrics.stringWidth(value.toString()) + 40;
	    this.setPreferredSize(new Dimension(width, this.getPreferredSize().height));

	    this.setBorder(new EmptyBorder(5, 5, 0, 0));

	    if (index == this.hoveredIndex && !isSelected)
	    {
		    	this.setForeground(new Color(88, 214, 141));
	        	this.setBackground(new Color(0, 0, 0, 0));
	        	this.setBorder(new EmptyBorder(5, 10, 0, 0));
	    }
	    else if (isSelected)
	    {
	    	this.setForeground(new Color(88, 214, 141));
			this.setBackground(new Color(0, 0, 0, 0));
			this.setBorder(new EmptyBorder(5, 10, 0, 0));
			this.setFont(this.getFont().deriveFont(12.5F));
	    }
	    else
	    {
	    		this.setForeground(/*new Color(52, 73, 94)*/ new Color(93, 109, 126));
			this.setBackground(list.getBackground());
			this.setBorder(new EmptyBorder(5, 5, 0, 0));
	    }
	    return renderer;
	}

}
