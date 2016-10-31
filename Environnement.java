
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
	private int xclic, yclic;
	private static int aleatoire;
	private static boolean peur;
	private static int[] pointfuite = new int[2];
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

	public double getXclic() {
		return xclic;
	}

	public double getYclic() {
		return yclic;
	}

	public static boolean getPeur() {
		return peur;
	}

	public static int[] getpointFuite() {
		return pointfuite;
	}

	public void setpointFuite(int[] p) {
		pointfuite = p;
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
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		xclic = (int) e.getX();
		yclic = (int) e.getY();
		System.out.println(xclic + ";" + yclic);

		int min = 1;
		int max = 10;
		aleatoire = (int) (min + (Math.random() * max - min));
		if (aleatoire > 1) {
			peur = false;
			// Gestion de la nourriture
			Nourriture n = new Nourriture("nourriture.png");
			// ajout de nourriture dans la liste nourriture de pigeon à la
			// position
			// cliquée
			n.setX((int) xclic);
			n.setY((int) yclic);
			Pigeon.addNourriture(n);

			// Ajout de l'image dans l'interface graphique
			n.getLabel().setBounds((int) n.getX(), (int) n.getY(), 100, 100);
			this.add(n.getLabel());
			Evenements.e.addNourriture(n);
			Evenements.e.startNourriture();

			// Position de l'image dans l'interface graphique
			System.out.println("nourriture image " + n.getLabel().getLocation());

		} else {
			peur = true;

			// Calcul de points de fuite
			int x = (int) Math.random() * (1000) * (Math.random() < 0.5 ? -1 : 1);
			int y = (int) Math.random() * (1000) * (Math.random() < 0.5 ? -1 : 1);
			int[] p = { x, y };
			setpointFuite(p);

			// Gestion de la nourriture
			Nourriture n = new Nourriture("pokeball.png");
			// ajout de nourriture dans la liste nourriture de pigeon à la
			// position
			// cliquée
			n.setX((int) xclic);
			n.setY((int) yclic);

			// ajout de l'image dans l'interface graphique
			n.getLabel().setBounds((int) n.getX(), (int) n.getY(), 100, 100);
			this.add(n.getLabel());
			Evenements.addPoke(n);
			Evenements.e.startPokeball();

			// Position de l'image dans l'interface graphique
			System.out.println("nourriture image " + n.getLabel().getLocation());

		}
	}
	// Gestion de la peur, une chance sur 5;

}
