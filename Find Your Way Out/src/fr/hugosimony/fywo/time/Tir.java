package fr.hugosimony.fywo.time;

import java.util.TimerTask;

import fr.hugosimony.fywo.Game;

public class Tir extends TimerTask{
	
	Game game;
	public int x_memoire;
	public int y_memoire;
	public int nouv_x;
	public int nouv_y;
	public int direction;
	public int memory = 0;
	public int memory_IA = 0;
	public int sender;

	public Tir(Game game, int sender, int direction) {
		this.game = game;
		this.direction = direction;
		this.sender = sender;
	}
	
	@Override
	public void run() {
		
		if(sender == game.player) {
			if(memory != 0) {
				for(int i=0;i<21;i++) {	
					for(int j=0;j<21;j++) {
						if(game.tableau_bouton[i][j].getBackground() == game.bullet_b || game.tableau_bouton[i][j].getBackground() == game.bullet_h) {
							x_memoire = i;
							y_memoire = j;
						}
					}
				}
			}else {
				for(int i=0;i<21;i++) {	
					for(int j=0;j<21;j++) {
						if(game.tableau_bouton[i][j].getBackground() == game.bonhome_beton || game.tableau_bouton[i][j].getBackground() == game.bonhome_herbe) {
							x_memoire = i;
							y_memoire = j;
						}
					}
				}
				memory++;
			}
		}else {
			if(memory_IA != 0) {
				for(int i=0;i<21;i++) {	
					for(int j=0;j<21;j++) {
						if(game.tableau_bouton[i][j].getBackground() == game.bulletm_b || game.tableau_bouton[i][j].getBackground() == game.bulletm_h) {
							x_memoire = i;
							y_memoire = j;
						}
					}
				}
			}else {
				for(int i=0;i<21;i++) {	
					for(int j=0;j<21;j++) {
						if(game.tableau_bouton[i][j].getBackground() == game.mage_b || game.tableau_bouton[i][j].getBackground() == game.mage_h) {
							x_memoire = i;
							y_memoire = j;
						}
					}
				}
				memory_IA++;
			}
		}
		
		if(direction == game.HAUT) {
			nouv_x = x_memoire - 1;
			nouv_y = y_memoire;
		}
		if(direction == game.GAUCHE) {
			nouv_y = y_memoire - 1;
			nouv_x = x_memoire;
		}
		if(direction == game.BAS) {
			nouv_x = x_memoire + 1;
			nouv_y = y_memoire;
		}
		if(direction == game.DROITE) {
			nouv_y = y_memoire + 1;
			nouv_x = x_memoire;
		}
		
		if(sender == game.player) {
			if(game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.bullet_b || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.bullet_h || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.bulletm_b || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.bulletm_h || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.mage_h || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.mage_b || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.rusheur_b || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.bonhome_herbe || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.bonhome_beton || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.mur_brique_ || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.portique || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.coffre_f || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.coffre_o || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.eau_ || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.lave_ || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.porte_hc || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.porte_gc || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.porte_dc || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.porte_bc || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.porte_h || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.porte_g || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.porte_d || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.porte_b) {
				if(game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.rusheur_b) {
					game.rusheur_vie--;
					game.verifMob(1);
				}
				if(game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.mage_h || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.mage_b) {
					game.mage_vie--;
					game.verifMob(2);
				}
				this.cancel();
				memory = 0;
				game.cooldown = 0;
				if(game.tableau_bouton[x_memoire][y_memoire].getBackground() == game.bullet_h) {
					game.tableau_bouton[x_memoire][y_memoire].setBackground(game.fond_herbe);
					game.tableau_bouton[x_memoire][y_memoire].setIcon(game.herbe);
				}
				if(game.tableau_bouton[x_memoire][y_memoire].getBackground() == game.bullet_b) {
					game.tableau_bouton[x_memoire][y_memoire].setBackground(game.fond_beton);
					game.tableau_bouton[x_memoire][y_memoire].setIcon(game.beton);
				}
			}else {
				if(game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.fond_herbe) {
					game.tableau_bouton[nouv_x][nouv_y].setBackground(game.bullet_h);
					game.tableau_bouton[nouv_x][nouv_y].setIcon(game.bullet_herbe);
					if(game.tableau_bouton[x_memoire][y_memoire].getBackground() == game.bullet_h) {
						game.tableau_bouton[x_memoire][y_memoire].setBackground(game.fond_herbe);
						game.tableau_bouton[x_memoire][y_memoire].setIcon(game.herbe);
					}
				}
				if(game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.fond_beton) {
					game.tableau_bouton[nouv_x][nouv_y].setBackground(game.bullet_b);
					game.tableau_bouton[nouv_x][nouv_y].setIcon(game.bullet_beton);
					if(game.tableau_bouton[x_memoire][y_memoire].getBackground() == game.bullet_b) {
						game.tableau_bouton[x_memoire][y_memoire].setBackground(game.fond_beton);
						game.tableau_bouton[x_memoire][y_memoire].setIcon(game.beton);
					}
				}
			}
		}else {
			if(game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.bulletm_b || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.bulletm_h || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.bullet_b || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.bullet_h || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.rusheur_b || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.bonhome_herbe || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.bonhome_beton || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.mur_brique_ || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.portique || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.coffre_f || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.coffre_o || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.eau_ || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.lave_ || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.porte_hc || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.porte_gc || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.porte_dc || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.porte_bc || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.porte_h || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.porte_g || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.porte_d || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.porte_b) {
				if(game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.bonhome_herbe || game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.bonhome_beton) {
					game.vie = game.vie - 0.5;
					game.label_info.setText("Vie : " + game.vie);
					game.verifMort();
				}
				this.cancel();
				memory_IA = 0;
				game.cooldown = 0;
				if(game.tableau_bouton[x_memoire][y_memoire].getBackground() == game.bulletm_h) {
					game.tableau_bouton[x_memoire][y_memoire].setBackground(game.fond_herbe);
					game.tableau_bouton[x_memoire][y_memoire].setIcon(game.herbe);
				}
				if(game.tableau_bouton[x_memoire][y_memoire].getBackground() == game.bulletm_b) {
					game.tableau_bouton[x_memoire][y_memoire].setBackground(game.fond_beton);
					game.tableau_bouton[x_memoire][y_memoire].setIcon(game.beton);
				}
			}else {
				if(game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.fond_herbe) {
					game.tableau_bouton[nouv_x][nouv_y].setBackground(game.bulletm_h);
					game.tableau_bouton[nouv_x][nouv_y].setIcon(game.bulletm_herbe);
					if(game.tableau_bouton[x_memoire][y_memoire].getBackground() == game.bulletm_h) {
						game.tableau_bouton[x_memoire][y_memoire].setBackground(game.fond_herbe);
						game.tableau_bouton[x_memoire][y_memoire].setIcon(game.herbe);
					}
				}
				if(game.tableau_bouton[nouv_x][nouv_y].getBackground() == game.fond_beton) {
					game.tableau_bouton[nouv_x][nouv_y].setBackground(game.bulletm_b);
					game.tableau_bouton[nouv_x][nouv_y].setIcon(game.bulletm_beton);
					if(game.tableau_bouton[x_memoire][y_memoire].getBackground() == game.bulletm_b) {
						game.tableau_bouton[x_memoire][y_memoire].setBackground(game.fond_beton);
						game.tableau_bouton[x_memoire][y_memoire].setIcon(game.beton);
					}
				}
			}
		}
	}
}
