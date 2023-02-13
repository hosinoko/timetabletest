package application;

import java.awt.Desktop;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class NotifyTimer extends Thread {

	String lectureTime;
	String lectureName;
	String lectureURL;
	static boolean notifierRunMon = false;
	static boolean notifierRunTue = false;
	static boolean notifierRunWed = false;
	static boolean notifierRunThur = false;
	static boolean notifierRunFri = false;
	static String overrideTime;

	NotifyTimer(String lectureTime, String lectureName, String lectureURL) {
		this.lectureTime = lectureTime;
		NotifyTimer.overrideTime = this.lectureTime;
		this.lectureName = lectureName;
		this.lectureURL = lectureURL;
	}

	void timerStart() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Timer timer = new Timer();

		TimerTask notifyTask = new TimerTask() {
			@Override
			public void run() {
				lectureTime = overrideTime;
				String lectureTimePlusSec = lectureTime + ":00";
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MINUTE, +SettingMenuController.notifyTimingBefore);
				String strTime = sdf.format(cal.getTime());
				System.out.println(strTime + ":" + lectureTimePlusSec);
				if (notifierRunMon == false && notifierRunTue == false && notifierRunWed == false
						&& notifierRunThur == false && notifierRunFri == false) {
					timer.cancel();
					timer.purge();
				}
				if (lectureTimePlusSec.equals(strTime) && lectureTimePlusSec != "") {
					sendNotify();
				}
			}
		};
		timer.scheduleAtFixedRate(notifyTask, 0, 1000);
	}

	private void sendNotify() {
		Desktop desktop = Desktop.getDesktop();

		MainMenu.trayIcon.setToolTip("Timetable Notifier");
		MainMenu.trayIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					desktop.browse(new URI(lectureURL));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
		});

		MainMenu.trayIcon.displayMessage(
				this.lectureName + "の講義があと" + SettingMenuController.notifyTimingBefore + "分で始まります",
				"オンライン講義に参加する場合はこの通知をクリックしてください",
				MessageType.INFO);
	}
}
