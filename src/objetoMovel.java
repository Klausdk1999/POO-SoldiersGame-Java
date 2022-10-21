
public class objetoMovel {
	private float energia;
	private int locX;
	private int locY;
	private float resistencia;
	private float dano;
	
	public objetoMovel(float energia, int locX, int locY, float resistencia, float dano) {
		super();
		this.energia = energia;
		this.locX = locX;
		this.locY = locY;
		this.resistencia = resistencia;
		this.dano = dano;
	}
	
	public float getEnergia() {
		return energia;
	}
	public void setEnergia(float energia) {
		this.energia = energia;
	}
	public int getLocX() {
		return locX;
	}
	public void setLocX(int locX) {
		this.locX = locX;
	}
	public int getLocY() {
		return locY;
	}
	public void setLocY(int locY) {
		this.locY = locY;
	}
	public float getResistencia() {
		return resistencia;
	}
	public void setResistencia(int resistencia) {
		this.resistencia = resistencia;
	}

	public float getDano() {
		return dano;
	}

	public void setDano(float dano) {
		this.dano = dano;
	}
}
