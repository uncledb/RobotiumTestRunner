package xml2html;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class TestXMLToHTML {
	/**
	 * 将XML转换成HTML
	 * 
	 * @throws Exception
	 */
	public static void translate() throws Exception {
		// 创建XML的文件输入流
		FileInputStream fis = new FileInputStream("e:/0xmltohtml/111.xml");
		Source source = new StreamSource(fis);

		// 创建XSL文件的输入流
		FileInputStream fis1 = new FileInputStream("e:/0xmltohtml/111.xsl");
		Source template = new StreamSource(fis1);

		// 创建HTML文件的输出流
		PrintStream htmlPstm = new PrintStream(new File(
				"e:/0xmltohtml/111.html"));

		// 将转换后的结果输出到 htmlPstm 中即 X:\123.html
		Result result = new StreamResult(htmlPstm);

		// 根据XSL文件创建转换对象
		Transformer transformer = TransformerFactory.newInstance()
				.newTransformer(template);
		// 处理xml进行交换
		transformer.transform(source, result);
		// 关闭文件流
		fis1.close();
		fis.close();
	}
}
