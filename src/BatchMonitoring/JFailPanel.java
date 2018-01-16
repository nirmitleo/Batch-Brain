package BatchMonitoring;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JFailPanel extends JPanel
{
	private final JLabel FAIL_COUNT = new JLabel("54");
	private final JLabel FAIL = new JLabel("Fail");
	private final JLabel FAIL_IMAGE = new JLabel();

	JFailPanel()
	{
		this.setBorder(null);
		this.setOpaque(true);

		FAIL.setVerticalAlignment(SwingConstants.BOTTOM);
		FAIL.setHorizontalAlignment(SwingConstants.CENTER);
		FAIL.setForeground(CSSProperty.COLOR_FAILTAG);
		FAIL.setFont(FAIL.getFont().deriveFont(CSSProperty.SIZE_FAILTAG));

		FAIL_COUNT.setForeground(CSSProperty.COLOR_FAILCOUNT);
		FAIL_COUNT.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
		FAIL_COUNT.setHorizontalAlignment(SwingConstants.CENTER);
		FAIL_COUNT.setBorder(null);

		this.setLayout(new BorderLayout());
		this.add(FAIL, BorderLayout.NORTH);
		this.add(FAIL_IMAGE, BorderLayout.WEST);
		this.add(FAIL_COUNT, BorderLayout.CENTER);

		FAIL_IMAGE.setIcon(new ImageIcon("./res/img/fail.png"));
		this.setBackground(CSSProperty.COLOR_FAIL_BACKGROUND);
	}

	public void updateFailCount(String failCount)
	{
		FAIL_COUNT.setForeground(CSSProperty.COLOR_FAILCOUNT);
		FAIL_COUNT.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
		FAIL_COUNT.setHorizontalAlignment(SwingConstants.CENTER);
		FAIL_COUNT.setText(failCount);
		FAIL_COUNT.setBorder(null);
	}
}
