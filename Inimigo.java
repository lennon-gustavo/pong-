package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Inimigo {
	
	
	
	//bot vai ter IA a velocidade pode alternar
	public double x,y;
	public int width,height;
	//metotodo de constru��o??
	public Inimigo (int x,int y) {
		this.x = x;
		this.y = y;
		this.width =40;
		this.height =10;
	}

	//tick � a logica
	public void tick () {
		x+= (Game.bola.x-x -5)* 0.06;

		
		
	}
	//renderiza��o do game
	public void render (Graphics g ) {
		g.setColor(Color.red);
		//filRect vai estar com erro, a posi��o precisa ter valor inteiro, adicionar um int no x e y
		g.fillRect((int)x,(int)y,width,height);
	}
}
