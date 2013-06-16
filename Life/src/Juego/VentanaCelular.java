package Juego                                                                   ;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Random;
import java.util.RandomAccess;

import javax.swing.JMenuBar;
import javax.swing.JMenu;


public class VentanaCelular extends JFrame {

	PanelCelular pec;
	
	public VentanaCelular() {
	
		super("El Juego de la Vida, Victoria Solís Sampedro, 1ºDAW");
		pec = new PanelCelular(65, 65);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		setMinimumSize(new Dimension(608, 666));
		setResizable(false);
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnCrearCelulas = new JButton("Crear C\u00E9lulas");
		btnCrearCelulas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pec.pintarCelulasAleatorias();
				    } 
		});
		toolBar.add(btnCrearCelulas);
		
		JButton btnLimpiar = new JButton("Limpiar");
		toolBar.add(btnLimpiar);
		
		btnLimpiar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			pec.limpiar();
			}});
			
		
		JButton btnModificar = new JButton("Modificar");
		toolBar.add(btnModificar);
		
		JButton btnSiguenteGeneracin = new JButton("Siguente Generaci\u00F3n");
		toolBar.add(btnSiguenteGeneracin);
		
		
		btnSiguenteGeneracin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			pec.siguenteGeneracion();
			}});
		
		JButton Ejecutar = new JButton("Ejecutar generación");
		toolBar.add(Ejecutar);
		Ejecutar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			pec.setEstado(true);
			}});
		
		JButton Detener = new JButton("Detener ejecución");
		toolBar.add(Detener);
		Detener.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			pec.setEstado(false);
			}});
		
		
		JButton btnSalir = new JButton("Salir");
		toolBar.add(btnSalir);
		
		getContentPane().add(pec, BorderLayout.CENTER);
		
		
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
			
			
		});
		
		new Thread (pec).start();
		
		
		pack();

		}
	
	public static void main(String[] args){
		
		new VentanaCelular().setVisible(true);
		
	}
}
