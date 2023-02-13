package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AddLectureGUI {
	public void create() {
		try {
			Region root = (Region) FXMLLoader.load(getClass().getResource("AddLectureGUI.fxml"));
			Scene scene = new Scene(root, 810, 255);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setMaxWidth(860);
			primaryStage.setMaxHeight(305);
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest((WindowEvent event) -> {
				MainMenu menuCreate = new MainMenu();
				menuCreate.create();
			});
			primaryStage.show();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
