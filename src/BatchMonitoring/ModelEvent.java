package BatchMonitoring;

import java.util.ArrayList;

public class ModelEvent
{
	private String server = null;
	private String wrapperBatchName = null;
	private String currentBatch = null;
	private int failCount = 0;
	private int passCount = 0;
	private int progress = 0;

	ModelEvent(WrapperBatch wrapperBatch)
	{
		this.server = wrapperBatch.getServerSignature();
		this.wrapperBatchName = wrapperBatch.getName();
		ArrayList<Batch> batchList = wrapperBatch.getBatchList();
		for (int i = 0; i < batchList.size(); i++)
		{
			Batch batch = batchList.get(i);
			if (batch.getBatchState() == BatchState.COMPLETED && batch.isAlert())
			{
				this.failCount++;
			}
			else
			{
				this.passCount++;
			}
			if (batch.getBatchState() == BatchState.RUNNING)
			{
				this.currentBatch = batch.getName();
			}
		}
		this.progress = ((failCount + passCount) * 100) / batchList.size();

	}

	public String getServer()
	{
		return server;
	}

	public String getWrapperBatchName()
	{
		return wrapperBatchName;
	}

	public String getCurrentBatch()
	{
		return currentBatch;
	}

	public String getFailCount()
	{
		return Integer.toString(failCount);
	}

	public String getPassCount()
	{
		return Integer.toString(passCount);
	}

	public int getProgress()
	{
		return progress;
	}

	public void updateDefaults(WrapperBatch wrapperBatch)
	{

	}

}
