package BatchMonitoring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class LogReader
{
	private final static Logger log = Logger.getLogger(LogReader.class);

	public void loadLogLines(ArrayList<String> logLines, String localLogFileLocation)
	{
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(new File(localLogFileLocation)));
			log.debug("Loading the log lines from the file at the location = " + localLogFileLocation);
			try
			{
				String line = br.readLine();
				while (line != null)
				{
					log.trace("Reading the line = " + line);
					logLines.add(line);
					line = br.readLine();
				}
			}
			catch (IOException e)
			{
				log.error("Location of the file = " + localLogFileLocation + "||||" + "Error while reading the file!");
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
			log.error("Location of the file = " + localLogFileLocation + "||||" + "File not found at the required place!");
			log.error(e.getMessage());
		}
		log.debug("Size of the log reader = " + logLines.size());
	}
}
