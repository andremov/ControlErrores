/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_redes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Andrés Movilla
 */
public class PanelInputFile extends JPanel {

    JFileChooser fc;
    JPanel panelArchivo;
    JButton browseBtn;
    JButton acceptBtn;
    JButton cancelBtn;
    JTextField fileLocation;
    JTextArea dataDisplay;
    File openedFile;
    String loadedData;
    boolean isLoaded = false;

    public PanelInputFile() {
	setBounds(Tools.getModuleSize(2));
	setLayout(null);
	setOpaque(true);
	setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
	setBackground(new Color(209, 233, 249));

	init();

	setVisible(false);
    }

    private void init() {
	fc = new JFileChooser();

	JLabel label1 = new JLabel("Archivo de entrada:");
	label1.setSize(400, 20);
	label1.setLocation(20, 10);
	add(label1);

	fileLocation = new JTextField();
	fileLocation.setSize(365, 40);
	fileLocation.setLocation(20, 30);
	fileLocation.setEditable(false);
	add(fileLocation);

	browseBtn = new JButton("Examinar");
	browseBtn.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		openFile();
	    }
	});
	browseBtn.setSize(Tools.largeBtnDims());
	browseBtn.setLocation(getWidth() - 20 - Tools.largeBtnDims().width, 30);
	add(browseBtn);

	JLabel label2 = new JLabel("Informacion cargada de archivo:");
	label2.setSize(400, 20);
	label2.setLocation(20, 80);
	add(label2);

	dataDisplay = new JTextArea();
	dataDisplay.setSize(475, 120);
	dataDisplay.addCaretListener(new CaretListener() {
	    @Override
	    public void caretUpdate(CaretEvent e) {
		Lab_Redes.main.updateInputText(dataDisplay.getText());
	    }
	});
	dataDisplay.setLocation(20, 100);
	add(dataDisplay);
    }

    public void clearDisplay() {
	dataDisplay.setText("");
	fileLocation.setText("");
    }

    public void setWarning(boolean value) {
	dataDisplay.setBackground(value ? new Color(255, 105, 97) : Color.white);
    }

    private void openFile() {
	FileNameExtensionFilter filter = new FileNameExtensionFilter(
		Tools.getFileDescription(), Tools.getInputFileFormat());
	fc.setCurrentDirectory(openedFile);
	fc.setFileFilter(filter);

	int returnVal = fc.showOpenDialog(this);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    if (openedFile != fc.getSelectedFile()) {
		openedFile = fc.getSelectedFile();
		fileLocation.setText(fc.getSelectedFile().getAbsolutePath());
		try {
		    List<String> text;
		    text = Files.readAllLines(openedFile.toPath());
		    loadedData = "";
		    if (Lab_Redes.modo == Lab_Redes.MODO_DETECCION_RECEPCION) {
			Lab_Redes.main.updatePolyText(text.get(0));
			Lab_Redes.main.sendPolynomial(text.get(0));
			for (int i = 1; i < text.size(); i++) {
			    loadedData += text.get(i) + "\n";
			}
		    } else {
			for (int i = 0; i < text.size(); i++) {
			    loadedData += text.get(i) + "\n";
			}
		    }
		    dataDisplay.setText(loadedData);
		    isLoaded = true;
		} catch (Exception e) {

		}
	    }
	}
    }
}
