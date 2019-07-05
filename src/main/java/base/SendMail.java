package base;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	
	private Properties props;
	private Session session;
	private Logger logger;

	@SuppressWarnings("static-access")
  public SendMail() {
		props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(System.getenv("EMAIL_ADR"), System.getenv("EMAIL_PW"));
				}
			});
		logger = logger.getAnonymousLogger();
	}
	
	public void send(String mail, String code) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("tg1a.2.swe1@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(mail));
			message.setSubject("Verificationcode Activity Meter - No Reply");
			message.setText("Hello Activty Meter User," +
					"\n\n you requested an verification code for a new Post. Enter the follwoing Code into the Testfield before sedning a Post."
					+"\n\n The Verificationcode can only be used once."
					+"\n\n Your new Code is: " + code + "\n\n \n\n \n\n"
					+ "This is an autogenarted email. Do not reply to this email. If you did not request an Verification Code please ignore the email");
			Transport.send(message);
		} catch (MessagingException e) {			
		  logger.log(Level.WARNING ,"SendMail Failure." , e);
		}
	}
}
