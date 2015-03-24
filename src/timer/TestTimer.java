package timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {
	static int count = 1;
	static int period_hour = 1000 * 60 * 60;// 1小时
	static int period_minutes = 1000 * 60;// 1分钟

	public static void showTimer(int hour, int minutes) {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("时间=" + new Date() + " 执行了" + count + "次"); // 1次
			}
		};

		// 设置执行时间
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);// 每天
		// 定制每天的几点几分执行，
		calendar.set(year, month, day, hour, minutes, 00);
		Date date = calendar.getTime();
		Timer timer = new Timer();
		System.out.println("设定的时间是：" + date);

		// 每天的date时刻执行task，每隔xx重复执行
		timer.schedule(task, date, period_minutes);

		// 每天的date时刻执行task, 仅执行一次
		// timer.schedule(task, date);
	}

	public static void main(String[] args) {
		showTimer(8, 1);
	}
}