package by.htp.jwd.service.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.service.SenderService;
import by.htp.jwd.service.ServiceException;

public class SenderServiceImpl implements SenderService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(SenderServiceImpl.class);

	@Override
	public void send(String subject, String text, String fromEmail, String password, String toEmail) throws ServiceException {
		
		LOGGER.info("SenderServiceImpl started adressCar send.");
		
		Properties props = new Properties();
		props.put(MAIL_SMTP_HOST, SMTP_GMAIL_COM);
		props.put(MAIL_SMTP_SOCKET_FACTORY_PORT, NUMBER);
		props.put(MAIL_SMTP_SOCKET_FACTORY_CLASS, JAVAX_NET_SSL_SSLSOCKET_FACTORY);
		props.put(MAIL_SMTP_AUTH, TRUE);
		props.put(MAIL_SMTP_PORT, NUMBER);
		
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});

		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(fromEmail));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

			message.setSubject(subject);

			message.setText(text);

			Transport.send(message);
		} catch (MessagingException e) {

		}
	}

}
