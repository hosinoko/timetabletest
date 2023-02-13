package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

public class MainMenuController {

	Map<String, String> envMap = System.getenv();

	static Properties timetableSettings = new Properties();

	static final File PROPERTY_FILE = new File(System.getenv("TIMETABLE_PROPERTYFILE_PATH"));
//	static final File PROPERTY_FILE = new File("D:\\javapleiades\\kubota2022\\timetable\\src\\Timetable.properties");
	FileOutputStream out = null;
	FileInputStream in = null;

	static final String DB_PATH = System.getenv("TIMETABLE_DB_PATH");
//	static final String DB_PATH = ("D:\\javapleiades\\\\kubota2022\\\\timetable\\\\src\\\\lecture.db");
	Connection conn = null;
	Statement stmt = null;
	Calendar DoW = Calendar.getInstance();

	private static final String[] TIMES = {
			"8:00", "8:10", "8:20", "8:30", "8:40", "8:50",
			"9:00", "9:10", "9:20", "9:30", "9:40", "9:50",
			"10:00", "10:10", "10:20", "10:30", "10:40", "10:50",
			"11:00", "11:10", "11:20", "11:30", "11:40", "11:50",
			"12:00", "12:10", "12:20", "12:30", "12:40", "12:50",
			"13:00", "13:10", "13:20", "13:30", "13:40", "13:50",
			"14:00", "14:10", "14:20", "14:30", "14:40", "14:50",
			"15:00", "15:10", "15:20", "15:30", "15:40", "15:50",
			"16:00", "16:10", "16:20", "16:30", "16:40", "16:50",
			"17:00", "17:10", "17:20", "17:30", "17:40", "17:50",
			"18:00", "18:10", "18:20", "18:30", "18:40", "18:50",
			"19:00", "19:10", "19:20", "19:30", "19:40", "19:50",
			"20:00", "20:10", "20:20", "20:30", "20:40", "20:50",
			"21:00", "21:10", "21:20", "21:30", "21:40", "21:50",
			"22:00", "22:10", "22:20", "22:30", "22:40", "22:50",
	};

	@FXML
	private Button appSettingButton;

	@FXML
	private Label classroomNumF1;

	@FXML
	private Label classroomNumF2;

	@FXML
	private Label classroomNumF3;

	@FXML
	private Label classroomNumF4;

	@FXML
	private Label classroomNumF5;

	@FXML
	private Label classroomNumM1;

	@FXML
	private Label classroomNumM2;

	@FXML
	private Label classroomNumM3;

	@FXML
	private Label classroomNumM4;

	@FXML
	private Label classroomNumM5;

	@FXML
	private Label classroomNumT1;

	@FXML
	private Label classroomNumT2;

	@FXML
	private Label classroomNumT3;

	@FXML
	private Label classroomNumT4;

	@FXML
	private Label classroomNumT5;

	@FXML
	private Label classroomNumTh1;

	@FXML
	private Label classroomNumTh2;

	@FXML
	private Label classroomNumTh3;

	@FXML
	private Label classroomNumTh4;

	@FXML
	private Label classroomNumTh5;

	@FXML
	private Label classroomNumW1;

	@FXML
	private Label classroomNumW2;

	@FXML
	private Label classroomNumW3;

	@FXML
	private Label classroomNumW4;

	@FXML
	private Label classroomNumW5;

	@FXML
	private ComboBox<String> endTime1;

	@FXML
	private ComboBox<String> endTime2;

	@FXML
	private ComboBox<String> endTime3;

	@FXML
	private ComboBox<String> endTime4;

	@FXML
	private ComboBox<String> endTime5;

	@FXML
	private ComboBox<String> startTime1;

	@FXML
	private ComboBox<String> startTime2;

	@FXML
	private ComboBox<String> startTime3;

	@FXML
	private ComboBox<String> startTime4;

	@FXML
	private ComboBox<String> startTime5;

	@FXML
	private Label subjectLabelF1;

	@FXML
	private Label subjectLabelF2;

	@FXML
	private Label subjectLabelF3;

	@FXML
	private Label subjectLabelF4;

	@FXML
	private Label subjectLabelF5;

	@FXML
	private Label subjectLabelM1;

	@FXML
	private Label subjectLabelM2;

	@FXML
	private Label subjectLabelM3;

	@FXML
	private Label subjectLabelM4;

	@FXML
	private Label subjectLabelM5;

	@FXML
	private Label subjectLabelT1;

	@FXML
	private Label subjectLabelT2;

	@FXML
	private Label subjectLabelT3;

	@FXML
	private Label subjectLabelT4;

	@FXML
	private Label subjectLabelT5;

