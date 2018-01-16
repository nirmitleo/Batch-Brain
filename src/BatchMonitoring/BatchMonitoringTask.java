package BatchMonitoring;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class BatchMonitoringTask
{
	private final static Logger log = Logger.getLogger(BatchMonitoringTask.class);
	public final WrapperBatch AIX583_IMACS_BATCH1 = new WrapperBatch(WrapperBatchType.AIX583_IMACSBATCH1);
	public final WrapperBatch AIX583_IMACS_BATCH2 = new WrapperBatch(WrapperBatchType.AIX583_IMACSBATCH2);
	public final WrapperBatch AIX583_IMACS_BATCH3 = new WrapperBatch(WrapperBatchType.AIX583_IMACSBATCH3);
	public final WrapperBatch AIX583_NTI_BATCH1 = new WrapperBatch(WrapperBatchType.AIX583_NTIBATCH1);
	public final WrapperBatch AIX583_NTI_BATCH2 = new WrapperBatch(WrapperBatchType.AIX583_NTIBATCH2);
	public final WrapperBatch AIX583_NTI_BATCH3 = new WrapperBatch(WrapperBatchType.AIX583_NTIBATCH3);
	public final WrapperBatch AIX583_NTI_BATCH4 = new WrapperBatch(WrapperBatchType.AIX583_NTIBATCH4);
	public final WrapperBatch AIX583_REPAIRS_BATCH1 = new WrapperBatch(WrapperBatchType.AIX583_REPAIRS1);
	public final WrapperBatch AIX601_IMACS_BATCH1 = new WrapperBatch(WrapperBatchType.AIX601_IMACSBATCH1);
	public final WrapperBatch AIX601_NTI_BATCH1 = new WrapperBatch(WrapperBatchType.AIX601_NTIBATCH1);
	public final WrapperBatch AIX601_NTI_BATCH2 = new WrapperBatch(WrapperBatchType.AIX601_NTIBATCH2);

	public void start()
	{
		// UnixSession unix = null;
		Property.loadParameter();
		try
		{
			Runtime.getRuntime().exec("taskkill /F /IM EXCEL.exe");
		}
		catch (IOException e)
		{
			log.error(e.getMessage());
		}
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			log.error(e.getMessage());
		}
		// unix = new UnixSession(Property.AIX583_SERVER, Property.AIX583_USERNAME, Property.AIX583_PASSWORD);
		processWrapperBatch(AIX583_IMACS_BATCH1);
		processWrapperBatch(AIX583_IMACS_BATCH2);
		processWrapperBatch(AIX583_IMACS_BATCH3);
		processWrapperBatch(AIX583_NTI_BATCH1);
		processWrapperBatch(AIX583_NTI_BATCH2);
		processWrapperBatch(AIX583_NTI_BATCH3);
		processWrapperBatch(AIX583_NTI_BATCH4);
		processWrapperBatch(AIX583_REPAIRS_BATCH1);

		// unix = new UnixSession(Property.AIX601_SERVER, Property.AIX601_USERNAME, Property.AIX601_PASSWORD);
		processWrapperBatch(AIX601_IMACS_BATCH1);
		processWrapperBatch(AIX601_NTI_BATCH1);
		processWrapperBatch(AIX601_NTI_BATCH2);

		long refreshInterval = new Long(Property.REFRESH_RATE) * 1000;
		double timeDifference = (refreshInterval) / 1.0;
		long firstTime = System.currentTimeMillis();
		double delta = 0;
		System.out.println("Process will again start in " + refreshInterval + "sec");
		while (true)
		{
			long secondTime = System.currentTimeMillis();
			delta += (secondTime - firstTime) / timeDifference;
			while (delta >= 1)
			{
				System.out.println("Update!!!");
				try
				{
					Runtime.getRuntime().exec("taskkill /F /IM EXCEL.exe");
				}
				catch (IOException e)
				{
					log.error(e.getMessage());
				}
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
					log.error(e.getMessage());
				}
				// unix = new UnixSession(Property.AIX583_SERVER, Property.AIX583_USERNAME, Property.AIX583_PASSWORD);
				processWrapperBatch(AIX583_IMACS_BATCH1);
				if (AIX583_IMACS_BATCH1.isWrapperBatchCompleted() && Property.BATCH_COMPLETE_EMAIL_ENABLED)
				{
					MailHandler.sendStatusCompleteMails("AIX583 - IMACSBatch1");
				}
				processWrapperBatch(AIX583_IMACS_BATCH2);
				if (AIX583_IMACS_BATCH2.isWrapperBatchCompleted() && Property.BATCH_COMPLETE_EMAIL_ENABLED)
				{
					MailHandler.sendStatusCompleteMails("AIX583 - IMACSBatch2");
				}
				processWrapperBatch(AIX583_IMACS_BATCH3);
				if (AIX583_IMACS_BATCH3.isWrapperBatchCompleted() && Property.BATCH_COMPLETE_EMAIL_ENABLED)
				{
					MailHandler.sendStatusCompleteMails("AIX583 - IMACSBatch3");
				}
				processWrapperBatch(AIX583_NTI_BATCH1);
				if (AIX583_NTI_BATCH1.isWrapperBatchCompleted() && Property.BATCH_COMPLETE_EMAIL_ENABLED)
				{
					MailHandler.sendStatusCompleteMails("AIX583 - NTIBatch1");
				}
				processWrapperBatch(AIX583_NTI_BATCH2);
				if (AIX583_NTI_BATCH2.isWrapperBatchCompleted() && Property.BATCH_COMPLETE_EMAIL_ENABLED)
				{
					MailHandler.sendStatusCompleteMails("AIX583 - NTIBatch2");
				}
				processWrapperBatch(AIX583_NTI_BATCH3);
				if (AIX583_NTI_BATCH3.isWrapperBatchCompleted() && Property.BATCH_COMPLETE_EMAIL_ENABLED)
				{
					MailHandler.sendStatusCompleteMails("AIX583 - NTIBatch3");
				}
				processWrapperBatch(AIX583_NTI_BATCH4);
				if (AIX583_NTI_BATCH4.isWrapperBatchCompleted() && Property.BATCH_COMPLETE_EMAIL_ENABLED)
				{
					MailHandler.sendStatusCompleteMails("AIX583 - NTIBatch4");
				}
				processWrapperBatch(AIX583_REPAIRS_BATCH1);
				if (AIX583_REPAIRS_BATCH1.isWrapperBatchCompleted() && Property.BATCH_COMPLETE_EMAIL_ENABLED)
				{
					MailHandler.sendStatusCompleteMails("AIX583 - Repairs");
				}

				// unix = new UnixSession(Property.AIX601_SERVER, Property.AIX601_USERNAME, Property.AIX601_PASSWORD);
				processWrapperBatch(AIX601_IMACS_BATCH1);
				if (AIX601_IMACS_BATCH1.isWrapperBatchCompleted() && Property.BATCH_COMPLETE_EMAIL_ENABLED)
				{
					MailHandler.sendStatusCompleteMails("AIX601 - IMACSBatch1");
				}
				processWrapperBatch(AIX601_NTI_BATCH1);
				if (AIX601_NTI_BATCH1.isWrapperBatchCompleted() && Property.BATCH_COMPLETE_EMAIL_ENABLED)
				{
					MailHandler.sendStatusCompleteMails("AIX601 - NTIBatch1");
				}
				processWrapperBatch(AIX601_NTI_BATCH2);
				if (AIX601_NTI_BATCH2.isWrapperBatchCompleted() && Property.BATCH_COMPLETE_EMAIL_ENABLED)
				{
					MailHandler.sendStatusCompleteMails("AIX601 - NTIBatch2");
				}

				if (AIX583_IMACS_BATCH1.isWrapperBatchCompleted() && AIX583_IMACS_BATCH2.isWrapperBatchCompleted() && AIX583_IMACS_BATCH3.isWrapperBatchCompleted() && AIX583_NTI_BATCH1.isWrapperBatchCompleted() && AIX583_NTI_BATCH2.isWrapperBatchCompleted() && AIX583_NTI_BATCH3.isWrapperBatchCompleted() && AIX583_NTI_BATCH4.isWrapperBatchCompleted() && AIX601_IMACS_BATCH1.isWrapperBatchCompleted() && AIX601_NTI_BATCH1.isWrapperBatchCompleted() && AIX601_NTI_BATCH2.isWrapperBatchCompleted())
				{
					System.out.println("That's all folks. See you tomorrow!");
				}
				else
				{
					System.out.println("Process will again start in " + refreshInterval + "sec");
					delta--;
				}

			}
		}

	}

	private void processWrapperBatch(WrapperBatch wrapperBatch)
	{
		boolean switchON = true;
		WrapperBatchType wrapperBatchType = wrapperBatch.getWrapperBatchType();
		String nameSignature = wrapperBatch.getNameSignature();
		String serverSignature = wrapperBatch.getServerSignature();
		wrapperBatch.setBatchList(new ArrayList<Batch>());
		wrapperBatch.setLogLines(new ArrayList<String>());
		ArrayList<Batch> batchList = wrapperBatch.getBatchList();
		ArrayList<String> logLines = wrapperBatch.getLogLines();

		// String searchCommand = "";
		// String remoteLogFileLocation = "";

		// ********************************* START UNIX SESSION *********************************//

		// searchCommand = UnixSession.generateUnixCommand(wrapperBatchType);
		// remoteLogFileLocation = unix.findFileByCommand(searchCommand);
		// if (remoteLogFileLocation != null)
		if (switchON)
		{
			// String logFileName = remoteLogFileLocation.substring(remoteLogFileLocation.lastIndexOf(nameSignature)).trim();
			/* Only for testing purposes - start */
			String logFileName = wrapperBatch.getLogFileName();
			/* Only for testing purposes - end */
			wrapperBatch.setName(logFileName);
			String localBatchFileLocation = wrapperBatch.getLocalBatchFileLocation();
			String localLogFileLocation = wrapperBatch.getLocalLogFileLocation();
			// if (unix.downloadFile(wrapperBatchType, remoteLogFileLocation))
			if (switchON)
			{
				log.debug("Wrapper Batch Type = " + wrapperBatchType + "||" + "Wrapper File Name = " + wrapperBatch.getName() + "- log file downloaded!!");

				// ************************************** BATCH (Start) ***********************************************//
				BatchReader AIX583_IMACS1_BatchReader = new BatchReader();
				log.debug("Wrapper Batch Type = " + wrapperBatchType + "||" + "Wrapper File Name = " + wrapperBatch.getName() + "- Starting the process of loading the batch names");
				AIX583_IMACS1_BatchReader.loadBatchNames(batchList, localBatchFileLocation);
				log.debug("Wrapper Batch Type = " + wrapperBatchType + "||" + "Wrapper File Name = " + wrapperBatch.getName() + "- Finished the process of loading the batch names");

				LogReader AIX583_IMACS1_LogReader = new LogReader();
				log.debug("Wrapper Batch Type = " + wrapperBatchType + "||" + "Wrapper File Name = " + wrapperBatch.getName() + "- Starting the process of loading the log lines");
				AIX583_IMACS1_LogReader.loadLogLines(logLines, localLogFileLocation);
				log.debug("Wrapper Batch Type = " + wrapperBatchType + "||" + "Wrapper File Name = " + wrapperBatch.getName() + "- Finished the process of loading the log lines");

				LogProcessor AIX583_IMACS1_LogProcessor = new LogProcessor();
				log.debug("Wrapper Batch Type = " + wrapperBatchType + "||" + "Wrapper File Name = " + wrapperBatch.getName() + "- Starting the process of processing the log file");
				AIX583_IMACS1_LogProcessor.processLogs(wrapperBatch);
				log.debug("Wrapper Batch Type = " + wrapperBatchType + "||" + "Wrapper File Name = " + wrapperBatch.getName() + "- Finished the process of processing the log file");

				ModelEvent event = new ModelEvent(wrapperBatch);
				wrapperBatch.getModelListener().refreshData(event);
				ReportGenerator.generateExcelReport(batchList, serverSignature + "-" + wrapperBatch.getName());

				// ************************************** BATCH1 (End) ***********************************************//

			}
			else
			{
				log.debug("Wrapper Batch Type = " + wrapperBatchType + "- log file did not download!");
			}
		}
		else
		{
			log.debug("Wrapper Batch Type = " + wrapperBatchType + "- log file has not been generated!");
		}
	}
}
