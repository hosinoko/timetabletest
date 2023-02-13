package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

public class AddLectureGUIController {

	static final String DB_PATH = System.getenv("TIMETABLE_DB_PATH");
	static String DoWPeriodText = "";
	static String tableId = "";
	Connection conn = null;
	Statement stmt = null;

	@FXML
	private Label DoWPeriod;

	@FXML
	private Button addAndUpdateButton;

	@FXML
	private Button lectureDelete;

	@FXML
	private TextField lectureName;

	@FXML
	private TextField lectureRoom;

	@FXML
	private TextField lectureURL;

	@FXML
	void initialize() {
		DoWPeriod.setText(DoWPeriodText);
		try {
			conn = DriverManager.getConnection(DB_PATH);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Lecture WHERE LECTURE_ID=" + tableId);
			lectureName.setText(rs.getString("LECTURE_NAME"));
			lectureRoom.setText(rs.getString("LECTURE_ROOM"));
			lectureURL.setText(rs.getString("LECTURE_URL"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void onMouseClickedAddAndUpdate(MouseEvent event) {
		try {
			conn = DriverManager.getConnection(DB_PATH);
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE Lecture SET LECTURE_NAME='" + lectureName.getText() + "', LECTURE_ROOM='"
					+ lectureRoom.getText() + "', LECTURE_URL='" + lectureURL.getText() + "' WHERE LECTURE_ID="
					+ tableId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			MainMenu.mainMenuController.timetableUpdate();
			Scene scene = ((Node) event.getSource()).getScene();
			Window window = scene.getWindow();
			window.hide();
			MainMenu menuCreate = new MainMenu();
			menuCreate.create();
		}
	}

	@FXML
	void onMouseClickedLectureDelete(MouseEvent event) {
		try {
			conn = DriverManager.getConnection(DB_PATH);
			stmt = conn.createStatement();
			stmt.executeUpdate(
					"UPDATE Lecture SET LECTURE_NAME='', LECTURE_ROOM='', LECTURE_URL='' WHERE LECTURE_ID="
							+ tableId);
			lectureName.setText("");
			lectureRoom.setText("");
			lectureURL.setText("");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			NotifyTimer.notifierRunMon = false;
			NotifyTimer.notifierRunTue = false;
			NotifyTimer.notifierRunWed = false;
			NotifyTimer.notifierRunThur = false;
			NotifyTimer.notifierRunFri = false;
			MainMenu.mainMenuController.timetableUpdate();
			Scene scene = ((Node) event.getSource()).getScene();
			Window window = scene.getWindow();
			window.hide();
			MainMenu menuCreate = new MainMenu();
			menuCreate.create();
		}
	}

}
