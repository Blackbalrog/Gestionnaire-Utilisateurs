package fr.blackbalrog.gestionnaire.components.label;

import javax.swing.JLabel;

import fr.blackbalrog.gestionnaire.color.ColorComponent;

@SuppressWarnings("serial")
public class LabelError extends JLabel
{
	public LabelError()
	{
		this.setForeground(ColorComponent.error_label);
	}
	
	public void setErrorMessage(String text)
	{
		this.setText(text);
		
		Thread thread = new Thread(new Runnable()
		{
            int i = 0;

            @Override
            public void run()
            {
                while (i < 5)
                {
                    i++;
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException exception)
                    {
                        System.out.println("Le thread a été interrompu.");
                    }
                    
                    if (i == 5)
	                {
                    	setText("");
	                }
                }
            }
        });
		thread.start();
	}
}
