package pong;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.Random;

public class Bola {
	
	public double x,y;
	public int width,height;
	//a bola vai precisar de uma direção constante
	public double dx,dy;
	public double speed=1.2;
	
	public Bola (int x,int y) {
		this.x = x;
		this.y = y;
		this.width =3;
		this.height =3;
		
		//a bola vai variar de angulo inicial baseano no Ceno e Coceno
		int angle = new Random().nextInt(120-45)+45;
		dx = Math.cos(Math.toRadians(angle));
		dy =  Math.sin(Math.toRadians(angle));
		
	}
// logica para a bola se mover
	public void tick () {
		if (x+(dx*speed)+width >= Game.WIDTH) {
			dx*=-1; 
		} else if (x+(dx*speed) < 0) {
			dx*=-1;
		}
		if(y >= Game.HEIGHT)
{	//ponto do inimigo
			System.out.println("+1");
			new Game();
			return;
		}else if (y	<0) {
			//ponto do jogador
			System.out.println("+1");
			new Game();
			return;
		} 
			
		//Rectange adiciona colisão 
		Rectangle bounds = new Rectangle ((int)(x+(dx*speed)),(int)(y+(dy*speed)),width,height);
		
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle boundsInimigo = new Rectangle((int)Game.inimigo.x,(int)Game.inimigo.y,Game.inimigo.width,Game.inimigo.height);
		
		if(bounds.intersects(boundsPlayer)) {	
			//vai mudar a posição da bola
			int angle = new Random().nextInt(120-45)+45+1;
			dx = Math.cos(Math.toRadians(angle));
			dy =  Math.sin(Math.toRadians(angle));
			if (dy>0)
			dy*= -1;
		} else if (bounds.intersects(boundsInimigo)) {
			int angle = new Random().nextInt(120-45)+45+1;
			dx = Math.cos(Math.toRadians(angle));
			dy =  Math.sin(Math.toRadians(angle));
			if (dy>1)
			dy*= -1;	
		}
		x+=dx*speed;
		y+=dy*speed;
	}
	public void render (Graphics g ) {
		g.setColor(Color.orange);
		g.fillRect((int)x,(int)y,width,height);
	}
	}
