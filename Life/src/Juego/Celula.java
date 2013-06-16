package Juego;

/* Comentarios y explicaci�n: 
 * Lo que vemos a continuaci�n es un Enum que utilizaremos para crear todos los posibles estados de una
 * c�lula, que ser�n:
 * - VIVA: La c�lula se ve en el tablero (De color verde en mi caso). En la siguiente generaci�n seguir� viva o
 * estar� moribunda, seg�n las reglas (Ver m�todo SgteGeneraci�n en la clase PanelCelular).
 * - MUERTA: La c�lula es un espacio vac�o en el tablero. En la siguiente generaci�n puede seguir muerta o
 * estar� embri�n si ten�a tres c�lulas alrededor.
 * - EMBRION: La c�lula es un espacio vac�o en el tablero, pero en la siguiente generaci�n va a nacer.
 * - MORIBUNDA: La c�lula aparece coloreada en el tablero, pero en la siguiente generaci�n morir�.
 */

public enum Celula {
	VIVA, MUERTA, EMBRION, MORIBUNDA;
}
