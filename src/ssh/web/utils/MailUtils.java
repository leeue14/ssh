package ssh.web.utils;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 发送邮件的工具类
 */
public class MailUtils {
	/*
	 * public static void sendMail(String to,String code) throws Exception{
	 * Properties props = new Properties(); props.setProperty("mail.smtp",
	 * "127.0.0.1"); // 1.Session对象.连接(与邮箱服务器连接) Session session =
	 * Session.getInstance(props, new Authenticator() {
	 * 
	 * @Override protected PasswordAuthentication getPasswordAuthentication() {
	 * return new PasswordAuthentication("service@lan.com", "1"); }
	 * 
	 * });
	 * 
	 * // 2.构建邮件信息: Message message = new MimeMessage(session); // 发件人:
	 * message.setFrom(new InternetAddress("service@lan.com")); // 收件人:
	 * message.setRecipient(RecipientType.TO, new InternetAddress(to)); // 设置标题
	 * message.setSubject("来自 LAOLAN 激活邮件"); // 设置正文 message.setContent(
	 * "<h1>来自  LAOLAN 的官网激活邮件</h1><h3><a href='http://127.0.0.1:8080/shop/user_active.action?code="
	 * +
	 * code+"'>http://127.0.0.1:8080/shop/user_active.action?code="+code+"</a></h3>"
	 * , "text/html;charset=UTF-8");
	 * 
	 * // 3.发送对象 Transport.send(message); }
	 */

	public static void sendMail(String to, String code) throws Exception {
		Properties props = new Properties();

		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.163.com");// JavaMail规范指定的
		props.setProperty("mail.smtp.auth", "true");// 需要身份验证，如果不认证就不能发送邮件

		Session session = Session.getInstance(props);// 发邮件的一些环境
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);

		message.setFrom(new InternetAddress("18081782961@163.com"));// 设置发件人
		message.setRecipient(RecipientType.TO, new InternetAddress(to));
		message.setContent(
				"<h1>来自  LAOLAN 的官网激活邮件</h1><h3><a href='http://127.0.0.1:8080/shop/user_active.action?code="
						+ code
						+ "'>http://127.0.0.1:8080/shop/user_active.action?code="
						+ code + "</a></h3>", "text/html;charset=UTF-8");
		message.saveChanges();
		// 发邮件
		Transport ts = session.getTransport();
		ts.connect("18081782961", "lan970220");
		ts.sendMessage(message, message.getAllRecipients());
 	}

}
