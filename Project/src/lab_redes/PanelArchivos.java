/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_redes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Andrés Movilla
 */
public class PanelArchivos extends JPanel {

    //////////////////////////////////////////////////////////////////////////////
    //			    PANEL DE CARGA DE ARCHIVO				//
    //////////////////////////////////////////////////////////////////////////////
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

    public void initPanelArchivo() {
	panelArchivo = new JPanel();
	panelArchivo.setSize(500, 500);
	panelArchivo.setLocation(500, 0);
	panelArchivo.setLayout(null);

	JLabel label1 = new JLabel("Archivo a cargar:");
	label1.setSize(400, 40);
	label1.setLocation(30, 0);
	panelArchivo.add(label1);

	fileLocation = new JTextField();
	fileLocation.setSize(280, 40);
	fileLocation.setLocation(20, 30);
	fileLocation.setEditable(false);
	panelArchivo.add(fileLocation);

	browseBtn = new JButton("Examinar");
	browseBtn.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		openFile();
	    }
	});
	browseBtn.setSize(150, 40);
	browseBtn.setLocation(500 - 180, 30);
	panelArchivo.add(browseBtn);

	JLabel label2 = new JLabel("Informacion cargada:");
	label2.setSize(400, 40);
	label2.setLocation(30, 70);
	panelArchivo.add(label2);

	dataDisplay = new JTextArea();
	dataDisplay.setSize(450, 110);
	dataDisplay.setLocation(20, 100);
	panelArchivo.add(dataDisplay);

	add(panelArchivo);
    }

    public void openFile() {
	fc = new JFileChooser(openedFile);
	FileNameExtensionFilter filter = new FileNameExtensionFilter(
		"Archivos .txt", "txt");
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
		    for (int i = 0; i < text.size(); i++) {
			loadedData += text.get(i) + "\n";
		    }
		    // TODO: verificar caracteres invalidos
		    dataDisplay.setText(loadedData);
		    isLoaded = true;
		} catch (Exception e) {
		    dataDisplay.setText("Error cargando archivo.");
		}
	    }
	}
    }
}
