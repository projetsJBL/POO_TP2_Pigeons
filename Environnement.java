
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

	public boolean getPeur() {
		return peur;
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
		int max = 5;
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

			// coordonnées des pigeons dans la liste pigeon dans la liste
			// nourritures
			for (int i = 0; i < Pigeon.getNourriture().size(); i++) {
				System.out.println("nourriture " + Pigeon.getNourriture().get(i).getX() + ";"
						+ Pigeon.getNourriture().get(i).getY());

				// Ajout de l'image dans l'interface graphique
				n.getLabel().setLocation((int) xclic, (int) yclic);
				this.add(n.getLabel());
				Evenements.e.addNourriture(n);
				Evenements.e.startNourriture();

				// Position de l'image dans l'interface graphique
				System.out.println("nourriture image " + n.getLabel().getLocation());
			}
		} else {

			peur = true;
			// Gestion de la nourriture
			Nourriture n = new Nourriture("fusil.jpg");
			// ajout de nourriture dans la liste nourriture de pigeon à la
			// position
			// cliquée
			n.setX((int) xclic);
			n.setY((int) yclic);
			Pigeon.addNourriture(n);

			// coordonnées des pigeons dans la liste pigeon dans la liste
			// nourritures
			for (int i = 0; i < Pigeon.getNourriture().size(); i++) {
				System.out.println("nourriture " + Pigeon.getNourriture().get(i).getX() + ";"
						+ Pigeon.getNourriture().get(i).getY());

				// Ajout de l'image dans l'interface graphique
				n.getLabel().setLocation((int) xclic, (int) yclic);
				this.add(n.getLabel());
				Evenements.e.addNourriture(n);
				Evenements.e.startNourriture();

				// Position de l'image dans l'interface graphique
				System.out.println("nourriture image " + n.getLabel().getLocation());

			}
		}
		// Gestion de la peur, une chance sur 5;

	}

}
