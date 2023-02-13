package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class NotifyLauncher {

	static final String DB_PATH = System.getenv("TIMETABLE_DB_PATH");
	Connection conn = null;
	Statement stmt = null;
	Calendar DoW = Calendar.getInstance();

	String mondayPeriod;
	String tuesdayPeriod;
	String wednesdayPeriod;
	String thursdayPeriod;
	String fridayPeriod;
	String startTimeProperty;

	public NotifyLauncher(String mondayPeriod, String tuesdayPeriod, String wednesdayPeriod, String thursdayPeriod,
			String fridayPeriod, String startTimeProperty) {
		this.mondayPeriod = mondayPeriod;
		this.tuesdayPeriod = tuesdayPeriod;
		this.wednesdayPeriod = wednesdayPeriod;
		this.thursdayPeriod = thursdayPeriod;
		this.fridayPeriod = fridayPeriod;
		this.startTimeProperty = startTimeProperty;
	}

	void launch() {
		// 各日0時になったら曜日判定をしなおす処理
		Timer timer = new Timer();
		TimerTask DayOfWeekCheck = new TimerTask() {
			@Override
			public void run() {
				launch();
			}
		};

		timer.schedule(DayOfWeekCheck, 21600000);

		// 曜日判定の本体
		switch (DoW.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.MONDAY):
			try {
				conn = DriverManager.getConnection(DB_PATH);
				stmt = conn.createStatement();
				ResultSet rsMonday = stmt
						.executeQuery("SELECT * FROM Lecture WHERE LECTURE_ID=" + this.mondayPeriod);
				if (rsMonday.getString("LECTURE_NAME") == null || rsMonday.getString("LECTURE_NAME").equals("")) {
					break;
				}
				NotifyTimer notifyerMon = new NotifyTimer(
						MainMenuController.timetableSettings.getProperty(this.startTimeProperty),
						rsMonday.getString("LECTURE_NAME"), rsMonday.getString("LECTURE_URL"));
				if (NotifyTimer.notifierRunThur == false) {
					NotifyTimer.notifierRunThur = true;
					notifyerMon.timerStart();
				} else {
					NotifyTimer.overrideTime = MainMenuController.timetableSettings.getProperty(this.startTimeProperty);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			break;
		case (Calendar.TUESDAY):
			try {
				conn = DriverManager.getConnection(DB_PATH);
				stmt = conn.createStatement();
				ResultSet rsTuesday = stmt
						.executeQuery("SELECT * FROM Lecture WHERE LECTURE_ID=" + this.tuesdayPeriod);
				if (rsTuesday.getString("LECTURE_NAME") == null || rsTuesday.getString("LECTURE_NAME").equals("")) {
					break;
				}
				NotifyTimer notifyerTue = new NotifyTimer(
						MainMenuController.timetableSettings.getProperty(this.startTimeProperty),
						rsTuesday.getString("LECTURE_NAME"), rsTuesday.getString("LECTURE_URL"));
				if (NotifyTimer.notifierRunThur == false) {
					NotifyTimer.notifierRunThur = true;
					notifyerTue.timerStart();
				} else {
					NotifyTimer.overrideTime = MainMenuController.timetableSettings.getProperty(this.startTimeProperty);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			break;
		case (Calendar.WEDNESDAY):
			try {
				conn = DriverManager.getConnection(DB_PATH);
				stmt = conn.createStatement();
				ResultSet rsWednesday = stmt
						.executeQuery("SELECT * FROM Lecture WHERE LECTURE_ID=" + this.wednesdayPeriod);
				if (rsWednesday.getString("LECTURE_NAME") == null || rsWednesday.getString("LECTURE_NAME").equals("")) {
					break;
				}
				NotifyTimer notifyerWed = new NotifyTimer(
						MainMenuController.timetableSettings.getProperty(this.startTimeProperty),
						rsWednesday.getString("LECTURE_NAME"), rsWednesday.getString("LECTURE_URL"));
				if (NotifyTimer.notifierRunThur == false) {
					NotifyTimer.notifierRunThur = true;
					notifyerWed.timerStart();
				} else {
					NotifyTimer.overrideTime = MainMenuController.timetableSettings.getProperty(this.startTimeProperty);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			break;
		case (Calendar.THURSDAY):
			try {
				conn = DriverManager.getConnection(DB_PATH);
				stmt = conn.createStatement();
				ResultSet rsThursday = stmt
						.executeQuery("SELECT * FROM Lecture WHERE LECTURE_ID=" + this.thursdayPeriod);
				if (rsThursday.getString("LECTURE_NAME") == null || rsThursday.getString("LECTURE_NAME").equals("")) {
					break;
				}
				NotifyTimer notifyerThur = new NotifyTimer(
						MainMenuController.timetableSettings.getProperty(this.startTimeProperty),
						rsThursday.getString("LECTURE_NAME"), rsThursday.getString("LECTURE_URL"));
				if (NotifyTimer.notifierRunThur == false) {
					NotifyTimer.notifierRunThur = true;
					notifyerThur.timerStart();
				} else {
					NotifyTimer.overrideTime = MainMenuController.timetableSettings.getProperty(this.startTimeProperty);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			break;
		case (Calendar.FRIDAY):
			try {
				conn = DriverManager.getConnection(DB_PATH);
				stmt = conn.createStatement();
				ResultSet rsFriday = stmt
						.executeQuery("SELECT * FROM Lecture WHERE LECTURE_ID=" + this.fridayPeriod);
				if (rsFriday.getString("LECTURE_NAME") == null || rsFriday.getString("LECTURE_NAME").equals("")) {
					break;
				}
				NotifyTimer notifyerFri = new NotifyTimer(
						MainMenuController.timetableSettings.getProperty(this.startTimeProperty),
						rsFriday.getString("LECTURE_NAME"), rsFriday.getString("LECTURE_URL"));
				if (NotifyTimer.notifierRunThur == false) {
					NotifyTimer.notifierRunThur = true;
					notifyerFri.timerStart();
				} else {
					NotifyTimer.overrideTime = MainMenuController.timetableSettings.getProperty(this.startTimeProperty);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			break;
		// 土日の時間割は現時点でアプリに実装されていないので無視
		case (Calendar.SATURDAY):
			break;
		case (Calendar.SUNDAY):
			break;
		}
	}
}
