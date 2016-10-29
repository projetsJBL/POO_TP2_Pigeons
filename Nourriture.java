import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Classe Objet
 * Implemente l'objet nourriture
 * @author Louis
 * maj 29/10/2016
 */


public class Nourriture extends ElementDynamique {	
	
	/**
	 * ATTRIBUTS ET CONSTRUCTEUR
	 */
	
	int quantite; //0 quand consommee, 1 sinon
	
	public Nourriture(String imageName){
		setImageName(imageName);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(imageName));
		setLabel(label);
	}
	
	/**
	 * ACCESSEURS
	 */
	
	public int getQuantite(){
		return quantite;
	}
	
	public void setQuantite(int q){
		quantite = q;
	}
	
	/**
	 * METHODES
	 */
	
	

}
