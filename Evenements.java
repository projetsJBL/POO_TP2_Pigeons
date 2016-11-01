import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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

	private static ArrayList<Nourriture> pokeball;
	static Environnement ev;
	static Evenements e = new Evenements();

	/* Constructeur */
	public Evenements() {
		super("Jeu des Pigeons affamés");

		// on récupère l'image
		ev = new Environnement();
		ImageIcon image = ev.getImage();

		// on adapte la fenêtre à la taille de l'image de fond
		ev.setLayout(null);
		this.setSize(image.getIconWidth(), image.getIconHeight());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// evenement Souris
		add(ev);
		addMouseListener(ev);

		// Création des listes nécessaires
		pokeball = new ArrayList<Nourriture>();
	}

	/* accesseurs */
	public ArrayList<Nourriture> getPokeball() {
		return pokeball;
	}

	public Environnement getEnvironnent() {
		return ev;
	}

	/* Accesions à la liste Pokeball */
	public static void addPoke(Nourriture poke) {
		pokeball.add(poke);
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
			// appel du thread des pigeons
			Pigeon.getPigeons().get(i).start();

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
		ev.remove(n.getLabel());
		ev.repaint();

	}

	/* affichage de l'image pokeball */
	public void startPokeball() {
		for (Nourriture poke : pokeball) {
			poke.getLabel().setBounds((int) poke.getX(), (int) poke.getY(), 100, 100);
			ev.repaint();
			setVisible(true);
		}
	}

	/* determine l'image a utiliser selon le cas */
	public static void determinerImage(Pigeon p) {
		JLabel label = p.getLabel();

		if (p.getEtat().equals(Etat.SLEEPING)) {
			label.setIcon(new ImageIcon("pigeon_endormi.png"));
			p.setLabel(label);
		} else if (p.getEtat().equals(Etat.RUNNING)) {
			label.setIcon(new ImageIcon("pigeon_peur.png"));
			p.setLabel(label);
		} else if (p.getFrameSprite() > 15 && !p.getEtat().equals(Etat.WAITING)) {
			label.setIcon(new ImageIcon("pigeon2.png"));
			p.setLabel(label);
			if (p.getFrameSprite() > 30)
				p.setFrameSprite(0);
		} else {
			label.setIcon(new ImageIcon("pigeon.png"));
			p.setLabel(label);
		}
	}

	/* execute et demarre le jeu */
	public static void main(String[] args) {

		// pigeons
		Pigeon p1 = new Pigeon("pigeon.png");
		p1.setX(10);
		p1.setY(10);
		p1.setVelocity(90);
		p1.setNom("p1");

		Pigeon p2 = new Pigeon("pigeon2.png");
		p2.setX(500);
		p2.setY(10);
		p2.setVelocity(110);
		p2.setNom("p2");

		// Lancement du jeu
		e.startPigeon();
	}
}
