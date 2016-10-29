import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Environnement extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g){
		ImageIcon image = new ImageIcon("environnement.jpg");
		super.setOpaque(false);
		g.drawImage(image.getImage(), 0, 0, null);
		super.paintComponent(g);
	}

}
