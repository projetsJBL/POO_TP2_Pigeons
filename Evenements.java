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

	private ArrayList<Pigeon> pigeons;
	private ArrayList<Nourriture> nourritures;
	Environnement ev;

	// Constructeur
	public Evenements() {
		super("Jeu des Pigeons affamés");

		// on récupère la taille de l'image
		ev = new Environnement();
		ImageIcon image = ev.getImage();

		// on adapte la fenêtre à la taille de l'image de fond
		this.setSize(image.getIconWidth(), image.getIconHeight());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		add(ev);
		addMouseListener(ev);
		addMouseMotionListener(ev);

		// Création des listes nécessaires
		pigeons = new ArrayList<Pigeon>();
		nourritures = new ArrayList<Nourriture>();
	}

	// Ajout d'un pigeon dans la liste appropriée
	public void addPigeon(Pigeon pigeon) {
		pigeons.add(pigeon);

	}

	// Retirer de la nourriture quand elle est mangée
	// retire le dernier élément ajouter
	public void RemoveNourriture() {
		int i = nourritures.size();
		nourritures.remove(i - 1);
	}
	// gestion des réactions des pigeons

	public void startPigeon() {
		// Ajout des pigeons dans la fenêtre de jeux

		for (Pigeon pigeon : pigeons) {
			ev.add(pigeon.getLabel());
			add(ev);
		}

		setVisible(true);

	}

	public void startNourriture() {
		for (Nourriture nourriture : nourritures) {
			ev.add(nourriture.getLabel());

		}
		add(ev);
		setVisible(true);

		for (Nourriture nourriture : nourritures) {
			nourriture.getLabel().setLocation((int) nourriture.getX(), (int) nourriture.getY());
		}
		for (Pigeon pigeon : pigeons) {

			pigeon.start();
		}
	}

	public void addNourriture(Nourriture n) {
		// TODO Auto-generated method stub
		nourritures.add(n);
	}

}
