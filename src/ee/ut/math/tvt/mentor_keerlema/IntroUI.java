package ee.ut.math.tvt.mentor_keerlema;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

public class IntroUI extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public IntroUI(){
		setTitle("IntroUI");
		
	    try {
	        UIManager.setLookAndFeel(new WindowsLookAndFeel());
	      } catch (UnsupportedLookAndFeelException e1) {
	        e1.printStackTrace();
	      }
	    
	    draw();
	    
	    int width = 600;
	    int height = 400;
	    setSize(width, height);
	    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    setLocation((screen.width - width) / 2, (screen.height - height) / 2);

	    addWindowListener(new WindowAdapter() {
	      @Override
	      public void windowClosing(WindowEvent e) {
	        System.exit(0);
	      }
	    });
	}
	

	
	private void draw(){
		JPanel panel = new JPanel();
		
		
	}
}
