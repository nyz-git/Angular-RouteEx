package utility;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailManager {
	
	public void setEmailConnection() {
		
	}

	public void sendRegisterationEmail(String name, String toEmailId) {

		final String fromEmailId = "niyaz.demo@gmail.com"; // enter your mail id
		final String password = "niyazdemo@123";// enter ur password

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmailId, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmailId)); // same email id
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailId));// whome u
																								// have
																								// to
																								// send
																								// mails
																								// that
																								// person
																								// id
			message.setSubject("Thanks for registering " + name + " !!!");
			message.setText("Welcome");

			Transport.send(message);

			System.out.println("Email Send");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
}
