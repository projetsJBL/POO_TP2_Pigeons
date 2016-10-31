import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Classe Evenements Implemente les réactions des pigeons selon leur etat
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

	/* appel du thread et affichage des pigeons */
	public void startPigeon() {
		// Ajout des pigeons dans la fenêtre de jeux

		for (Pigeon pigeon : pigeons) {
			pigeon.getLabel().setBounds((int) pigeon.getX(), (int) pigeon.getY(), 100, 100);
			ev.add(pigeon.getLabel());
		}
		add(ev);
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
			ev.add(nourriture.getLabel());
			ev.repaint();
			setVisible(true);
		}
	}

	/* suppresion de l'image nourriture */
	public static void supprimerNourriture(Nourriture n) {
		if (!nourritures.isEmpty()) {
			RemoveNourriture();
			ev.remove(n.getLabel());
			ev.repaint();
			e.setVisible(true);
		}

	}

	public static void main(String[] args) {
		
		// pigeons
		Pigeon p1 = new Pigeon("pigeon.jpg");
		p1.setX(10);
		p1.setY(10);
		p1.setVelocity(90);
		e.addPigeon(p1);

		Pigeon p2 = new Pigeon("pigeon.jpg");
		p2.setX(500);
		p2.setY(400);
		p2.setVelocity(110);
		e.addPigeon(p2);

		e.startPigeon();
	}
}
