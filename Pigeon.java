import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Classe Objet Implemente l'element dynamique pigeon
 * 
 * @author MrMephisto maj 30/10/2016
 */

public class Pigeon extends ElementDynamique {

	private int velocity;
	private Etat etat;
	private static int fatigue;
	private static ArrayList<Nourriture> nourriture = new ArrayList<Nourriture>();
	private static ArrayList<Pigeon> pigeons = new ArrayList<Pigeon>();
	private static ArrayList<Pokeball> pokeball = new ArrayList<Pokeball>();
	private double frameSprite;

	String nom;
	boolean devantNourriture;

	public Pigeon(String imageName) {
		// graphique
		setImageName(imageName);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(imageName));
		setLabel(label);
		// caracteristiques
		pigeons.add(this);
		setEtat(Etat.WAITING);
		devantNourriture = false;
		velocity = 100;
		// pour affichage console
		nom = "";
	}

	/**
	 * ACCESSEURS
	 */
	public Etat getEtat() {
		return etat;
	}

	public static ArrayList<Nourriture> getNourriture() {
		return nourriture;
	}

	public static Nourriture getLastNourriture() {
		if (!nourriture.isEmpty()) {
			return nourriture.get(nourriture.size() - 1);
		} else {
			System.out.println("error getLasNourriture");
			return null;
		}
	}

	public String getNom() {
		return nom;
	}

	public static ArrayList<Pigeon> getPigeons() {
		return pigeons;
	}

	public static ArrayList<Pokeball> getpokeball() {
		return pokeball;
	}

	public static Pokeball getLastPokeball() {
		if (!pokeball.isEmpty()) {
			return pokeball.get(pokeball.size() - 1);
		} else {
			System.out.println("error getLastPokeball");
			return null;
		}
	}

	public boolean getDevantNourriture() {
		return devantNourriture;
	}

	public double getFrameSprite() {
		return frameSprite;
	}

	public void setEtat(Etat e) {
		etat = e;
	}

	public void setNom(String new_nom) {
		nom = new_nom;
	}

	public void setDevantNourriture(boolean b) {
		devantNourriture = b;
	}

	public void setVelocity(int v) {
		velocity = v;
	}

	public void setFrameSprite(double f) {
		frameSprite = f;
	}

	/**
	 * METHODES
	 */

	/*
	 * Acces aux listes
	 */
	public static void addNourriture(Nourriture n) {
		nourriture.add(n);
	}

	public static void addPoke(Pokeball poke) {
		pokeball.add(poke);
	}

	public static void removeNourriture(Nourriture n) {
		if (!nourriture.isEmpty()) {
			nourriture.remove(n);
		} else
			System.out.println("nourriture vide");
	}

	public static void addPigeon(Pigeon p) {
		pigeons.add(p);
	}

	/*
	 * Determine l'etat du pigeon selon la situation
	 */
	public void determinerEtat() {
		if (Environnement.getPeur()) {
			this.setEtat(Etat.RUNNING);

		} else {
			//(il n'y a plus de nourriture ou les nourriture sont pourries) et il n'est pas fatigue
			if ((nourriture.isEmpty() || getLastNourriture().getPourrie())
					&& fatigue < 10) {
				this.setEtat(Etat.WAITING);
			}
			// il y a de la nourriture et elle n'est pas pourrie et il n'est pas
			// devant la nourriture
			else if (!nourriture.isEmpty() && !this.devantNourriture
					&& !getLastNourriture().getPourrie()) {
				// si il y a de la nourriture et qu'il n'a pas encore bouge
				Nourriture n = nourriture.get(nourriture.size() - 1);
				// vecteur pigeon-nourriture
				double[] vecteurPN = { n.getX() - this.getX(),n.getY() - this.getY() }; 
				// distance pigeon-nourriture
				double normePN = Math.sqrt((Math.pow(vecteurPN[0], 2) + Math.pow(vecteurPN[1], 2))); 
				if (normePN > 1) {
					this.setEtat(Etat.MOVING);
					// on remet leur fatigue a 0 pour eviter qu'il s'endorment juste apres
					fatigue = 0; 
				} else {
					this.setDevantNourriture(true);
				}
			}

			// il est devant la nourriture et elle n'est pas pourrie
			else if (!nourriture.isEmpty() && this.devantNourriture
					// si il arrive sur de la nourriture qui n'est pas mangee
					&& !getLastNourriture().getPourrie()) { 
				System.out.println(this.getNom() + " ARRIVED");
				System.out.println("Position arrivee " + this.getNom() + ": "
						+ this.getX() + "," + this.getY());
				this.setEtat(Etat.EATING);
			}

			// dormir
			else if ((nourriture.isEmpty() || getLastNourriture().getPourrie())
					&& fatigue > 10) {
				this.setEtat(Etat.SLEEPING);
			}
		}
	}

	/*
	 * Deplacement du pigeon vers une position donnee
	 */
	public void seDeplacer() {
		double normePN = 2;
		Nourriture n = nourriture.get(nourriture.size() - 1);
		if (normePN > 1) {
			// vecteur pigeon-nourriture
			double[] vecteurPN = { n.getX() - this.getX(), n.getY() - this.getY() }; 			
			// distance pigeon-nourriture
			normePN = Math.sqrt((Math.pow(vecteurPN[0], 2) + Math.pow(vecteurPN[1], 2))); 
			// Vecteur normalise pour un deplacement a vitesse uniforme
			double[] PNnormalise = { vecteurPN[0] / normePN,vecteurPN[1] / normePN }; 
			// tant qu'il n'a pas atteint la destination (approximatif)
			this.setX(this.getX() + PNnormalise[0]);
			this.setY(this.getY() + PNnormalise[1]);
			// on met a jour le sprite
			this.getLabel().setLocation((int) this.getX(), (int) this.getY()); 
			// on recalcule la position du pigeon par rapport a la nourriture
			vecteurPN[0] = n.getX() - this.getX();
			vecteurPN[1] = n.getY() - this.getY();
			// distance pigeon-nourriture
			normePN = Math.sqrt((Math.pow(vecteurPN[0], 2) + Math.pow(
					vecteurPN[1], 2))); 
		}
	}

	/*
	 * Mange et retire la nourriture la plus fraiche de la liste nourriture si
	 * tous les pigeons l'ont mange
	 */
	public void manger(Nourriture n) {
		removeNourriture(n);
	}

	/*
	 * Les pigeons fuient
	 */
	public void fuir() {
		// position pokeball
		double x = (double) Environnement.getXclic();
		double y = (double) Environnement.getYclic();
		// vecteur fuite
		double[] vecteurFuite = { this.getX() - x, this.getY() - y };
		// distance pigeon-pokeball
		double normeVecteurFuite = Math
				.sqrt((Math.pow(vecteurFuite[0], 2) + Math.pow(vecteurFuite[1],
						2)));
		// Vecteur normalise pour un deplacementa vitesse uniforme
		double[] vecteurFuiteNormalise = { vecteurFuite[0] / normeVecteurFuite,
				vecteurFuite[1] / normeVecteurFuite };
		// fuite
		this.setX(this.getX() + vecteurFuiteNormalise[0]);
		this.setY(this.getY() + vecteurFuiteNormalise[1]);
		// on met a jour le sprite
		this.getLabel().setLocation((int) this.getX(), (int) this.getY()); 

		// condition de fin de fuite
		if (System.currentTimeMillis() - getLastPokeball().getTime() > 2000) {
			Environnement.setPeur(false);
		}

	}

	/**
	 * THREAD
	 */

	@Override
	public void run() {
		while (true) {
			this.frameSprite++;

			this.determinerEtat();
			Evenements.determinerImage(this);

			// les aliments se periment
			if (!nourriture.isEmpty()) {
				for (Nourriture n : nourriture) {

					if (System.currentTimeMillis() - n.getTime() > Nourriture.seuil_pourriture) {
						n.setPourrie(true);
						JLabel label = n.getLabel();
						label.setIcon(new ImageIcon("nourriture_pourrie.png"));
						n.setLabel(label);
					}
				}
			}

			switch (this.getEtat()) {
			case WAITING:
				try {
					fatigue++;
					Thread.sleep(1000);
					System.out.println(this.getNom() + " WAITING...");
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				break;
			case MOVING:
				try {
					this.seDeplacer();
					Thread.sleep(1000 / velocity);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			case EATING:
				try {
					Nourriture n = nourriture.get(nourriture.size() - 1);
					System.out.println(this.getNom() + " EATING...");
					if (!n.getPourrie()) {
						this.manger(n);
						Evenements.supprimerNourriture(n);
					}
					Thread.sleep(1000);
					this.setDevantNourriture(false);
					System.out.println(this.getNom() + " ATE");
					System.out.println("Il reste " + nourriture.size()
							+ " nourritures");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			case SLEEPING:
				try {
					System.out.println(this.getNom() + " SLEEPING...");
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			case RUNNING:
				try {
					System.out.println(this.getNom() + " is RUNNING");
					this.fuir();
					Thread.sleep(1000 / velocity);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			default:
				break;
			}
		}
	}

}
