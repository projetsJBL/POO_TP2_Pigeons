
/**
 * Classe Environnement
 * Implemente le fond d'écran du jeu
 * @author JB
 * maj 29/10/2016
 */

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Environnement extends JPanel implements MouseListener {

	protected ImageIcon image = new ImageIcon("environnement.jpg");
	private static int xclic;
	private static int yclic;
	private static int aleatoire;
	private static boolean peur;
	/**
	 * Version par défault
	 */
	private static final long serialVersionUID = 1L;

	/* Creation du fond */
	@Override
	protected void paintComponent(Graphics g) {

		super.setOpaque(false);
		g.drawImage(image.getImage(), 0, 0, null);
		super.paintComponent(g);

	}

	/* Accesseurs */
	public ImageIcon getImage() {
		return image;
	}

	public static int getAleatoire() {
		return aleatoire;
	}

	public static double getXclic() {
		return xclic;
	}

	public static double getYclic() {
		return yclic;
	}

	public static boolean getPeur() {
		return peur;
	}

	public void setImage(ImageIcon nomImage) {
		image = nomImage;
	}

	public static void setAleatoire(int a) {
		aleatoire = a;
	}

	public static void setXclic(int x) {
		xclic = x;
	}

	public static void setYclic(int y) {
		yclic = y;
	}

	public static void setPeur(Boolean b) {
		peur = b;
	}

	/* Evenements des cliques */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	/* lorsaue le clic est releve */
	public void mouseReleased(MouseEvent e) {
		// on recupere les coordonnes de l'endroit cliquer
		xclic = (int) e.getX();
		yclic = (int) e.getY();

		// Generation d'un nombre aleatoire entre 1 et 10
		int min = 1;
		int max = 10;
		aleatoire = (int) (min + (Math.random() * max - min));

		// s'il est superieur strict a un c'est une nourriture
		if (aleatoire > 1) {
			peur = false;

			// Gestion de la nourriture
			Nourriture n = new Nourriture("nourriture.png");
			// ajout de nourriture dans la liste nourriture de Pigeon à la
			// position cliquée
			n.setX((int) xclic - 15);
			n.setY((int) yclic - 85);
			Pigeon.addNourriture(n);

			// Ajout de l'image dans l'interface graphique
			n.getLabel().setBounds((int) n.getX(), (int) n.getY(), 100, 100);
			this.add(n.getLabel());
			Evenements.e.startNourriture();

		} else {
			peur = true;

			// Gestion de la peur
			Nourriture n = new Nourriture("pokeball.png");
			// Ajout de l'element declencheur dans la liste de pokeball de
			// Evenement
			n.setX((int) xclic - 15);
			n.setY((int) yclic - 85);
			Evenements.addPoke(n);

			// ajout de l'image dans l'interface graphique
			n.getLabel().setBounds((int) n.getX(), (int) n.getY(), 100, 100);
			this.add(n.getLabel());
			Evenements.e.startPokeball();

		}
	}

}
