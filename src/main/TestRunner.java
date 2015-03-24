package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import xmlreader.XmlUtil;

/**
 * 用于运行测试用例 和 读入robotium.xls 文件 和 读出/sdcard/robotium/Test_Report.xml 后续考虑将常量分离
 * 
 * @author Dongbin
 * 
 */
public class TestRunner {
	// 导入的命令 adb push e:/robotium.xls /sdcard/
	// 导出的命令 adb pull /storage/sdcard0/robotium/Test_Report.xml d:/
	// 执行的命令 adb shell am instrument -e class
	// com.yonyou.travelmanager2.cases.runner.RunAllTestCase -w
	// com.yonyou.travelmanager2.test/com.yonyou.android.test.InstrumentationTestRunner
	public static String CRASH = "shortMsg=Process crashed";

	// public static String CLEARDATA =
	// "adb shell pm clear com.yonyou.travelmanager2";
	// //
	// --------任务单模块-------------------------------------------------------------------------------------------------------------
	// public static final String TRAVEL_T1 =
	// "com.yonyou.travelmanager2.cases.travelapply.Travel_T1_AddTravelTestCase";
	// public static final String TRAVEL_T2 =
	// "com.yonyou.travelmanager2.cases.travelapply.Travel_T2_LookTravelTestCase";
	// public static final String TRAVEL_T3 =
	// "com.yonyou.travelmanager2.cases.travelapply.Travel_T3_EditTravelTestCase";
	// //public static final String TRAVEL_T4 = "";
	// public static final String TRAVEL_T5 =
	// "com.yonyou.travelmanager2.cases.travelapply.Travel_T5_CopyTravelTestCase";
	// public static final String TRAVEL_T6 =
	// "com.yonyou.travelmanager2.cases.travelapply.Travel_T6_FilterRepeatPersonTestCase";
	// public static final String TRAVEL_T7 =
	// "com.yonyou.travelmanager2.cases.travelapply.Travel_T7_RefreshAndGetMoreTestCase";
	// public static final String TRAVEL_T8 =
	// "com.yonyou.travelmanager2.cases.travelapply.Travel_T8_CallOrMessageToPersonTestCase";
	//
	// //
	// ---------预订模块--------------------------------------------------------------------------------------------------------------
	// public static final String ORDER_T1 =
	// "com.yonyou.travelmanager2.cases.order.Order_T1_IWantToOrderTestCase";
	// public static final String ORDER_T2 =
	// "com.yonyou.travelmanager2.cases.order.Order_T2_JourneyOrderTestCase";
	// public static final String ORDER_T3 =
	// "com.yonyou.travelmanager2.cases.order.Order_T3_DirectOrderTestCase";
	//
	// //
	// ---------审批模块--------------------------------------------------------------------------------------------------------------
	// public static final String EXAMINE_T1 =
	// "com.yonyou.travelmanager2.cases.examine.Examine_T1_TravelApplyTestCase";
	// //public static final String EXAMINE_T2 = "";
	// public static final String EXAMINE_T3 =
	// "com.yonyou.travelmanager2.cases.examine.Examine_T3_EditTravelTestCase";
	// //public static final String EXAMINE_T4 = "";
	//
	// //
	// ---------我模块----------------------------------------------------------------------------------------------------------------
	// public static final String MINE_T1 =
	// "com.yonyou.travelmanager2.cases.mine.Mine_T1_RepassTestCase";
	// public static final String MINE_T2 =
	// "com.yonyou.travelmanager2.cases.mine.Mine_T2_MyOrderListTestCase";
	// public static final String MINE_T3 =
	// "com.yonyou.travelmanager2.cases.mine.Mine_T3_VersionUpdateTestCase";
	// public static final String MINE_T4 =
	// "com.yonyou.travelmanager2.cases.mine.Mine_T4_CancelOrderTestCase";
	// public static final String MINE_T5 =
	// "com.yonyou.travelmanager2.cases.mine.Mine_T5_MyExamineTravelTestCase";
	// public static final String MINE_T6 =
	// "com.yonyou.travelmanager2.cases.mine.Mine_T6_ChangeMyInfoTestCase";
	// public static final String MINE_T7 =
	// "com.yonyou.travelmanager2.cases.mine.Mine_T7_SuggestionTestCase";
	//
	// //
	// ---------消息模块----------------------------------------------------------------------------------------------------------------
	// public static final String MESSAGE_T1 =
	// "com.yonyou.travelmanager2.cases.message.Message_T1_LookMessageTestCase";
	// public static final String MESSAGE_T4 =
	// "com.yonyou.travelmanager2.cases.message.Message_T4_ClickAllMessageInTestCase";
	//
	// //
	// ---------审批流部门-----------------------------------------------------------------------------------------------------------
	// public static final String DTPROCESS_T0 =
	// "com.yonyou.travelmanager2.cases.process.department.DtProcess_T0_D0TestCase";
	// public static final String DTPROCESS_T1 =
	// "com.yonyou.travelmanager2.cases.process.department.DtProcess_T1_D1TestCase";
	// public static final String DTPROCESS_T2 =
	// "com.yonyou.travelmanager2.cases.process.department.DtProcess_T2_D2TestCase";
	// public static final String DTPROCESS_T3 =
	// "com.yonyou.travelmanager2.cases.process.department.DtProcess_T3_D3TestCase";
	// public static final String DTPROCESS_T4 =
	// "com.yonyou.travelmanager2.cases.process.department.DtProcess_T4_D4TestCase";
	// public static final String DTPROCESS_T5 =
	// "com.yonyou.travelmanager2.cases.process.department.DtProcess_T5_D5TestCase";
	// public static final String DTPROCESS_T6 =
	// "com.yonyou.travelmanager2.cases.process.department.DtProcess_T6_D6TestCase";
	// public static final String DTPROCESS_T7 =
	// "com.yonyou.travelmanager2.cases.process.department.DtProcess_T7_D7TestCase";
	// public static final String DTPROCESS_T8 =
	// "com.yonyou.travelmanager2.cases.process.department.DtProcess_T8_D8TestCase";
	// public static final String DTPROCESS_T9 =
	// "com.yonyou.travelmanager2.cases.process.department.DtProcess_T9_D9TestCase";
	// public static final String DTPROCESS_T10 =
	// "com.yonyou.travelmanager2.cases.process.department.DtProcess_T10_D10TestCase";
	//
	// //
	// ---------审批流项目-----------------------------------------------------------------------------------------------------------
	// public static final String PTPROCESS_T0 =
	// "com.yonyou.travelmanager2.cases.process.project.PtProcess_T0_P0TestCase";
	// public static final String PTPROCESS_T1 =
	// "com.yonyou.travelmanager2.cases.process.project.PtProcess_T1_P1TestCase";
	// public static final String PTPROCESS_T2 =
	// "com.yonyou.travelmanager2.cases.process.project.PtProcess_T2_P2TestCase";
	// public static final String PTPROCESS_T3 =
	// "com.yonyou.travelmanager2.cases.process.project.PtProcess_T3_P3TestCase";
	// public static final String PTPROCESS_T4 =
	// "com.yonyou.travelmanager2.cases.process.project.PtProcess_T4_P4TestCase";
	// public static final String PTPROCESS_T5 =
	// "com.yonyou.travelmanager2.cases.process.project.PtProcess_T5_P5TestCase";
	// public static final String PTPROCESS_T6 =
	// "com.yonyou.travelmanager2.cases.process.project.PtProcess_T6_P6TestCase";
	// public static final String PTPROCESS_T7 =
	// "com.yonyou.travelmanager2.cases.process.project.PtProcess_T7_P7TestCase";
	// public static final String PTPROCESS_T8 =
	// "com.yonyou.travelmanager2.cases.process.project.PtProcess_T8_P8TestCase";
	// public static final String PTPROCESS_T9 =
	// "com.yonyou.travelmanager2.cases.process.project.PtProcess_T9_P9TestCase";
	// public static final String PTPROCESS_T10 =
	// "com.yonyou.travelmanager2.cases.process.project.PtProcess_T10_P10TestCase";
	// public static final String PTPROCESS_T11 =
	// "com.yonyou.travelmanager2.cases.process.project.PtProcess_T11_P11TestCase";
	//
	// //
	// --------TestRunner总的运行用例分模块-------------------------------------------------------------
	// // 登录
	// public static final String SYSTEM_RUNNER =
	// "com.yonyou.travelmanager2.cases.runner.single.SystemRunner";
	// // 任务单模块
	// public static final String TRAVEL_RUNNER =
	// "com.yonyou.travelmanager2.cases.runner.single.TravelRunner";
	//
	// // 预订模块
	// public static final String ORDER_RUNNER =
	// "com.yonyou.travelmanager2.cases.runner.single.OrderRunner";
	//
	// // 审批模块
	// public static final String EXAMINE_RUNNER =
	// "com.yonyou.travelmanager2.cases.runner.single.ExamineRunner";
	//
	// // 我模块
	// public static final String MINE_RUNNER =
	// "com.yonyou.travelmanager2.cases.runner.single.MineRunner";
	//
	// // 消息模块
	// public static final String MESSAGE_RUNNER =
	// "com.yonyou.travelmanager2.cases.runner.single.MessageRunner";
	//
	// // 审批流 - 部门
	// public static final String PROCESS_DT_RUNNER =
	// "com.yonyou.travelmanager2.cases.runner.process.DepartmentProcessRunner";
	//
	// // 审批流 - 项目
	// public static final String PROCESS_PT_RUNNER =
	// "com.yonyou.travelmanager2.cases.runner.process.ProjectProcessRunner";

