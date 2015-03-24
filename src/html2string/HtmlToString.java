package html2string;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class HtmlToString {
	/**
	 * html转string
	 * 
	 * @param filePath
	 * @return
	 */
	public static String readHtmlToString(String filePath) {
		int l = 0;
		StringBuffer str = new StringBuffer("");
		File file = new File(filePath);
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader in = new BufferedReader(isr);
			String line = null;
			while ((line = in.readLine()) != null) {
				if (l != 0) {
					str.append("\r\n" + line);
				} else {
					str.append(line);
				}
				l++;
			}
			in.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("htmlToString出错了。");
		}
		return str.toString();
	}

	/**
	 * 得到某一路径下所有的html文件 并且转换为String
	 * 
	 * @param filePath
	 * @return
	 */
	public static String parseAllHtmlToString(String filePath) {
		StringBuffer sb = new StringBuffer();
		// File file = new File("e:" + File.separator + "0xmltohtml");
		File file = new File(filePath);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				if (f.getName().endsWith(".html")) {
					sb.append(HtmlToString.readHtmlToString(f.getPath()));
					// System.out.println("找到了1个html后缀的文件");
				}
			}
		}
		// System.out.println(sb);
		return sb.toString();
	}
}
