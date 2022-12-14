package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


// ruunable pra rodar aplica??o, keylistener para adicionar as teclas
public class Game extends Canvas implements Runnable,KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	//tamano da janela 
	public static int WIDTH=160;
	public static int HEIGHT=121;
	public static int SCALE=3;
	
	//todos os atores/classes s?o chamados aqui pela palavra PUBLIC para instancia-los
	public BufferedImage layer =new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);	
	public static Player player;
	public static Inimigo inimigo;
	public  static Bola bola;
	
	public Game () {
		//tamanho da tela
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
	//teclas
		this.addKeyListener(this);
		//aqui vai criar e posicionar o jogador depois de instanciado
	player = new Player(100,HEIGHT-10);
	inimigo = new Inimigo (100,0);
	bola =new Bola (100,HEIGHT/2 -1 );
	}

	public static void main(String[] args) {
		Game game = new Game();
	//JFrame ? para criar uma nova janela
	JFrame frame = new JFrame("pong");
	// o usuario n?o vai poder redimensionar a tela
	frame.setResizable(false);
	//vai fechar a aplica??o ap?s fechar a janela
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(game);
	frame.pack();
	//posi??o da tela 
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	
	new Thread (game).start();
	} 
	
	//chamar a logica/classe criada no thick 
	public void tick () {
		player.tick();
		inimigo.tick();
		bola.tick();
		}
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs==null) {
			this.createBufferStrategy(3);
			return;  
		}
		
		//para mostrar e renderizar
		Graphics g= layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH,HEIGHT);
		// classe +.render vai mostrar na tela
		player.render(g);
		inimigo.render(g);
		bola.render(g);
		//bs.show  vai mostrar o jogador
		
		g= bs.getDrawGraphics();
		g.drawImage(layer,0,0,WIDTH*SCALE,HEIGHT*SCALE,null);
		bs.show(); 
	}
	// aqui eu vou por um Wilhe pra renderizar em loop
	public void run() {
		while(true){
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void keyPressed(KeyEvent e) {	
	  if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
		  player.right = true;
	  }
	  else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		  player.left = true;
	  }
	}
	public void keyReleased(KeyEvent e) {	
		  if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			  player.right =false;
		  }
		  else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			  player.left = false;
		  }
		}

}
