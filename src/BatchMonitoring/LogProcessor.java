package BatchMonitoring;

import org.apache.log4j.Logger;

import java.util.ArrayList;

class LogProcessor
{
	private final static Logger log = Logger.getLogger(LogProcessor.class);

	public void processLogs(WrapperBatch wrapperBatch)
	{
		WrapperBatchType wrapperBatchType = wrapperBatch.getWrapperBatchType();
		switch (wrapperBatchType)
		{
		case AIX583_IMACSBATCH1:
			log.debug("Wrapper Batch Type for Log Processing = " + wrapperBatchType);
			process_AIX583_IMACSBATCH1_Logs(wrapperBatch);
			break;
		case AIX583_IMACSBATCH3:
			log.debug("Wrapper Batch Type for Log Processing = " + wrapperBatchType);
			process_AIX583_IMACSBATCH3_Logs(wrapperBatch);
			break;
		case AIX583_REPAIRS1:
			log.debug("Wrapper Batch Type for Log Processing = " + wrapperBatchType);
			process_AIX583_REPAIRS1_Logs(wrapperBatch);
			break;
		default:
			log.debug("Wrapper Batch Type for Log Processing = " + wrapperBatchType);
			processLogsDefault(wrapperBatch);
			break;
		}
	}

	private void checkExceptions(ArrayList<Batch> batchList, ArrayList<String> logLines, int lineNumber, int currentBatchIndex)
	{
		for (int l = lineNumber; l < logLines.size() && !logLines.get(l).startsWith("Starting "); l++)
		{
			if (logLines.get(l).indexOf("Exception") != -1 || logLines.get(l).indexOf("exception") != -1 || logLines.get(l).indexOf("Error") != -1 || logLines.get(l).indexOf("error") != -1)
			{
				batchList.get(currentBatchIndex).hasAlert(true);
				batchList.get(currentBatchIndex).setExceptionMessage(logLines.get(l) + "\n" + logLines.get(l + 1));
			}
			if (batchList.get(currentBatchIndex).isAlert())
			{
				break;
			}
		}
	}

	private void process_AIX583_IMACSBATCH1_Logs(WrapperBatch wrapperBatch)
	{
		ArrayList<Batch> batchList = wrapperBatch.getBatchList();
		ArrayList<String> logLines = wrapperBatch.getLogLines();
		log.debug("Batch List = " + batchList);
		log.debug("Log Lines = " + logLines);

		int currentBatchIndex = 0;

		// Start reading the log from line 5//
		for (int i = 5; i < logLines.size(); i++) // first for loop//
		{
			String line = logLines.get(i);

			// Reading the first Starting, which has the batch name as well//
			if (line.startsWith("Starting "))
			{
				String tokens[] = line.split(" ");
				String batch = tokens[1].trim();
				boolean isBatchExists = false;
				for (int j = 0; j < batchList.size(); j++) // second for loop//
				{
					if (batch.equalsIgnoreCase(batchList.get(j).getName()) && batchList.get(j).getBatchState() == BatchState.NEW)
					{
						currentBatchIndex = j;
						batchList.get(currentBatchIndex).setBatchState(BatchState.RUNNING);
						isBatchExists = true;
						break;
					}
				} // End of second for loop//

				if (isBatchExists) // start of isBatchExists//
				{
					if (batchList.get(currentBatchIndex).getName().equalsIgnoreCase("STANDINGORDER") && batchList.get(currentBatchIndex).getBatchState() == BatchState.RUNNING)
					{
						System.out.println("In standing order!!!!!!!");
						for (int h = i; h < logLines.size(); h++)
						{
							System.out.println(logLines.get(h));
							if (logLines.get(h).equalsIgnoreCase("No Cash disk to Process !!"))
							{
								batchList.get(currentBatchIndex).setBatchState(BatchState.COMPLETED);
								i = h;
								break;
							}
						}
					}
					else
					{
						for (int j = i; j < logLines.size(); j++) // third for loop//
						{
							/********** Exception Handling(Start) Starting->Starting(with time) **********/
							checkExceptions(batchList, logLines, j, currentBatchIndex);
							/********** Exception Handling(End) **********/
							if (logLines.get(j).indexOf("+:Starting - ") != -1)
							{
								batchList.get(currentBatchIndex).setStartTime(logLines.get(j).substring(0, 0 + 24));
								batchList.get(currentBatchIndex).setBatchState(BatchState.RUNNING);
								for (int k = j; k < logLines.size(); k++)
								{
									/********** Exception Handling(Start) Starting(with time)->Completed(with time) **********/
									checkExceptions(batchList, logLines, k, currentBatchIndex);
									/********** Exception Handling(End) **********/
									if (logLines.get(k).indexOf("+:Completed - ") != -1 || logLines.get(k).indexOf("+:Not Allowed to run") != -1)
									{
										batchList.get(currentBatchIndex).setEndTime(logLines.get(k).substring(0, 0 + 24));
										batchList.get(currentBatchIndex).setBatchState(BatchState.COMPLETED);
										/********** Exception Handling(Start) Completed(with time)->Starting **********/
										checkExceptions(batchList, logLines, k, currentBatchIndex);
										/********** Exception Handling(End) **********/
										i = k;
										break;
									}
								}
								break;
							}
						}// End of third for loop//
					}
				} // end of isBatchExists//
				else
				{
					System.out.println("Batch did not exist!" + batch);
				}
			}

		} // End of first for loop//

	}

