package Juego;

/* Comentarios y explicación: 
 * Lo que vemos a continuación es un Enum que utilizaremos para crear todos los posibles estados de una
 * célula, que serán:
 * - VIVA: La célula se ve en el tablero (De color verde en mi caso). En la siguiente generación seguirá viva o
 * estará moribunda, según las reglas (Ver método SgteGeneración en la clase PanelCelular).
 * - MUERTA: La célula es un espacio vacío en el tablero. En la siguiente generación puede seguir muerta o
 * estará embrión si tenía tres células alrededor.
 * - EMBRION: La célula es un espacio vacío en el tablero, pero en la siguiente generación va a nacer.
 * - MORIBUNDA: La célula aparece coloreada en el tablero, pero en la siguiente generación morirá.
 */

public enum Celula {
	VIVA, MUERTA, EMBRION, MORIBUNDA;
}
