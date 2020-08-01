package fr.hugosimony.fywo.time;

import java.util.TimerTask;

import fr.hugosimony.fywo.Game;

public class Rush extends TimerTask{
	Game game;
	int x_rusheur;
	int y_rusheur;
	int x_player;
	int y_player;
	
	public Rush(Game game) {
		this.game = game;
	}
	
	@Override
	public void run() {
		for(int i=0;i<21;i++) {	
			for(int j=0;j<21;j++) {
				if(game.tableau_bouton[i][j].getBackground() == game.rusheur_b) {
					x_rusheur = i;
					y_rusheur = j;
				}
			}
		}
		game.tableau_bouton[x_rusheur][y_rusheur].setBackground(game.fond_beton);
		game.tableau_bouton[x_rusheur][y_rusheur].setIcon(game.beton);
		for(int i=0;i<21;i++) {	
			for(int j=0;j<21;j++) {
				if(game.tableau_bouton[i][j].getBackground() == game.bonhome_beton) {
					x_player = i;
					y_player = j;
				}
			}
		}
		
		if(game.rusheur_mort){
			this.cancel();
		}else {
			if((x_rusheur - 1 == x_player && y_rusheur == y_player) || (x_rusheur + 1 == x_player && y_rusheur == y_player) || (y_rusheur + 1 == y_player && x_rusheur == x_player) || (y_rusheur - 1 == y_player && x_rusheur == x_player)) {
				game.vie = game.vie - 0.5;
				game.label_info.setText("Vie : " + game.vie);
				game.verifMort();
				game.tableau_bouton[x_rusheur][y_rusheur].setBackground(game.rusheur_b);
				game.tableau_bouton[x_rusheur][y_rusheur].setIcon(game.rusheur);
			}else {
				if(x_rusheur == x_player) {
					if(y_rusheur < y_player) {
						if(game.tableau_bouton[x_rusheur][y_rusheur+1].getBackground() == game.fond_beton) {
							game.tableau_bouton[x_rusheur][y_rusheur+1].setBackground(game.rusheur_b);
							game.tableau_bouton[x_rusheur][y_rusheur+1].setIcon(game.rusheur);
						}else {
							if(game.tableau_bouton[x_rusheur-1][y_rusheur].getBackground() == game.fond_beton) {
								game.tableau_bouton[x_rusheur-1][y_rusheur].setBackground(game.rusheur_b);
								game.tableau_bouton[x_rusheur-1][y_rusheur].setIcon(game.rusheur);
							}else {
								game.tableau_bouton[x_rusheur+1][y_rusheur].setBackground(game.rusheur_b);
								game.tableau_bouton[x_rusheur+1][y_rusheur].setIcon(game.rusheur);
							}
						}
					}else {
						if(game.tableau_bouton[x_rusheur][y_rusheur-1].getBackground() == game.fond_beton) {
							game.tableau_bouton[x_rusheur][y_rusheur-1].setBackground(game.rusheur_b);
							game.tableau_bouton[x_rusheur][y_rusheur-1].setIcon(game.rusheur);
						}else {
							if(game.tableau_bouton[x_rusheur-1][y_rusheur].getBackground() == game.fond_beton) {
								game.tableau_bouton[x_rusheur-1][y_rusheur].setBackground(game.rusheur_b);
								game.tableau_bouton[x_rusheur-1][y_rusheur].setIcon(game.rusheur);
							}else {
								game.tableau_bouton[x_rusheur+1][y_rusheur].setBackground(game.rusheur_b);
								game.tableau_bouton[x_rusheur+1][y_rusheur].setIcon(game.rusheur);
							}
						}
					}
				}else {
					if(x_rusheur < x_player) {
						if(game.tableau_bouton[x_rusheur+1][y_rusheur].getBackground() == game.fond_beton) {
							game.tableau_bouton[x_rusheur+1][y_rusheur].setBackground(game.rusheur_b);
							game.tableau_bouton[x_rusheur+1][y_rusheur].setIcon(game.rusheur);
						}else {
							if(game.tableau_bouton[x_rusheur][y_rusheur-1].getBackground() == game.fond_beton) {
								game.tableau_bouton[x_rusheur][y_rusheur-1].setBackground(game.rusheur_b);
								game.tableau_bouton[x_rusheur][y_rusheur-1].setIcon(game.rusheur);
							}else {
								game.tableau_bouton[x_rusheur][y_rusheur+1].setBackground(game.rusheur_b);
								game.tableau_bouton[x_rusheur][y_rusheur+1].setIcon(game.rusheur);
							}
						}
					}else {
						if(game.tableau_bouton[x_rusheur-1][y_rusheur].getBackground() == game.fond_beton) {
							game.tableau_bouton[x_rusheur-1][y_rusheur].setBackground(game.rusheur_b);
							game.tableau_bouton[x_rusheur-1][y_rusheur].setIcon(game.rusheur);
						}else {
							if(game.tableau_bouton[x_rusheur][y_rusheur-1].getBackground() == game.fond_beton) {
								game.tableau_bouton[x_rusheur][y_rusheur-1].setBackground(game.rusheur_b);
								game.tableau_bouton[x_rusheur][y_rusheur-1].setIcon(game.rusheur);
							}else {
								game.tableau_bouton[x_rusheur][y_rusheur+1].setBackground(game.rusheur_b);
								game.tableau_bouton[x_rusheur][y_rusheur+1].setIcon(game.rusheur);
							}
						}
					}
				}
			}
		}
	}
}
