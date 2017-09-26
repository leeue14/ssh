package ssh.web.utils;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

//发送带有图片的邮件
public class MailDemo2 {
	public static void main(String[] args) throws AddressException, MessagingException {
		Properties props = new Properties();
		
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.163.com");//JavaMail规范指定的
		props.setProperty("mail.smtp.auth", "true");//需要身份验证，如果不认证就不能发送邮件
		
		Session session = Session.getInstance(props);//发邮件的一些环境
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		
		message.setFrom(new InternetAddress("18081141953@163.com"));//设置发件人
		message.setRecipients(Message.RecipientType.TO, "1264308633@qq.com");
		message.setSubject("文本中内嵌图片的邮件");
		message.setContent("Hello Message Imag Mail","text/plain");
		
		//文本部分
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent("aaa<img src='cid:mm'/>aaa","text/html");
		//图片部分
		MimeBodyPart imagePart = new MimeBodyPart();
		DataHandler dh = new DataHandler(new FileDataSource("src/0.jpg"));
		//借助框架
		imagePart.setDataHandler(dh);
		imagePart.setContentID("mm");
		
		//描述关系
		MimeMultipart mPart = new MimeMultipart();
		mPart.addBodyPart(textPart);
		mPart.addBodyPart(imagePart);
		mPart.setSubType("related");//设置关系
		message.setContent(mPart);
		message.saveChanges();
		//发邮件
		Transport ts = session.getTransport();
		ts.connect("18081141953","ly13966435312");
		ts.sendMessage(message, message.getAllRecipients());
}

}
