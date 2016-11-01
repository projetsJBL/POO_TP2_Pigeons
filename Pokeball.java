import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Classe objet
 * Objet qui effraie les pidgey
 * @author MrMephisto
 * maj 01/11/2016
 */

public class Pokeball extends ElementDynamique{
	
	private long time;
	
	public Pokeball(String imageName){
		//graphique
		setImageName(imageName);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(imageName));
		setLabel(label);
		
		time = System.currentTimeMillis();
	}
	
	public long getTime(){
		return time;
	}
}
