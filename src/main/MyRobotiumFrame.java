package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import xmlreader.XmlUtil;

public class MyRobotiumFrame extends JFrame {

	private String casesXmlPath = "e:" + File.separator + "TestCases.xml";

	private static final long serialVersionUID = 1L;
	protected JButton btn_excel;
	protected JButton btn_xml;
	protected JButton btn_run;
	protected JButton btn_sendMail;
	protected JButton btn_timerControl;
	protected JPanel jPanel;
	protected JTextField jTextFiled_deviceName;
	protected JLabel label_device;
	protected JLabel label_cases;
	protected JScrollPane jScrollPane1;
	protected JTextArea textArea;
	protected JComboBox jComboBox_case;
	protected JComboBox jComboBox_devices;
	static Font font = new Font("微软雅黑", Font.BOLD, 12);
	TestRunner testRunner = new TestRunner();
	SimpleDateFormat dateformat1 = new SimpleDateFormat("MM-dd HH-mm-ss");
	Map<String, String> cases;

	public MyRobotiumFrame() {
		initComponents();
	}

	private void initComponents() {
		Container con = this.getContentPane();
		label_device = new JLabel("设备：");
		label_cases = new JLabel("选择用例：");
		jPanel = new JPanel();
		btn_excel = new JButton();
		btn_xml = new JButton();
		btn_run = new JButton();
		btn_timerControl = new JButton();
		jTextFiled_deviceName = new JTextField(20);
		btn_sendMail = new JButton();
		jComboBox_case = new JComboBox();
		jComboBox_devices = new JComboBox();
		btn_excel.addActionListener(new ExcelButtonListener());
		btn_xml.addActionListener(new XmlButtonListener());
		btn_run.addActionListener(new RunButtonListener());
		jComboBox_devices.addItemListener(new DeviceChangeListener());
		btn_sendMail.addActionListener(new BtnListener());
		textArea = new JTextArea(
				"系统提示：----请将robotium.xls放到e盘根目录---\r\n--------用例会 自动 导出xml,文件命名为设备名_用例名.xml---------\r\n系统日志：\r\n");
		jScrollPane1 = new JScrollPane(textArea);

		btn_excel.setText("导入e:/robotium.xls到设备");
		btn_xml.setText("导出结果xml电脑E盘/Robotium-Xml/");
		btn_run.setText("运行用例");
		btn_sendMail.setText("发送HTML结果到邮箱(即将开放)");
		btn_timerControl.setText("定时执行(即将开放)");
		// textArea.setFont(font);
		textArea.setColumns(80);
		textArea.setRows(30);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		jScrollPane1.setViewportView(textArea);
		textArea.setEditable(false);

		cases = XmlUtil.testXPath(casesXmlPath);
		Set<String> key = cases.keySet();
		for (Iterator<String> it = key.iterator(); it.hasNext();) {
			jComboBox_case.addItem(it.next());
		}
		jComboBox_devices.addItem("");
		List<String> devices = ADBUtil.getDevices();
		for (int i = 0; i < devices.size(); i++) {
			jComboBox_devices.addItem(devices.get(i));
		}
		jTextFiled_deviceName.setText("");
		jTextFiled_deviceName.setCaretColor(Color.RED);// 光标颜色
		jTextFiled_deviceName.setEnabled(false);// 不可编辑
		jTextFiled_deviceName.setDisabledTextColor(Color.BLUE);// 不可编辑时候的字体颜色
		// jTextFiled_deviceName.setForeground(Color.green);//修改字体颜色

		jPanel.add(label_device);
		jPanel.add(jComboBox_devices);
		jPanel.add(jTextFiled_deviceName);
		jPanel.add(btn_excel);
		jPanel.add(label_cases);
		jPanel.add(jComboBox_case);
		jPanel.add(btn_run);
		jPanel.add(btn_xml);
		jPanel.add(btn_sendMail);
		jPanel.add(btn_timerControl);
		jPanel.add(jScrollPane1);
		con.add(jPanel);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Robotium Tool    __By uncle");
		pack();
		setSize(1100, 630);
		setLocation(300, 200);
		setVisible(true);
	}

	class ExcelButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("您点击了excel按钮");
			String selected = jComboBox_devices.getSelectedItem().toString();
			String result = testRunner.pushExcelDataFile(selected);
			textArea.append("Excel导入结果为：" + result + "\r\n");
			textArea.append("----------------------------------------------------------------\r\n");
		}
	}

	class XmlButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("您点击了xml按钮");
			String selected = jComboBox_devices.getSelectedItem().toString();
			String selectedcase = jComboBox_case.getSelectedItem().toString();
			// String selecteddevice = jComboBox_devices.getSelectedItem()
			// .toString();
			String deviceName = jTextFiled_deviceName.getText();
			if (null == deviceName || "".equals(deviceName.trim())) {
				deviceName = "default devices";
			}
			int beginIndex = deviceName.indexOf(" ");
			deviceName = deviceName.substring(0, beginIndex);
			String fileName = deviceName + "_" + selectedcase + "_手动";
			String result = testRunner.pullXMLResultFile(fileName, selected);
			textArea.append("XML导出结果为：" + result + "\r\n");
			textArea.append("----------------------------------------------------------------\r\n");
		}
	}

	class RunButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("您点击了run按钮");
			String selectedcase = jComboBox_case.getSelectedItem().toString();
			String selecteddevice = jComboBox_devices.getSelectedItem()
					.toString();
			String deviceName = jTextFiled_deviceName.getText();
			if (null == deviceName || "".equals(deviceName.trim())) {
				deviceName = "default devices";
			}
			int beginIndex = deviceName.indexOf(" ");
			deviceName = deviceName.substring(0, beginIndex);
			System.out.println("---" + selectedcase + "---");
			textArea.append("开始运行用例：" + selectedcase + "\r\n");
			try {
				boolean b = testRunner.runTestCase(cases.get(selectedcase),
						selecteddevice);
				if (b) {
					textArea.append("用例：" + selectedcase + "执行成功\r\n");
				} else {
					textArea.append("用例：" + selectedcase + "执行失败\r\n");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			textArea.append("用例结束 自动导出XML返回的结果："
					+ testRunner.pullXMLResultFile(deviceName + "_"
							+ selectedcase, selecteddevice) + "\r\n");
			textArea.append("----------------------------------------------------------------\r\n");
		}
	}

	class DeviceChangeListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			String deviceNo = jComboBox_devices.getSelectedItem().toString();
			Map<String, String> map = ADBUtil.getDeviceName();
			if (null == deviceNo || "".equals(deviceNo)) {
				jTextFiled_deviceName.setText("");
				return;
			}
			jTextFiled_deviceName.setText(map.get(deviceNo));
			if (null == map.get(deviceNo) || "".equals(map.get(deviceNo))) {
				jTextFiled_deviceName.setText("");
			}
		}
	}

	class BtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btn_sendMail) {
				System.out.println("点击了sendMail");
				// 1.转换xml 到 html 使用相对路径读取xsl 从手机上导出一个xml 就自动转换为html
				// 2.所有html 读取到 String
				// 3.如果字符串 为""或者null 则发送读取html文件失败 到 邮箱 如果结果正常 mail发送String到邮箱
			}
		}
	}

}