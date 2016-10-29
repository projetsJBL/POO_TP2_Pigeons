import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Classe Evenements Implemente les réactions des pigeons selon leur etat
 * 
 * @author JB maj 29/10/2016
 */

public class Evenements extends JFrame {

	/**
	 * version par default
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Pigeon> pigeons;
	private ArrayList<Nourriture> nourritures;

	// Constructeur
	public Evenements() {
		super("Jeu des Pigeons affamés");
		
		//on récupère la taille de l'image
		Environnement ev = new Environnement();
		ImageIcon image = ev.getImage();

		
		//on adapte la fenêtre à la taille de l'image de fond
		this.setSize(image.getIconWidth(), image.getIconHeight());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Création des listes nécessaires
		pigeons = new ArrayList<Pigeon>();
		nourritures = new ArrayList<Nourriture>();
	}

	// Ajout d'un pigeon dans la liste appropriée
	public void addPigeon(Pigeon pigeon) {
		pigeons.add(pigeon);

	}

	// Ajout de nourritures dans la liste appropriée
	public void addNourriture(Nourriture nourriture) {
		nourritures.add(nourriture);
	}

	// Retirer de la nourriture quand elle est mangée
	// retire le dernier élément ajouter
	public void RemoveNourriture() {
		int i = nourritures.size();
		nourritures.remove(i);
	}

	//gestion des réactions des pigeons
	public void start() {
		Environnement evt = new Environnement();

		for (Pigeon pigeon : pigeons) {
			evt.add(pigeon.getLabel());
		}

		add(evt);

		setVisible(true);

	}

}
