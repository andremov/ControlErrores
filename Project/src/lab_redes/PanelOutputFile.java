/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_redes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

/**
 *
 * @author Andrés Movilla
 */
public class PanelOutputFile extends JPanel {

    JFileChooser fc;
    JPanel panelArchivo;
    JButton browseBtn;
    JButton acceptBtn;
    JButton cancelBtn;
    JTextField fileLocation;
    JTextField fileName;
    JTextArea dataDisplay;
    File openedFile;
    String loadedData;
    boolean isLoaded = false;

    public PanelOutputFile() {
	setBounds(Tools.getModuleSize(4));
	setLayout(null);
	setOpaque(true);
	setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
	setBackground(new Color(209, 233, 249));

	init();

	setVisible(false);
    }

    private void init() {
	fc = new JFileChooser();
	fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

	JLabel label1 = new JLabel("Directorio y nombre de archivo de salida:");
	label1.setSize(400, 20);
	label1.setLocation(20, 10);
	add(label1);

	fileLocation = new JTextField();
	fileLocation.setSize(235, 40);
	fileLocation.setLocation(20, 30);
	fileLocation.setEditable(false);
	add(fileLocation);

	fileName = new JTextField("salida." + Tools.getOutputFileFormat());
	fileName.setSize(125, 40);
	fileName.setLocation(260, 30);
	add(fileName);

	browseBtn = new JButton("Examinar");
	browseBtn.addActionListener((ActionEvent e) -> {
	    openFile();
	});
	browseBtn.setSize(Tools.largeBtnDims());
	browseBtn.setLocation(getWidth() - 20 - Tools.largeBtnDims().width, 30);
	add(browseBtn);

	JLabel label3 = new JLabel("Informacion para guardar en archivo:");
	label3.setSize(400, 20);
	label3.setLocation(20, 80);
	add(label3);

	dataDisplay = new JTextArea();
	dataDisplay.setSize(475, 120);
	dataDisplay.setLocation(20, 100);
	dataDisplay.setEditable(false);
	add(dataDisplay);
    }

    public void correctSaveFile() {
	String curText = fileName.getText();
	if (curText.split("\\.").length > 1) {
	    String format = Tools.getOutputFileFormat();
	    if (!curText.split("\\.")[1].equals(format)) {
		fileName.setText(curText.split("\\.")[0] + "." + format);
	    }
	} else {
	    fileName.setText(curText + "." + Tools.getOutputFileFormat());
	}
    }

    public void updateOutputText(String text) {
	dataDisplay.setText(text);
    }

    public void clearDisplay() {
	dataDisplay.setText("");
    }

    public void saveFile() {

	correctSaveFile();
	try {
	    String absoluteFilePath = openedFile.getAbsolutePath() +"\\"+ fileName.getText();
	    
	    File file = new File(absoluteFilePath);

	    file.createNewFile();

	    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    if (Lab_Redes.modo == Lab_Redes.MODO_DETECCION_ENVIO) {
		writer.write(Lab_Redes.main.getPolynomialText() + "\n");
	    }
	    writer.write(dataDisplay.getText());
	    writer.close();
	    
	} catch (Exception ex) {
	    
	}
    }

    private void openFile() {
	fc.setCurrentDirectory(openedFile);

	int returnValue = fc.showSaveDialog(null);
	if (returnValue == JFileChooser.APPROVE_OPTION) {
	    if (fc.getSelectedFile().isDirectory()) {
		openedFile = fc.getSelectedFile();
		fileLocation.setText(fc.getSelectedFile().getAbsolutePath());
		saveFile();
	    }
	}
    }
}
