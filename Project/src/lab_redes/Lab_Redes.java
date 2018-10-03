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
    }

    JPanel menu;
    JPanel inputFile;
    JPanel outputFile;
    JPanel polynomial;

    private String inputText;
    private String outputText;
    private String polynomialText;

    public Lab_Redes() {
	setSize(800, 520);
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

	this.inputText = "";
	this.outputText = "";
	this.polynomialText = "";
    }

    public String getPolynomialText() {
	return polynomialText;
    }
    
    public void updateUI() {
	inputFile.setVisible(modo != MODO_ERROR);
	outputFile.setVisible(modo != MODO_ERROR);
	polynomial.setVisible(modo > 1);
	((PanelInputFile) inputFile).clearDisplay();
	((PanelOutputFile) outputFile).clearDisplay();
	((PanelOutputFile) outputFile).correctSaveFile();

	this.inputText = "";
	this.outputText = "";
    }

    public void updatePolyText(String polyText) {
	this.polynomialText = polyText;
	process();
    }
    
    public void sendPolynomial(String polyText) {
	((PanelPolynomial) polynomial).updatePolynomial(polyText);
    }

    public void updateInputText(String inputText) {
	this.inputText = inputText;
	process();
    }

    public void process() {

	((PanelInputFile) inputFile).setWarning(false);
	((PanelPolynomial) polynomial).setWarning(false);
	
	if (inputText.isEmpty()) {
	    return;
	}

	this.outputText = "";
	String middleMan;

	try {
	    if (modo == MODO_CORRECCION_ENVIO) {
		middleMan = Tools.translateAll(inputText, Tools.ASCII, Tools.BINARY);
		middleMan = middleMan.replaceAll("(\\d{8})", "$1" + "\n");
		middleMan = middleMan.substring(0, middleMan.length() - 1);
		this.outputText = Hamming.encode(middleMan);
	    }
	    
	    if (modo == MODO_CORRECCION_RECEPCION) {
		middleMan = Hamming.decode(this.inputText);
		middleMan = Tools.translateAll(middleMan, Tools.BINARY, Tools.ASCII);
		this.outputText = middleMan.replaceAll("\\n", "");
	    }
	    
	    if (modo == MODO_DETECCION_ENVIO) {
		middleMan = Tools.translateAll(inputText, Tools.ASCII, Tools.BINARY);

		int length = middleMan.length();
		middleMan = middleMan.replaceAll("(\\d{128})", "$1" + "\n");
		if (length % 128 == 0) {
		    middleMan = middleMan.substring(0, middleMan.length() - 1);
		}

		this.outputText = CRC.encode(middleMan, polynomialText);
	    }
	    
	    if (modo == MODO_DETECCION_RECEPCION) {
		middleMan = CRC.decode(this.inputText, polynomialText);
		String error = middleMan.split(";")[1];
		middleMan = middleMan.split(";")[0];
		if (error.equals("0")) {
		    error = "No se encontraron errores.";
		} else {
		    if (error.equals("1")) {
			error = "Se encontraron errores en 1 palabra de datos.";
		    } else if (error.isEmpty()){
			error="";
		    }else{
			error = "Se encontraron errores en " + error + " palabra de datos";
		    }
		}
		middleMan = middleMan.replaceAll("(\\d{8})", "$1" + "\n");
		this.outputText = Tools.translateAll(middleMan, Tools.BINARY, Tools.ASCII) + "\n" + error;
	    }

	    ((PanelOutputFile) outputFile).saveFile();
	} catch (Exception e) {
	    if (polynomial.isVisible() && polynomialText.isEmpty()) {
		((PanelPolynomial) polynomial).setWarning(true);
	    } else {
		((PanelInputFile) inputFile).setWarning(true);
	    }
	}

	((PanelOutputFile) outputFile).updateOutputText(this.outputText);
    }

}
