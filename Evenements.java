import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Classe Evenements Implemente l'interface graphique et appel le thread pigeon
 * 
 * @author JB maj 29/10/2016
 */

public class Evenements extends JFrame {
	private static final long serialVersionUID = 1L;
	/**
	 * version par default
	 */
	/* Attributs */
	private ArrayList<Pigeon> pigeons;
	private static ArrayList<Nourriture> nourritures;
	private static ArrayList<Nourriture> pokeball;
	static Environnement ev;
	static Evenements e = new Evenements();

	// Constructeur
	public Evenements() {
		super("Jeu des Pigeons affamés");

		// on récupère l'image
		ev = new Environnement();
		ImageIcon image = ev.getImage();

		// on adapte la fenêtre à la taille de l'image de fond
		ev.setLayout(null);
		this.setSize(image.getIconWidth(), image.getIconHeight());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		/* evenemnt Souris */
		add(ev);
		addMouseListener(ev);

		// Création des listes nécessaires
		pigeons = new ArrayList<Pigeon>();
		nourritures = new ArrayList<Nourriture>();
		pokeball = new ArrayList<Nourriture>();
	}

	public ArrayList<Nourriture> getPokeball() {
		return pokeball;
	}

	// Ajout d'un pigeon dans la liste appropriée
	public void addPigeon(Pigeon pigeon) {
		pigeons.add(pigeon);
	}

	/* ajout de nourriture dans la liste appropriée */
	public void addNourriture(Nourriture n) {
		nourritures.add(n);
	}

	// Retirer de la nourriture quand elle est mangée
	// retire le dernier élément ajouter
	public static void RemoveNourriture() {
		int i = nourritures.size();
		nourritures.remove(i - 1);

	}

	public static void addPoke(Nourriture poke) {
		pokeball.add(poke);
	}

	public static void RemovePokeball() {
		int i = pokeball.size();
		pokeball.remove(i - 1);
	}

	/* appel du thread et affichage des pigeons */
	public void startPigeon() {
		// Ajout des pigeons dans la fenêtre de jeux

		for (Pigeon pigeon : pigeons) {
			pigeon.getLabel().setBounds((int) pigeon.getX(), (int) pigeon.getY(), 100, 100);
			ev.add(pigeon.getLabel());
			repaint();
		}

		setVisible(true);

		for (Pigeon pigeon : pigeons) {
			// appel du thread des pigeons
			pigeon.start();
		}

	}

	/* ajout de l'image nourriture dans l'interface */
	public void startNourriture() {
		for (Nourriture nourriture : nourritures) {
			nourriture.getLabel().setBounds((int) nourriture.getX(), (int) nourriture.getY(), 100, 100);
			ev.repaint();
			setVisible(true);
		}
	}

	/* suppresion de l'image nourriture */
	public static void supprimerNourriture(Nourriture n) {
		if (!nourritures.isEmpty()) {
			RemoveNourriture();
			Pigeon.removeNourriture(n);
			ev.remove(n.getLabel());
			ev.repaint();
			e.setVisible(true);
		}

	}

	public void startPokeball() {
		for (Nourriture poke : pokeball) {
			poke.getLabel().setBounds((int) poke.getX(), (int) poke.getY(), 100, 100);
			ev.repaint();
			setVisible(true);
		}
	}

	/* suppresion de l'image nourriture */
	public static void supprimerPokeball(Nourriture poke) {
		if (!nourritures.isEmpty()) {
			RemovePokeball();
			ev.remove(poke.getLabel());
			ev.repaint();
			e.setVisible(true);
		}

	}

	public static void main(String[] args) {

		// pigeons
		Pigeon p1 = new Pigeon("pigeon.png");
		p1.setX(10);
		p1.setY(10);
		p1.setVelocity(90);
		e.addPigeon(p1);

		Pigeon p2 = new Pigeon("pigeon2.png");
		p2.setX(500);
		p2.setY(10);
		p2.setVelocity(110);
		e.addPigeon(p2);

		/*
		 * Pigeon p3 = new Pigeon("pigeon.png"); p3.setX(10); p3.setY(400);
		 * p3.setVelocity(90); e.addPigeon(p3);
		 * 
		 * Pigeon p4 = new Pigeon("pigeon2.png"); p4.setX(10); p4.setY(200);
		 * p4.setVelocity(110); e.addPigeon(p4);
		 * 
		 * Pigeon p5 = new Pigeon("pigeon.png"); p5.setX(250); p5.setY(10);
		 * p5.setVelocity(90); e.addPigeon(p5);
		 * 
		 * Pigeon p6 = new Pigeon("pigeon2.png"); p6.setX(500); p6.setY(400);
		 * p6.setVelocity(110); e.addPigeon(p6);
		 */

		e.startPigeon();
	}
}
