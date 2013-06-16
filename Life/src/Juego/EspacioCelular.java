package Juego;


import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;



public class EspacioCelular {
	private Celula [][] poblacion;
	private int filas;
	private int columnas;
	

	private static int tamañoCelda=10;
	
	
	public EspacioCelular(int filas, int columnas) {
		/* Comentarios y explicación: 
		 * Este método crea un espacio celular vacío (poblado solo por células muertas) en el que se desarrollará
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
		/* Comentarios y explicación: 
		 * el método paint nos permitirá dibujar las células en el tablero, y según los atributos que le
		 * pongamos, cambiará cosas como el color de las células vivas (que en mi caso es verde).
		 */
		g.setColor(c);
	for(int i=0;i<filas;i++){
		for(int j=0;j<columnas; j++){
	if(poblacion[i][j]==Celula.VIVA){
		g.setColor(Color.GREEN);
		
		g.fillRect( j*tamañoCelda,i*tamañoCelda,tamañoCelda,tamañoCelda );
	}
}
	}
		
		
		
		for(int i=0;i<filas;i++){
		
			g.setColor(c);
			
			
			g.drawLine(i, i * tamañoCelda, filas * tamañoCelda, i * tamañoCelda);
			g.drawLine(i * tamañoCelda, 0, i * tamañoCelda, columnas
					* tamañoCelda);

			g.drawLine(0, i * tamañoCelda, columnas * tamañoCelda, i
					* tamañoCelda);
			g.drawLine(i * tamañoCelda, 0, i * tamañoCelda, filas * tamañoCelda);		
	}
	}	
		
	public void setViva(int corx,int cory){
		
		poblacion[cory/tamañoCelda][corx/tamañoCelda]=Celula.VIVA;
		
	}
	
	public void setCelulasAleatorias(){
		/* Comentarios y explicación: 
		 * Este método se encargará de generar 2000 células distribuidas aleatoriamente en el tablero.
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
		/* Comentarios y explicación: 
		 * La única función de este método es la de matar a TODAS las
		 * células que hay en el tablero medianet la sentencia "poblacion[i][j]=Celula.MUERTA;", que recorre
		 * todo el array con dos bucles for cambiando el estado de todas las células a "MUERTA"
		 */
		for(int i=0;i<filas;i++){
		for (int j=0;j<columnas;j++){
			poblacion[i][j]=Celula.MUERTA;
		}
		}
	}
	
	

	public void sgteGeneracion() {
		/*Comentarios y explicación:
		 * El método "Siguiente Generación" es el que permite que, al presionar el botón "Siguiente
		 * Generación" en la ventana ejecutable, las células que están pintadas (ya sea por nosotros o
		 * generadas aleatoriamente) avancen una generación, muriendo y naciendo según las reglas estipuladas
		 * por el problema, que son:
		 * - Si una célula viva tiene menos de dos células a su alrededor, morirá de soledad.
		 * - Si una célula viva tiene más de cuatro células a su alrededor, morirá por superpoblación.
		 * - Si una célula muerta (en blanco) tiene tres células vivas a su alrededor, en la siguiente
		 * generación nacerá.
		 */
// Primera pasada: A partir de las células vivas y muertas, determinamos cuáles nacerán y morirán (embriones y moribundas).
	int vecinas;
	
		for (int f=0; f<filas; f++){
		for (int c=0; c<columnas; c++) {
//Recorremos las celdas del tablero (del array bidimensional) mediante dos bucles for.
//Estos bucles de arriba ÚNICAMENTE sirven para determinar la posición de la célula indicada.
			vecinas=0;
			
			for (int i=f-1; i<=f+1;i++){
				for (int j=c-1;j<=c+1;j++){
//Con estos otros dos bucles for, determinamos cuáles son las células vecinas que rodean a otra célula.
					try{
					
					if ((i!=f || j!=c) && (poblacion[i][j]==Celula.VIVA || poblacion[i][j]==Celula.MORIBUNDA )){
					vecinas++;
					}
//Mediante un if, nos aseguramos de excluir a la célula central de sus "vecinas".
					
					}catch(IndexOutOfBoundsException e){};
				}
				}
					if (poblacion[f][c]==Celula.VIVA && (vecinas <=1 || vecinas >= 4)) {
			            poblacion[f][c]=Celula.MORIBUNDA;
			        }
			       
					if (poblacion[f][c]==Celula.MUERTA && vecinas == 3) {
			        	poblacion[f][c]=Celula.EMBRION;
			        }
//Ahora, gracias a otros dos if establecemos las condiciones para que unas células vivan y otras mueran, ya
//citadas arriba y en el enunciado del problema.
			        
			}
		}
		
//Segunda pasada: Ahora que sabemos qué células están moribundas y cuales van a nacer, las matamos y las creamos.
		
		for (int f=0; f<filas; f++){
			for (int c=0; c<columnas; c++) {
			
				if (poblacion[f][c]==Celula.EMBRION) {
		            poblacion[f][c]=Celula.VIVA;
		        }
		       
				else if (poblacion[f][c]==Celula.MORIBUNDA) {
		        	poblacion[f][c]=Celula.MUERTA;
		        }

//Todas las células que iban a nacer nacen, y las moribundas desaparecen.
}
}}}


	