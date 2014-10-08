package ee.ut.math.tvt.mentor_keerlema;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class IntroUI{
	
	public IntroUI(){
	}

	public GridPane UI(){
		GridPane root = new GridPane();
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(25,25,25,25));
		
		String Version = "version.properties";
		Properties prop = null;
		try {
			prop = getProperties(Version);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Text tname = new Text("Team name: Mentor Keerlema");
		Text tleader = new Text("Team leader: Hendrik Rasmus Elmet");
		Text tleaderemail = new Text("Team leader email: ");
		Text tmembers = new Text("Team memers: Marko Täht, Rene Lehtma");
		Text logo = new Text("Team logo:");
		Image Logo = new Image("logo.jpg");
		ImageView iv = new ImageView();
		iv.setImage(Logo);
		Text version = new Text("Software version: " + prop.getProperty("build.number"));
		root.add(tname, 0,0);
		root.add(tleader, 0, 1);
		root.add(tleaderemail, 0, 2);
		root.add(tmembers, 0, 3);
		root.add(logo, 0, 4);
		root.add(iv,1,4);
		root.add(version, 0, 5);
		return root;
	}
	
	public Properties getProperties(String name) throws IOException{
		Properties prop = new Properties();
		
		InputStream input = getClass().getClassLoader().getResourceAsStream(name);
		if(input == null){
			throw new FileNotFoundException("property file '" + name + "' not found!");
		}
		prop.load(input);		
		return prop;
	}
}
