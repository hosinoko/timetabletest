package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SettingMenu {
	public void create() {
		try {
			VBox root = (VBox) FXMLLoader.load(getClass().getResource("SettingMenu.fxml"));
			Scene scene = new Scene(root, 330, 400);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setMaxWidth(380);
			primaryStage.setMaxHeight(450);
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
