import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Classe Objet
 * Implemente l'element dynamique pigeon
 * @author MrMephisto
 * maj 29/10/2016
 */

public class Pigeon extends ElementDynamique{
	
	static int velocity = 1000; //vitesse commune a tous les pigeons
	static int etat; //
	
	public Pigeon(String imageName){
		setImageName(imageName);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(imageName));
		setLabel(label);		
	}
	
	/**
	 * METHODES
	 */
	
	public static void seDeplacer(Pigeon p, Nourriture n){
		double distance = (int)Math.sqrt((Math.pow(n.getX()-p.getX(), 2)+Math.pow(n.getY()-p.getY(), 2)));
		
		while(distance > 0){
			double deltaX = (n.getX() - p.getX()) / velocity;
			double deltaY = (n.getY() - p.getY()) / velocity;
			
			p.setX(p.getX() + (int)deltaX);
			p.setY(p.getY() + (int)deltaY);
			p.getLabel().setLocation(p.getX(), p.getY());
		}
		
	}

}
