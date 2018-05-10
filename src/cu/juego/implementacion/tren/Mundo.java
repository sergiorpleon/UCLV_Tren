package cu.juego.implementacion.tren;

import java.util.Random;

import android.util.Log;
import android.widget.Toast;

public class Mundo {
static final int MUNDO_ANCHO = 10;
static final int MUNDO_ALTO = 13;
static final int INCREMENTO_PUNTUACION = 10;
static final float TICK_INICIAL = 0.5f;
static final float TICK_DECREMENTO = 0.05f;

public Locomotora locomotora;
public Bagon bagon;
public boolean finalJuego = false;
public int puntuacion = 0;

boolean[][]  campo = new boolean[MUNDO_ANCHO][MUNDO_ALTO];
Random random = new Random();
float tiempoTick = 0;
static float tick = TICK_INICIAL;

public Mundo(){
	locomotora = new Locomotora();
	tiempoTick = 0;
	tick = TICK_INICIAL;
	colocarBotin();
}

private void colocarBotin(){
	for (int x = 0; x< MUNDO_ANCHO; x++) {
		for (int y = 0; y < MUNDO_ALTO; y++) {
			campo[x][y] = false;
		}
	}
	
	int len = locomotora.partes.size();
	for (int i = 0; i < len; i++) {
		Tripulacion parte = locomotora.partes.get(i);
		//Log.d("ver","" + parte.x + " _ "+parte.y);
		campo[parte.x][parte.y] = true;
	}
	
	int botinX = random.nextInt(MUNDO_ANCHO);
	int botinY = random.nextInt(MUNDO_ALTO);
	while(true){
		if(campo[botinX][botinY]==false){
			//Log.d("Entro", "Botin OK");
			break;
		}
		botinX +=1;
		if(botinX >= MUNDO_ANCHO ){
			botinX = 0;
			botinY +=1;
			if(botinY >= MUNDO_ALTO ){
				botinY = 0;
			}
		}
	}
	bagon = new Bagon(botinX, botinY, random.nextInt(3));
	//Log.d("Entro", "BOT INI " + botin.x + " _ " + botin.y);
}

public void update(float deltaTime){
	if(finalJuego){
		return ;
	}
	
	tiempoTick += deltaTime;
	
	while(tiempoTick > tick){
		
		tiempoTick-=tick;
		locomotora.avance();
		if(locomotora.comprobarChoque()){
			//Log.d("Entro", "Choque BOOM");
			finalJuego = true;
			return ;
		}
		
		//Log.d("Entro", "BOT " + botin.x + " _ " + botin.y);
		
		Tripulacion head = locomotora.partes.get(0);
		if(head.x == bagon.x && head.y == bagon.y){
			//Log.d("Entro", "Botin facho");
			puntuacion+=INCREMENTO_PUNTUACION;
			locomotora.abordaje();
			if(locomotora.partes.size() == MUNDO_ANCHO*MUNDO_ALTO){
				finalJuego =true;
				return;
			}else{
				colocarBotin();
			}
			
			if(puntuacion % 100 == 0 && tick - TICK_DECREMENTO > 0 ){
				tick -= TICK_DECREMENTO;
			}
		}
		
		
	}
			
			
			
}




















}
