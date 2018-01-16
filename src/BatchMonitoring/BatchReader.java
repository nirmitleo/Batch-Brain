package BatchMonitoring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

class BatchReader
{
	private final static Logger log = Logger.getLogger(BatchReader.class);

	public void loadBatchNames(ArrayList<Batch> batchList, final String localBatchFileLocation)
	{
		BufferedReader br;
		try
		{
			br = new BufferedReader(new FileReader(new File(localBatchFileLocation)));
			log.debug("Loading the batch name from the file at the location = " + localBatchFileLocation);
			try
			{
				String line = br.readLine().trim();
				while (line != null)
				{
					log.trace("Reading the line = " + line);
					batchList.add(new Batch(line));
					line = br.readLine();
				}
			}
			catch (IOException e)
			{
				log.error("Location of the file = " + localBatchFileLocation + "||||" + "Error while reading the file!");
				log.error(e.getMessage());
			}
			finally
			{
				try
				{
					if (br != null)
					{
						br.close();
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		catch (FileNotFoundException e)
		{
			log.error("Location of the file = " + localBatchFileLocation + "||||" + "File not found at the required place!");
			log.error(e.getMessage());
		}
	}
}