	/**
	 * 文件导入到手机中
	 */
	public String pushExcelDataFile(String device) {
		BufferedReader br = null;
		String result = "";
		if (null == device || "".equals(device)) {
			device = "";
		} else {
			device = " -s " + device;
		}
		try {
			Process p = Runtime.getRuntime().exec(
					"adb " + device + " push e:/robotium.xls /sdcard/");
			br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((result = br.readLine()) != null) {
				System.out.println("导入Excel文件得到的命令行返回结果是" + result);
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("向手机中导入excel文件出错...");
		}
		return result;
	}

	/**
	 * 执行命令
	 * 
	 * @param command
	 */
	public String runCommand(String command) {
		BufferedReader br = null;
		String result = "";
		try {
			Process p = Runtime.getRuntime().exec(command);
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((result = br.readLine()) != null) {
				System.out.println("执行命令返回结果是" + result);
				break;
			}
		} catch (IOException e) {
			System.out.println("运行cmd命令出错...");
		}
		return result;
	}

	/**
	 * 手机中的文件导入到电脑
	 */
	public String pullXMLResultFile(String outFileName, String device) {
		BufferedReader br = null;
		String result = "";
		if (null == device || "".equals(device)) {
			device = "";
		} else {
			device = " -s " + device;
		}
		try {
			Process p = Runtime.getRuntime().exec(
					" adb " + device
							+ " pull /sdcard/robotium/Test_Report.xml e:"
							+ File.separator + "Robotium-Xml/" + outFileName
							+ ".xml");
			br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((result = br.readLine()) != null) {
				System.out.println("XML文件导入电脑得到的命令行返回结果是" + result);
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("向电脑中导入XML文件出错...");
		}
		if (null == result) {
			result = "";
		}
		return result;
	}

	/**
	 * 命令行工具 运行用例 返回用例的运行结果
	 */
	public String runTest(String className, String device) {
		System.out.println("执行了runTest方法");
		String result = "";
		String error = "";
		StringBuffer sb = new StringBuffer();
		StringBuffer errorsb = new StringBuffer();
		BufferedReader br = null;
		BufferedReader errorbr = null;
		if (null == device || "".equals(device)) {
			device = "";
		} else {
			device = " -s " + device;
		}
		try {
			Process p = Runtime
					.getRuntime()
					.exec("adb "
							+ device
							+ " shell am instrument -e class "
							+ className
							+ " -w com.yonyou.travelmanager2.test/com.yonyou.android.test.InstrumentationTestRunner");
			System.out
					.println("adb "
							+ device
							+ " shell am instrument -e class "
							+ className
							+ " -w com.yonyou.travelmanager2.test/com.yonyou.android.test.InstrumentationTestRunner");
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			errorbr = new BufferedReader(new InputStreamReader(
					p.getErrorStream()));
			while ((result = br.readLine()) != null) {
				// System.out.println("运行用例时得到的命令行返回结果是" + result);
				sb.append(result);
			}
			while ((error = errorbr.readLine()) != null) {
				// System.out.println("运行用例时得到的命令行返回error结果是" + error);
				errorsb.append(error);
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("运行命令出错...");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("关闭流出错...");
				}
			}
		}

		System.out.println("最终得到的result结果是：" + sb.toString());
		System.out.println("最终得到的error结果是：" + errorsb.toString());
		return sb.append(errorsb).toString();
	}

	/**
	 * 将截图复制到电脑上 并且删除文件夹内的所有文件 以免重复导入 /sdcard/Robotium-Screenshots/
	 * 
	 * @return
	 */
	public String copyPicture(String device) {
		System.out.println("执行了runTest方法");
		String result = "";
		String error = "";
		StringBuffer sb = new StringBuffer();
		StringBuffer errorsb = new StringBuffer();
		BufferedReader br = null;
		BufferedReader errorbr = null;
		if (null == device || "".equals(device)) {
			device = "";
		} else {
			device = " -s " + device;
		}
		try {
			Process p = Runtime.getRuntime().exec("adb " + device + " shell");
			p = Runtime.getRuntime().exec(
					"pull /sdcard/Robotium-Screenshots/ e:" + File.separator
							+ "Robotium-Screenshots/");
			p = Runtime.getRuntime().exec("su");
			p = Runtime.getRuntime().exec("cd /sdcard/");
			p = Runtime.getRuntime().exec("rm -r Robotium-Screenshots");
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			errorbr = new BufferedReader(new InputStreamReader(
					p.getErrorStream()));
			while ((result = br.readLine()) != null) {
				// System.out.println("运行用例时得到的命令行返回结果是" + result);
				sb.append(result);
			}
			while ((error = errorbr.readLine()) != null) {
				// System.out.println("运行用例时得到的命令行返回error结果是" + error);
				errorsb.append(error);
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("运行命令出错...");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("关闭流出错...");
				}
			}
		}

		System.out.println("最终得到的result结果是：" + sb.toString());
		System.out.println("最终得到的error结果是：" + errorsb.toString());
		return sb.append(errorsb).toString();
	}

