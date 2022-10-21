
public class objetoFixo {
	private int locX;
	private int locY;
	private float resistencia;
	private String path;
	
	public objetoFixo(int locX, int locY, float resistencia, String path) {
		super();
		this.locX = locX;
		this.locY = locY;
		this.resistencia = resistencia;
		this.path = path;
	}
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
}
