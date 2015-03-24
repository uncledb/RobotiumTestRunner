package html2string;

import java.io.File;

import mail.MailUtil;

public class Test {
	public static void main(String[] args) {
		String path = "e:" + File.separator + "0xmltohtml";
		String htmlString = HtmlToString.parseAllHtmlToString(path);
		System.out.println(htmlString);
		MailUtil.sendHTMLMail(htmlString);
	}
}
