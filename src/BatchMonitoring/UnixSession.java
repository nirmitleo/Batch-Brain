package BatchMonitoring;

import com.jcraft.jsch.*;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Inet4Address;
import java.util.Calendar;

class UnixSession
{
	private JSch javaSecureChannel;
	private Session session;

	private final static int PORT_UNIXCOMMAND = 22;
	private final static int PORT_SFTP = 22;

	private String username;
	private String server;
	private String password;

	private final static Logger log = Logger.getLogger(UnixSession.class);

	UnixSession(String server, String username, String password)
	{
		this.server = server;
		this.username = username;
		this.password = password;
		log.debug("****************Server Credentials****************");
		log.debug("Server = " + server);
		log.debug("Username = " + username);
		log.debug("**************************************************");

		javaSecureChannel = new JSch();
		log.debug("JavaSecureChannel = " + javaSecureChannel);
	}

	public boolean downloadFile(WrapperBatchType wrapperBatchType, String remoteFilePath)
	{
		String remoteFileName = null;
		String localFileName = null;
		String remoteLocation = null;
		switch (wrapperBatchType)
		{
		case AIX583_IMACSBATCH1:
			remoteFileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("imacsbatchLog")).trim();
			remoteLocation = remoteFilePath.substring(0, remoteFilePath.lastIndexOf("imacsbatchLog")).trim();
			localFileName = "./res/Downloaded/AIX-583/IMACS/IMACS-Batch1/" + remoteFileName;
			break;
		case AIX583_IMACSBATCH2:
			remoteFileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("imacsbatchLog")).trim();
			remoteLocation = remoteFilePath.substring(0, remoteFilePath.lastIndexOf("imacsbatchLog")).trim();
			localFileName = "./res/Downloaded/AIX-583/IMACS/IMACS-Batch2/" + remoteFileName;
			break;
		case AIX583_IMACSBATCH3:
			System.out.println(remoteFilePath);
			remoteFileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("imacsbatchLog")).trim();
			remoteLocation = remoteFilePath.substring(0, remoteFilePath.lastIndexOf("imacsbatchLog")).trim();
			localFileName = "./res/Downloaded/AIX-583/IMACS/IMACS-Batch3/" + remoteFileName;
			break;
		case AIX583_NTIBATCH1:
			remoteFileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("ntibatchLog")).trim();
			remoteLocation = remoteFilePath.substring(0, remoteFilePath.lastIndexOf("ntibatchLog")).trim();
			localFileName = "./res/Downloaded/AIX-583/NTI/NTI-Batch1/" + remoteFileName;
			break;
		case AIX583_NTIBATCH2:
			remoteFileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("ntibatchLog")).trim();
			remoteLocation = remoteFilePath.substring(0, remoteFilePath.lastIndexOf("ntibatchLog")).trim();
			localFileName = "./res/Downloaded/AIX-583/NTI/NTI-Batch2/" + remoteFileName;
			break;
		case AIX583_NTIBATCH3:
			remoteFileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("ntibatchLog")).trim();
			remoteLocation = remoteFilePath.substring(0, remoteFilePath.lastIndexOf("ntibatchLog")).trim();
			localFileName = "./res/Downloaded/AIX-583/NTI/NTI-Batch3/" + remoteFileName;
			break;
		case AIX583_NTIBATCH4:
			remoteFileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("ntibatchLog")).trim();
			remoteLocation = remoteFilePath.substring(0, remoteFilePath.lastIndexOf("ntibatchLog")).trim();
			localFileName = "./res/Downloaded/AIX-583/NTI/NTI-Batch4/" + remoteFileName;
			break;
		case AIX583_REPAIRS1:
			remoteFileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("repairsbatchLog")).trim();
			remoteLocation = remoteFilePath.substring(0, remoteFilePath.lastIndexOf("repairsbatchLog")).trim();
			localFileName = "./res/Downloaded/AIX-583/Repairs/" + remoteFileName;
			break;
		case AIX601_IMACSBATCH1:
			remoteFileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("imacsbatchLog")).trim();
			remoteLocation = remoteFilePath.substring(0, remoteFilePath.lastIndexOf("imacsbatchLog")).trim();
			localFileName = "./res/Downloaded/AIX-601/IMACS/" + remoteFileName;
			break;
		case AIX601_NTIBATCH1:
			remoteFileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("ntibatchLog")).trim();
			remoteLocation = remoteFilePath.substring(0, remoteFilePath.lastIndexOf("ntibatchLog")).trim();
			localFileName = "./res/Downloaded/AIX-601/NTI/NTI-Batch1/" + remoteFileName;
			break;
		case AIX601_NTIBATCH2:
			remoteFileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("ntibatchLog")).trim();
			remoteLocation = remoteFilePath.substring(0, remoteFilePath.lastIndexOf("ntibatchLog")).trim();
			localFileName = "./res/Downloaded/AIX-601/NTI/NTI-Batch2/" + remoteFileName;
			break;
		}

		File localFile = new File(localFileName);
		try
		{
			localFile.createNewFile();
		}
		catch (IOException e)
		{
			System.out.println("Local file could not be created!");
		}
		try
		{
			System.out.println("PREPARING FOR FILE DOWNLOAD!!");
			session = javaSecureChannel.getSession(username, server, PORT_SFTP);
			System.out.println("File download session = " + session);
			session.setPassword(password);
			System.out.println(username);
			System.out.println(server);
			System.out.println(password);
//			session.setConfig("StrictHostKeyChecking", "no");
			System.out.println("StrictHostKeyChecking = " + session.getConfig("StrictHostKeyChecking"));
			System.out.println("Just before SFTP connection");
			session.connect();
			System.out.println("Just after SFTP connected - Connected!");

			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp channelSFTP = (ChannelSftp) channel;

			channelSFTP.cd(remoteLocation);
			byte buffer[] = new byte[1024];
			BufferedInputStream bis = new BufferedInputStream(channelSFTP.get(remoteFileName));

			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(localFile));

			int count = bis.read(buffer);
			while (count > 0)
			{
				bos.write(buffer, 0, count);
				count = bis.read(buffer);
			}
			bis.close();
			bos.close();
			System.out.println("file downloaded");
			return true;

		}
		catch (Exception e)
		{
			System.out.println("Problem while setting up the connection");
			e.printStackTrace();
			return false;
		}

	}

	public String findFileByCommand(String searchCommand)
	{
		try
		{
			session = javaSecureChannel.getSession(username, server, PORT_UNIXCOMMAND);
			session.setPassword(password);
//			session.setConfig("StrictHostKeyChecking", "no");
			log.debug("JSch Session = " + session);
			session.connect(5000);

			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(searchCommand);
			log.debug("JSch Channel = " + channel);

			BufferedReader br = new BufferedReader(new InputStreamReader(channel.getInputStream()));
			channel.connect();
			log.debug("Connection has been established with the following credentials");
			log.debug("User = " + Inet4Address.getLocalHost().getHostAddress());
			log.debug("Server = " + server);
			log.debug("Username = " + username);
			String line = br.readLine();
			log.debug("Unix search command = " + searchCommand);
			log.debug("Unix output = " + line);

			channel.disconnect();
			log.debug("JSch Channel is safety disconnected.");
			session.disconnect();
			log.debug("JSch Session is safety disconnected.");
			return line;

		}
		catch (Exception e)
		{
			log.error("Problem while setting up the connection to the unix server.");
			log.error("The following credentials would have expired. [High Possibility!]");
			log.error("Server = " + server);
			log.error("Username = " + username);
			log.error(e.getMessage());
		}
		return null;
	}

	public static String generateUnixCommand(WrapperBatchType wrapperBatchType)
	{
		String todayDate = null;
		String pathLocation = null;
		String unixCommand = null;
		switch (wrapperBatchType)
		{
		case AIX583_IMACSBATCH1:
			todayDate = UnixSession.generateFileNameByDate(false);
			pathLocation = "/log/imacs-was";
			unixCommand = "find " + pathLocation + " -name 'imacsbatchLog-" + todayDate + "-1[0-9][0-9][0-9][0-9][0-9].log'";
			log.debug("WrapperBatchType = " + wrapperBatchType + "\t" + "Unix command = " + unixCommand);
			break;
		case AIX583_IMACSBATCH2:
			todayDate = UnixSession.generateFileNameByDate(true);
			pathLocation = "/log/imacs-was";
			unixCommand = "find " + pathLocation + " -name 'imacsbatchLog-" + todayDate + "-[0-9][0-9][0-9][0-9][0-9][0-9].log'";
			log.debug("WrapperBatchType = " + wrapperBatchType + "\t" + "Unix command = " + unixCommand);
			break;
		case AIX583_IMACSBATCH3:
			todayDate = UnixSession.generateFileNameByDate(true);
			todayDate = todayDate.substring(0, 2) + "-" + todayDate.substring(2, 2 + 2) + "-" + todayDate.substring(4, 4 + 4);
			pathLocation = "/log/imacs-was";
			unixCommand = "find " + pathLocation + " -name 'imacsbatchLog-" + todayDate + ".log'";
			log.debug("WrapperBatchType = " + wrapperBatchType + "\t" + "Unix command = " + unixCommand);
			break;
		case AIX583_NTIBATCH1:
			todayDate = UnixSession.generateFileNameByDate(true);
			pathLocation = "/log/nti-was";
			unixCommand = "find " + pathLocation + " -name 'ntibatchLog-" + todayDate + "-02[0-9][0-9][0-9][0-9].log'";
			log.debug("WrapperBatchType = " + wrapperBatchType + "\t" + "Unix command = " + unixCommand);
			break;
		case AIX583_NTIBATCH2:
			todayDate = UnixSession.generateFileNameByDate(true);
			pathLocation = "/log/nti-was";
			unixCommand = "find " + pathLocation + " -name 'ntibatchLog-" + todayDate + "-04[0-9][0-9][0-9][0-9].log'";
			log.debug("WrapperBatchType = " + wrapperBatchType + "\t" + "Unix command = " + unixCommand);
			break;
		case AIX583_NTIBATCH3:
			todayDate = UnixSession.generateFileNameByDate(true);
			pathLocation = "/log/nti-was";
			unixCommand = "find " + pathLocation + " -name 'ntibatchLog-" + todayDate + "-05[0-9][0-9][0-9][0-9].log'";
			log.debug("WrapperBatchType = " + wrapperBatchType + "\t" + "Unix command = " + unixCommand);
			break;
		case AIX583_NTIBATCH4:
			todayDate = UnixSession.generateFileNameByDate(false);
			pathLocation = "/log/nti-was";
			unixCommand = "find " + pathLocation + " -name 'ntibatchLog-" + todayDate + "-19[0-9][0-9][0-9][0-9].log'";
			log.debug("WrapperBatchType = " + wrapperBatchType + "\t" + "Unix command = " + unixCommand);
			break;
		case AIX583_REPAIRS1:
			todayDate = UnixSession.generateFileNameByDate(true);
			todayDate = todayDate.substring(0, 2) + "-" + todayDate.substring(2, 2 + 2) + "-" + todayDate.substring(4, 4 + 4);
			pathLocation = "/opt/app/repairs/log";
			unixCommand = "find " + pathLocation + " -name 'repairsbatchLog-" + todayDate + ".log'";
			log.debug("WrapperBatchType = " + wrapperBatchType + "\t" + "Unix command = " + unixCommand);
			break;
		case AIX601_IMACSBATCH1:
			todayDate = UnixSession.generateFileNameByDate(false);
			pathLocation = "/opt/app/imacs-was/log";
			unixCommand = "find " + pathLocation + " -name 'imacsbatchLog-" + todayDate + "-[0-9][0-9][0-9][0-9][0-9][0-9].log'";
			break;
		case AIX601_NTIBATCH1:
			todayDate = UnixSession.generateFileNameByDate(true);
			pathLocation = "/opt/app/nti-was/log";
			unixCommand = "find " + pathLocation + " -name 'ntibatchLog-" + todayDate + "-[0-9][0-9][0-9][0-9][0-9][0-9].log'";
			log.debug("WrapperBatchType = " + wrapperBatchType + "\t" + "Unix command = " + unixCommand);
			break;
		case AIX601_NTIBATCH2:
			todayDate = UnixSession.generateFileNameByDate(false);
			pathLocation = "/opt/app/nti-was/log";
			unixCommand = "find " + pathLocation + " -name 'ntibatchLog-" + todayDate + "-[0-9][0-9][0-9][0-9][0-9][0-9].log'";
			log.debug("WrapperBatchType = " + wrapperBatchType + "\t" + "Unix command = " + unixCommand);
			break;
		}
		return unixCommand;
	}

	private static String generateFileNameByDate(boolean isToday)
	{

		Calendar c = Calendar.getInstance();
		String dd = Integer.toString(c.get(Calendar.DATE));
		if (!isToday)
		{
			Integer temp = Integer.parseInt(dd);
			temp--;
			dd = Integer.toString(temp);
		}

		if (dd.length() != 2)
		{
			dd = "0" + dd;
		}
		String mm = Integer.toString(c.get(Calendar.MONTH) + 1);
		if (mm.length() != 2)
		{
			mm = "0" + mm;
		}
		String yyyy = Integer.toString(c.get(Calendar.YEAR));
		return dd + mm + yyyy;
	}
}