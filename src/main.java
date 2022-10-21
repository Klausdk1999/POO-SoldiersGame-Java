import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.awt.Rectangle;
import javax.swing.*;

//Bruno Mafra Pelence 17104215
//Klaus Dieter Kupper 17102435
public class main {
	//Politica de dano utilizada
	//Projetil = 40
	//Guerreiro = 20
	//Ambiente = 1 (Deixamos o guerreiro entrar no ambiente)
	
	//Resistencia
	//Guerreiro = 10
	//Projetil = Atingiu algo -> destruido	
	public static void main(String[] args) throws InterruptedException {
		JFrame janela = new JFrame();
		janela.setSize(1200,768);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MyPanel panel = new MyPanel();
		janela.setContentPane(panel);
		janela.setVisible(true);
		
		objetoFixo ambiente1 = new objetoFixo(50,50,(float)1.0,"src/Imagens/russia.jpg");
		panel.addObjetoFixo(ambiente1);
		objetoFixo ambiente2 = new objetoFixo(350,450,(float)1.0,"src/Imagens/ucrania.png");
		panel.addObjetoFixo(ambiente2);
		objetoFixo ambiente3 = new objetoFixo(850,150,(float)1.0,"src/Imagens/eua.png");
		panel.addObjetoFixo(ambiente3);
		objetoFixo ambiente4 = new objetoFixo(850,450,(float)1.0,"src/Imagens/china.jpg");
		panel.addObjetoFixo(ambiente4);
		
		int certo = 0;
		int randX = 0;
		int randY = 0;
		//criar guerreiros
		for (int n = 0; n<100; n++) {
			certo = 0;
			randX = 0;
			randY = 0;
			Random rand = new Random();
			
			//corrigir para nao nascer onde ja tem ambiente
			while (certo < 4) {
				certo = 0;
				randX = rand.nextInt(1120);
				randY = rand.nextInt(700);
				//20 é o tamanho definido para o retangulo de guerreiros
				Rectangle auxRec = new Rectangle(randX,randY, 20, 20);
				
				for (objetoFixo o1: panel.getDesenhaFixo()) {
					//100 é o tamanho definido para o retangulo de ambientes
					Rectangle auxRec1 = new Rectangle (o1.getLocX(), o1.getLocY(),100,100);
					Rectangle intersec1 = auxRec1.intersection(auxRec);
					
					if ((intersec1.getWidth() < 0.0)||(intersec1.getHeight() < 0.0)) {
						certo++;
					}
				}
			}
				
			Guerreiro g = new Guerreiro((float) 100.0, randX, randY, (float) 10.0, (float) 20.0,n);
			panel.addGuerreiro(g);
		}
		
		//Atualização da tela
		int cont = 0;
		while(true) {	
			// Fazer movimentação dos guerreiros
			for (Guerreiro g : panel.getDesenhaGuerreiro()) {
				do {
					g.atualizaPosicao();
				}while ((g.getLocX()<20)||(g.getLocX()>1120)||(g.getLocY()<20)||(g.getLocY()>700));
				
				//Pode entrar no ambiente mas vai tomar dano
				Rectangle auxRec10 = new Rectangle(g.getLocX(),g.getLocY(), 20, 20);
				for (objetoFixo o1: panel.getDesenhaFixo()) {
					Rectangle auxRec11 = new Rectangle (o1.getLocX(), o1.getLocY(),100,100);
					Rectangle intersec2 = auxRec10.intersection(auxRec11);
					if ((intersec2.getWidth() > 0.0)&&(intersec2.getHeight() > 0.0)) {
						g.colisaoAmbiente(o1);
					}
				}
			}
			
			//criar 1 projetil para cada guerreiro a cada 50 atualizacoes
			List<Projetil> auxProjetilAdd= new LinkedList<Projetil>();
			for (Guerreiro g : panel.getDesenhaGuerreiro()) {
				if (cont%50 == 0) {
					Projetil p = g.disparo();
					auxProjetilAdd.add(p);
				}
			}
			panel.getDesenhaProjetil().addAll(auxProjetilAdd);
			
			List<Projetil> auxProjetilFora= new LinkedList<Projetil>();
			//atualiza posicao dos projeteis
			for(Projetil n : panel.getDesenhaProjetil()) {
				n.atualizaPosicao();
				//verificar se chegou ao fim da tela
				if((n.getLocX()>=1120)||(n.getLocY()>=700)||(n.getLocX()<20)||(n.getLocY()<20)) {
					auxProjetilFora.add(n);
				}
				//verificar se bateu em objeto fixo
				Rectangle auxRec4 = new Rectangle(n.getLocX(),n.getLocY(), 2, 2);
				for (objetoFixo o1: panel.getDesenhaFixo()) {
					Rectangle auxRec5 = new Rectangle (o1.getLocX(), o1.getLocY(),100,100);
					Rectangle intersec2 = auxRec5.intersection(auxRec4);
					if ((intersec2.getWidth() > 0.0)&&(intersec2.getHeight() > 0.0)) {
						auxProjetilFora.add(n);
						break;
					}
				}
			}
			//retira da lista de desenho de projeteis
			panel.getDesenhaProjetil().removeAll(auxProjetilFora);
			
			//verificar colisao objeto com projetil
			List<Projetil> auxProjetilUtilizado= new LinkedList<Projetil>();
			for (Guerreiro g : panel.getDesenhaGuerreiro()) {
				Rectangle auxRec6 = new Rectangle(g.getLocX(),g.getLocY(), 20, 20);
				for(Projetil n : panel.getDesenhaProjetil()) {
					//verifica se o tiro esta saindo do proprio guerreiro
					if(n.getDono()!=g.getId()){
						Rectangle auxRec7 = new Rectangle (n.getLocX(), n.getLocY(),2,2);
						Rectangle intersec3 = auxRec6.intersection(auxRec7);
	
						if ((intersec3.getWidth() > 0.0)&&(intersec3.getHeight() > 0.0)) {
							g.colisaoProjetil(n);
							//Lista auxiliar para tirar o projetil
							auxProjetilUtilizado.add(n);
						}
					}
				}
			}
			//retira da lista de desenho de projeteis
			panel.getDesenhaProjetil().removeAll(auxProjetilUtilizado);
			
			//verificar colisao objeto com objeto
			for (Guerreiro g1 : panel.getDesenhaGuerreiro()) {
				Rectangle auxRec8 = new Rectangle(g1.getLocX(),g1.getLocY(), 20, 20);
				for(Guerreiro g2 : panel.getDesenhaGuerreiro()) {
					//verifica se o tiro esta saindo do proprio guerreiro
					if(g1.getId()!=g2.getId()){
						Rectangle auxRec9 = new Rectangle (g2.getLocX(), g2.getLocY(),20,20);
						Rectangle intersec4 = auxRec8.intersection(auxRec9);
	
						if ((intersec4.getWidth() > 0.0)&&(intersec4.getHeight() > 0.0)) {
							g1.colisaoGuerreiro(g2);
						}
					}
				}
			}
			
			//verificar vida de cada objeto e tirar da lista se morreu
			List<Guerreiro> auxMortos= new LinkedList<Guerreiro>();
			for (Guerreiro g : panel.getDesenhaGuerreiro()) {
				if(g.getEnergia()<=0.0) {
					auxMortos.add(g);
				}
			}
			panel.getDesenhaGuerreiro().removeAll(auxMortos);
			
			// redesenhar
			panel.repaint();

			Thread.sleep(10);
			cont++;
		}
		
	}

}
