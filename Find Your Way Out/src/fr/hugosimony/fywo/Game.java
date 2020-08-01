package fr.hugosimony.fywo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.hugosimony.fywo.plateaux.Liste_Plateaux;
import fr.hugosimony.fywo.plateaux.Plateaux;

public class Game extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	Plateaux plateau = new Plateaux(this);
	Liste_Plateaux plateau_actuel;
	public int plateau_memoire;
	public int position = 4;
	public int compteur = 0;
	public Bouton[][] tableau_bouton;
	
	public int cooldown = 0;
	
	public int score;
	public double vie;
	
	public final int GAUCHE = 1;
	public final int DROITE = 2;
	public final int HAUT = 3;
	public final int BAS = 4;
	
	public final int player = 1;
	public final int IA = 2;
	
	public int compteur_room = 0;
	
	public int rusheur_vie = 2;
	public boolean rusheur_mort = false;
	public int mage_vie = 2;
	public boolean mage_mort = false;
	
	public AudioInputStream audioIn_musique_casual;
	public Clip clip_musique_casual;
	public final URL url_musique_casual = Game.class.getResource("musiques/musique_casual.wav");
	
	public final URL rusheur_url = Game.class.getResource("images/beton_rusheur.jpg");
	public final ImageIcon rusheur = new ImageIcon(rusheur_url);
	public final URL mage_h_url = Game.class.getResource("images/herbe_mage.jpg");
	public final ImageIcon mage_herbe = new ImageIcon(mage_h_url);
	public final URL mage_b_url = Game.class.getResource("images/beton_mage.jpg");
	public final ImageIcon mage_beton = new ImageIcon(mage_b_url);
	public final URL herbe_url = Game.class.getResource("images/herbe.jpg");
	public final ImageIcon herbe = new ImageIcon(herbe_url);
	public final URL herbe_bonhome_url = Game.class.getResource("images/herbe_bonhome.jpg");
	public final ImageIcon herbe_bonhome = new ImageIcon(herbe_bonhome_url);
	public final URL herbe_bullet_url = Game.class.getResource("images/bullet_herbe.jpg");
	public final ImageIcon bullet_herbe = new ImageIcon(herbe_bullet_url);
	public final URL herbe_bulletm_url = Game.class.getResource("images/bulletm_herbe.jpg");
	public final ImageIcon bulletm_herbe = new ImageIcon(herbe_bulletm_url);
	public final URL herbe_pomme_url = Game.class.getResource("images/herbe_pomme.jpg");
	public final ImageIcon herbe_pomme = new ImageIcon(herbe_pomme_url);
	public final URL beton_url = Game.class.getResource("images/béton.jpg");
	public final ImageIcon beton = new ImageIcon(beton_url);
	public final URL beton_bonhome_url = Game.class.getResource("images/béton_bonhome.jpg");
	public final ImageIcon beton_bonhome = new ImageIcon(beton_bonhome_url);
	public final URL bullet_beton_url = Game.class.getResource("images/bullet_beton.jpg");
	public final ImageIcon bullet_beton = new ImageIcon(bullet_beton_url);
	public final URL bulletm_beton_url = Game.class.getResource("images/bulletm_beton.jpg");
	public final ImageIcon bulletm_beton = new ImageIcon(bulletm_beton_url);
	public final URL beton_pomme_url = Game.class.getResource("images/béton_pomme.jpg");
	public final ImageIcon beton_pomme = new ImageIcon(beton_pomme_url);
	public final URL mur_brique_url = Game.class.getResource("images/mur_brique.jpg");
	public final ImageIcon mur_brique = new ImageIcon(mur_brique_url);
	public final URL eau_url = Game.class.getResource("images/eau.jpg");
	public final ImageIcon eau = new ImageIcon(eau_url);
	public final URL lave_url = Game.class.getResource("images/lave.jpg");
	public final ImageIcon lave = new ImageIcon(lave_url);
	public final URL portique_piece_url = Game.class.getResource("images/portique_piece.jpg");
	public final ImageIcon portique_piece = new ImageIcon(portique_piece_url);
	public final URL porte_haut_url = Game.class.getResource("images/porte_haut.jpg");
	public final ImageIcon porte_haut = new ImageIcon(porte_haut_url);
	public final URL porte_droite_url = Game.class.getResource("images/porte_droite.jpg");
	public final ImageIcon porte_droite = new ImageIcon(porte_droite_url);
	public final URL porte_gauche_url = Game.class.getResource("images/porte_gauche.jpg");
	public final ImageIcon porte_gauche = new ImageIcon(porte_gauche_url);
	public final URL porte_bas_url = Game.class.getResource("images/porte_bas.jpg");
	public final ImageIcon porte_bas = new ImageIcon(porte_bas_url);
	public final URL porte_haut_close_url = Game.class.getResource("images/porte_haut_close.jpg");
	public final ImageIcon porte_haut_close = new ImageIcon(porte_haut_close_url);
	public final URL porte_droite_close_url = Game.class.getResource("images/porte_droite_close.jpg");
	public final ImageIcon porte_droite_close = new ImageIcon(porte_droite_close_url);
	public final URL porte_gauche_close_url = Game.class.getResource("images/porte_gauche_close.jpg");
	public final ImageIcon porte_gauche_close = new ImageIcon(porte_gauche_close_url);
	public final URL porte_bas_close_url = Game.class.getResource("images/porte_bas_close.jpg");
	public final ImageIcon porte_bas_close = new ImageIcon(porte_bas_close_url);
	public final URL coffre_ferme_url = Game.class.getResource("images/coffre_fermé.jpg");
	public final ImageIcon coffre_ferme = new ImageIcon(coffre_ferme_url);
	public final URL coffre_ouvert_url = Game.class.getResource("images/coffre_ouvert.jpg");
	public final ImageIcon coffre_ouvert = new ImageIcon(coffre_ouvert_url);
	
	public final Color rusheur_b = new Color(75,75,75);
	public final Color mage_h = new Color(76,76,76);
	public final Color mage_b = new Color(77,77,77);
	public final Color bonhome_herbe = new Color(0,0,0);
	public final Color bonhome_beton = new Color(0,0,1);
	public final Color bullet_h = new Color(0,0,10);
	public final Color bullet_b = new Color(0,0,11);
	public final Color bulletm_h = new Color(0,0,12);
	public final Color bulletm_b = new Color(0,0,13);
	public final Color porte_h = new Color(1,1,1);
	public final Color porte_g = new Color(2,2,2);
	public final Color porte_d = new Color(3,3,3);
	public final Color porte_b = new Color(4,4,4);
	public final Color porte_hc = new Color(10,10,10);
	public final Color porte_gc = new Color(20,20,20);
	public final Color porte_dc = new Color(30,30,30);
	public final Color porte_bc = new Color(40,40,40);
	public final Color fond_herbe = new Color(100,100,100);
	public final Color fond_beton = new Color(101,101,101);
	public final Color eau_ = new Color(102,102,102);
	public final Color lave_ = new Color(103,103,103);
	public final Color coffre_f = new Color(150,150,150);
	public final Color coffre_o = new Color(151,151,151);
	public final Color herbe_p = new Color(175,175,175);
	public final Color beton_p = new Color(176,176,176);
	public final Color mur_brique_ = new Color(200,200,200);
	public final Color portique = new Color(201,201,201);

	public JLabel label_info = new JLabel();
	public Font font = new Font("Arial", Font.BOLD, 25);
	
	public JPanel panel_game = new JPanel();
	
	public Game(int difficulty, boolean IA){
		
		this.setTitle("Find Your Way Out [Difficulté " + difficulty + "]     -----    Made by Hugo Simony-Jungo");
		this.setSize(700, 700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
		   @Override
		   public void windowClosed(WindowEvent e) {
		      System.exit(0);
		   }
		});
		compteur = difficulty * 7;
		vie = 6 - difficulty;
		score = 0;
		
		setPlateau(Liste_Plateaux.STARTING_ROOM);
		plateau_memoire = 0;
		
		JPanel panel_all = new JPanel();
		panel_all.setLayout(new BorderLayout());
		
		label_info.setText("Vie : " + vie);
		label_info.setFont(font);
		label_info.setHorizontalAlignment(JLabel.CENTER);
		label_info.setVerticalAlignment(JLabel.CENTER);
		
		panel_all.add("North", label_info);
		
		panel_game.setLayout(new GridLayout(21,21));
		
		tableau_bouton = new Bouton[21][21];
		
		for(int i=0;i<21;i++) { 	
			for(int j=0;j<21;j++) {
				Bouton bouton = new Bouton(i,j,this);
				bouton.setBorder(null);
				panel_game.add(bouton);
				tableau_bouton[i][j] = bouton;
			}
		}
		
		panel_all.add("Center", panel_game);
		
		this.add(panel_all);
		
		try {
			audioIn_musique_casual = AudioSystem.getAudioInputStream(url_musique_casual);
			clip_musique_casual = AudioSystem.getClip();
			clip_musique_casual.open(audioIn_musique_casual);
			clip_musique_casual.loop(1000);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
	}
	
	public void verifMort() {
		if(vie <= 0) {
			clip_musique_casual.stop();
			tableau_bouton = new Bouton[21][21];
			setEnabled(false);
			setVisible(false);
			Victoire victoire = new Victoire(score, false);
			victoire.setVisible(true);
		}
	}

	public void verifMob(int type) {
		if(type == 1) {
			if (rusheur_vie == 0) {
				rusheur_mort = true;
				compteur_room--;
				verifRoom();
			}
		}
		if(type == 2) {
			if (mage_vie == 0) {
				mage_mort = true;
				compteur_room--;
				verifRoom();
			}
		}
	}
	
	public void passRoom(){
		if(plateau_actuel == Liste_Plateaux.SALLE_BOSS) {
			tableau_bouton[0][10].setIcon(porte_haut);
			tableau_bouton[0][10].setBackground(porte_h);
		}else {
			for(int i=0;i<21;i++) {	
				for(int j=0;j<21;j++) {
					if(tableau_bouton[i][j].getBackground() == porte_hc) {
						tableau_bouton[i][j].setIcon(porte_haut);
						tableau_bouton[i][j].setBackground(porte_h);
					}
					if(tableau_bouton[i][j].getBackground() == porte_gc) {
						tableau_bouton[i][j].setIcon(porte_gauche);
						tableau_bouton[i][j].setBackground(porte_g);
					}
					if(tableau_bouton[i][j].getBackground() == porte_dc) {
						tableau_bouton[i][j].setIcon(porte_droite);
						tableau_bouton[i][j].setBackground(porte_d);
					}
					if(tableau_bouton[i][j].getBackground() == porte_bc) {
						tableau_bouton[i][j].setIcon(porte_bas);
						tableau_bouton[i][j].setBackground(porte_b);
					}
				}
			}
		}
	}
	
	public void verifRoom() {
		if(compteur_room == 0) {
			passRoom();	
		}
	}
	
	public void setPlateau(Liste_Plateaux plateau) {
		plateau_actuel = plateau;
	}
}
