/**
 * Classe Environnement
 * Implemente le fond d'écran du jeu
 * @author JB
 * maj 29/10/2016
 */

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Environnement extends JPanel{
	
	/**
	 * Version par défault
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
