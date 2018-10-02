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

    public static final int MODO_ERROR = -1;
    public static final int MODO_CORRECCION_ENVIO = 0;
    public static final int MODO_CORRECCION_RECEPCION = 1;
    public static final int MODO_DETECCION_ENVIO = 2;
    public static final int MODO_DETECCION_RECEPCION = 3;

    public static int modo = -1;

    public static void main(String[] args) {
	main = new Lab_Redes();

//	String start = "testing binary; please wait.....";
//	System.out.println(start);
//	main.updateInputText(start);
	System.out.println(CRC.encode("1001", "1011"));
	System.out.println(CRC.decode("1001110", "1011"));
	System.out.println(CRC.decode("1000110", "1011"));
	main.dispose();
    }

    //////////////////////////////////////////////////////////////////////////////
    //					MAIN WINDOW				//
    //////////////////////////////////////////////////////////////////////////////
    JPanel menu;
    JPanel file;
    JPanel polynomial;

    private String inputText;
    private String outputText;
    private String polynomialText;

    public Lab_Redes() {
//	setSize(800, 900);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
//	setLocationRelativeTo(null);
//	setLayout(null);
//	setResizable(false);
//	setTitle("Laboratorio de Redes");

//	init();
//	setVisible(true);
//	menu.setVisible(true);
    }

    public void init() {
	menu = new PanelMenu();
	add(menu);

	file = new PanelFiles();
	add(file);

	polynomial = new PanelPolynomial();
	add(polynomial);

	add(new Placeholder(4));
    }

    public void updateUI() {
	if (modo != -1 && !file.isVisible()) {
	    file.setVisible(true);
	}
    }

    public void updateInputText(String inputText) {
	switch (modo) {
	    case MODO_CORRECCION_ENVIO:
		this.inputText = inputText;
		break;
	    case MODO_CORRECCION_RECEPCION:
		this.inputText = inputText;
		break;
	    case MODO_DETECCION_ENVIO:
		this.inputText = inputText;
		this.outputText = CRC.encode(this.inputText, polynomialText);
		break;
	    case MODO_DETECCION_RECEPCION:
		this.inputText = inputText;
		this.outputText = CRC.decode(this.inputText, polynomialText);
		break;
	}
    }

}
