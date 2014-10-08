package ee.ut.math.tvt.mentor_keerlema;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Intro extends Application {
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primary){
		primary.setTitle("IntroUI");
		IntroUI ui = new IntroUI();
		
		GridPane root;
		root = ui.UI();
		
		primary.setScene(new Scene(root, 670,450));
		primary.show();
	}
}