	@FXML
	private Label subjectLabelTh1;

	@FXML
	private Label subjectLabelTh2;

	@FXML
	private Label subjectLabelTh3;

	@FXML
	private Label subjectLabelTh4;

	@FXML
	private Label subjectLabelTh5;

	@FXML
	private Label subjectLabelW1;

	@FXML
	private Label subjectLabelW2;

	@FXML
	private Label subjectLabelW3;

	@FXML
	private Label subjectLabelW4;

	@FXML
	private Label subjectLabelW5;

	@FXML
	private Label timetableName;

	ObservableList<String> startTimeModel1;

	ObservableList<String> startTimeModel2;

	ObservableList<String> startTimeModel3;

	ObservableList<String> startTimeModel4;

	ObservableList<String> startTimeModel5;

	ObservableList<String> endTimeModel1;

	ObservableList<String> endTimeModel2;

	ObservableList<String> endTimeModel3;

	ObservableList<String> endTimeModel4;

	ObservableList<String> endTimeModel5;

	@FXML
	void initialize() {
		timetableUpdate();

		startTimeModel1 = startTime1.getItems();
		startTimeModel1.addAll(TIMES);
		endTimeModel1 = endTime1.getItems();
		endTimeModel1.addAll(TIMES);
		startTimeModel2 = startTime2.getItems();
		startTimeModel2.addAll(TIMES);
		endTimeModel2 = endTime2.getItems();
		endTimeModel2.addAll(TIMES);
		startTimeModel3 = startTime3.getItems();
		startTimeModel3.addAll(TIMES);
		endTimeModel3 = endTime3.getItems();
		endTimeModel3.addAll(TIMES);
		startTimeModel4 = startTime4.getItems();
		startTimeModel4.addAll(TIMES);
		endTimeModel4 = endTime4.getItems();
		endTimeModel4.addAll(TIMES);
		startTimeModel5 = startTime5.getItems();
		startTimeModel5.addAll(TIMES);
		endTimeModel5 = endTime5.getItems();
		endTimeModel5.addAll(TIMES);

		if (!PROPERTY_FILE.exists()) {
			try {
				out = new FileOutputStream("Timetable.properties");
				timetableSettings.store(out, "Timetable Savedata");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
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

		try {
			in = new FileInputStream("Timetable.properties");
			timetableSettings.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		timerCheckAndSet();
	}

	void timetableUpdate() {
		try {
			conn = DriverManager.getConnection(DB_PATH);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM lecture");

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelM1.setText("");
			}
			subjectLabelM1.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumM1.setText("");
			}
			classroomNumM1.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelM2.setText("");
			}
			subjectLabelM2.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumM2.setText("");
			}
			classroomNumM2.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelM3.setText("");
			}
			subjectLabelM3.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumM3.setText("");
			}
			classroomNumM3.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelM4.setText("");
			}
			subjectLabelM4.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumM4.setText("");
			}
			classroomNumM4.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelM5.setText("");
			}
			subjectLabelM5.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumM5.setText("");
			}
			classroomNumM5.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelT1.setText("");
			}
			subjectLabelT1.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumT1.setText("");
			}
			classroomNumT1.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelT2.setText("");
			}
			subjectLabelT2.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumT2.setText("");
			}
			classroomNumT2.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelT3.setText("");
			}
			subjectLabelT3.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumT3.setText("");
			}
			classroomNumT3.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelT4.setText("");
			}
			subjectLabelT4.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumT4.setText("");
			}
			classroomNumT4.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelT5.setText("");
			}
			subjectLabelT5.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumT5.setText("");
			}
			classroomNumT5.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelW1.setText("");
			}
			subjectLabelW1.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumW1.setText("");
			}
			classroomNumW1.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelW2.setText("");
			}
			subjectLabelW2.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumW2.setText("");
			}
			classroomNumW2.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelW3.setText("");
			}
			subjectLabelW3.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumW3.setText("");
			}
			classroomNumW3.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelW4.setText("");
			}
			subjectLabelW4.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumW4.setText("");
			}
			classroomNumW4.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelW5.setText("");
			}
			subjectLabelW5.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumW5.setText("");
			}
			classroomNumW5.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelTh1.setText("");
			}
			subjectLabelTh1.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumTh1.setText("");
			}
			classroomNumTh1.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelTh2.setText("");
			}
			subjectLabelTh2.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumTh2.setText("");
			}
			classroomNumTh2.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelTh3.setText("");
			}
			subjectLabelTh3.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumTh3.setText("");
			}
			classroomNumTh3.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelTh4.setText("");
			}
			subjectLabelTh4.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumTh4.setText("");
			}
			classroomNumTh4.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelTh5.setText("");
			}
			subjectLabelTh5.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumTh5.setText("");
			}
			classroomNumTh5.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelF1.setText("");
			}
			subjectLabelF1.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumF1.setText("");
			}
			classroomNumF1.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelF2.setText("");
			}
			subjectLabelF2.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumF2.setText("");
			}
			classroomNumF2.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelF3.setText("");
			}
			subjectLabelF3.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumF3.setText("");
			}
			classroomNumF3.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelF4.setText("");
			}
			subjectLabelF4.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumF4.setText("");
			}
			classroomNumF4.setText(rs.getString("LECTURE_ROOM"));

			rs.next();
			if (rs.getString("LECTURE_NAME") == null) {
				subjectLabelF5.setText("");
			}
			subjectLabelF5.setText(rs.getString("LECTURE_NAME"));
			if (rs.getString("LECTURE_ROOM") == null) {
				classroomNumF5.setText("");
			}
			classroomNumF5.setText(rs.getString("LECTURE_ROOM"));
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void timerCheckAndSet() {
		String timetableNameProperty = timetableSettings.getProperty("timetableName");
		if (timetableNameProperty != null) {
			timetableName.setText(timetableNameProperty);
		}

		String startTimeProperty1 = timetableSettings.getProperty("startTime1");
		if (startTimeProperty1 != null) {
			startTime1.setPromptText(startTimeProperty1);
			NotifyLauncher S1NotifyLauncher = new NotifyLauncher("1", "6", "11", "16", "21", "startTime1");
			S1NotifyLauncher.launch();
		}

		String endTimeProperty1 = timetableSettings.getProperty("endTime1");
		if (endTimeProperty1 != null) {
			endTime1.setPromptText(endTimeProperty1);
		}

		String startTimeProperty2 = timetableSettings.getProperty("startTime2");
		if (startTimeProperty2 != null) {
			startTime2.setPromptText(startTimeProperty2);
			NotifyLauncher S2NotifyLauncher = new NotifyLauncher("2", "7", "12", "17", "22", "startTime2");
			S2NotifyLauncher.launch();
		}

		String endTimeProperty2 = timetableSettings.getProperty("endTime2");
		if (endTimeProperty2 != null) {
			endTime2.setPromptText(endTimeProperty2);
		}

		String startTimeProperty3 = timetableSettings.getProperty("startTime3");
		if (startTimeProperty3 != null) {
			startTime3.setPromptText(startTimeProperty3);
			NotifyLauncher S3NotifyLauncher = new NotifyLauncher("3", "8", "13", "18", "23", "startTime3");
			S3NotifyLauncher.launch();
		}

		String endTimeProperty3 = timetableSettings.getProperty("endTime3");
		if (endTimeProperty3 != null) {
			endTime3.setPromptText(endTimeProperty3);
		}

		String startTimeProperty4 = timetableSettings.getProperty("startTime4");
		if (startTimeProperty4 != null) {
			startTime4.setPromptText(startTimeProperty4);
			NotifyLauncher S4NotifyLauncher = new NotifyLauncher("4", "9", "14", "19", "24", "startTime4");
			S4NotifyLauncher.launch();
		}

		String endTimeProperty4 = timetableSettings.getProperty("endTime4");
		if (endTimeProperty4 != null) {
			endTime4.setPromptText(endTimeProperty4);
		}

		String startTimeProperty5 = timetableSettings.getProperty("startTime5");
		if (startTimeProperty5 != null) {
			startTime5.setPromptText(startTimeProperty5);
			NotifyLauncher S5NotifyLauncher = new NotifyLauncher("5", "10", "15", "20", "25", "startTime5");
			S5NotifyLauncher.launch();
		}

		String endTimeProperty5 = timetableSettings.getProperty("endTime5");
		if (endTimeProperty5 != null) {
			endTime5.setPromptText(endTimeProperty5);
		}
	}

	void setTimetableName(String name) {
		this.timetableName.setText(name);
		timetableSettings.setProperty("timetableName", this.timetableName.getText());
		try {
			out = new FileOutputStream("Timetable.properties");
			timetableSettings.store(out, "Timetable Savedata");
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

	String getTimetableName() {
		return this.timetableName.getText();
	}

	@FXML
	void clickedSettingButton(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		SettingMenu menuCreate = new SettingMenu();
		menuCreate.create();
	}

	@FXML
	void onSelectActionS1(ActionEvent event) {
		timetableSettings.setProperty("startTime1", startTime1.getValue());
		try {
			out = new FileOutputStream("Timetable.properties");
			timetableSettings.store(out, "Timetable Savedata");
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

		timerCheckAndSet();
	}

	@FXML
	void onSelectActionS2(ActionEvent event) {
		timetableSettings.setProperty("startTime2", startTime2.getValue());
		try {
			out = new FileOutputStream("Timetable.properties");
			timetableSettings.store(out, "Timetable Savedata");
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

		timerCheckAndSet();
	}

	@FXML
	void onSelectActionS3(ActionEvent event) {
		timetableSettings.setProperty("startTime3", startTime3.getValue());
		try {
			out = new FileOutputStream("Timetable.properties");
			timetableSettings.store(out, "Timetable Savedata");
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

		timerCheckAndSet();
	}

	@FXML
	void onSelectActionS4(ActionEvent event) {
		timetableSettings.setProperty("startTime4", startTime4.getValue());
		try {
			out = new FileOutputStream("Timetable.properties");
			timetableSettings.store(out, "Timetable Savedata");
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

		timerCheckAndSet();
	}

	@FXML
	void onSelectActionS5(ActionEvent event) {
		timetableSettings.setProperty("startTime5", startTime5.getValue());
		try {
			out = new FileOutputStream("Timetable.properties");
			timetableSettings.store(out, "Timetable Savedata");
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

		timerCheckAndSet();
	}

	@FXML
	void onSelectActionE1(ActionEvent event) {
		timetableSettings.setProperty("endTime1", endTime1.getValue());
		try {
			out = new FileOutputStream("Timetable.properties");
			timetableSettings.store(out, "Timetable Savedata");
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
	void onSelectActionE2(ActionEvent event) {
		timetableSettings.setProperty("endTime2", endTime2.getValue());
		try {
			out = new FileOutputStream("Timetable.properties");
			timetableSettings.store(out, "Timetable Savedata");
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
	void onSelectActionE3(ActionEvent event) {
		timetableSettings.setProperty("endTime3", endTime3.getValue());
		try {
			out = new FileOutputStream("Timetable.properties");
			timetableSettings.store(out, "Timetable Savedata");
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
	void onSelectActionE4(ActionEvent event) {
		timetableSettings.setProperty("endTime4", endTime4.getValue());
		try {
			out = new FileOutputStream("Timetable.properties");
			timetableSettings.store(out, "Timetable Savedata");
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
	void onSelectActionE5(ActionEvent event) {
		timetableSettings.setProperty("endTime5", endTime5.getValue());
		try {
			out = new FileOutputStream("Timetable.properties");
			timetableSettings.store(out, "Timetable Savedata");
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
	void onMouseClickedM1(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "月曜1限";
		AddLectureGUIController.tableId = "1";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedM2(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "月曜2限";
		AddLectureGUIController.tableId = "2";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedM3(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "月曜3限";
		AddLectureGUIController.tableId = "3";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedM4(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "月曜4限";
		AddLectureGUIController.tableId = "4";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedM5(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "月曜5限";
		AddLectureGUIController.tableId = "5";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedT1(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "火曜1限";
		AddLectureGUIController.tableId = "6";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedT2(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "火曜2限";
		AddLectureGUIController.tableId = "7";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedT3(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "火曜3限";
		AddLectureGUIController.tableId = "8";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedT4(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "火曜4限";
		AddLectureGUIController.tableId = "9";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedT5(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "火曜5限";
		AddLectureGUIController.tableId = "10";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedW1(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "水曜1限";
		AddLectureGUIController.tableId = "11";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedW2(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "水曜2限";
		AddLectureGUIController.tableId = "12";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedW3(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "水曜3限";
		AddLectureGUIController.tableId = "13";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedW4(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "水曜4限";
		AddLectureGUIController.tableId = "14";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedW5(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "水曜5限";
		AddLectureGUIController.tableId = "15";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedTh1(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "木曜1限";
		AddLectureGUIController.tableId = "16";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedTh2(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "木曜2限";
		AddLectureGUIController.tableId = "17";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedTh3(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "木曜3限";
		AddLectureGUIController.tableId = "18";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedTh4(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "木曜4限";
		AddLectureGUIController.tableId = "19";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedTh5(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "木曜5限";
		AddLectureGUIController.tableId = "20";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedF1(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "金曜1限";
		AddLectureGUIController.tableId = "21";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedF2(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "金曜2限";
		AddLectureGUIController.tableId = "22";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedF3(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "金曜3限";
		AddLectureGUIController.tableId = "23";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedF4(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "金曜4限";
		AddLectureGUIController.tableId = "24";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}

	@FXML
	void onMouseClickedF5(MouseEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
		AddLectureGUIController.DoWPeriodText = "金曜5限";
		AddLectureGUIController.tableId = "25";
		AddLectureGUI menuCreate = new AddLectureGUI();
		menuCreate.create();
	}
}