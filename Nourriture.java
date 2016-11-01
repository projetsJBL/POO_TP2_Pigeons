import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Classe Objet
 * Implemente l'objet nourriture
 * @author Louis
 * maj 30/10/2016
 */


public class Nourriture extends ElementDynamique {	
	
	/**
	 * ATTRIBUTS ET CONSTRUCTEUR
	 */
	
	private boolean pourrie;
	static int seuil_pourriture = 10000;
	private long time;
	
	public Nourriture(String imageName){
		//graphique
		setImageName(imageName);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(imageName));
		setLabel(label);
		
		pourrie = false;
		time = System.currentTimeMillis();
	}
	
	/**
	 * ACCESSEURS
	 */	
	public long getTime(){
		return time;
	}
	
	public boolean getPourrie(){
		return pourrie;
	}
	
	public void setPourrie(boolean b){
		pourrie = b;
	}
	
	/**
	 * METHODES
	 */
	
	

}
