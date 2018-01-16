package BatchMonitoring;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JTitle extends JLabel
{

	public JTitle(Name nametype, String name)
	{
		this.setOpaque(true);

		/** -Start-Setting the Background of the Label **/
		this.setBackground(CSSProperty.COLOR_WRAPPER_BATCH_PANEL);
		/** -End-Setting the Background of the Label **/

		/** -Start-Setting the Font of the title **/
		this.setForeground(Color.WHITE);
		if (nametype == Name.WRAPPER_BATCH_NAME)
		{
			this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		}
		else if (nametype == Name.SERVER_NAME)
		{
			this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		}
		else
		{
			this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 11));
		}
		/** -End-Setting the Font of the title **/

		this.setBorder(null);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setVerticalAlignment(SwingConstants.CENTER);
		this.setText(name);
	}

	public void updateName(String name)
	{
		this.setText(name);
	}
}
