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
	
	private int velocity = 1;
	private Etat etat;
	private static int fatigue;
	private static ArrayList<Nourriture> nourriture = new ArrayList<Nourriture>();
	private static ArrayList<Pigeon> pigeons = new ArrayList<Pigeon>();
	private int timer;
	private double frameSprite;
	
	String nom;
	boolean devantNourriture;
	
	public Pigeon(String imageName){
		//graphique
		setImageName(imageName);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(imageName));
		setLabel(label);
		
		//caracteristiques
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
	
	public static ArrayList<Pigeon> getPigeons(){
		return pigeons;
	}
	
	public boolean getDevantNourriture(){
		return devantNourriture;
	}
	
	public double getFrameSprite(){
		return frameSprite;
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
	
	public void setVelocity(int v){
		velocity = v;
	}
	
	public void setFrameSprite(double f){
		frameSprite = f;
	}
	
	
	/**
	 * METHODES
	 */
	
	/*
	 * Acces aux listes
	 */
	public static void addNourriture(Nourriture n){
		nourriture.add(n);
	}
	
	
	public static void removeNourriture(Nourriture n){
		if(!nourriture.isEmpty()){
			nourriture.remove(n);
		}
		else
			System.out.println("nourriture vide");
	}
	
	public static void addPigeon(Pigeon p){
		pigeons.add(p);
	}
	
	/*
	 * Determine l'etat du pigeon selon la situation
	 */
	public void determinerEtat(){	
		if(Environnement.getPeur()){
			this.setEtat(Etat.RUNNING);
			this.timer++;
		}
		else{
			this.timer = 0;
			//(il n'y a plus de nourriture ou les nourriture sont pourries) et il n'est pas fatigue
			if((nourriture.isEmpty() || nourriture.get(nourriture.size()-1).getPourrir() > 1000) && fatigue < 10){
				System.out.println("c1 :" + (nourriture.isEmpty() || nourriture.get(nourriture.size()-1).getPourrir() > 1000));
				System.out.println("c2: " + (fatigue < 20));
				System.out.println("c3: " + (!this.devantNourriture));
				this.setEtat(Etat.WAITING);
			}
			
			//il y a de la nourriture et elle n'est pas pourrie et il n'est pas devant la nourriture
			else if (!nourriture.isEmpty() && !this.devantNourriture && nourriture.get(nourriture.size()-1).getPourrir() < 1000){ //si il y a de la nourriture et qu'il n'a pas encore bouge
				Nourriture n = nourriture.get(nourriture.size()-1);
				double[] vecteurPN = {n.getX() - this.getX(), n.getY() - this.getY()}; //vecteur pigeon-nourriture
				double normePN = Math.sqrt((Math.pow(vecteurPN[0], 2) + Math.pow(vecteurPN[1], 2))); //distance pigeon-nourriture			
				if(normePN > 1){
					this.setEtat(Etat.MOVING);
					fatigue = 0; //on remet leur fatigue a 0 pour eviter qu'il s'endorment juste apres
				}
				else{
					this.setDevantNourriture(true);
				}
			}
			
			//il est devant la nourriture
			else if(!nourriture.isEmpty() && this.devantNourriture){ //si il arrive sur de la nourriture qui n'est pas mangee
				if(nourriture.get(nourriture.size()-1).getPourrir() < 1000){
					System.out.println(this.getNom() + " ARRIVED");
					System.out.println("Position arrivee " + this.getNom() + ": " + this.getX() + "," + this.getY());
					this.setEtat(Etat.EATING);
				}
				else{
					this.setDevantNourriture(false);
					//this.setEtat(Etat.WAITING);
				}
			}
			
			//dormir
			else if((nourriture.isEmpty() || nourriture.get(nourriture.size()-1).getPourrir() > 1000) && fatigue > 10){				
				this.setEtat(Etat.SLEEPING);
			}
		}
	}
	
	/*
	 * Deplacement du pigeon vers une position donnee
	 */
	public void seDeplacer(){
		double normePN = 2;
		Nourriture n = nourriture.get(nourriture.size()-1);
		if(normePN > 1 ){			
			double[] vecteurPN = {n.getX() - this.getX(), n.getY() - this.getY()}; //vecteur pigeon-nourriture
			normePN = Math.sqrt((Math.pow(vecteurPN[0], 2) + Math.pow(vecteurPN[1], 2))); //distance pigeon-nourriture
			double[] PNnormalise = {vecteurPN[0] / normePN, vecteurPN[1] / normePN}; //Vecteur normalise pour un deplacement a vitesse uniforme
			
			//tant qu'il n'a pas atteint la destination (approximatif) 		
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
		//position pokeball
		double x = (double)Environnement.getXclic();
		double y = (double)Environnement.getYclic();
		
		//vecteur fuite
		double[] vecteurFuite = {this.getX() - x, this.getY() - y};
		double normeVecteurFuite = Math.sqrt((Math.pow(vecteurFuite[0], 2) + Math.pow(vecteurFuite[1], 2))); //distance pigeon-pokeball
		double[] vecteurFuiteNormalise = {vecteurFuite[0] / normeVecteurFuite, vecteurFuite[1] / normeVecteurFuite}; //Vecteur normalise pour un deplacement a vitesse uniforme
					
		//fuite
		this.setX(this.getX() + vecteurFuiteNormalise[0]);
		this.setY(this.getY() + vecteurFuiteNormalise[1]);
		this.getLabel().setLocation((int)this.getX(), (int)this.getY()); //on met a jour le sprite
		
		//condition de fin de fuite
		if(timer > 250){	
			Environnement.setPeur(false);
		}
		
	}
	
	/**
	 * THREAD
	 */
	
	@Override
	public void run(){
		while(true){
			this.frameSprite++;
			
			this.determinerEtat();
			Evenements.determinerImage(this);
			
			//les aliments se periment
			if(!nourriture.isEmpty()){
				for(Nourriture n : nourriture){
					n.setPourrir(n.getPourrir()+1);
					if(n.getPourrir() > 1000){
						JLabel label = n.getLabel();
						label.setIcon(new ImageIcon("nourriture_pourrie.png"));
						n.setLabel(label);
					}
				}			
			}			
			
			switch(this.getEtat()){
			case WAITING: 
				try {
					fatigue++;
					System.out.println("fatigue: " + fatigue);
					Thread.sleep(1000);
					System.out.println(this.getNom() + " WAITING...");
				} catch (InterruptedException e1) {e1.printStackTrace();}
				break;
			case MOVING:
				try {		
					this.seDeplacer();
					Thread.sleep(1000/velocity);
					} catch (InterruptedException e) {e.printStackTrace();}
				break;
			case EATING:
				try{
					Nourriture n = nourriture.get(nourriture.size()-1);
					System.out.println(this.getNom() + " EATING...");
					if(n.getPourrir() < 1000){
						this.manger(n);
						Evenements.supprimerNourriture(n);
					}
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
			case RUNNING:
				try{
					System.out.println(this.getNom() + " is RUNNING");
					this.fuir();
					Thread.sleep(1000/velocity);
				}catch (InterruptedException e) {e.printStackTrace();}
			default:
					System.out.println("Default case in run method");
					break;
			}
		}
	}
	
	
}