	/**
	 * 运行用例 判断是否需要重跑 返回是否运行通过
	 * 
	 * @param TestCase
	 * @return
	 */
	public boolean runTestCase(String TestCase, String device) {
		boolean isPass = false;
		for (int i = 0; i < 2; i++) {
			String message = runTest(TestCase, device);
			if (message.contains(CRASH)) {
				System.out.println("@@@@@@@@@@@@@@@ 重复执行了:" + TestCase
						+ " @@@@@@@@@@@@@@@@@@");
				continue;
			} else {
				if (message.contains("error") || message.contains("Error")) {
					isPass = false;
					break;
				}
				isPass = true;
				break;
			}
		}
		return isPass;
	}

	/**
	 * 得到静态字段的值 public static final修饰的 没用了这个方法 现在是引入外部xml文件
	 * 
	 * @return
	 */
	public List<String> getFinalStrings() {
		List<String> list = new ArrayList<String>();
		Field[] fields = TestRunner.class.getDeclaredFields();
		for (Field field : fields) {
			// 属性的修饰
			String descriptor = Modifier.toString(field.getModifiers());
			descriptor = descriptor.equals("") == true ? "" : descriptor + " ";
			if (descriptor.contains("public static final")) {
				list.add(field.getName());
			}
		}
		return list;
	}

