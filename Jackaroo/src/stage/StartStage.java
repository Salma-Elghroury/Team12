package stage;

import javafx.application.Application;
import javafx.stage.Stage;

public class StartStage extends Application {

	public void start(Stage stage) throws Exception {
		GameStage main = new GameStage("Flower");
		stage.setScene(main.getScene());
		stage.setResizable(false);
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
