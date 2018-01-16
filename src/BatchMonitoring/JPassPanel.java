package BatchMonitoring;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JPassPanel extends JPanel
{
	private final JLabel PASS_COUNT = new JLabel("54");
	private final JLabel PASS = new JLabel("Pass");
	private final JLabel PASS_IMAGE = new JLabel();

	JPassPanel()
	{
		this.setBorder(null);
		this.setOpaque(true);

		PASS.setVerticalAlignment(SwingConstants.BOTTOM);
		PASS.setHorizontalAlignment(SwingConstants.CENTER);
		PASS.setForeground(CSSProperty.COLOR_PASS_TAG);
		PASS.setFont(PASS.getFont().deriveFont(CSSProperty.SIZE_PASSTAG));

		PASS_COUNT.setForeground(CSSProperty.COLOR_PASSCOUNT);
		PASS_COUNT.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
		PASS_COUNT.setHorizontalAlignment(SwingConstants.CENTER);
		PASS_COUNT.setBorder(null);

		this.setLayout(new BorderLayout());
		this.add(PASS, BorderLayout.NORTH);
		this.add(PASS_IMAGE, BorderLayout.WEST);
		this.add(PASS_COUNT, BorderLayout.CENTER);

		PASS_IMAGE.setIcon(new ImageIcon("./res/img/success.png"));
		this.setBackground(CSSProperty.COLOR_PASS_BACKGROUND);
		PASS_IMAGE.setBorder(null);
	}

	public void updatePassCount(String passCount)
	{
		PASS_COUNT.setForeground(CSSProperty.COLOR_FAILCOUNT);
		PASS_COUNT.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
		PASS_COUNT.setHorizontalAlignment(SwingConstants.CENTER);
		PASS_COUNT.setText(passCount);
		PASS_COUNT.setBorder(null);
	}
}
