package BatchMonitoring;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class JBatchStatusPanel extends JPanel
{

	private JTitle serverName;
	private JTitle wrapperBatchName;
	private JTitle currentBatchName;

	private JPassPanel passPanel;
	private JFailPanel failPanel;

	JBatchStatusPanel()
	{
		serverName = new JTitle(Name.SERVER_NAME, "AIX-583");
		wrapperBatchName = new JTitle(Name.WRAPPER_BATCH_NAME, "imacsBatchLog152-651.log");
		currentBatchName = new JTitle(Name.BATCH_NAME, "RECEIVEDDPAYMENTRESPONSETPUK");
		passPanel = new JPassPanel();
		failPanel = new JFailPanel();

		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		/*******************************************
		 **************** FIRST-ROW (Start)**********
		 *******************************************/
		gc.weightx = 1.0;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 2;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.LINE_START;
		this.add(serverName, gc);

		/*******************************************
		 **************** FIRST-ROW (End)**********
		 *******************************************/

		/*******************************************
		 **************** SECOND-ROW (Start)**********
		 *******************************************/
		gc.weightx = 1.0;
		gc.weighty = 0.2;
		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 2;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.LINE_START;
		this.add(wrapperBatchName, gc);

		/*******************************************
		 **************** SECOND-ROW (End)**********
		 *******************************************/

		/*******************************************
		 **************** THIRD-ROW (Start)**********
		 *******************************************/
		gc.weightx = 1.0;
		gc.weighty = 0.2;
		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridwidth = 2;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.LINE_START;
		this.add(currentBatchName, gc);

		/*******************************************
		 **************** THIRD-ROW (End)**********
		 *******************************************/

		/*******************************************
		 **************** FOURTH-ROW (Start)**********
		 *******************************************/
		gc.weightx = 0.5;
		gc.weighty = 0.2;
		gc.gridx = 0;
		gc.gridy = 3;
		gc.gridwidth = 1;
		gc.gridheight = 2;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.LINE_START;
		this.add(failPanel, gc);

		gc.weightx = 0.5;
		gc.weighty = 0.2;
		gc.gridx = 1;
		gc.gridy = 3;
		gc.gridwidth = 1;
		gc.gridheight = 2;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.LINE_START;
		this.add(passPanel, gc);

		/*******************************************
		 **************** FOURTH-ROW (End)**********
		 *******************************************/

	}

	public void updateServerName(String serverName)
	{
		this.serverName.updateName(serverName);
	}

	public void updateWrapperBatchName(String wrapperBatchName)
	{
		this.wrapperBatchName.updateName(wrapperBatchName);
	}

	public void updateCurrentBatchName(String currentBatchName)
	{
		this.currentBatchName.updateName(currentBatchName);
	}

	public void updatePassCount(String passCount)
	{
		passPanel.updatePassCount(passCount);
	}

	public void updateFailCount(String failCount)
	{
		failPanel.updateFailCount(failCount);
	}

}
