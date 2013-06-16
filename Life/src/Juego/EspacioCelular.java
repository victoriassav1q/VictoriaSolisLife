package Juego;


import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;



public class EspacioCelular {
	private Celula [][] poblacion;
	private int filas;
	private int columnas;
	

	private static int tama�oCelda=10;
	
	
	public EspacioCelular(int filas, int columnas) {
		/* Comentarios y explicaci�n: 
		 * Este m�todo crea un espacio celular vac�o (poblado solo por c�lulas muertas) en el que se desarrollar�
		 * nuestro programa.
		 */
		poblacion = new Celula[this.filas=filas][this.columnas=columnas];
		for (int i=0;i<filas;i++){
			for(int j=0;j<columnas;j++){
			
			poblacion[i][j]=Celula.MUERTA;
			}
		}
	}
	

	
	public void paint(Graphics g, Color c){
		/* Comentarios y explicaci�n: 
		 * el m�todo paint nos permitir� dibujar las c�lulas en el tablero, y seg�n los atributos que le
		 * pongamos, cambiar� cosas como el color de las c�lulas vivas (que en mi caso es verde).
		 */
		g.setColor(c);
	for(int i=0;i<filas;i++){
		for(int j=0;j<columnas; j++){
	if(poblacion[i][j]==Celula.VIVA){
		g.setColor(Color.GREEN);
		
		g.fillRect( j*tama�oCelda,i*tama�oCelda,tama�oCelda,tama�oCelda );
	}
}
	}
		
		
		
		for(int i=0;i<filas;i++){
		
			g.setColor(c);
			
			
			g.drawLine(i, i * tama�oCelda, filas * tama�oCelda, i * tama�oCelda);
			g.drawLine(i * tama�oCelda, 0, i * tama�oCelda, columnas
					* tama�oCelda);

			g.drawLine(0, i * tama�oCelda, columnas * tama�oCelda, i
					* tama�oCelda);
			g.drawLine(i * tama�oCelda, 0, i * tama�oCelda, filas * tama�oCelda);		
	}
	}	
		
	public void setViva(int corx,int cory){
		
		poblacion[cory/tama�oCelda][corx/tama�oCelda]=Celula.VIVA;
		
	}
	
	public void setCelulasAleatorias(){
		/* Comentarios y explicaci�n: 
		 * Este m�todo se encargar� de generar 2000 c�lulas distribuidas aleatoriamente en el tablero.
		 */
		
		  int cont = 0;
		  Random r = new Random();
		  for (int i = 0; i < filas; i++) {
		   for (int j = 0; j < columnas; j++) {
		    if (cont > 2000)
		     break;

		    if (r.nextBoolean() && poblacion[i][j] == Celula.MUERTA) {
		     poblacion[i][j] = Celula.EMBRION;
		     cont++;
		    }

		   }

		  }

		  for (int i = 0; i < filas; i++) {
		   for (int j = 0; j < columnas; j++) {
		    if (poblacion[i][j] == Celula.EMBRION)
		     poblacion[i][j] = Celula.VIVA;
		   }
		  }
		 }
	public void setLimpiar(){
		/* Comentarios y explicaci�n: 
		 * La �nica funci�n de este m�todo es la de matar a TODAS las
		 * c�lulas que hay en el tablero medianet la sentencia "poblacion[i][j]=Celula.MUERTA;", que recorre
		 * todo el array con dos bucles for cambiando el estado de todas las c�lulas a "MUERTA"
		 */
		for(int i=0;i<filas;i++){
		for (int j=0;j<columnas;j++){
			poblacion[i][j]=Celula.MUERTA;
		}
		}
	}
	
	

	public void sgteGeneracion() {
		/*Comentarios y explicaci�n:
		 * El m�todo "Siguiente Generaci�n" es el que permite que, al presionar el bot�n "Siguiente
		 * Generaci�n" en la ventana ejecutable, las c�lulas que est�n pintadas (ya sea por nosotros o
		 * generadas aleatoriamente) avancen una generaci�n, muriendo y naciendo seg�n las reglas estipuladas
		 * por el problema, que son:
		 * - Si una c�lula viva tiene menos de dos c�lulas a su alrededor, morir� de soledad.
		 * - Si una c�lula viva tiene m�s de cuatro c�lulas a su alrededor, morir� por superpoblaci�n.
		 * - Si una c�lula muerta (en blanco) tiene tres c�lulas vivas a su alrededor, en la siguiente
		 * generaci�n nacer�.
		 */
// Primera pasada: A partir de las c�lulas vivas y muertas, determinamos cu�les nacer�n y morir�n (embriones y moribundas).
	int vecinas;
	
		for (int f=0; f<filas; f++){
		for (int c=0; c<columnas; c++) {
//Recorremos las celdas del tablero (del array bidimensional) mediante dos bucles for.
//Estos bucles de arriba �NICAMENTE sirven para determinar la posici�n de la c�lula indicada.
			vecinas=0;
			
			for (int i=f-1; i<=f+1;i++){
				for (int j=c-1;j<=c+1;j++){
//Con estos otros dos bucles for, determinamos cu�les son las c�lulas vecinas que rodean a otra c�lula.
					try{
					
					if ((i!=f || j!=c) && (poblacion[i][j]==Celula.VIVA || poblacion[i][j]==Celula.MORIBUNDA )){
					vecinas++;
					}
//Mediante un if, nos aseguramos de excluir a la c�lula central de sus "vecinas".
					
					}catch(IndexOutOfBoundsException e){};
				}
				}
					if (poblacion[f][c]==Celula.VIVA && (vecinas <=1 || vecinas >= 4)) {
			            poblacion[f][c]=Celula.MORIBUNDA;
			        }
			       
					if (poblacion[f][c]==Celula.MUERTA && vecinas == 3) {
			        	poblacion[f][c]=Celula.EMBRION;
			        }
//Ahora, gracias a otros dos if establecemos las condiciones para que unas c�lulas vivan y otras mueran, ya
//citadas arriba y en el enunciado del problema.
			        
			}
		}
		
//Segunda pasada: Ahora que sabemos qu� c�lulas est�n moribundas y cuales van a nacer, las matamos y las creamos.
		
		for (int f=0; f<filas; f++){
			for (int c=0; c<columnas; c++) {
			
				if (poblacion[f][c]==Celula.EMBRION) {
		            poblacion[f][c]=Celula.VIVA;
		        }
		       
				else if (poblacion[f][c]==Celula.MORIBUNDA) {
		        	poblacion[f][c]=Celula.MUERTA;
		        }

//Todas las c�lulas que iban a nacer nacen, y las moribundas desaparecen.
}
}}}


	