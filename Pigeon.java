import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Classe Objet
 * Implemente l'element dynamique pigeon
 * @author MrMephisto
 * maj 30/10/2016
 */

public class Pigeon extends ElementDynamique{
	
	//static int velocity = 10;
	Etat etat;
	static int mangent;
	static ArrayList<Nourriture> nourriture = new ArrayList<Nourriture>();
	static ArrayList<Pigeon> pigeons = new ArrayList<Pigeon>();
	
	String nom;
	
	
	public Pigeon(String imageName){
		setImageName(imageName);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(imageName));
		setLabel(label);
		etat = Etat.WAITING; //le pigeon est en attente par defaut
		pigeons.add(this);
		
		//pour tests
		nom = "";		
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
	
	public String getNom(){
		return nom;
	}
	
	public ArrayList<Pigeon> getPigeons(){
		return pigeons;
	}
	
	public void setEtat(Etat e){
		etat = e;
	}
	
	public void setNom(String new_nom){
		nom = new_nom;
	}
	
	
	/**
	 * METHODES
	 */
	public static void addNourriture(Nourriture n){
		nourriture.add(n);
	}
	
	/*
	 * retire la nourriture la plus fraiche
	 */
	public static void removeNourriture(Nourriture n){
		if(nourriture.size() > 0)
			nourriture.remove(n);
		else
			System.out.println("nourriture vide");
	}
	
	/*
	 * Determine l'etat du pigeon selon la situation
	 */
	public void determinerEtat(){
		if(nourriture.isEmpty()) //s'il n'y a pas de nourriture
			this.setEtat(Etat.WAITING);
		else if (!nourriture.isEmpty() && !this.getEtat().equals(Etat.MOVING)) //si il y a de la nourriture et qu'il n'a pas encore bouge
			this.setEtat(Etat.MOVING);
		else if(!nourriture.isEmpty() && this.getEtat().equals(Etat.MOVING)) //si il arrive sur de la nourriture qui n'est pas mangee
			this.setEtat(Etat.EATING);
	}
	
	/*
	 * Deplacement du pigeon vers une position donnee
	 */
	public void seDeplacer(double xn, double yn){
		double[] vecteurPN = {xn - this.getX(), yn - this.getY()}; //vecteur pigeon-nourriture
		double normePN = Math.sqrt((Math.pow(vecteurPN[0], 2) + Math.pow(vecteurPN[1], 2))); //distance pigeon-nourriture
		double[] PNnormalise = {vecteurPN[0] / normePN, vecteurPN[1] / normePN}; //Vecteur normalise pour un deplacement a vitesse uniforme
		
		while(normePN > 1){ //tant qu'il n'a pas atteint la nourriture (approximatif)
			System.out.println("position arrondie pigeon " + this.getNom() + ": " + (int)this.getX() + " , " + (int)this.getY());
			
			this.setX(this.getX() + PNnormalise[0]);
			this.setY(this.getY() + PNnormalise[1]);
			this.getLabel().setLocation((int)this.getX(), (int)this.getY()); //on met a jour le sprite
			
			//on recalcule la position du pigeon par rapport a la nourriture
			vecteurPN[0] = xn - this.getX();
			vecteurPN[1] = yn - this.getY();
			normePN = Math.sqrt((Math.pow(vecteurPN[0], 2) + Math.pow(vecteurPN[1], 2))); //distance pigeon-nourriture
		}		
	}
	
	/*
	 * Mange et retire la nourriture la plus fraiche de la liste nourriture
	 */
	public void manger(Nourriture n){
		mangent++;		
		if(mangent == pigeons.size()){
			removeNourriture(n);
			mangent = 0;
		}
	}
	
	/**
	 * THREAD
	 */
	
	@Override
	public void run(){
		for(int i = 0; i < 5; i++){
			this.determinerEtat();
				
			
			switch(this.getEtat()){
			case WAITING: 
				try {
					Thread.sleep(1000);
					System.out.println(this.getNom() + " WAITING...");
				} catch (InterruptedException e1) {e1.printStackTrace();}
				break;
			case MOVING:
				try {
					Nourriture n = nourriture.get(nourriture.size()-1);
					double xn = n.getX(); double yn = n.getY(); //on recupere sa position
					System.out.println("Position depart " + this.getNom() + ": " + this.getX() + "," + this.getY());
					System.out.println(this.getNom() + " MOVING...");
					this.seDeplacer(xn, yn);			
					Thread.sleep(1000);
					System.out.println(this.getNom() + " MOVED");
					System.out.println("Position arrivee " + this.getNom() + ": " + this.getX() + "," + this.getY());
					} catch (InterruptedException e) {e.printStackTrace();}
				break;
			case EATING:
				try{
					Nourriture n = nourriture.get(nourriture.size()-1);
					System.out.println(this.getNom() + " EATING...");
					this.manger(n);
					Thread.sleep(1000);
					System.out.println(this.getNom() + " ATE");
					System.out.println("Il reste " + nourriture.size() + " nourritures");
				}catch (InterruptedException e) {e.printStackTrace();}
				break;
			default:
					System.out.println("Default case in run method");
					break;
			}
		}
	}
	
	
}
