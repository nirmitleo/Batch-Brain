package BatchMonitoring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

public class WrapperBatch
{
	private final static Logger log = Logger.getLogger(WrapperBatch.class);
	private WrapperBatchType wrapperBatchType;
	private String name;
	private ArrayList<Batch> batchList;
	private ArrayList<String> logLines;
	private ModelListener listener;

	WrapperBatch(WrapperBatchType wrapperBatchType)
	{
		this.wrapperBatchType = wrapperBatchType;
		this.batchList = new ArrayList<Batch>();
		this.logLines = new ArrayList<String>();
	}

	public WrapperBatchType getWrapperBatchType()
	{
		return wrapperBatchType;
	}

	public ModelListener getModelListener()
	{
		return listener;
	}

	public void setModelListener(ModelListener listener)
	{
		this.listener = listener;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getStartTime()
	{
		String dateLine = logLines.get(1);
		String date = dateLine.substring(dateLine.charAt('+') + 1).trim();
		Date startTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("EEE dd MMM hh:mm:ss yyyy");
		try
		{
			startTime = sdf.parse(date);

		}
		catch (ParseException e)
		{
			System.out.println("Parsing problem with start time");
		}
		return startTime.toString();
	}

	public String getEndTime()
	{
		String dateLine = logLines.get(logLines.size() - 1);
		String date = dateLine.substring(dateLine.charAt('+') + 1).trim();
		Date endTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("EEE dd MMM hh:mm:ss yyyy");
		try
		{
			endTime = sdf.parse(date);

		}
		catch (ParseException e)
		{
			System.out.println("Parsing problem with start time");
		}
		return endTime.toString();
	}

	public ArrayList<Batch> getBatchList()
	{
		return batchList;
	}

	public void setBatchList(ArrayList<Batch> batchList)
	{
		this.batchList = batchList;
	}

	public ArrayList<String> getLogLines()
	{
		return logLines;
	}

	public void setLogLines(ArrayList<String> logLines)
	{
		this.logLines = logLines;
	}

	public String getServerSignature()
	{
		String serverSignature = null;
		switch (wrapperBatchType)
		{
		case AIX583_IMACSBATCH1:
		case AIX583_IMACSBATCH2:
		case AIX583_IMACSBATCH3:
		case AIX583_NTIBATCH1:
		case AIX583_NTIBATCH2:
		case AIX583_NTIBATCH3:
		case AIX583_NTIBATCH4:
		case AIX583_REPAIRS1:
			serverSignature = "AIX-583";
			break;
		case AIX601_NTIBATCH1:
		case AIX601_NTIBATCH2:
		case AIX601_IMACSBATCH1:
			serverSignature = "AIX-601";
			break;
		}
		return serverSignature;
	}

	public String getNameSignature()
	{
		String nameSignature = null;
		switch (wrapperBatchType)
		{
		case AIX583_IMACSBATCH1:
		case AIX583_IMACSBATCH2:
		case AIX583_IMACSBATCH3:
		case AIX601_IMACSBATCH1:
			nameSignature = "imacsbatchLog";
			break;
		case AIX583_NTIBATCH1:
		case AIX583_NTIBATCH2:
		case AIX583_NTIBATCH3:
		case AIX583_NTIBATCH4:
		case AIX601_NTIBATCH1:
		case AIX601_NTIBATCH2:
			nameSignature = "ntibatchLog";
			break;
		case AIX583_REPAIRS1:
			nameSignature = "repairsbatchLog";
			break;
		}
		return nameSignature;
	}

	public String getLocalLogFileLocation()
	{
		String localLogFileLocation = null;
		switch (wrapperBatchType)
		{
		case AIX583_IMACSBATCH1:
			localLogFileLocation = "./res/Downloaded/AIX-583/IMACS/IMACS-Batch1/" + name;
			log.debug("Local Log file location = " + localLogFileLocation);
			break;
		case AIX583_IMACSBATCH2:
			localLogFileLocation = "./res/Downloaded/AIX-583/IMACS/IMACS-Batch2/" + name;
			break;
		case AIX583_IMACSBATCH3:
			localLogFileLocation = "./res/Downloaded/AIX-583/IMACS/IMACS-Batch3/" + name;
			break;
		case AIX583_NTIBATCH1:
			localLogFileLocation = "./res/Downloaded/AIX-583/NTI/NTI-Batch1/" + name;
			break;
		case AIX583_NTIBATCH2:
			localLogFileLocation = "./res/Downloaded/AIX-583/NTI/NTI-Batch2/" + name;
			break;
		case AIX583_NTIBATCH3:
			localLogFileLocation = "./res/Downloaded/AIX-583/NTI/NTI-Batch3/" + name;
			break;
		case AIX583_NTIBATCH4:
			localLogFileLocation = "./res/Downloaded/AIX-583/NTI/NTI-Batch4/" + name;
			break;
		case AIX583_REPAIRS1:
			localLogFileLocation = "./res/Downloaded/AIX-583/REPAIRS/" + name;
			break;
		case AIX601_IMACSBATCH1:
			localLogFileLocation = "./res/Downloaded/AIX-601/IMACS/IMACS-Batch1/" + name;
			break;
		case AIX601_NTIBATCH1:
			localLogFileLocation = "./res/Downloaded/AIX-601/NTI/NTI-Batch1/NTI-Batch1/" + name;
			break;
		case AIX601_NTIBATCH2:
			localLogFileLocation = "./res/Downloaded/AIX-601/NTI/NTI-Batch2/NTI-Batch2/" + name;
			break;
		}
		return localLogFileLocation;
	}

	public String getLocalBatchFileLocation()
	{
		String localBatchFileLocation = null;
		switch (wrapperBatchType)
		{
		case AIX583_IMACSBATCH1:
			localBatchFileLocation = "./res/Resources/AIX-583/IMACS/IMACS-Batch1/IMACS-Batch1";
			log.debug(localBatchFileLocation);
			break;
		case AIX583_IMACSBATCH2:
			localBatchFileLocation = "./res/Resources/AIX-583/IMACS/IMACS-Batch2/IMACS-Batch2";
			break;
		case AIX583_IMACSBATCH3:
			localBatchFileLocation = "./res/Resources/AIX-583/IMACS/IMACS-Batch3/IMACS-Batch3";
			break;
		case AIX583_NTIBATCH1:
			localBatchFileLocation = "./res/Resources/AIX-583/NTI/NTI-Batch1/NTI-Batch1";
			break;
		case AIX583_NTIBATCH2:
			localBatchFileLocation = "./res/Resources/AIX-583/NTI/NTI-Batch2/NTI-Batch2";
			break;
		case AIX583_NTIBATCH3:
			localBatchFileLocation = "./res/Resources/AIX-583/NTI/NTI-Batch3/NTI-Batch3";
			break;
		case AIX583_NTIBATCH4:
			localBatchFileLocation = "./res/Resources/AIX-583/NTI/NTI-Batch4/NTI-Batch4";
			break;
		case AIX583_REPAIRS1:
			localBatchFileLocation = "./res/Resources/AIX-583/REPAIRS/REPAIRS-BATCH1";
			break;
		case AIX601_IMACSBATCH1:
			localBatchFileLocation = "./res/Resources/AIX-601/IMACS/IMACS-Batch1";
			break;
		case AIX601_NTIBATCH1:
			localBatchFileLocation = "./res/Resources/AIX-601/NTI/NTI-Batch1/NTI-Batch1";
			break;
		case AIX601_NTIBATCH2:
			localBatchFileLocation = "./res/Resources/AIX-601/NTI/NTI-Batch2/NTI-Batch2";
			break;
		}
		return localBatchFileLocation;
	}

	public boolean isWrapperBatchStarted()
	{
		return logLines.get(0) != null;
	}

	public boolean isWrapperBatchCompleted()
	{
		return logLines.get(logLines.size() - 2).indexOf("Completed") != -1;
	}

	/*********************************** Only for testing purposes ************************************/
	public String getLogFileName()
	{
		String logFileName = null;
		switch (wrapperBatchType)
		{
		case AIX583_IMACSBATCH1:
			logFileName = "imacsbatchLog-31012015-194716.log";
			log.debug("Log file name = " + logFileName);
			break;
		case AIX583_IMACSBATCH2:
			logFileName = "imacsbatchLog-31012015-051233.log";
			log.debug("Log file name = " + logFileName);
			break;
		case AIX583_IMACSBATCH3:
			logFileName = "imacsbatchLog-31-01-2015.log";
			log.debug("Log file name = " + logFileName);
			break;
		case AIX583_NTIBATCH1:
			logFileName = "ntibatchLog-31012015-020000.log";
			log.debug("Log file name = " + logFileName);
			break;
		case AIX583_NTIBATCH2:
			logFileName = "ntibatchLog-31012015-040000.log";
			log.debug("Log file name = " + logFileName);
			break;
		case AIX583_NTIBATCH3:
			logFileName = "ntibatchLog-31012015-050000.log";
			log.debug("Log file name = " + logFileName);
			break;
		case AIX583_NTIBATCH4:
			logFileName = "ntibatchLog-31012015-190000.log";
			log.debug("Log file name = " + logFileName);
			break;
		case AIX583_REPAIRS1:
			logFileName = "repairsbatchLog-31-01-2015.log";
			log.debug("Log file name = " + logFileName);
			break;
		case AIX601_IMACSBATCH1:
			logFileName = "imacsbatchLog-31122014-210000.log";
			log.debug("Log file name = " + logFileName);
			break;
		case AIX601_NTIBATCH1:
			logFileName = "ntibatchLog-31012015-020000.log";
			log.debug("Log file name = " + logFileName);
			break;
		case AIX601_NTIBATCH2:
			logFileName = "ntibatchLog-31012015-230000.log";
			log.debug("Log file name = " + logFileName);
			break;
		}
		return logFileName;
	}
}
