import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;

public class MyPanel extends JPanel{
	
	private List<objetoFixo> desenhaFixo= new LinkedList<objetoFixo>();
	private List<Guerreiro> desenhaGuerreiro= new LinkedList<Guerreiro>();
	private List<Projetil> desenhaProjetil= new LinkedList<Projetil>();

	public void addObjetoFixo(objetoFixo f) {
		desenhaFixo.add(f);
	}
	
	public void addGuerreiro(Guerreiro g) {
		desenhaGuerreiro.add(g);
	}
	
	public void addProjetil(Projetil p) {
		desenhaProjetil.add(p);
	}

	public List<objetoFixo> getDesenhaFixo(){
		return this.desenhaFixo;
	}
	
	public List<Guerreiro> getDesenhaGuerreiro(){
		return this.desenhaGuerreiro;
	}
	
	public List<Projetil> getDesenhaProjetil(){
		return this.desenhaProjetil;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for (objetoFixo f: desenhaFixo) {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(f.getPath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (img==null) {
				g.drawRect(f.getLocX(), f.getLocY(), 100, 100);
			}else {
				g.drawImage(img, f.getLocX(), f.getLocY(), 100, 100,null);
			}
		}
		
		for (Guerreiro gu: desenhaGuerreiro) {
			g.drawString(Float.toString(gu.getEnergia()), gu.getLocX(), gu.getLocY());
			g.drawRect(gu.getLocX(), gu.getLocY(), 20, 20);
		}
		
		for (Projetil p: desenhaProjetil) {
			g.drawRect(p.getLocX(), p.getLocY(), 2,2);
		}
		
		g.drawLine(0, 700, 1200, 700);
		g.drawLine(0, 20, 1200, 20);
		g.drawLine(20, 0, 20,768);
		g.drawLine(1120, 0, 1120,768);

	}
}
