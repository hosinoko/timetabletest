package application;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainMenu extends Application {
	static MainMenuController mainMenuController;
	static TrayIcon trayIcon;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene((Region) fxmlLoader.load(), 1200, 930);
			primaryStage.setScene(scene);
			primaryStage.setMaxWidth(1250);
			primaryStage.setMaxHeight(980);
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest((WindowEvent event) -> {
				System.exit(0);
			});
			primaryStage.show();
			mainMenuController = fxmlLoader.getController();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void create() {
		try {
			Region root = (Region) FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(root, 1200, 930);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setMaxWidth(1250);
			primaryStage.setMaxHeight(980);
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest((WindowEvent event) -> {
				System.exit(0);
			});
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Image image = Toolkit.getDefaultToolkit().createImage(
				System.getenv("ICON_PATH"));
		trayIcon = new TrayIcon(image, "Timetable Notifier");
		trayIcon.setImageAutoSize(true);
		try {
			SystemTray.getSystemTray().add(trayIcon);
		} catch (AWTException e) {
			e.printStackTrace();
		}

		launch(args);
	}
}
