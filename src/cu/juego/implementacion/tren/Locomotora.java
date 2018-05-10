package cu.juego.implementacion.tren;

import java.util.ArrayList;
import java.util.List;

public class Locomotora {
	public static int ARRIBA=0;
	public static int IZQUIERDA=1;
	public static int ABAJO=2;
	public static int DERECHA=3;
	
	public List<Tripulacion> partes = new ArrayList<Tripulacion>();
	public int direccion;
	
	public Locomotora(){
		direccion = ARRIBA;
		partes.add(new Tripulacion(5, 6, Tripulacion.VERITCAL ));
		partes.add(new Tripulacion(5, 7, Tripulacion.VERITCAL));
		partes.add(new Tripulacion(5, 8, Tripulacion.VERITCAL));
	}
	
	public void girarIzquierda(){
		direccion += 1;
		if(direccion > DERECHA){
			direccion = ARRIBA;
		}
	}
	
	public void girarDerecha(){
		direccion -= 1;
		if(direccion < ARRIBA){
			direccion = DERECHA;
		}
	}
	
	public void abordaje(){
		Tripulacion end = partes.get(partes.size()-1);
		partes.add(new Tripulacion(end.x, end.y, end.direccion));
	}
	
	public void avance(){
		Tripulacion barco = partes.get(0);
		
		int len = partes.size()-1;
		for (int i = len; i > 0; i--) {
			Tripulacion ante = partes.get(i-1);
			Tripulacion parte = partes.get(i);
			parte.x = ante.x;
			parte.y = ante.y;
			parte.direccion = ante.direccion;
		}
			if(direccion == ARRIBA){
				barco.y-=1;
				barco.direccion = Tripulacion.VERITCAL;
			}
			if(direccion == IZQUIERDA){
				barco.x-=1;
				barco.direccion = Tripulacion.HORIZONTAL;
			}
			if(direccion == ABAJO){
				barco.y+=1;
				barco.direccion = Tripulacion.VERITCAL;
			}
			if(direccion == DERECHA){
				barco.x+=1;
				barco.direccion = Tripulacion.HORIZONTAL;
			}
			
			
			if(barco.x < 0){
				barco.x=9;
			}
			if(barco.x > 9){
				barco.x=0;
			}
			if(barco.y < 0){
				barco.y=12;
			}
			if(barco.y > 12){
				barco.y=0;
			}
		
		
		
	}
	
	public boolean comprobarChoque(){
		int len = partes.size();
		Tripulacion barco = partes.get(0);
		for (int i = 1; i < len; i++) {
			Tripulacion parte = partes.get(i);
			if(parte.x == barco.x && parte.y == barco.y){
				return true;
			}
		}
		return false;
	}
	
	
}
