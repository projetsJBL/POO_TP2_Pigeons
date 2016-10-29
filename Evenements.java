import java.util.ArrayList;
import javax.swing.JFrame;

/**
 * Classe Evenements Implement les r�actions des pigeons selon leur etat
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
		super("Jeu des Pigeons affam�s");
		setExtendedState(JFrame.MAXIMIZED_BOTH); // totalit� de l'�cran
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pigeons = new ArrayList<Pigeon>();
		nourritures = new ArrayList<Nourriture>();
	}

	// Ajout d'un pigeon dans la liste appropri�e
	public void addPigeon(Pigeon pigeon) {
		pigeons.add(pigeon);

	}

	// Ajout de nourritures dans la liste appropri�e
	public void addNourriture(Nourriture nourriture) {
		nourritures.add(nourriture);
	}

	// Retirer de la nourriture quand elle est mang�e 
	// retire le dernier �l�ment ajouter
	public void RemoveNourriture() {
		int i = nourritures.size();
		nourritures.remove(i);
	}

	public void start() {

	}

}
