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
	static Evenements e = new Evenements();
	private static final long serialVersionUID = 1L;
	/**
	 * version par default
	 */
	/* Attributs */

	private static ArrayList<Nourriture> pokeball;
	static Environnement ev;

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

		pokeball = new ArrayList<Nourriture>();
	}

	public ArrayList<Nourriture> getPokeball() {
		return pokeball;
	}

	/* ajout de nourriture dans la liste appropriée */

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

		for (Pigeon pigeon : Pigeon.getPigeons()) {
			pigeon.getLabel().setBounds((int) pigeon.getX(), (int) pigeon.getY(), 100, 100);
			ev.add(pigeon.getLabel());
			repaint();
		}

		setVisible(true);
		for (int i = 0; i < Pigeon.getPigeons().size(); i++) {
			Pigeon.getPigeons().get(i).start();
			// appel du thread des pigeons
		}

	}

	/* ajout de l'image nourriture dans l'interface */
	public void startNourriture() {
		for (Nourriture nourriture : Pigeon.getNourriture()) {
			nourriture.getLabel().setBounds((int) nourriture.getX(), (int) nourriture.getY(), 100, 100);
			ev.repaint();
			setVisible(true);
		}

	}

	/* suppresion de l'image nourriture */
	public static void supprimerNourriture(Nourriture n) {
		if (!Pigeon.getNourriture().isEmpty()) {
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

	public static void main(String[] args) {

		// pigeons
		Pigeon p1 = new Pigeon("pigeon2.png");
		p1.setX(10);
		p1.setY(10);
		p1.setVelocity(90);

		// Démarre le jeu
		e.startPigeon();
	}
}
