package xmlreader;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test {
	public static void main(String[] args) {
		// 使用xml管理用例 读取用例列表
		// XmlUtil dom4j = new XmlUtil();
		// Dom4jDemo dom4j = new Dom4jDemo();
		// dom4j.parserXml("E:/ByHandXMLResult.xml");
		// dom4j.parserXml("E:/Test_Report.xml");

		Map<String, String> cases = XmlUtil.testXPath("e:/TestCases.xml");

		Set<String> key = cases.keySet();

		for (Iterator<String> it = key.iterator(); it.hasNext();) {
			String s = it.next();
			System.out.print(s + ":");
			System.out.print(cases.get(s));
			System.out.println();
		}

		// dom4j.createXml("e:/TestCases2.xml");

		// String result = ADBUtil.runCommand(ADBUtil.DEVICES);
		// List<String> s = ADBUtil.getDevices();

	}
}
