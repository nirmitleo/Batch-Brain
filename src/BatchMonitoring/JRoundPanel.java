package BatchMonitoring;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class JRoundPanel extends JPanel
{
	private int strokeSize = 3;
	private Dimension arcRadius = new Dimension(20, 20);

	public JRoundPanel()
	{
		super();
		this.setOpaque(false);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		int width = this.getWidth();
		int height = this.getHeight();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(getBackground());
		g2d.fillRoundRect(0, 0, width - strokeSize, height - strokeSize, arcRadius.width, arcRadius.height);
		g2d.setColor(getForeground());
		g2d.setStroke(new BasicStroke(strokeSize));
		g2d.drawRoundRect(0, 0, width - strokeSize, height - strokeSize, arcRadius.width, arcRadius.height);

		g2d.setStroke(new BasicStroke());
	}

}
