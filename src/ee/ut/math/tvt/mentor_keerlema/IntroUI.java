package ee.ut.math.tvt.mentor_keerlema;


import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

public class IntroUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(IntroUI.class);
	public IntroUI(){
		initUI();
	}

	private void initUI(){
		
	    try {
	        UIManager.setLookAndFeel(new WindowsLookAndFeel());

	      } catch (UnsupportedLookAndFeelException e1) {
	        log.warn(e1.getMessage());
	    }
		
		String Version = "version.properties";
		String Application = "application.properties";
		Properties prop = null;
		try {
			prop = getProperties(Version);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		JLabel version = new JLabel("Software version: " + prop.getProperty("build.number"));
		
		try {
			prop = getProperties(Application);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
		JLabel tname = new JLabel("Team name: "+ prop.getProperty("team.name"));
		JLabel tleader = new JLabel("Team leader: "+ prop.getProperty("team.leader"));
		JLabel tleaderemail = new JLabel("Team leader email: " + prop.getProperty("team.leader.email"));
		JLabel tmembers = new JLabel("Team members: " + prop.getProperty("team.members"));
		ImageIcon logo = new ImageIcon(prop.getProperty("team.logo"));
		JLabel tlogo = new JLabel(logo);
		tlogo.setIcon(logo);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(tname);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		panel.add(tleader);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		panel.add(tleaderemail);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		panel.add(tmembers);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		panel.add(tlogo);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		panel.add(version);
		
		add(panel);
		pack();
		log.info("IntroUI initiated");
	}
	
	public Properties getProperties(String name) throws IOException{
		Properties prop = new Properties();
		
		InputStream input = getClass().getClassLoader().getResourceAsStream(name);
		if(input == null){
			throw new FileNotFoundException("property file '" + name + "' not found!");
		}
		prop.load(input);
		log.info(name + " loaded");
		return prop;
	}
}
