package BatchMonitoring;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class Property
{
	private static Property properties;
	public static String REFRESH_RATE;
	public static boolean TIPS_ENABLED;
	public static boolean EXCEPTIONS_ENABLED;
	public static boolean REMINDER_EMAIL_ENABLED;

	public static String REMINDER_EMAIL_FROM;
	public static String REMINDER_EMAIL_TO;
	public static String REMINDER_EMAIL_CC;
	public static String REMINDER_EMAIL_SUBJECT;
	public static String REMINDER_EMAIL_HOST;
	public static String REMINDER_EMAIL_USERNAME;
	public static String REMINDER_EMAIL_PASSWORD;

	public static boolean EXCEPTION_EMAIL_ENABLED;
	public static String EXCEPTION_EMAIL_FROM;
	public static String EXCEPTION_EMAIL_TO;
	public static String EXCEPTION_EMAIL_CC;
	public static String EXCEPTION_EMAIL_SUBJECT;
	public static String EXCEPTION_EMAIL_HOST;
	public static String EXCEPTION_EMAIL_USERNAME;
	public static String EXCEPTION_EMAIL_PASSWORD;

	public static boolean BATCH_COMPLETE_EMAIL_ENABLED;
	public static String BATCH_COMPLETE_EMAIL_FROM;
	public static String BATCH_COMPLETE_EMAIL_TO;
	public static String BATCH_COMPLETE_EMAIL_CC;
	public static String BATCH_COMPLETE_EMAIL_SUBJECT;
	public static String BATCH_COMPLETE_EMAIL_HOST;
	public static String BATCH_COMPLETE_EMAIL_USERNAME;
	public static String BATCH_COMPLETE_EMAIL_PASSWORD;

	public static boolean IRPM_BATCH_JOB_STATUS_EMAIL_ENABLED;
	public static String IRPM_BATCH_COMPLETE_EMAIL_FROM;
	public static String IRPM_BATCH_COMPLETE_EMAIL_TO;
	public static String IRPM_BATCH_COMPLETE_EMAIL_CC;
	public static String IRPM_BATCH_COMPLETE_EMAIL_SUBJECT;
	public static String IRPM_BATCH_COMPLETE_EMAIL_HOST;
	public static String IRPM_BATCH_COMPLETE_EMAIL_USERNAME;
	public static String IRPM_BATCH_COMPLETE_EMAIL_PASSWORD;

	public static String AIX583_SERVER;
	public static String AIX583_USERNAME;
	public static String AIX583_PASSWORD;

	public static String AIX583_IMACS_DIRECTORY_PATH;
	public static String AIX583_IMACS_LOG_SIGNATURE1;
	public static String AIX583_IMACS_LOG_SIGNATURE2;
	public static String AIX583_IMACS_LOG_SIGNATURE3;

	public static String AIX583_NTI_DIRECTORY_PATH;
	public static String AIX583_NTI_LOG_SIGNATURE1;
	public static String AIX583_NTI_LOG_SIGNATURE2;
	public static String AIX583_NTI_LOG_SIGNATURE3;
	public static String AIX583_NTI_LOG_SIGNATURE4;

	public static String AIX583_REPAIRS_DIRECTORY_PATH;
	public static String AIX583_REPAIRS_LOG_SIGNATURE1;

	public static String AIX601_SERVER;
	public static String AIX601_USERNAME;
	public static String AIX601_PASSWORD;

	public static String AIX601_IMACS_DIRECTORY_PATH;
	public static String AIX601_IMACS_LOG_SIGNATURE;
	public static String AIX601_NTI_DIRECTORY_PATH;
	public static String AIX601_NTI_LOG_SIGNATURE1;
	public static String AIX601_NTI_LOG_SIGNATURE2;

	private Property()
	{

		InputStream in = null;
		try
		{
			in = new FileInputStream(new File("./res/Properties/application.properties"));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		Properties props = new Properties();
		if (in != null)
		{
			try
			{
				props.load(in);
				REFRESH_RATE = props.getProperty("REFRESH_RATE");
				TIPS_ENABLED = Boolean.parseBoolean(props.getProperty("TIPS_ENABLED"));
				EXCEPTIONS_ENABLED = Boolean.parseBoolean(props.getProperty("EXCEPTIONS_ENABLED"));
				REMINDER_EMAIL_ENABLED = Boolean.parseBoolean(props.getProperty("REMINDER_EMAIL_ENABLED"));
				BATCH_COMPLETE_EMAIL_ENABLED = Boolean.parseBoolean(props.getProperty("BATCH_COMPLETE_EMAIL_ENABLED"));
				IRPM_BATCH_JOB_STATUS_EMAIL_ENABLED = Boolean.parseBoolean(props.getProperty("IRPM_BATCH_JOB_STATUS_EMAIL_ENABLED"));

				if (REMINDER_EMAIL_ENABLED)
				{
					REMINDER_EMAIL_FROM = props.getProperty("REMINDER_EMAIL_FROM");
					REMINDER_EMAIL_TO = props.getProperty("REMINDER_EMAIL_TO");
					REMINDER_EMAIL_CC = props.getProperty("REMINDER_EMAIL_CC");
					REMINDER_EMAIL_SUBJECT = props.getProperty("REMINDER_EMAIL_TO");
					REMINDER_EMAIL_HOST = props.getProperty("REMINDER_EMAIL_HOST");
					REMINDER_EMAIL_USERNAME = props.getProperty("REMINDER_EMAIL_USERNAME");
					REMINDER_EMAIL_PASSWORD = props.getProperty("REMINDER_EMAIL_PASSWORD");
				}

				if (EXCEPTION_EMAIL_ENABLED)
				{
					EXCEPTION_EMAIL_FROM = props.getProperty("EXCEPTION_EMAIL_FROM");
					EXCEPTION_EMAIL_TO = props.getProperty("EXCEPTION_EMAIL_TO");
					EXCEPTION_EMAIL_CC = props.getProperty("EXCEPTION_EMAIL_CC");
					EXCEPTION_EMAIL_SUBJECT = props.getProperty("EXCEPTION_EMAIL_SUBJECT");
					EXCEPTION_EMAIL_HOST = props.getProperty("EXCEPTION_EMAIL_HOST");
					EXCEPTION_EMAIL_USERNAME = props.getProperty("EXCEPTION_EMAIL_USERNAME");
					EXCEPTION_EMAIL_PASSWORD = props.getProperty("EXCEPTION_EMAIL_PASSWORD");
				}

				if (BATCH_COMPLETE_EMAIL_ENABLED)
				{
					BATCH_COMPLETE_EMAIL_FROM = props.getProperty("BATCH_COMPLETE_EMAIL_FROM");
					BATCH_COMPLETE_EMAIL_TO = props.getProperty("BATCH_COMPLETE_EMAIL_TO");
					BATCH_COMPLETE_EMAIL_CC = props.getProperty("BATCH_COMPLETE_EMAIL_CC");
					BATCH_COMPLETE_EMAIL_SUBJECT = props.getProperty("BATCH_COMPLETE_EMAIL_SUBJECT");
					BATCH_COMPLETE_EMAIL_HOST = props.getProperty("BATCH_COMPLETE_EMAIL_HOST");
					BATCH_COMPLETE_EMAIL_USERNAME = props.getProperty("BATCH_COMPLETE_EMAIL_USERNAME");
					BATCH_COMPLETE_EMAIL_PASSWORD = props.getProperty("BATCH_COMPLETE_EMAIL_PASSWORD");
				}

				if (IRPM_BATCH_JOB_STATUS_EMAIL_ENABLED)
				{
					IRPM_BATCH_COMPLETE_EMAIL_FROM = props.getProperty("IRPM_BATCH_JOB_STATUS_EMAIL_FROM");
					IRPM_BATCH_COMPLETE_EMAIL_TO = props.getProperty("IRPM_BATCH_JOB_STATUS_EMAIL_TO");
					IRPM_BATCH_COMPLETE_EMAIL_CC = props.getProperty("IRPM_BATCH_JOB_STATUS_EMAIL_CC");
					IRPM_BATCH_COMPLETE_EMAIL_SUBJECT = props.getProperty("IRPM_BATCH_JOB_STATUS_EMAIL_SUBJECT");
					IRPM_BATCH_COMPLETE_EMAIL_HOST = props.getProperty("IRPM_BATCH_JOB_STATUS_EMAIL_HOST");
					IRPM_BATCH_COMPLETE_EMAIL_USERNAME = props.getProperty("IRPM_BATCH_JOB_STATUS_EMAIL_USERNAME");
					IRPM_BATCH_COMPLETE_EMAIL_PASSWORD = props.getProperty("IRPM_BATCH_JOB_STATUS_EMAIL_PASSWORD");
				}

				AIX583_SERVER = props.getProperty("AIX583_SERVER");
				AIX583_USERNAME = props.getProperty("AIX583_USERNAME");
				AIX583_PASSWORD = props.getProperty("AIX583_PASSWORD");

				AIX583_IMACS_DIRECTORY_PATH = props.getProperty("AIX583_IMACS_DIRECTORY_PATH");
				AIX583_IMACS_LOG_SIGNATURE1 = props.getProperty("AIX583_IMACS_LOG_SIGNATURE1");
				AIX583_IMACS_LOG_SIGNATURE2 = props.getProperty("AIX583_IMACS_LOG_SIGNATURE2");

				AIX583_NTI_DIRECTORY_PATH = props.getProperty("AIX583_NTI_DIRECTORY_PATH");
				AIX583_NTI_LOG_SIGNATURE1 = props.getProperty("AIX583_NTI_LOG_SIGNATURE1");
				AIX583_NTI_LOG_SIGNATURE2 = props.getProperty("AIX583_NTI_LOG_SIGNATURE2");
				AIX583_NTI_LOG_SIGNATURE3 = props.getProperty("AIX583_NTI_LOG_SIGNATURE3");
				AIX583_NTI_LOG_SIGNATURE4 = props.getProperty("AIX583_NTI_LOG_SIGNATURE4");

				AIX583_REPAIRS_DIRECTORY_PATH = props.getProperty("AIX583_REPAIRS_DIRECTORY_PATH");
				AIX583_REPAIRS_LOG_SIGNATURE1 = props.getProperty("AIX583_REPAIRS_LOG_SIGNATURE1");

				AIX601_SERVER = props.getProperty("AIX601_SERVER");
				AIX601_USERNAME = props.getProperty("AIX601_USERNAME");
				AIX601_PASSWORD = props.getProperty("AIX601_PASSWORD");

				AIX601_IMACS_DIRECTORY_PATH = props.getProperty("AIX601_IMACS_DIRECTORY_PATH");
				AIX601_IMACS_LOG_SIGNATURE = props.getProperty("AIX601_IMACS_LOG_SIGNATURE");

				AIX601_NTI_DIRECTORY_PATH = props.getProperty("AIX601_NTI_DIRECTORY_PATH");
				AIX601_NTI_LOG_SIGNATURE1 = props.getProperty("AIX601_NTI_LOG_SIGNATURE1");
				AIX601_NTI_LOG_SIGNATURE2 = props.getProperty("AIX601_NTI_LOG_SIGNATURE2");

			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Application property file not found!");
		}
	}

	public static Property loadParameter()
	{
		if (properties == null)
		{
			properties = new Property();
		}
		return properties;
	}

	public String toString()
	{
		return "AIX583_USERNAME = " + AIX583_USERNAME + "\n" + "AIX583_PASSWORD = " + AIX583_PASSWORD + "\n" + "AIX583_IMACS_DIRECTORY_PATH = " + AIX583_IMACS_DIRECTORY_PATH + "\n" + "AIX583_IMACS_LOG_SIGNATURE1 = " + AIX583_IMACS_LOG_SIGNATURE1 + "\n" + "AIX583_IMACS_LOG_SIGNATURE2 = " + AIX583_IMACS_LOG_SIGNATURE2 + "\n" + "AIX583_NTI_DIRECTORY_PATH = " + AIX583_NTI_DIRECTORY_PATH + "\n" + "AIX583_NTI_LOG_SIGNATURE1 = " + AIX583_NTI_LOG_SIGNATURE1 + "\n" + "AIX583_NTI_LOG_SIGNATURE2 = " + AIX583_NTI_LOG_SIGNATURE2 + "\n" + "AIX583_NTI_LOG_SIGNATURE3 = " + AIX583_NTI_LOG_SIGNATURE3 + "\n" + "AIX583_NTI_LOG_SIGNATURE4 = " + AIX583_NTI_LOG_SIGNATURE4 + "\n" + "AIX583_REPAIRS_DIRECTORY_PATH = " + AIX583_REPAIRS_DIRECTORY_PATH + "\n" + "AIX583_REPAIRS_LOG_SIGNATURE1 = "
				+ AIX583_REPAIRS_LOG_SIGNATURE1 + "\n" + "AIX601_USERNAME = " + AIX601_USERNAME + "\n" + "AIX601_PASSWORD = " + AIX601_PASSWORD + "\n" + "AIX601_IMACS_DIRECTORY_PATH = " + AIX601_IMACS_DIRECTORY_PATH + "\n" + "AIX601_IMACS_LOG_SIGNATURE = " + AIX601_IMACS_LOG_SIGNATURE + "\n" + "AIX601_NTI_DIRECTORY_PATH = " + AIX601_NTI_DIRECTORY_PATH + "\n" + "AIX601_NTI_LOG_SIGNATURE1 = " + AIX601_NTI_LOG_SIGNATURE1 + "\n" + "AIX601_NTI_LOG_SIGNATURE2 = " + AIX601_NTI_LOG_SIGNATURE2 + "\n";
	}

	public static boolean isPropertyLoaded()
	{
		if (AIX583_USERNAME != null && AIX583_PASSWORD != null && AIX583_IMACS_DIRECTORY_PATH != null && AIX583_IMACS_LOG_SIGNATURE1 != null && AIX583_IMACS_LOG_SIGNATURE2 != null && AIX583_NTI_DIRECTORY_PATH != null && AIX583_NTI_LOG_SIGNATURE1 != null && AIX583_NTI_LOG_SIGNATURE2 != null && AIX583_NTI_LOG_SIGNATURE3 != null && AIX583_NTI_LOG_SIGNATURE4 != null && AIX583_REPAIRS_DIRECTORY_PATH != null && AIX583_REPAIRS_LOG_SIGNATURE1 != null && AIX601_USERNAME != null && AIX601_PASSWORD != null && AIX601_IMACS_DIRECTORY_PATH != null && AIX601_IMACS_LOG_SIGNATURE != null && AIX601_NTI_DIRECTORY_PATH != null && AIX601_NTI_LOG_SIGNATURE1 != null && AIX601_NTI_LOG_SIGNATURE2 != null)
		{
			return true;
		}
		return false;
	}
}

class Demo123
{
	public static void main(String[] args)
	{
		Property.loadParameter();
		InputStream in = Demo123.class.getClassLoader().getResourceAsStream("/application.properties");
	}
}
