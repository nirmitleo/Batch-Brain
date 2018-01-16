package BatchMonitoring;

import javax.swing.SwingUtilities;

public class DemoApplication
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new MainFrame();
			}
		});
	}
}
