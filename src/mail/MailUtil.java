package mail;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MailUtil {

	public static void sendHTMLMail(String detail) {
		SimpleDateFormat dateformat1 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss E");
		String date = dateformat1.format(new Date());
		System.out.println(date);
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("xxx@163.com");
		mailInfo.setPassword("xxx");// 您的邮箱密码
		mailInfo.setFromAddress("xxx@163.com");
		mailInfo.setToAddress("xxx@qq.com");
		mailInfo.setSubject("自动化测试结果" + date);// 设置题目
		mailInfo.setContent(detail);// 设置内容
		// 这个类主要来发送邮件
		// SimpleMailSender sms = new SimpleMailSender();
		// sms.sendTextMail(mailInfo);// 发送文体格式
		boolean b = SimpleMailSender.sendHtmlMail(mailInfo);// 发送html格式
		if (b) {
			System.out.println("发送完成...");
		} else {
			System.out.println("发送失败...");
		}
	}
}
