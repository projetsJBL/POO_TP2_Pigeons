
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

	public int[] getTaille() {
		int x = image.getIconWidth();
		int y = image.getIconHeight();
		int[] taille = { x, y };

		return taille;

	}

	public static int getXclic() {
		return xclic;
	}

	public static int getYclic() {
		return yclic;
	}

	public static boolean getPeur() {
		return peur;
	}

	public static void setPeur(Boolean b) {
		peur = b;
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

	/* Evenements clics de souris */
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

	/* Au relachement du clique */
	@Override
	public void mouseReleased(MouseEvent e) {

		xclic = (int) e.getX();
		yclic = (int) e.getY();

		// Généréation aléatoire d'un nombre entre et 1 et 10
		int min = 1;
		int max = 10;
		aleatoire = (int) (min + (Math.random() * max - min));

		// Si le nombre n'est pas 1, le clique de souris génére une nourriture
		if (aleatoire > 1) {
			peur = false;
			// Gestion de la nourriture
			Nourriture n = new Nourriture("nourriture.png");
			// ajout de nourriture dans la liste nourriture de pigeon à la
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

			// Gestion de la Pokeball
			Pokeball p = new Pokeball("pokeball.png");
			p.setX((int) xclic - 15);
			p.setY((int) yclic - 85);

			// ajout de l'image dans l'interface graphique
			p.getLabel().setBounds((int) p.getX(), (int) p.getY(), 100, 100);
			this.add(p.getLabel());
			Evenements.addPoke(p);
			Evenements.e.startPokeball();
		}
	}

}
