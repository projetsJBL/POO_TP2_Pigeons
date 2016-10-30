import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Classe Objet
 * Implemente l'element dynamique pigeon
 * @author MrMephisto
 * maj 29/10/2016
 */

public class Pigeon extends ElementDynamique{
	
	static int velocity = 10;
	Etat etat;
	static ArrayList<Nourriture> nourriture = new ArrayList<Nourriture>();
	
	
	
	public Pigeon(String imageName){
		setImageName(imageName);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(imageName));
		setLabel(label);
		etat = Etat.WAITING; //le pigeon est en attente par defaut
	}
	
	/**
	 * ACCESSEURS
	 */
	public Etat getEtat(){
		return etat;
	}
	
	public static ArrayList<Nourriture> getNourriture(){
		return nourriture;
	}
	
	public void setEtat(Etat e){
		etat = e;
	}
	
	
	/**
	 * METHODES
	 */
	public static void addNourriture(Nourriture n){
		nourriture.add(n);
	}
	
	public void determinerEtat(){
		if(nourriture.isEmpty()) this.setEtat(Etat.WAITING);
		else this.setEtat(Etat.MOVING);
	}
	
	/*
	 * Deplacement du pigeon vers de la nourriture
	 */
	public void seDeplacer(Nourriture n){
		double[] vecteurPN = {n.getX() - this.getX(), n.getY() - this.getY()}; //vecteur pigeon-nourriture
		double normePN = Math.sqrt((Math.pow(vecteurPN[0], 2) + Math.pow(vecteurPN[1], 2))); //distance pigeon-nourriture
		double[] PNnormalise = {vecteurPN[0] / normePN, vecteurPN[1] / normePN}; //Vecteur normalise pour un deplacement a vitesse uniforme
		
		while(normePN > 1){ //tant qu'il n'a pas atteint la nourriture (approximatif)
			System.out.println("position arrondie pigeon: " + (int)this.getX() + " , " + (int)this.getY());
			
			this.setX(this.getX() + PNnormalise[0]);
			this.setY(this.getY() + PNnormalise[1]);
			this.getLabel().setLocation((int)this.getX(), (int)this.getY()); //on met a jour le sprite
			
			//on recalcule la position du pigeon par rapport a la nourriture
			vecteurPN[0] = n.getX() - this.getX();
			vecteurPN[1] = n.getY() - this.getY();
			normePN = Math.sqrt((Math.pow(vecteurPN[0], 2) + Math.pow(vecteurPN[1], 2))); //distance pigeon-nourriture
		}		
	}
	
	public void attendre(){
		
	}
	
	/**
	 * THREAD
	 */
	
	@Override
	public void run(){
		this.determinerEtat();
		
		switch(this.getEtat()){
		case WAITING: 
			try {
				Thread.sleep(1000);
				System.out.println("WAITING...");
			} catch (InterruptedException e1) {e1.printStackTrace();}
			break;
		case MOVING:
			try {
				Nourriture n = nourriture.get(nourriture.size()-1); //on recupere la nourriture la plus fraiche (en fin de liste)
				System.out.println("MOVING...");
				this.seDeplacer(n);			
				Thread.sleep(1000);
				System.out.println("MOVED");
				} catch (InterruptedException e) {e.printStackTrace();}
			break;
		}
	}
	
	
}
