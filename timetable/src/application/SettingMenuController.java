package application;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SettingMenuController {

	static final String DB_PATH = System.getenv("TIMETABLE_DB_PATH");
	//static final String DB_PATH = ("D:\\javapleiades\\\\kubota2022\\\\timetable\\\\src\\\\lecture.db");
	Connection conn = null;
	Statement stmt = null;

	FileOutputStream out = null;

	static Integer notifyTimingBefore = 5; // デフォでは5分

	@FXML
	private Button allDeleteButton;

	@FXML
	private CheckBox deleteCheckBox;

	@FXML
	private Button nameChangeButton;

	@FXML
	private TextField notifyTiming;

	@FXML
	private TextField timetableName;

	@FXML
	private Button timingChangeButton;

	@FXML
	void initialize() {
		String notifyTimingBeforeProperty = MainMenuController.timetableSettings.getProperty("notifyTimingBefore");
		if (notifyTimingBeforeProperty != null) {
			notifyTiming.setText(notifyTimingBeforeProperty);
			notifyTimingBefore = Integer.parseInt(this.notifyTiming.getText());
		} else {
			this.notifyTiming.setText(notifyTimingBefore.toString());
		}
		this.timetableName.setText(MainMenu.mainMenuController.getTimetableName());
	}

	@FXML
	void lectureAllDelete(MouseEvent event) {
		if (deleteCheckBox.isSelected()) {
			try {
				conn = DriverManager.getConnection(DB_PATH);
				stmt = conn.createStatement();
				for (int i = 1; i <= 25; i++) {
					stmt.executeUpdate(
							"UPDATE Lecture SET LECTURE_NAME='', LECTURE_ROOM='', LECTURE_URL='' WHERE LECTURE_ID="
									+ i);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				MainMenu.mainMenuController.timetableUpdate();
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		}
	}

	@FXML
	void notifyTimingChange(MouseEvent event) {
		notifyTimingBefore = Integer.parseInt(this.notifyTiming.getText());
		MainMenuController.timetableSettings.setProperty("notifyTimingBefore", this.notifyTiming.getText());
		try {
			out = new FileOutputStream("Timetable.properties");
			MainMenuController.timetableSettings.store(out, "Timetable Savedata");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@FXML
	void timeTableNameChange(MouseEvent event) {
		MainMenu.mainMenuController.setTimetableName(this.timetableName.getText());
	}
}
