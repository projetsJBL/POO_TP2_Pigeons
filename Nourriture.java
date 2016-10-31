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
	
	int pourrir;
	
	public Nourriture(String imageName){
		//graphique
		setImageName(imageName);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(imageName));
		setLabel(label);
		
		pourrir = 0;
	}
	
	/**
	 * ACCESSEURS
	 */
	
	public int getPourrir(){
		return pourrir;
	}
	
	public void setPourrir(int i){
		pourrir = i;
	}
	
	/**
	 * METHODES
	 */
	
	

}