	private void process_AIX583_IMACSBATCH3_Logs(WrapperBatch wrapperBatch)
	{
		ArrayList<Batch> batchList = wrapperBatch.getBatchList();
		ArrayList<String> logLines = wrapperBatch.getLogLines();
		int currentBatchIndex = 0;
		for (int i = 5; i < logLines.size(); i++) // first for loop//
		{
			String line1 = logLines.get(i);
			if (line1.startsWith("Starting "))
			{
				String batch = line1.substring(line1.indexOf("Auto"));
				boolean isBatchExists = false;
				for (int j = 0; j < batchList.size(); j++) // second for loop//
				{
					if (batch.equalsIgnoreCase(batchList.get(j).getName()) && batchList.get(j).getBatchState() == BatchState.NEW)
					{
						currentBatchIndex = j;
						batchList.get(currentBatchIndex).setBatchState(BatchState.RUNNING);
						isBatchExists = true;
						break;
					}
				} // End of second for loop//
				if (isBatchExists)
				{

					for (int j = i; j < logLines.size(); j++) // third for loop//
					{

						/********** Exception Handling(Start) Starting->Starting(with time) **********/
						checkExceptions(batchList, logLines, j, currentBatchIndex);
						/********** Exception Handling(End) **********/
						if (logLines.get(j).indexOf("+:Starting - ") != -1)
						{
							batchList.get(currentBatchIndex).setStartTime(logLines.get(j).substring(0, 0 + 24));
							batchList.get(currentBatchIndex).setBatchState(BatchState.RUNNING);
							for (int k = j; k < logLines.size(); k++)
							{
								/********** Exception Handling(Start) Starting(with time)->Completed(with time) **********/
								checkExceptions(batchList, logLines, k, currentBatchIndex);
								/********** Exception Handling(End) **********/
								if (logLines.get(k).indexOf("+:Completed - ") != -1)
								{
									batchList.get(currentBatchIndex).setEndTime(logLines.get(k).substring(0, 0 + 24));
									batchList.get(currentBatchIndex).setBatchState(BatchState.COMPLETED);
									/********** Exception Handling(Start) Completed(with time)->Starting **********/
									checkExceptions(batchList, logLines, k, currentBatchIndex);
									/********** Exception Handling(End) **********/
									i = k;
									break;
								}
							}
							break;
						}
					}// End of third for loop//
				}
				else
				{
					System.out.println("The batch did not exist = " + batch);
				}
			} // End of first for loop//
		}
	}

