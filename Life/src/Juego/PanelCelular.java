package Juego;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import javax.swing.KeyStroke;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.event.KeyEvent;

public class PanelCelular extends JPanel implements Runnable, ActionListener{
	EspacioCelular ec;
	boolean estado=false;
	public PanelCelular(int ancho, int alto) {
		setPreferredSize(new Dimension(ancho, alto));

		ec = new EspacioCelular(alto, ancho);
		setBackground(Color.BLACK);
		setForeground(Color.DARK_GRAY);

		EspacioCelular espacioCelular = new EspacioCelular(alto, ancho);


		addMouseListener(new MouseListener() {
			/* Comentarios y explicaci�n: 
			 * Es importante para nuestro programa que reciba las instrucciones introducidas mediante el rat�n,
			 * as� que implementamos un MouseListener para que reconozca sus isntrucciones y el programa las
			 * procese. 
			 * Debajo, el propio Java genera todas las posibles acciones que el rat�n cometer�.
			 */

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {

				ec.setViva(e.getX(), e.getY());

				repaint();

			}
		});
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				ec.setViva(e.getX(), e.getY());

				repaint();

			}
		});


	}

	/* Comentarios y explicaci�n: 
	 * Los siguientes m�todos se limitan a pintar sobre el tablero todos aquellos m�todos con funciones que ya construimos
	 * en la clase EspacioCelular.
	 */

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ec.paint(g, getForeground());

	}

	public void pintarCelulasAleatorias() {
		ec.setCelulasAleatorias();
		repaint();

	}

	public void limpiar() {

		ec.setLimpiar();

		repaint();
	}

	public void siguenteGeneracion() {
		ec.sgteGeneracion();

		repaint();
	}

	@Override
	public void run() {
		/* Comentarios y explicaci�n: 
		 * La utilidad de este m�todo es que el bot�n "Ejecutar Generaci�n" pueda hacer avanzar el programa a
		 * la siguiente generaci�n indefinidas veces. Para esto, creamos un bucle infinito cualquiera.
		 * Por ejemplo, hemos decidido poner while (true), como siempre ser� true, al cliquear en "Ejecutar
		 * Generaci�n" el programa repetir�a infinitas veces la siguiente generaci�n.
		 * Sin embargo, esto crea un conflicto, ya que una vez inicializado el bucle infinito el usuario
		 * deber�a poder ser capaz de pararlo a placer mediante el bot�n "Detener ejecuci�n".
		 * Para hacer esto posible, creamos otro bucle infinito dentro del primer bucle infinito,
		 * cuya funcionalidad ser� detener al primero, y como ser� un bucle infinito controlado por
		 * nosotros (Lo ejecutaremos en el momento en que pulsemos "Detener ejecuci�n", har� psoible al
		 * usuario decidir cu�ndo desea dejar de repetir la siguiente generaci�n indefinidas veces.
		 * 
		 * Por otra parte, mediante "Thread Sleep" diremos al programa cu�ntos milisegundos quiere que
		 * transcurran entre una generaci�n y la siguiente y azs� sucesivamente creadas indefinidamente. 
		 * Es decir, cuanto menor sea el n�mero m�s r�pido ir�.
		 */
		 while (true){
			 while (estado){
				 
			 ec.sgteGeneracion();
			 repaint();
			 
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				} 
			 
			
			 
			 
		 }}
		
	}
	public void setEstado (boolean booleano){
		/* Comentarios y explicaci�n: 
		 * Este m�todo est� aqu� porque llamamos de un modo distinto a estado cuando lo declaramos y cuando
		 * lo usamos, as� que en este m�todo los igualamos para que al usar la variable "booleano" use la
		 * declaracion que hemos hecho para "estado".
		 */
		estado=booleano;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
