/**
 * Classe Objet
 * Implemente l'objet nourriture
 * @author Louis
 * maj 29/10/2016
 */


public class Nourriture {	
	
	/**
	 * ATTRIBUTS ET CONSTRUCTEUR
	 */
	
	int x, y; //position de la nourriture
	int quantite; //0 quand consommee, 1 sinon
	
	public Nourriture(){
		x = 0;
		y = 0;
		quantite = 1;
	}
	
	public Nourriture(int px, int py)
	{
		x = px;
		y = py;
		quantite = 1;
	}
	
	/**
	 * ACCESSEURS
	 */
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getQuantite(){
		return quantite;
	}
	
	public void setX(int px){
		x = px;
	}
	
	public void setY(int py){
		y = py;
	}
	
	public void setQuantite(int q){
		quantite = q;
	}
	
	/**
	 * METHODES
	 */
	
	public void setPosition(int px, int py){
		x = px;
		y = py;
	}

}
