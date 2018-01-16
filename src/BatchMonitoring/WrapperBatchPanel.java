package BatchMonitoring;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JProgressBar;

public class WrapperBatchPanel extends JRoundPanel implements ModelListener
{
	private int batchesCompleted;
	private String wrapperBatchName;
	private JBatchStatusPanel batchStatusPanel;
	private JProgressBar circularProgressBar;

	private final static int WIDTH = 212;
	private final static int HEIGHT = WIDTH / 9 * 16;

	WrapperBatchPanel()
	{
		this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		this.setBackground(CSSProperty.COLOR_WRAPPER_BATCH_PANEL);

		batchStatusPanel = new JBatchStatusPanel();
		batchStatusPanel.updateServerName("Loading..");
		batchStatusPanel.updateWrapperBatchName("Loading..");
		batchStatusPanel.updateCurrentBatchName("Loading..");
		batchStatusPanel.updateFailCount("0");
		batchStatusPanel.updatePassCount("0");
		batchStatusPanel.setBorder(null);

		circularProgressBar = new JProgressBar()
		{
			public void updateUI()
			{
				super.updateUI();
				this.setUI(new CircularProgressBarUI());

			}
		};
		circularProgressBar.setIndeterminate(false);
		circularProgressBar.setValue(0);
		circularProgressBar.setStringPainted(true);
		circularProgressBar.setBorder(null);

		this.setLayout(new BorderLayout());
		this.add(batchStatusPanel, BorderLayout.CENTER);
		this.add(circularProgressBar, BorderLayout.SOUTH);

		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	public void refreshData(ModelEvent e)
	{
		batchStatusPanel.updateServerName(e.getServer());
		batchStatusPanel.updateWrapperBatchName(e.getWrapperBatchName());
		batchStatusPanel.updateCurrentBatchName(e.getCurrentBatch());
		batchStatusPanel.updateFailCount(e.getFailCount());
		batchStatusPanel.updatePassCount(e.getPassCount());
		circularProgressBar.setValue(e.getProgress());
	}
}
