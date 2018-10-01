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
//	String start = "testing binary; please wait...";
//	System.out.println(start);
//	try {
//	String b = Tools.translateAll(start, Tools.ASCII, Tools.BINARY);
//	System.out.println(b);
//	String end = Tools.translateAll(b, Tools.BINARY, Tools.ASCII);
//	System.out.println(end);
//	} catch (Exception e) {
//	    e.printStackTrace();
//	}
    }

    //////////////////////////////////////////////////////////////////////////////
    //					MAIN WINDOW				//
    //////////////////////////////////////////////////////////////////////////////
    JPanel menu;
    JPanel archivo;
    JPanel polinomio;

    private String asciiText;
    private String binaryText;
    private String codedText;

    public Lab_Redes() {
	setSize(800, 900);
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

	polinomio = new PanelPolinomio();
	add(polinomio);

	add(new Placeholder(4));
    }

    public void updateUI() {
	if (modo != -1 && !archivo.isVisible()) {
	    archivo.setVisible(true);
	}
    }

    public void updateInputText(String inputText) {
	if (modo % 2 == 0) {
	    asciiText = inputText;
	} else {
	    codedText = inputText;
	}
    }

}
