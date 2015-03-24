package xml2html;

import java.io.File;

/**
 * CLASSPATH文件的绝对路径获取测试
 */
public class PathTest {
	// classpath的文件路径
	private static String XSL_PATH = "/xmltohtml/mydemo/111.xsl";

	public static void main(String[] args) {
		// 当前类的绝对路径
		System.out.println(Test.class.getResource("/").getFile());
		// 指定CLASSPATH文件的绝对路径
		System.out.println(Test.class.getResource(XSL_PATH).getFile());
		// 指定CLASSPATH文件的绝对路径
		File f = new File(Test.class.getResource(XSL_PATH).getFile());
		System.out.println(f.getPath());
	}
}
