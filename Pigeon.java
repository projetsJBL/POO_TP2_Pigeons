import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Classe Objet Implemente l'element dynamique pigeon
 * 
 * @author MrMephisto maj 30/10/2016
 */

public class Pigeon extends ElementDynamique {

	int velocity = 1;
	Etat etat;
	static int mangent;
	static int fatigue;
	static ArrayList<Nourriture> nourriture = new ArrayList<Nourriture>();
	static ArrayList<Pigeon> pigeons = new ArrayList<Pigeon>();

	String nom;
	boolean devantNourriture;
	boolean fuit;

	public Pigeon(String imageName) {
		// graphique
		setImageName(imageName);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(imageName));
		setLabel(label);

		// caracteristiques
		etat = Etat.WAITING; // le pigeon est en attente par defaut
		pigeons.add(this);
		devantNourriture = false;
		fuit = false;

		// pour tests
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

	public String getNom() {
		return nom;
	}

	public ArrayList<Pigeon> getPigeons() {
		return pigeons;
	}

	public boolean getDevantNourriture() {
		return devantNourriture;
	}

	public boolean getFuit() {
		return fuit;
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

	public void setFuit(boolean b) {
		fuit = b;
	}

	/**
	 * METHODES
	 */
	public static void addNourriture(Nourriture n) {
		nourriture.add(n);
	}

	/*
	 * retire la nourriture la plus fraiche
	 */
	public static void removeNourriture(Nourriture n) {
		if (!nourriture.isEmpty()) {
			nourriture.remove(n);
		} else
			System.out.println("nourriture vide");
	}

	/*
	 * Determine l'etat du pigeon selon la situation
	 */
	public void determinerEtat() {
		if (this.getFuit()) {
			this.setEtat(Etat.RUNNING);
		} else {
			// il n'y a plus de nourriture
			if (nourriture.isEmpty() && fatigue < 20)
				this.setEtat(Etat.WAITING);

			// il y a de la nourriture
			else if (!nourriture.isEmpty() && !this.devantNourriture) { // si il
																		// y a
																		// de la
																		// nourriture
																		// et
																		// qu'il
																		// n'a
																		// pas
																		// encore
																		// bouge
				Nourriture n = nourriture.get(nourriture.size() - 1);
				double[] vecteurPN = { n.getX() - this.getX(), n.getY() - this.getY() }; // vecteur
																							// pigeon-nourriture
				double normePN = Math.sqrt((Math.pow(vecteurPN[0], 2) + Math.pow(vecteurPN[1], 2))); // distance
																										// pigeon-nourriture
				if (normePN > 1) {
					this.setEtat(Etat.MOVING);
					fatigue = 0; // on remet leur fatigue a 0 pour eviter qu'il
									// s'endorment juste apres
				} else {
					this.setDevantNourriture(true);
					System.out.println(this.getDevantNourriture());
				}
			}

			// manger
			else if (!nourriture.isEmpty() && this.devantNourriture) { // si il
																		// arrive
																		// sur
																		// de la
																		// nourriture
																		// qui
																		// n'est
																		// pas
																		// mangee
				System.out.println(this.getNom() + " ARRIVED");
				System.out.println("Position arrivee " + this.getNom() + ": " + this.getX() + "," + this.getY());
				this.setEtat(Etat.EATING);
			}

			// dormir
			else if (nourriture.isEmpty() && fatigue >= 20)
				this.setEtat(Etat.SLEEPING);
		}
	}

	/*
	 * Deplacement du pigeon vers une position donnee
	 */
	public void seDeplacer() {
		double normePN = 2;
		Nourriture n = nourriture.get(nourriture.size() - 1);
		if (normePN > 1) {
			double[] vecteurPN = { n.getX() - this.getX(), n.getY() - this.getY() }; // vecteur
																						// pigeon-nourriture
			normePN = Math.sqrt((Math.pow(vecteurPN[0], 2) + Math.pow(vecteurPN[1], 2))); // distance
																							// pigeon-nourriture
			double[] PNnormalise = { vecteurPN[0] / normePN, vecteurPN[1] / normePN }; // Vecteur
																						// normalise
																						// pour
																						// un
																						// deplacement
																						// a
																						// vitesse
																						// uniforme

			// tant qu'il n'a pas atteint la destination (approximatif)
			System.out.println(
					"position arrondie pigeon " + this.getNom() + ": " + (int) this.getX() + " , " + (int) this.getY());

			this.setX(this.getX() + PNnormalise[0]);
			this.setY(this.getY() + PNnormalise[1]);
			this.getLabel().setLocation((int) this.getX(), (int) this.getY()); // on
																				// met
																				// a
																				// jour
																				// le
																				// sprite

			// on recalcule la position du pigeon par rapport a la nourriture
			vecteurPN[0] = n.getX() - this.getX();
			vecteurPN[1] = n.getY() - this.getY();
			normePN = Math.sqrt((Math.pow(vecteurPN[0], 2) + Math.pow(vecteurPN[1], 2))); // distance
																							// pigeon-nourriture
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
	public void fuir(double x, double y) {
		this.setVelocity(750); // vitesse augmentee
		// generation d'un point aleatoire

		double[] vecteurFuite = { x - this.getX(), y - this.getY() };
		double normeVecteurFuite = Math.sqrt((Math.pow(vecteurFuite[0], 2) + Math.pow(vecteurFuite[1], 2))); // distance
																												// pigeon-nourriture
		double[] vecteurFuiteNormalise = { vecteurFuite[0] / normeVecteurFuite, vecteurFuite[1] / normeVecteurFuite }; // Vecteur
																														// normalise
																														// pour
																														// un
																														// deplacement
																														// a
																														// vitesse
																														// uniforme

		// fuite
		this.setX(this.getX() + vecteurFuiteNormalise[0]);
		this.setY(this.getY() + vecteurFuiteNormalise[1]);
		this.getLabel().setLocation((int) this.getX(), (int) this.getY()); // on
																			// met
																			// a
																			// jour
																			// le
																			// sprite

		// on recalcule la position du pigeon par rapport a la nourriture
		vecteurFuite[0] = x - this.getX();
		vecteurFuite[1] = y - this.getY();
		normeVecteurFuite = Math.sqrt((Math.pow(vecteurFuite[0], 2) + Math.pow(vecteurFuite[1], 2))); // distance
																										// pigeon-nourriture

		if (normeVecteurFuite <= 1) {
			this.setFuit(false);
			this.setVelocity(100 + (int) (Math.random() * (110 - 90) + 90));
		}
	}

	/**
	 * THREAD
	 */

	@Override
	public void run() {
		while (true) {
			this.determinerEtat();
			this.setFuit(Environnement.getPeur());

			int[] pointFuite = new int[2];
			if (this.getFuit()) {
				pointFuite = Environnement.getpointFuite();
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
					this.manger(n);
					Evenements.supprimerNourriture(n);
					Thread.sleep(1000 / velocity);
					this.setDevantNourriture(false);
					System.out.println(this.getNom() + " ATE");
					System.out.println("Il reste " + nourriture.size() + " nourritures");
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
					this.fuir((double) pointFuite[0], (double) pointFuite[1]);
					Thread.sleep(1000 / velocity);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			default:
				System.out.println("Default case in run method");
				break;
			}
		}
	}

}
