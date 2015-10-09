package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane pane = (Pane)FXMLLoader.load(getClass().getResource("View.fxml"));
        Scene scene = new Scene(pane, 1280, 800);
		primaryStage.setTitle("AP Computer Science");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}
