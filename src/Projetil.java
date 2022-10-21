
public class Projetil extends objetoMovel implements acoes{
	private int direcao;
	private int sentido;
	private int dono;

	public Projetil(float energia, int locX, int locY, float resistencia, float dano, int direcao, int sentido,
			int dono) {
		super(energia, locX+10, locY+10, resistencia, dano);
		this.direcao = direcao;
		this.sentido = sentido;
		this.dono = dono;
	}

	public int getDono() {
		return dono;
	}

	public void setDono(int dono) {
		this.dono = dono;
	}

	@Override
	public void atualizaPosicao() {
		if (this.direcao==0) {
			if(this.sentido==0) {
				this.setLocX(this.getLocX()+1);
			}else {
				this.setLocX(this.getLocX()-1);
			}
		}else {
			if(this.sentido==0) {
				this.setLocY(this.getLocY()+1);
			}else {
				this.setLocY(this.getLocY()-1);
			}
		}
		
	}
}
