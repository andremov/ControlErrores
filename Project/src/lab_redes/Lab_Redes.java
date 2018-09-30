/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_redes;

import javax.swing.*;

/**
 *
 * @author Andrés Movilla
 */
public class Lab_Redes extends JFrame {
    
    JPanel menu;
    

    /**
     * ERROR		    - -1
     * CORRECCION+ENVIO	    - +0
     * CORRECCION+RECIBO    - +1
     * DETECCION+ENVIO	    - +2 
     * DETECCION+RECIBO	    - +3
     **/
    public static int modo;

    public static void main(String[] args) {
	new Lab_Redes();
    }

    public Lab_Redes() {
	setSize(1000, 900);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLocationRelativeTo(null);
	setLayout(null);
	setTitle("Laboratorio de Redes");
	
	init();

	setVisible(true);
    }

    public void init() {
	menu = new PanelMenu();
	add(menu);
    }

}
