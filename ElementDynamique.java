import javax.swing.JLabel;

/**
 * Classe Objet
 * Classe mere de Pigeon et Nourriture
 * @author MrMephisto
 * maj 29/10/2016
 * 
 * COMS:
 * 1. voir le comportement pour des setX(int_) et des setX(double_) (possible?)
 */


public class ElementDynamique extends Thread{
	
	private JLabel label; //manipulation des images
	private String imageName; //chemin de l'image
	private int x, y; //position des images
	
	/*
	 * ACCESSEURS
	 */
	public JLabel getLabel(){
		return label;
	}
	
	public void setLabel(JLabel label){
		this.label = label;
	}
	
	public String getImageName(){
		return imageName;
	}
	
	public void setImageName(String image) {
		this.imageName = image;
	}
	
	public int getX(){
		return x;
	}
	
	public void setX(int new_x){
		this.x = new_x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setY(int new_y){
		this.y = new_y;
	}
	
}
