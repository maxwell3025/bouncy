package polys;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class test extends JFrame implements Runnable {
	BufferedImage ufscreen = new BufferedImage(1080, 720, BufferedImage.TYPE_INT_ARGB);
	Graphics2D graphics = ufscreen.createGraphics();
	BufferedImage fscreen = new BufferedImage(1080, 720, BufferedImage.TYPE_INT_ARGB);
	int threadtype;
	Vertex[] vertexes = new Vertex[2];
	public test() {
		vertexes[0] = new Vertex(new point(400,100));
		vertexes[1] = new Vertex(new point(550,100));
		for(int x = 0;x<vertexes.length; x++){
			for(int y = 0;y<vertexes.length; y++){
				vertexes[x].addrod(new rod(y,100));
			}
		}
		setSize(1080, 720);
		setVisible(true);
	} 

	public static void main(String[] args) {
		test t = new test();
		new Thread(t).start();
		new Thread(t).start();
	}

	public synchronized void paint(Graphics g) {
		g.drawImage(fscreen, 0, 0, 1080, 720, null);
	}

	public synchronized void graphicsupdate() {
		graphics.clearRect(0,0, 1080, 720);
		graphics.drawLine((int)vertexes[0].position.x,(int)vertexes[0].position.y,(int)vertexes[1].position.x,(int)vertexes[1].position.y);
	}

	public void run() {
		threadtype++;
		if (threadtype == 1) {
			while (true) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
				graphicsupdate();
				fscreen = ufscreen;
				repaint();
			}
		}
		if (threadtype == 2) {
			while (true) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
				for(int i = 0;i<vertexes.length;i++){
					vertexes[i].updatevel(vertexes);
				}
				for(int i = 0;i<vertexes.length;i++){
					vertexes[i].updatepos();
				}
			}
		}

	}

}
