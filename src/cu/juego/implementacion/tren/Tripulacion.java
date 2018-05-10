package cu.juego.implementacion.tren;

public class Tripulacion {
	public static int HORIZONTAL=0;
	public static int VERITCAL=1;
	public int x, y, direccion;
	
	public Tripulacion(int x, int y, int direccion){
		this.x = x;
		this.y = y;
		this.direccion = direccion;
	}
	
}
