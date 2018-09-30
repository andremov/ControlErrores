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

    public static Lab_Redes main;

    /*
	ERROR	    -1
	CORR. ENV.  +0
	CORR. REC.  +1
	DETE. ENV.  +2
	DETE. REC.  +3
     */
    public static int modo = -1;

    public static void main(String[] args) {
	main = new Lab_Redes();
    }

    public static String getFileDescription() {
	if (modo % 2 == 0) {
	    return "Archivos ASCII";
	} else {
	    return "Archivos codificado";
	}
    }

    public static String getInputFileFormat() {
	if (modo % 2 == 0) {
	    return "txt";
	} else if (modo == 1) {
	    return "ham";
	} else {
	    return "crc";
	}
    }

    public static String getOutputFileFormat() {
	if (modo % 2 == 1) {
	    return "txt";
	} else if (modo == 0) {
	    return "ham";
	} else {
	    return "crc";
	}
    }

    JPanel menu;
    JPanel archivo;

    public Lab_Redes() {
	setSize(1000, 900);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLocationRelativeTo(null);
	setLayout(null);
	setResizable(false);
	setTitle("Laboratorio de Redes");

	init();

	setVisible(true);
	menu.setVisible(true);

    }

    public void init() {
	menu = new PanelMenu();
	add(menu);

	archivo = new PanelArchivos();
	add(archivo);

	add(new Placeholder(3));
	add(new Placeholder(4));
	add(new Placeholder(5));
    }

    public void updateUI() {
	if (modo != -1 && !archivo.isVisible()) {
	    archivo.setVisible(true);
	}
    }
}
