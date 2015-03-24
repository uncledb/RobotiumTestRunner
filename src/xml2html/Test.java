package xml2html;

public class Test {
	public static void main(String[] args) {
		try {
			TestXMLToHTML.translate();
		} catch (Exception e) {
			System.out.println("XML转换成HTML失败：" + e.getMessage());
		}
	}
}
