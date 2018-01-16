package BatchMonitoring;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class CircularProgressBarUI extends BasicProgressBarUI
{

	public Dimension getPreferredSize(JComponent component)
	{
		Dimension dimension = super.getPreferredSize(component);
		int size = Math.max(dimension.width, dimension.height);
		dimension.setSize(size, size);
		return dimension;
	}

	protected Color getSelectionBackground()
	{
		return CSSProperty.COLOR_PROGRESS_BAR_TEXT;
	}

	public void paintDeterminate(Graphics g, JComponent component)
	{

		Insets borderArea = progressBar.getInsets();

		int rectangleBarWidth = progressBar.getWidth() - borderArea.left - borderArea.right;
		int rectangleBarHeight = progressBar.getHeight() - borderArea.top - borderArea.bottom;

		if (rectangleBarHeight <= 0 || rectangleBarWidth <= 0)
		{
			return;
		}

		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		double degree = 360 * progressBar.getPercentComplete();
		double size = Math.min(rectangleBarWidth, rectangleBarHeight);
		double cx = borderArea.left + rectangleBarWidth * 0.5;
		double cy = borderArea.top + rectangleBarHeight * 0.5;
		double outerRadius = size * 0.5;
		double innerRadius = outerRadius * 0.90;

		Shape innerCircle = new Ellipse2D.Double(cx - innerRadius, cy - innerRadius, innerRadius * 2, innerRadius * 2);
		Shape outerCircle = new Ellipse2D.Double(cx - outerRadius, cy - outerRadius, size, size);
		Shape sector = new Arc2D.Double(cx - outerRadius, cy - outerRadius, size, size, 90 - degree, degree, Arc2D.PIE);
		Area sectorArea = new Area(sector);
		Area outerCircleArea = new Area(outerCircle);
		Area innerCircleArea = new Area(innerCircle);
		sectorArea.subtract(innerCircleArea);
		outerCircleArea.subtract(innerCircleArea);

		Shape rectangle = new Rectangle(0, 0, rectangleBarWidth, rectangleBarHeight);
		Area rectangleArea = new Area(rectangle);
		rectangleArea.subtract(outerCircleArea);

		g2d.setPaint(CSSProperty.COLOR_PROGRESS_BAR_BACKGROUND);
		g2d.fill(rectangleArea);

		progressBar.setFont(progressBar.getFont().deriveFont(28f));

		// draw the track
		g2d.setPaint(CSSProperty.COLOR_PROGRESS_BAR_TRACK);
		g2d.fill(outerCircleArea);

		// draw the sector
		g2d.setPaint(CSSProperty.COLOR_PROGRESS_BAR_SECTOR);
		g2d.fill(sectorArea);
		g2d.dispose();

		if (progressBar.isStringPainted())
		{
			paintString(g, borderArea.left, borderArea.top, rectangleBarWidth, rectangleBarHeight, 0, borderArea);
		}
	}
}
