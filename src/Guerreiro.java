import java.util.Random;

public class Guerreiro extends objetoMovel implements acoes{
	private int ultimaDir = 0;
	private int ultimaSent = 0;
	private int id;

	public Guerreiro(float energia, int locX, int locY, float resistencia, float dano, int id) {
		super(energia, locX, locY, resistencia, dano);
		this.id = id;
	}

	@Override
	public void atualizaPosicao() {
		Random rand = new Random();
		int decisao = rand.nextInt(2);
		this.ultimaDir = decisao;
		int randNext = rand.nextInt(2);
		this.ultimaSent = randNext;

		if (decisao == 0) {
			if (randNext == 0) {
				this.setLocX(this.getLocX()+1); 
			} else {
				this.setLocX(this.getLocX()-1);
			}
		} else {
			if (randNext == 0) {
				this.setLocY(this.getLocY()+1); 
			} else {
				this.setLocY(this.getLocY()-1);
			}
		}
	}

	public Projetil disparo() {
		Projetil p = new Projetil((float) 0.1, this.getLocX(), this.getLocY(), (float) 0.1, (float) 40.0, this.ultimaDir, this.ultimaSent,id);
		return p;
	}

	public void colisaoGuerreiro(Guerreiro g) {
		float auxEnergia;
		auxEnergia = this.getEnergia()-g.getDano()+this.getResistencia();
		this.setEnergia(auxEnergia);
	}
	
	public void colisaoProjetil(Projetil p) {
		float auxEnergia;
		auxEnergia = this.getEnergia()-p.getDano()+this.getResistencia();
		this.setEnergia(auxEnergia);
	}
	
	public void colisaoAmbiente(objetoFixo a) {
		float auxEnergia;
		auxEnergia = this.getEnergia()-(1+a.getResistencia());
		this.setEnergia(auxEnergia);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
