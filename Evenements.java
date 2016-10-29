import java.util.ArrayList;
import javax.swing.JFrame;

/**
 * Classe Evenements Implement les réactions des pigeons selon leur etat
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
		setExtendedState(JFrame.MAXIMIZED_BOTH); // totalité de l'écran
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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

	public void start() {

	}

}
