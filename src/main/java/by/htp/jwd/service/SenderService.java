package by.htp.jwd.service;

public interface SenderService {

	final String MAIL_SMTP_HOST = "mail.smtp.host";
	final String SMTP_GMAIL_COM = "smtp.gmail.com";
	final String MAIL_SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";
	final String NUMBER = "465";
	final String MAIL_SMTP_SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";
	final String JAVAX_NET_SSL_SSLSOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
	final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	final String MAIL_SMTP_PORT = "mail.smtp.port";
	final String TRUE = "true";
	
	public void send(String subject, String text, String fromEmail,String password, String toEmail) throws ServiceException;
}