	private void process_AIX583_REPAIRS1_Logs(WrapperBatch wrapperBatch)
	{
		ArrayList<Batch> batchList = wrapperBatch.getBatchList();
		ArrayList<String> logLines = wrapperBatch.getLogLines();
		int currentBatchIndex = 0;
		for (int i = 5; i < logLines.size(); i++) // first for loop//
		{
			String line1 = logLines.get(i);
			if (line1.startsWith("Starting "))
			{
				String batch = line1.substring(line1.indexOf("CUSTOMER"));
				boolean isBatchExists = false;
				for (int j = 0; j < batchList.size(); j++) // second for loop//
				{
					if (batch.equalsIgnoreCase(batchList.get(j).getName()) && batchList.get(j).getBatchState() == BatchState.NEW)
					{
						currentBatchIndex = j;
						batchList.get(currentBatchIndex).setBatchState(BatchState.RUNNING);
						isBatchExists = true;
						break;
					}
				} // End of second for loop//
				if (isBatchExists)
				{

					for (int j = i; j < logLines.size(); j++) // third for loop//
					{

						/********** Exception Handling(Start) Starting->Starting(with time) **********/
						checkExceptions(batchList, logLines, j, currentBatchIndex);
						/********** Exception Handling(End) **********/
						if (logLines.get(j).indexOf("+:Starting - ") != -1)
						{
							batchList.get(currentBatchIndex).setStartTime(logLines.get(j).substring(0, 0 + 24));
							batchList.get(currentBatchIndex).setBatchState(BatchState.RUNNING);
							for (int k = j; k < logLines.size(); k++)
							{
								/********** Exception Handling(Start) Starting(with time)->Completed(with time) **********/
								checkExceptions(batchList, logLines, k, currentBatchIndex);
								/********** Exception Handling(End) **********/
								if (logLines.get(k).indexOf("+:Completed - ") != -1)
								{
									batchList.get(currentBatchIndex).setEndTime(logLines.get(k).substring(0, 0 + 24));
									batchList.get(currentBatchIndex).setBatchState(BatchState.COMPLETED);
									/********** Exception Handling(Start) Completed(with time)->Starting **********/
									checkExceptions(batchList, logLines, k, currentBatchIndex);
									/********** Exception Handling(End) **********/
									i = k;
									break;
								}
							}
							break;
						}
					}// End of third for loop//
				}
				else
				{
					System.out.println("The batch did not exist = " + batch);
				}
			} // End of first for loop//
		}
	}

	private void processLogsDefault(WrapperBatch wrapperBatch)
	{
		ArrayList<Batch> batchList = wrapperBatch.getBatchList();
		ArrayList<String> logLines = wrapperBatch.getLogLines();
		int currentBatchIndex = 0;
		// Start reading the log from line 5//
		for (int i = 5; i < logLines.size(); i++) // first for loop//
		{
			String line1 = logLines.get(i);
			if (line1.startsWith("Starting "))
			{
				String tokens[] = line1.split(" ");
				String batch = tokens[1].trim();
				boolean isBatchExists = false;
				for (int j = 0; j < batchList.size(); j++) // second for loop//
				{
					if (batch.equalsIgnoreCase(batchList.get(j).getName()) && batchList.get(j).getBatchState() == BatchState.NEW)
					{
						currentBatchIndex = j;
						batchList.get(currentBatchIndex).setBatchState(BatchState.RUNNING);
						isBatchExists = true;
						break;
					}
				} // End of second for loop//
				if (isBatchExists) // start of isBatchExists//
				{
					for (int j = i; j < logLines.size(); j++) // third for loop//
					{
						/********** Exception Handling(Start) Starting->Starting(with time) **********/
						checkExceptions(batchList, logLines, j, currentBatchIndex);
						/********** Exception Handling(End) **********/
						if (logLines.get(j).indexOf("+:Starting - ") != -1)
						{
							batchList.get(currentBatchIndex).setStartTime(logLines.get(j).substring(0, 0 + 24));
							batchList.get(currentBatchIndex).setBatchState(BatchState.RUNNING);
							for (int k = j; k < logLines.size(); k++)
							{
								/********** Exception Handling(Start) Starting(with time)->Completed(with time) **********/
								checkExceptions(batchList, logLines, k, currentBatchIndex);
								/********** Exception Handling(End) **********/
								if (logLines.get(k).indexOf("+:Completed - ") != -1)
								{
									batchList.get(currentBatchIndex).setEndTime(logLines.get(k).substring(0, 0 + 24));
									batchList.get(currentBatchIndex).setBatchState(BatchState.COMPLETED);
									/********** Exception Handling(Start) Completed(with time)->Starting **********/
									checkExceptions(batchList, logLines, k, currentBatchIndex);
									/********** Exception Handling(End) **********/
									i = k;
									break;
								}
							}
							break;
						}
					}// End of third for loop//
				}
				else
				{
					System.out.println("The batch did not exist = " + batch);
				}
			}
		}

	}

}
