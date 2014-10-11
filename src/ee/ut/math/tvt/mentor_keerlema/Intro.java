package ee.ut.math.tvt.mentor_keerlema;

import java.awt.EventQueue;

import org.apache.log4j.Logger;


public class Intro {
	private static final Logger log = Logger.getLogger(Intro.class);
	public static void main(String[] args){
		log.info("IntroUI created");
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				IntroUI intro = new IntroUI();
				intro.setVisible(true);
			}
		});
	}
}
