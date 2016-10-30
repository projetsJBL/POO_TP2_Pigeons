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
	static int fatigue;
	static ArrayList<Nourriture> nourriture = new ArrayList<Nourriture>();
	static ArrayList<Pigeon> pigeons = new ArrayList<Pigeon>();
	
	String nom;
	boolean devantNourriture;
	
	public Pigeon(String imageName){
		//graphique
		setImageName(imageName);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(imageName));
		setLabel(label);
		
		//caracteristiques
		etat = Etat.WAITING; //le pigeon est en attente par defaut
		pigeons.add(this);
		devantNourriture = false;
		
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
	
	public boolean getDevantNourriture(){
		return devantNourriture;
	}
	
	public void setEtat(Etat e){
		etat = e;
	}
	
	public void setNom(String new_nom){
		nom = new_nom;
	}
	
	public void setDevantNourriture(boolean b){
		devantNourriture = b;
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
		if(!nourriture.isEmpty()){
			nourriture.remove(n);
		}
		else
			System.out.println("nourriture vide");
	}
	
	/*
	 * Determine l'etat du pigeon selon la situation
	 */
	public void determinerEtat(){
		
		//il n'y a plus de nourriture
		if(nourriture.isEmpty() && fatigue < 20)
			this.setEtat(Etat.WAITING);
		
		//il y a de la nourriture
		else if (!nourriture.isEmpty() && !this.devantNourriture){ //si il y a de la nourriture et qu'il n'a pas encore bouge		

			Nourriture n = nourriture.get(nourriture.size()-1);
			double[] vecteurPN = {n.getX() - this.getX(), n.getY() - this.getY()}; //vecteur pigeon-nourriture
			double normePN = Math.sqrt((Math.pow(vecteurPN[0], 2) + Math.pow(vecteurPN[1], 2))); //distance pigeon-nourriture
			
			if(normePN > 1){
				this.setEtat(Etat.MOVING);
				fatigue = 0; //on remet leur fatigue a 0 pour eviter qu'il s'endorment juste apres
			}
			else{
				this.setDevantNourriture(true);
				System.out.println(this.getDevantNourriture());
			}
		}
		
		//manger
		else if(!nourriture.isEmpty() && this.devantNourriture){ //si il arrive sur de la nourriture qui n'est pas mangee
			System.out.println(this.getNom() + " ARRIVED");
			System.out.println("Position arrivee " + this.getNom() + ": " + this.getX() + "," + this.getY());
			this.setEtat(Etat.EATING);
		}
		
		//dormir
		else if(nourriture.isEmpty() && fatigue >= 20)
			this.setEtat(Etat.SLEEPING);
	}
	
	/*
	 * Deplacement du pigeon vers une position donnee
	 */
	public void seDeplacer(){
		double normePN = 2;
		if(normePN >1 ){
			Nourriture n = nourriture.get(nourriture.size()-1);
			double[] vecteurPN = {n.getX() - this.getX(), n.getY() - this.getY()}; //vecteur pigeon-nourriture
			normePN = Math.sqrt((Math.pow(vecteurPN[0], 2) + Math.pow(vecteurPN[1], 2))); //distance pigeon-nourriture
			double[] PNnormalise = {vecteurPN[0] / normePN, vecteurPN[1] / normePN}; //Vecteur normalise pour un deplacement a vitesse uniforme
			
			//tant qu'il n'a pas atteint la destination (approximatif) 
			System.out.println("position arrondie pigeon " + this.getNom() + ": " + (int)this.getX() + " , " + (int)this.getY());
			
			this.setX(this.getX() + PNnormalise[0]);
			this.setY(this.getY() + PNnormalise[1]);
			this.getLabel().setLocation((int)this.getX(), (int)this.getY()); //on met a jour le sprite
			
			//on recalcule la position du pigeon par rapport a la nourriture
			vecteurPN[0] = n.getX() - this.getX();
			vecteurPN[1] = n.getY() - this.getY();
			normePN = Math.sqrt((Math.pow(vecteurPN[0], 2) + Math.pow(vecteurPN[1], 2))); //distance pigeon-nourriture
		}			
	}
	
	/*
	 * Mange et retire la nourriture la plus fraiche de la liste nourriture si tous les pigeons l'ont mange
	 */
	public void manger(Nourriture n){	
			removeNourriture(n);
	}
	
	/*
	 * Les pigeons fuient
	 */
	public void fuir(){
		double x = Math.random()*(1000) * (Math.random() < 0.5 ? -1 : 1); //le premier terme donne un alea entre 0 et mille, le deuxieme un facteur -1 ou 1
		double y = Math.random()*(1000) * (Math.random() < 0.5 ? -1 : 1);
	}
	
	/**
	 * THREAD
	 */
	
	@Override
	public void run(){
		for(int i = 0; i < 100; i++){
			this.determinerEtat();
			
			switch(this.getEtat()){
			case WAITING: 
				try {
					fatigue++;
					Thread.sleep(1000);
					System.out.println(this.getNom() + " WAITING...");
				} catch (InterruptedException e1) {e1.printStackTrace();}
				break;
			case MOVING:
				try {		
					this.seDeplacer();					
					Thread.sleep(500);
					} catch (InterruptedException e) {e.printStackTrace();}
				break;
			case EATING:
				try{
					Nourriture n = nourriture.get(nourriture.size()-1);
					System.out.println(this.getNom() + " EATING...");
					this.manger(n);
					Evenements.supprimerNourriture(n);
					Thread.sleep(1000);
					this.setDevantNourriture(false);
					System.out.println(this.getNom() + " ATE");
					System.out.println("Il reste " + nourriture.size() + " nourritures");
				}catch (InterruptedException e) {e.printStackTrace();}
				break;
			case SLEEPING:
				try{
					System.out.println(this.getNom() + " SLEEPING...");
					Thread.sleep(1000);
				}catch (InterruptedException e) {e.printStackTrace();}
				break;
			default:
					System.out.println("Default case in run method");
					break;
			}
		}
	}
	
	
}
