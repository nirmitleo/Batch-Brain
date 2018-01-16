package BatchMonitoring;

import java.awt.FlowLayout;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

public class MainFrame extends JFrame
{
	private final static Logger log = Logger.getLogger(MainFrame.class);

	private final WrapperBatchPanel AIX583_IMACS_BATCH1_PANEL = new WrapperBatchPanel();
	private final WrapperBatchPanel AIX583_IMACS_BATCH2_PANEL = new WrapperBatchPanel();
	private final WrapperBatchPanel AIX583_IMACS_BATCH3_PANEL = new WrapperBatchPanel();
	private final WrapperBatchPanel AIX583_NTI_BATCH1_PANEL = new WrapperBatchPanel();
	private final WrapperBatchPanel AIX583_NTI_BATCH2_PANEL = new WrapperBatchPanel();
	private final WrapperBatchPanel AIX583_NTI_BATCH3_PANEL = new WrapperBatchPanel();
	private final WrapperBatchPanel AIX583_NTI_BATCH4_PANEL = new WrapperBatchPanel();
	private final WrapperBatchPanel AIX583_REPAIRS_BATCH1_PANEL = new WrapperBatchPanel();
	private final WrapperBatchPanel AIX601_IMACS_BATCH1_PANEL = new WrapperBatchPanel();
	private final WrapperBatchPanel AIX601_NTI_BATCH1_PANEL = new WrapperBatchPanel();
	private final WrapperBatchPanel AIX601_NTI_BATCH2_PANEL = new WrapperBatchPanel();
	BatchMonitoringTask task = new BatchMonitoringTask();
	private static UnixSession unix;

	MainFrame()
	{

		this.setLayout(new FlowLayout(FlowLayout.LEADING));
		this.add(AIX583_IMACS_BATCH1_PANEL);
		this.add(AIX583_IMACS_BATCH2_PANEL);
		this.add(AIX583_IMACS_BATCH3_PANEL);
		this.add(AIX583_NTI_BATCH1_PANEL);
		this.add(AIX583_NTI_BATCH2_PANEL);
		this.add(AIX583_NTI_BATCH3_PANEL);
		this.add(AIX583_NTI_BATCH4_PANEL);
		this.add(AIX583_REPAIRS_BATCH1_PANEL);
		this.add(AIX601_IMACS_BATCH1_PANEL);
		this.add(AIX601_NTI_BATCH1_PANEL);
		this.add(AIX601_NTI_BATCH2_PANEL);

		this.setTitle("IRPM Batch Monitoring Tool");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		task.AIX583_IMACS_BATCH1.setModelListener(AIX583_IMACS_BATCH1_PANEL);
		task.AIX583_IMACS_BATCH2.setModelListener(AIX583_IMACS_BATCH2_PANEL);
		task.AIX583_IMACS_BATCH3.setModelListener(AIX583_IMACS_BATCH3_PANEL);
		task.AIX583_NTI_BATCH1.setModelListener(AIX583_NTI_BATCH1_PANEL);
		task.AIX583_NTI_BATCH2.setModelListener(AIX583_NTI_BATCH2_PANEL);
		task.AIX583_NTI_BATCH3.setModelListener(AIX583_NTI_BATCH3_PANEL);
		task.AIX583_NTI_BATCH4.setModelListener(AIX583_NTI_BATCH4_PANEL);
		task.AIX583_REPAIRS_BATCH1.setModelListener(AIX583_REPAIRS_BATCH1_PANEL);
		task.AIX601_IMACS_BATCH1.setModelListener(AIX601_IMACS_BATCH1_PANEL);
		task.AIX601_NTI_BATCH1.setModelListener(AIX601_NTI_BATCH1_PANEL);
		task.AIX601_NTI_BATCH2.setModelListener(AIX601_NTI_BATCH2_PANEL);

		Runnable batchMonitoringRunnable = new Runnable()
		{
			public void run()
			{
				task.start();
			}
		};
		Thread batchMonitoringThread = new Thread(batchMonitoringRunnable);
		batchMonitoringThread.start();
	}
}
