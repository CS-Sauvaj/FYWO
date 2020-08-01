package fr.hugosimony.fywo;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.JButton;

import fr.hugosimony.fywo.plateaux.Liste_Plateaux;
import fr.hugosimony.fywo.plateaux.Plateaux;
import fr.hugosimony.fywo.time.Tir;
public class Bouton extends JButton {

	private static final long serialVersionUID = 1L;
	
	Game game;
	Plateaux plateau;
	
	public int x_memoire;
	public int y_memoire;
	
	static boolean ok = false;
	
	public Bouton(int i, int j, Game game) {
		
		this.game = game;
		plateau = new Plateaux(game);
		
		placernew(i,j,plateau.getPlateau(Liste_Plateaux.LOAD, game.position));
		
		this.addKeyListener(new KeyListener() {
			int nouv_x;
			int nouv_y;
			
			public void keyPressed(KeyEvent event) {
				int keyCode = event.getKeyCode();
				int x_=0;
				int y_=0;
				for(int i2=0;i2<21;i2++) {	
					for(int j2=0;j2<21;j2++) {
						if(game.tableau_bouton[i2][j2].getBackground() == game.bonhome_herbe) {
							game.tableau_bouton[i2][j2].setIcon(game.herbe);
							game.tableau_bouton[i2][j2].setBackground(game.fond_herbe);
							x_memoire=i2;
							y_memoire=j2;
						}
						if(game.tableau_bouton[i2][j2].getBackground() == game.bonhome_beton) {
							game.tableau_bouton[i2][j2].setIcon(game.beton);
							game.tableau_bouton[i2][j2].setBackground(game.fond_beton);
							x_memoire=i2;
							y_memoire=j2;
						}
					}
				}
				
				if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_UP) {
					if (keyCode == KeyEvent.VK_LEFT) {
						y_=-1;
						nouv_x = x_memoire + x_;
						nouv_y = y_memoire + y_;
						ok = true;
					}
					if (keyCode == KeyEvent.VK_RIGHT) {
						y_=1;
						nouv_x = x_memoire + x_;
						nouv_y = y_memoire + y_;
						ok = true;
					}
					if (keyCode == KeyEvent.VK_DOWN) {
							x_=1;
							nouv_x = x_memoire + x_;
							nouv_y = y_memoire + y_;
							ok = true;
					}
					if (keyCode == KeyEvent.VK_UP) {
						x_=-1;
						nouv_x = x_memoire + x_;
						nouv_y = y_memoire + y_;
						ok = true;
					}
				}else {
					if((keyCode == KeyEvent.VK_Z || keyCode == KeyEvent.VK_Q || keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_D) && game.cooldown == 0) {

						game.cooldown = 1;
						
						if(keyCode == KeyEvent.VK_Z)					
							tir(game.HAUT);	
						if(keyCode == KeyEvent.VK_Q)	
							tir(game.GAUCHE);
						if(keyCode == KeyEvent.VK_S)
							tir(game.BAS);						
						if(keyCode == KeyEvent.VK_D)						
							tir(game.DROITE);
						
						debug_men(x_memoire, y_memoire);
						
					}else {
						debug_men(x_memoire, y_memoire);
					}
				}
				
				if(ok) {
				
					Bouton ce_bouton = game.tableau_bouton[nouv_x][nouv_y];
					ok = false;
	
					if(ce_bouton.getBackground() == game.rusheur_b || ce_bouton.getBackground() == game.mur_brique_ || ce_bouton.getBackground() == game.coffre_f || ce_bouton.getBackground() == game.coffre_o || ce_bouton.getBackground() == game.eau_ || ce_bouton.getBackground() == game.lave_ || ce_bouton.getBackground() == game.porte_hc || ce_bouton.getBackground() == game.porte_gc || ce_bouton.getBackground() == game.porte_dc || ce_bouton.getBackground() == game.porte_bc) {
						if(ce_bouton.getBackground() == game.lave_) {
							game.vie = game.vie - 0.5;
							game.label_info.setText("Vie : " + game.vie);
							game.verifMort();
						}
						if(game.tableau_bouton[x_memoire][y_memoire].getBackground() == game.fond_herbe) {
							game.tableau_bouton[x_memoire][y_memoire].setIcon(game.herbe_bonhome);
							game.tableau_bouton[x_memoire][y_memoire].setBackground(game.bonhome_herbe);
						}
						if(game.tableau_bouton[x_memoire][y_memoire].getBackground() == game.fond_beton) {
							game.tableau_bouton[x_memoire][y_memoire].setIcon(game.beton_bonhome);
							game.tableau_bouton[x_memoire][y_memoire].setBackground(game.bonhome_beton);
						}
					}else {
						if(ce_bouton.getBackground() == game.fond_herbe || ce_bouton.getBackground() == game.herbe_p) {
							if(ce_bouton.getBackground() == game.herbe_p) {
								game.vie = game.vie + 0.5;
								game.label_info.setText("Vie : " + game.vie);
							}
							game.tableau_bouton[nouv_x][nouv_y].setIcon(game.herbe_bonhome);
							game.tableau_bouton[nouv_x][nouv_y].setBackground(game.bonhome_herbe);
						}
						if(ce_bouton.getBackground() == game.fond_beton || ce_bouton.getBackground() == game.beton_p) {
							if(ce_bouton.getBackground() == game.beton_p) {
								game.vie = game.vie + 0.5;
								game.label_info.setText("Vie : " + game.vie);
							}
							game.tableau_bouton[nouv_x][nouv_y].setIcon(game.beton_bonhome);
							game.tableau_bouton[nouv_x][nouv_y].setBackground(game.bonhome_beton);
						}
						if(ce_bouton.getBackground() == game.portique) {
							if(game.tableau_bouton[x_memoire][y_memoire].getBackground() == game.fond_beton) {
								if(nouv_x > x_memoire) {
									game.tableau_bouton[nouv_x+1][nouv_y].setIcon(game.herbe_bonhome);
									game.tableau_bouton[nouv_x+1][nouv_y].setBackground(game.bonhome_herbe);
								}else {
									game.tableau_bouton[nouv_x-1][nouv_y].setIcon(game.herbe_bonhome);
									game.tableau_bouton[nouv_x-1][nouv_y].setBackground(game.bonhome_herbe);
								}
							}
							if(game.tableau_bouton[x_memoire][y_memoire].getBackground() == game.fond_herbe) {
								if(nouv_x > x_memoire) {
									game.tableau_bouton[nouv_x+1][nouv_y].setIcon(game.beton_bonhome);
									game.tableau_bouton[nouv_x+1][nouv_y].setBackground(game.bonhome_beton);
								}else {
									game.tableau_bouton[nouv_x-1][nouv_y].setIcon(game.beton_bonhome);
									game.tableau_bouton[nouv_x-1][nouv_y].setBackground(game.bonhome_beton);
								}
							}
						}
						if(ce_bouton.getBackground() == game.porte_h || ce_bouton.getBackground() == game.porte_g || ce_bouton.getBackground() == game.porte_d || ce_bouton.getBackground() == game.porte_b) {
							Color ce_bouton_couleur = ce_bouton.getBackground();
							if((ce_bouton.getBackground() == game.porte_h || ce_bouton.getBackground() == game.porte_g || ce_bouton.getBackground() == game.porte_d) && game.plateau_actuel == Liste_Plateaux.SALLE1) {
								game.compteur++;
								game.score--;
							}
							if(ce_bouton_couleur == game.porte_h)
								game.position = game.BAS;
							if(ce_bouton_couleur == game.porte_g)
								game.position = game.DROITE;
							if(ce_bouton_couleur == game.porte_d)
								game.position = game.GAUCHE;
							if(ce_bouton_couleur == game.porte_b)
								game.position = game.HAUT;
							int[][] new_plateau = plateau.getPlateau(game.plateau_actuel, game.position);
							for(int i=0;i<21;i++) { 	
								for(int j=0;j<21;j++) {
									replace(i, j, new_plateau);
								}
							}
							if(ce_bouton_couleur == game.porte_h)
								replace_men(game.BAS);
							if(ce_bouton_couleur == game.porte_g)
								replace_men(game.DROITE);
							if(ce_bouton_couleur == game.porte_d)
								replace_men(game.GAUCHE);
							if(ce_bouton_couleur == game.porte_b)
								replace_men(game.HAUT);
							game.compteur--;
							game.score++;
						}
					}
				}
			}

			public void keyReleased(KeyEvent event) {
				//Do nothing
			}

			public void keyTyped(KeyEvent event) {
				//Do nothing
			}
		});
		
	}
	
	public void placernew(int i, int j, int tab[][]) {
		
		if(tab[i][j] == 0) {
			this.setIcon(game.herbe);
			this.setBackground(game.fond_herbe);
		}
		if(tab[i][j] == 1) {
			this.setIcon(game.mur_brique);
			this.setBackground(game.mur_brique_);
		}
		if(tab[i][j] == 2) {
			this.setIcon(game.herbe_bonhome);
			this.setBackground(game.bonhome_herbe);
		}
		if(tab[i][j] == 3) {
			this.setIcon(game.porte_haut_close);
			this.setBackground(game.porte_hc);
		}
		if(tab[i][j] == 4) {
			this.setIcon(game.porte_gauche_close);
			this.setBackground(game.porte_gc);
		}
		if(tab[i][j] == 5) {
			this.setIcon(game.porte_droite_close);
			this.setBackground(game.porte_dc);
		}
		if(tab[i][j] == 6) {
			this.setIcon(game.porte_bas_close);
			this.setBackground(game.porte_bc);
		}
		if(tab[i][j] == 7) {
			this.setIcon(game.beton);
			this.setBackground(game.fond_beton);
		}
		if(tab[i][j] == 8) {
			this.setIcon(game.portique_piece);
			this.setBackground(game.portique);
		}
		if(tab[i][j] == 9) {
			this.setIcon(game.coffre_ferme);
			this.setBackground(game.coffre_f);
		}
		if(tab[i][j] == 10) {
			this.setIcon(game.herbe_pomme);
			this.setBackground(game.herbe_p);
		}
		if(tab[i][j] == 11) {
			this.setIcon(game.beton_pomme);
			this.setBackground(game.beton_p);
		}
		if(tab[i][j] == 12) {
			this.setIcon(game.eau);
			this.setBackground(game.eau_);
		}
		if(tab[i][j] == 13) {
			this.setIcon(game.lave);
			this.setBackground(game.lave_);
		}
		if(tab[i][j] == 14) {
			this.setIcon(game.porte_haut);
			this.setBackground(game.porte_h);
		}
		if(tab[i][j] == 15) {
			this.setIcon(game.porte_gauche);
			this.setBackground(game.porte_g);
		}
		if(tab[i][j] == 16) {
			this.setIcon(game.porte_droite);
			this.setBackground(game.porte_d);
		}
		if(tab[i][j] == 17) {
			this.setIcon(game.porte_bas);
			this.setBackground(game.porte_b);
		}
	}
	
	public void replace(int i, int j, int tab[][]) {
		
		if(tab[i][j] == 0) {
			game.tableau_bouton[i][j].setIcon(game.herbe);
			game.tableau_bouton[i][j].setBackground(game.fond_herbe);
		}
		if(tab[i][j] == 1) {
			game.tableau_bouton[i][j].setIcon(game.mur_brique);
			game.tableau_bouton[i][j].setBackground(game.mur_brique_);
		}
		if(tab[i][j] == 3) {
			game.tableau_bouton[i][j].setIcon(game.porte_haut_close);
			game.tableau_bouton[i][j].setBackground(game.porte_hc);
		}
		if(tab[i][j] == 4) {
			game.tableau_bouton[i][j].setIcon(game.porte_gauche_close);
			game.tableau_bouton[i][j].setBackground(game.porte_gc);
		}
		if(tab[i][j] == 5) {
			game.tableau_bouton[i][j].setIcon(game.porte_droite_close);
			game.tableau_bouton[i][j].setBackground(game.porte_dc);
		}
		if(tab[i][j] == 6) {
			game.tableau_bouton[i][j].setIcon(game.porte_bas_close);
			game.tableau_bouton[i][j].setBackground(game.porte_bc);
		}
		if(tab[i][j] == 7) {
			game.tableau_bouton[i][j].setIcon(game.beton);
			game.tableau_bouton[i][j].setBackground(game.fond_beton);
		}
		if(tab[i][j] == 8) {
			game.tableau_bouton[i][j].setIcon(game.portique_piece);
			game.tableau_bouton[i][j].setBackground(game.portique);
		}
		if(tab[i][j] == 9) {
			game.tableau_bouton[i][j].setIcon(game.coffre_ferme);
			game.tableau_bouton[i][j].setBackground(game.coffre_f);
		}
		if(tab[i][j] == 10) {
			game.tableau_bouton[i][j].setIcon(game.herbe_pomme);
			game.tableau_bouton[i][j].setBackground(game.herbe_p);
		}
		if(tab[i][j] == 11) {
			game.tableau_bouton[i][j].setIcon(game.beton_pomme);
			game.tableau_bouton[i][j].setBackground(game.beton_p);
		}
		if(tab[i][j] == 12) {
			game.tableau_bouton[i][j].setIcon(game.eau);
			game.tableau_bouton[i][j].setBackground(game.eau_);
		}
		if(tab[i][j] == 13) {
			game.tableau_bouton[i][j].setIcon(game.lave);
			game.tableau_bouton[i][j].setBackground(game.lave_);
		}
		if(tab[i][j] == 14) {
			game.tableau_bouton[i][j].setIcon(game.porte_haut);
			game.tableau_bouton[i][j].setBackground(game.porte_h);
		}
		if(tab[i][j] == 15) {
			game.tableau_bouton[i][j].setIcon(game.porte_gauche);
			game.tableau_bouton[i][j].setBackground(game.porte_g);
		}
		if(tab[i][j] == 16) {
			game.tableau_bouton[i][j].setIcon(game.porte_droite);
			game.tableau_bouton[i][j].setBackground(game.porte_d);
		}
		if(tab[i][j] == 17) {
			game.tableau_bouton[i][j].setIcon(game.porte_bas);
			game.tableau_bouton[i][j].setBackground(game.porte_b);
		}
		if(tab[i][j] == 18) {
			game.tableau_bouton[i][j].setBackground(game.rusheur_b);
			game.tableau_bouton[i][j].setIcon(game.rusheur);
		}
		if(tab[i][j] == 19) {
			game.tableau_bouton[i][j].setBackground(game.mage_h);
			game.tableau_bouton[i][j].setIcon(game.mage_herbe);
		}
		if(tab[i][j] == 20) {
			game.tableau_bouton[i][j].setBackground(game.mage_b);
			game.tableau_bouton[i][j].setIcon(game.mage_beton);
		}
	}
	
	public void replace_men(int position) {
		if(game.plateau_actuel == Liste_Plateaux.STARTING_ROOM || game.plateau_actuel == Liste_Plateaux.SALLE4 || game.plateau_actuel == Liste_Plateaux.SALLE_TRESOR || game.plateau_actuel == Liste_Plateaux.SALLE_PIEGE || game.plateau_actuel == Liste_Plateaux.SALLE_FIN) {
			if(position == game.GAUCHE) {
				game.tableau_bouton[10][1].setIcon(game.herbe_bonhome);
				game.tableau_bouton[10][1].setBackground(game.bonhome_herbe);
			}
			if(position == game.DROITE) {
				game.tableau_bouton[10][19].setIcon(game.herbe_bonhome);
				game.tableau_bouton[10][19].setBackground(game.bonhome_herbe);
			}
			if(position == game.HAUT) {
				game.tableau_bouton[1][10].setIcon(game.herbe_bonhome);
				game.tableau_bouton[1][10].setBackground(game.bonhome_herbe);
			}
			if(position == game.BAS) {
				game.tableau_bouton[19][10].setIcon(game.herbe_bonhome);
				game.tableau_bouton[19][10].setBackground(game.bonhome_herbe);
			}
		}else {
			if(game.plateau_actuel == Liste_Plateaux.SALLE7) {
				game.tableau_bouton[8][10].setIcon(game.beton_bonhome);
				game.tableau_bouton[8][10].setBackground(game.bonhome_beton);
			}else {
				if(game.plateau_actuel == Liste_Plateaux.SALLE1) {
					game.tableau_bouton[19][10].setIcon(game.herbe);
					game.tableau_bouton[19][10].setBackground(game.fond_herbe);
					if(position == game.GAUCHE) {
						game.tableau_bouton[10][1].setIcon(game.beton_bonhome);
						game.tableau_bouton[10][1].setBackground(game.bonhome_beton);
					}
					if(position == game.DROITE) {
						game.tableau_bouton[10][19].setIcon(game.herbe_bonhome);
						game.tableau_bouton[10][19].setBackground(game.bonhome_herbe);
					}
					if(position == game.HAUT) {
						game.tableau_bouton[1][10].setIcon(game.beton_bonhome);
						game.tableau_bouton[1][10].setBackground(game.bonhome_beton);
					}
					if(position == game.BAS) {
						game.tableau_bouton[19][10].setIcon(game.herbe_bonhome);
						game.tableau_bouton[19][10].setBackground(game.bonhome_herbe);
					}
				}else {
					if(position == game.GAUCHE) {
						game.tableau_bouton[10][1].setIcon(game.beton_bonhome);
						game.tableau_bouton[10][1].setBackground(game.bonhome_beton);
					}
					if(position == game.DROITE) {
						game.tableau_bouton[10][19].setIcon(game.beton_bonhome);
						game.tableau_bouton[10][19].setBackground(game.bonhome_beton);
					}
					if(position == game.HAUT) {
						game.tableau_bouton[1][10].setIcon(game.beton_bonhome);
						game.tableau_bouton[1][10].setBackground(game.bonhome_beton);
					}
					if(position == game.BAS) {
						game.tableau_bouton[19][10].setIcon(game.beton_bonhome);
						game.tableau_bouton[19][10].setBackground(game.bonhome_beton);
					}
				}
			}
		}
	}
	
	public void debug_men(int i, int j){
		if(game.tableau_bouton[i][j].getBackground() == game.fond_herbe) {
			game.tableau_bouton[i][j].setIcon(game.herbe_bonhome);
			game.tableau_bouton[i][j].setBackground(game.bonhome_herbe);
		}
		if(game.tableau_bouton[i][j].getBackground() == game.fond_beton) {
			game.tableau_bouton[i][j].setIcon(game.beton_bonhome);
			game.tableau_bouton[i][j].setBackground(game.bonhome_beton);
		}
	}
	
	public void tir(int direction) {
		Timer timer;
		timer = new Timer();
		timer.schedule(new Tir(game, game.player, direction), 0, 100);
	}
}
