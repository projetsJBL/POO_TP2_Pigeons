
/**
 * Classe Environnement
 * Implemente le fond d'écran du jeu
 * @author JB
 * maj 29/10/2016
 */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Environnement extends JPanel implements MouseListener, MouseMotionListener {
	protected ImageIcon image = new ImageIcon("environnement.jpg");
	private int xclic, yclic;
	/**
	 * Version par défault
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.setOpaque(false);
		g.drawImage(image.getImage(), 0, 0, null);
		super.paintComponent(g);

	}

	public ImageIcon getImage() {
		return image;
	}

	public int[] getTaille() {
		int x = image.getIconWidth();
		int y = image.getIconHeight();
		int[] taille = { x, y };

		return taille;

	}

	public int getXclic() {
		return xclic;
	}

	public int getYclic() {
		return yclic;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		xclic = e.getX();
		yclic = e.getY();
		System.out.println(xclic + ";" + yclic);

		Nourriture n = new Nourriture("nourriture.png");

		// ajout de nourriture dans la liste nourriture de pigeon
		n.setX((double) xclic);
		n.setY((double) yclic);
		Pigeon.addNourriture(n);
		for (int i = 0; i < Pigeon.getNourriture().size(); i++){
		System.out.println(
					"nourriture " + Pigeon.getNourriture().get(i).getX() + ";" + Pigeon.getNourriture().get(i).getX());}

		// Ajout de l'image dans l'interface graphique
		n.getLabel().setLocation(xclic, yclic);
		this.add(n.getLabel());

		Main.evt.addNourriture(n);
		System.out.println("nourriture image " + n.getLabel().getLocation());

		Main.evt.startNourriture();

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

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
