package pong;

import java.awt.Graphics;
import java.awt.Color;

public class Player {
	
	//aqui vai definir o que o ator ou classe pode fazer 
	public boolean right,left;
	public int x,y;
	public int width,height;
	
	public Player (int x, int y) {
		this.x =x;
		this.y =y;
		this.width =40;
		this.height =10;
	}

	  //logica da movimentação. o personagem se move prea +x ou -x
	public void tick() {
		if (right){
			x++;
		}
		else if (left) {
			x--;
			
		}
		if (x+width> Game.WIDTH) {
			x = Game. WIDTH - width;
		}
		else if (x< 0) {
				x=0;
		}
	}
	//renderizar o jogador
	public void render(Graphics g) {
		// cor do personagem
		g.setColor(Color.blue);
		//posição do personagem
		g.fillRect(x,y,width,height);
		
	}
}
