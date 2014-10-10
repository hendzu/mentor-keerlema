package ee.ut.math.tvt.mentor_keerlema;

import org.apache.log4j.Logger;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Intro extends Application {
	private static final Logger log = Logger.getLogger(Intro.class);
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
		log.info("Intro started");
	}
}