	public static void main(String[] args) {
		TestRunner testRunner = new TestRunner();
		// String s = testRunner.runCommand(CLEARDATA);
		// System.out.println(s);
		testRunner.pushExcelDataFile("");

		Map<String, String> cases = XmlUtil.testXPath("e:" + File.separator
				+ "TestCases.xml");
		boolean b = testRunner.runTestCase(cases.get("SYSTEM_RUNNER"), "");
		if (b) {
			System.out.println("用例：SYSTEM_RUNNER执行成功");
		} else {
			System.out.println("用例：SYSTEM_RUNNER执行失败");
		}
		testRunner.pullXMLResultFile("SYSTEM_RUNNER", null);

		// testRunner.runAndroidTestCase(TRAVEL_RUNNER);
		// testRunner.pullXMLResultFile("travel");
		//
		// testRunner.runAndroidTestCase(EXAMINE_RUNNER);
		// testRunner.pullXMLResultFile("examine");
		//
		// testRunner.runAndroidTestCase(MESSAGE_RUNNER);
		// testRunner.pullXMLResultFile("message");
		//
		// testRunner.runAndroidTestCase(ORDER_RUNNER);
		// testRunner.pullXMLResultFile("order");
		//
		// testRunner.runAndroidTestCase(MINE_RUNNER);
		// testRunner.pullXMLResultFile("mine");
		//
		// testRunner.runAndroidTestCase(PROCESS_DT_RUNNER);
		// testRunner.pullXMLResultFile("department");
		//
		// testRunner.runAndroidTestCase(PROCESS_PT_RUNNER);
		// testRunner.pullXMLResultFile("project");
	}
}
