package xmlreader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import main.TestRunner;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * Dom4j
 */
public class XmlUtil {
	/**
	 * 前期使用的工具方法 现在已经没有用处
	 * 
	 * @param fileName
	 */
	public static void createXmlByClass(String fileName) {
		Document document = DocumentHelper.createDocument();
		Element employees = document.addElement("testcases");
		TestRunner testRunner = new TestRunner();
		List<String> list = testRunner.getFinalStrings();
		System.out.println(list.size());
		for (int j = 0; j < list.size(); j++) {
			Element employee = employees.addElement("testcase");
			employee.addAttribute("casename", list.get(j));
			try {
				employee.addAttribute("class", testRunner.getClass()
						.getDeclaredField(list.get(j)).get(list.get(j))
						.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			Writer fileWriter = new FileWriter(fileName);
			XMLWriter xmlWriter = new XMLWriter(fileWriter);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}

	}

	/**
	 * 解析XML文档 demo -- 无用
	 * 
	 * @param fileName
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void parserXml(String fileName) {
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(inputXml);
			Element root = document.getRootElement();
			for (Iterator i = root.elementIterator(); i.hasNext();) {
				Element employee = (Element) i.next();
				System.out.println(" " + employee.getName());
				for (Iterator j = employee.elementIterator(); j.hasNext();) {
					Element node1 = (Element) j.next();

					// 得到该标签的属性
					List<Attribute> atts = node1.attributes();
					System.out.print("  " + node1.getName() + ":"
							+ node1.getText());
					// 遍历标签的属性
					for (int k = 0; k < atts.size(); k++) {
						System.out.print("" + atts.get(k).getName() + "="
								+ atts.get(k).getValue() + " ");
					}
					for (Iterator y = node1.elementIterator(); y.hasNext();) {
						Element node2 = (Element) y.next();
						System.out.println("");
						System.out.print("   " + node2.getName() + " "
								+ node2.getText());
					}
					System.out.println("");
				}
				System.out.println(" " + employee.getName());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 读取xml文件到map 有用
	 * 
	 * @param fileName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> testXPath(String fileName) {
		SAXReader reader = new SAXReader();
		Document doc;
		Map<String, String> cases = new LinkedHashMap<String, String>();
		try {
			doc = reader.read(new File(fileName));
			Element root = doc.getRootElement();
			// System.out.println(root.getName());
			List<Element> param = root.elements();
			for (Element element : param) {
				String casename = element.attributeValue("casename").trim();
				String caseclass = element.attributeValue("class").trim();
				// System.out.println("casename:" + casename + ",class:"
				// + caseclass);
				cases.put(casename, caseclass);
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return cases;
	}
}
