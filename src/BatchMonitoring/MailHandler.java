package BatchMonitoring;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailHandler
{

	public static void sendExceptionMails(String wrapperBatchName, String batchName, String exceptionMessage)
	{
		String recipient = Property.EXCEPTION_EMAIL_TO;
		String sender = Property.EXCEPTION_EMAIL_FROM;
		String cc = Property.EXCEPTION_EMAIL_CC;
		String host = Property.EXCEPTION_EMAIL_HOST;
		final String username = Property.EXCEPTION_EMAIL_USERNAME;
		final String password = Property.EXCEPTION_EMAIL_PASSWORD;
		String subject = Property.EXCEPTION_EMAIL_SUBJECT;

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(username, password);
			}
		});

		try
		{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject(subject);
			message.setText("Wrapper batch name = " + wrapperBatchName + "\n" + "Batch name = " + batchName + "\n" + "Java exception = " + exceptionMessage + "\n - mail sent!");
			Transport.send(message);
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
		}
	}

	public static void sendReminderMails()
	{
		String recipient = Property.REMINDER_EMAIL_TO;
		String sender = Property.REMINDER_EMAIL_FROM;
		String cc = Property.REMINDER_EMAIL_CC;
		String host = Property.REMINDER_EMAIL_HOST;
		final String username = Property.REMINDER_EMAIL_USERNAME;
		final String password = Property.REMINDER_EMAIL_PASSWORD;
		String subject = Property.REMINDER_EMAIL_SUBJECT;

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(username, password);
			}
		});

		try
		{

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject(subject);
			message.setText("Run mailmerge and daily check sent!");
			Transport.send(message);
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
		}
	}

	public static void sendStatusCompleteMails(String wrapperBatchName)
	{
		String recipient = Property.BATCH_COMPLETE_EMAIL_TO;
		String sender = Property.BATCH_COMPLETE_EMAIL_FROM;
		String cc = Property.BATCH_COMPLETE_EMAIL_CC;
		String host = Property.BATCH_COMPLETE_EMAIL_HOST;
		final String username = Property.BATCH_COMPLETE_EMAIL_USERNAME;
		final String password = Property.BATCH_COMPLETE_EMAIL_PASSWORD;
		String subject = Property.BATCH_COMPLETE_EMAIL_SUBJECT;

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(username, password);
			}
		});

		try
		{

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject(subject);
			message.setText(wrapperBatchName + " is completed - mail sent!");
			Transport.send(message);
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
		}
	}

	public static void sendIRPMBatchJobStatusMails()
	{
		String recipient = Property.IRPM_BATCH_COMPLETE_EMAIL_TO;
		String sender = Property.IRPM_BATCH_COMPLETE_EMAIL_FROM;
		String cc = Property.IRPM_BATCH_COMPLETE_EMAIL_CC;
		String host = Property.IRPM_BATCH_COMPLETE_EMAIL_HOST;
		final String username = Property.IRPM_BATCH_COMPLETE_EMAIL_USERNAME;
		final String password = Property.IRPM_BATCH_COMPLETE_EMAIL_PASSWORD;
		String subject = Property.IRPM_BATCH_COMPLETE_EMAIL_SUBJECT;

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(username, password);
			}
		});

		try
		{

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject(subject);
			message.setText("IRPM Batch Job Status sent!");
			Transport.send(message);
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
		}
	}
}
