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
	Hamming.encode("01000010");

	main.dispose();
    }

    //////////////////////////////////////////////////////////////////////////////
    //					MAIN WINDOW				//
    //////////////////////////////////////////////////////////////////////////////
    JPanel menu;
    JPanel inputFile;
    JPanel outputFile;
    JPanel polynomial;

    private String inputText;
    private String outputText;
    private String polynomialText;

    public Lab_Redes() {
//	setSize(800, 520);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
//	setLocationRelativeTo(null);
//	setLayout(null);
//	setResizable(false);
//	setTitle("Laboratorio de Redes");
//
//	init();
//	setVisible(true);
//	menu.setVisible(true);
    }

    public void init() {
	
	menu = new PanelMenu();
	add(menu);

	inputFile = new PanelInputFile();
	add(inputFile);

	polynomial = new PanelPolynomial();
	add(polynomial);
	
	outputFile = new PanelOutputFile();
	add(outputFile);

	add(new Placeholder(2));
	add(new Placeholder(3));
	add(new Placeholder(4));
	add(new Placeholder(5));
    }

    public void updateUI() {
	inputFile.setVisible(modo != MODO_ERROR);
	outputFile.setVisible(modo != MODO_ERROR);
	polynomial.setVisible(modo > 1);
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
