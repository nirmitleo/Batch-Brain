package BatchMonitoring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Batch
{
	private String name;
	private Date startTime;
	private Date endTime;
	private BatchState state = BatchState.NEW;
	private boolean alert = false;
	private String exceptionMessage;
	private String tip;

	Batch(String name)
	{
		this.name = name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}

	public void setStartTime(String time)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("EEE dd MMM hh:mm:ss yyyy");
		try
		{
			this.startTime = sdf.parse(time);
		}
		catch (ParseException e)
		{
			System.out.println("Parsing problem with start time");
		}
	}

	public Date getStartTime()
	{
		return startTime;
	}

	public void setEndTime(String time)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("EEE dd MMM hh:mm:ss yyyy");
		try
		{
			this.endTime = sdf.parse(time);
		}
		catch (ParseException e)
		{
			System.out.println("Parsing problem with the end time");
		}
	}

	public Date getEndTime()
	{
		return endTime;
	}

	public void setBatchState(BatchState state)
	{
		this.state = state;
	}

	public BatchState getBatchState()
	{
		return state;
	}

	public void hasAlert(boolean alert)
	{
		this.alert = alert;
	}

	public boolean isAlert()
	{
		return alert;
	}

	public void setExceptionMessage(String exceptionMessage)
	{
		this.exceptionMessage = exceptionMessage;
	}

	public String getExceptionMessage()
	{
		return this.exceptionMessage;
	}


	public String timeInterval()
	{
		Calendar calendarStartTime = Calendar.getInstance();
		calendarStartTime.setTime(this.getStartTime());

		Calendar calendarEndTime = Calendar.getInstance();
		calendarEndTime.setTime(this.getEndTime());

		long timeDifference = (calendarEndTime.getTimeInMillis() - calendarStartTime.getTimeInMillis()) / 1000;
		String hh = Long.toString(timeDifference / 3600);
		String mm = Long.toString((timeDifference % 3600) / 60);
		String ss = Long.toString((timeDifference % 3600) % 60);

		if (hh.length() == 1)
		{
			hh = "0" + hh;
		}
		if (mm.length() == 1)
		{
			mm = "0" + mm;
		}
		if (ss.length() == 1)
		{
			ss = "0" + ss;
		}

		return hh + ":" + mm + ":" + ss;
	}
}
