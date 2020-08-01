package fr.hugosimony.fywo;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	
	Main main = new Main();
	JFrame menu = this;
	int difficulty_set = 1;
	
	public Menu() {
		
		this.setTitle("Find Your Way Out [Menu]");
		this.setSize(500, 300);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		Font font_question = new Font("Arial", Font.BOLD, 40);
		Font font_difficulty = new Font("Arial", Font.BOLD, 20);
		Font font_button = new Font("Arial", Font.BOLD, 50);
		
		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(new GridLayout(3,1)); // 4 lignes 1 colonne
		
		//Label pour la question de la difficulté
		JLabel question = new JLabel("Choisissez la difficulté");
		question.setFont(font_question);
		question.setHorizontalAlignment(JLabel.CENTER);
		panel.add(question);
		
		//Slider pour connaître la difficulté
		JSlider difficulty = new JSlider(1,5);
		difficulty.setFont(font_difficulty);
		difficulty.setMinorTickSpacing(1);
		difficulty.setMajorTickSpacing(1);
		difficulty.setPaintTicks(true);
		difficulty.setPaintLabels(true);
		difficulty.setValue(3); // Choisir la valeur par défaut
		difficulty.setToolTipText("Choisissez la difficulté du jeu."
				+ " Pour une difficulté de 1, vous aurez peu de salles à explorer."
				+ " Pour une difficulté de 5, vous aurez beaucoup de salles et d'ennemis à découvrir !");
		panel.add(difficulty);
		
		//Bouton pour lancer la partie
		JButton button = new JButton("Jouer");
		button.setFont(font_button);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficulty_set = difficulty.getValue();
				Game game = new Game(difficulty_set, false);
				game.setVisible(true);
				menu.dispose();
			}
		});
		
	}
}
