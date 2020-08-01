package fr.hugosimony.fywo.time;

import java.util.Timer;
import java.util.TimerTask;

import fr.hugosimony.fywo.Game;

public class Magie extends TimerTask{
	Game game;
	int x_mage;
	int y_mage;
	int x_player;
	int y_player;
	
	public Magie(Game game) {
		this.game = game;
	}
	
	@Override
	public void run() {
		for(int i=0;i<21;i++) {	
			for(int j=0;j<21;j++) {
				if(game.tableau_bouton[i][j].getBackground() == game.mage_b || game.tableau_bouton[i][j].getBackground() == game.mage_h) {
					x_mage = i;
					y_mage = j;
				}
			}
		}
		if(game.tableau_bouton[x_mage][y_mage].getBackground() == game.mage_b) {
			game.tableau_bouton[x_mage][y_mage].setBackground(game.fond_beton);
			game.tableau_bouton[x_mage][y_mage].setIcon(game.beton);
		}else {
			game.tableau_bouton[x_mage][y_mage].setBackground(game.fond_herbe);
			game.tableau_bouton[x_mage][y_mage].setIcon(game.herbe);
		}
		for(int i=0;i<21;i++) {	
			for(int j=0;j<21;j++) {
				if(game.tableau_bouton[i][j].getBackground() == game.bonhome_beton || game.tableau_bouton[i][j].getBackground() == game.bonhome_herbe) {
					x_player = i;
					y_player = j;
				}
			}
		}
		
		if(game.mage_mort){
			this.cancel();
		}else {
			if(x_mage == x_player || y_mage == y_player) {
				Timer timer;
				timer = new Timer();
				if(x_mage == x_player) {
					if(y_mage < y_player) {
						timer.schedule(new Tir(game, game.IA, game.DROITE), 0, 100);
					}else {
						timer.schedule(new Tir(game, game.IA, game.GAUCHE), 0, 100);
					}
				}else {
					if(x_mage < x_player) {
						timer.schedule(new Tir(game, game.IA, game.BAS), 0, 100);
					}else {
						timer.schedule(new Tir(game, game.IA, game.HAUT), 0, 100);
					}
				}
				if(game.tableau_bouton[x_mage][y_mage].getBackground() == game.fond_beton) {
					game.tableau_bouton[x_mage][y_mage].setBackground(game.mage_b);
					game.tableau_bouton[x_mage][y_mage].setIcon(game.mage_beton);
				}
				if(game.tableau_bouton[x_mage][y_mage].getBackground() == game.fond_herbe) {
					game.tableau_bouton[x_mage][y_mage].setBackground(game.mage_h);
					game.tableau_bouton[x_mage][y_mage].setIcon(game.mage_herbe);
				}
			}else {
				if(y_mage < y_player) {
					if(game.tableau_bouton[x_mage][y_mage+1].getBackground() == game.fond_beton) {
						game.tableau_bouton[x_mage][y_mage+1].setBackground(game.mage_b);
						game.tableau_bouton[x_mage][y_mage+1].setIcon(game.mage_beton);
					}else {
						if(game.tableau_bouton[x_mage][y_mage+1].getBackground() == game.fond_herbe) {
							game.tableau_bouton[x_mage][y_mage+1].setBackground(game.mage_h);
							game.tableau_bouton[x_mage][y_mage+1].setIcon(game.mage_herbe);
						}else {
							if(game.tableau_bouton[x_mage-1][y_mage].getBackground() == game.fond_beton) {
								game.tableau_bouton[x_mage-1][y_mage].setBackground(game.mage_b);
								game.tableau_bouton[x_mage-1][y_mage].setIcon(game.mage_beton);
							}else {
								if(game.tableau_bouton[x_mage-1][y_mage].getBackground() == game.fond_herbe) {
									game.tableau_bouton[x_mage-1][y_mage].setBackground(game.mage_h);
									game.tableau_bouton[x_mage-1][y_mage].setIcon(game.mage_herbe);
								}else {
									if(game.tableau_bouton[x_mage+1][y_mage].getBackground() == game.fond_beton) {
										game.tableau_bouton[x_mage+1][y_mage].setBackground(game.mage_b);
										game.tableau_bouton[x_mage+1][y_mage].setIcon(game.mage_beton);
									}else {
										if(game.tableau_bouton[x_mage+1][y_mage].getBackground() == game.fond_herbe) {
											game.tableau_bouton[x_mage+1][y_mage].setBackground(game.mage_h);
											game.tableau_bouton[x_mage+1][y_mage].setIcon(game.mage_herbe);
										}
									}
								}
							}
						}
					}
				}else {
					if(game.tableau_bouton[x_mage][y_mage-1].getBackground() == game.fond_beton) {
						game.tableau_bouton[x_mage][y_mage-1].setBackground(game.mage_b);
						game.tableau_bouton[x_mage][y_mage-1].setIcon(game.mage_beton);
					}else {
						if(game.tableau_bouton[x_mage][y_mage-1].getBackground() == game.fond_herbe) {
							game.tableau_bouton[x_mage][y_mage-1].setBackground(game.mage_h);
							game.tableau_bouton[x_mage][y_mage-1].setIcon(game.mage_herbe);
						}else {
							if(game.tableau_bouton[x_mage-1][y_mage].getBackground() == game.fond_beton) {
								game.tableau_bouton[x_mage-1][y_mage].setBackground(game.mage_b);
								game.tableau_bouton[x_mage-1][y_mage].setIcon(game.mage_beton);
							}else {
								if(game.tableau_bouton[x_mage-1][y_mage].getBackground() == game.fond_herbe) {
									game.tableau_bouton[x_mage-1][y_mage].setBackground(game.mage_h);
									game.tableau_bouton[x_mage-1][y_mage].setIcon(game.mage_herbe);
								}else {
									if(game.tableau_bouton[x_mage+1][y_mage].getBackground() == game.fond_beton) {
										game.tableau_bouton[x_mage+1][y_mage].setBackground(game.mage_b);
										game.tableau_bouton[x_mage+1][y_mage].setIcon(game.mage_beton);
									}else {
										if(game.tableau_bouton[x_mage+1][y_mage].getBackground() == game.fond_herbe) {
											game.tableau_bouton[x_mage+1][y_mage].setBackground(game.mage_h);
											game.tableau_bouton[x_mage+1][y_mage].setIcon(game.mage_herbe);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
