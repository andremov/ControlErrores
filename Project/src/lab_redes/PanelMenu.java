/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_redes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author Andrés Movilla
 */
public class PanelMenu extends JPanel {

    JButton corregirBtn;
    JButton detectarBtn;

    JButton enviarBtn;
    JButton recibirBtn;

    JLabel dispProceso;
    JLabel dispModo;

    public PanelMenu() {
	setBounds(Tools.getModuleSize(1));
	setLayout(null);
	setOpaque(true);
	setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
	setBackground(new Color(209, 233, 249));
	
	init();

	setVisible(false);
    }

    public void init() {
	
	int marg = 10;

	int x1 = 20;
	int y1 = 10;

	dispProceso = new JLabel("Proceso seleccionado: N/A");
	dispProceso.setSize(getWidth()-20,20);
	dispProceso.setLocation(x1, y1);
	add(dispProceso);

	dispModo = new JLabel("Modo seleccionado: N/A");
	dispModo.setSize(getWidth()-20,20);
	dispModo.setLocation(x1, y1 + 20);
	add(dispModo);

	y1 = 50;
	int y2 = y1 + Tools.largeBtnDims().height + marg;
	int x2 = x1 + Tools.largeBtnDims().width + marg;

	corregirBtn = new JButton("Corrección");
	corregirBtn.setSize(Tools.largeBtnDims());
	corregirBtn.setLocation(x1, y1);
	corregirBtn.addActionListener((ActionEvent e) -> {
	    if (Lab_Redes.modo == Lab_Redes.MODO_ERROR) {
		dispModo.setText("Modo seleccionado: Envío");
		Lab_Redes.modo = 0;
		enviarBtn.setEnabled(false);
	    }
	    
	    corregirBtn.setEnabled(false);
	    detectarBtn.setEnabled(true);
	    
	    dispProceso.setText("Proceso seleccionado: Corrección");
	    
	    Lab_Redes.modo %= 2;
	    
	    Lab_Redes.main.updateUI();
	});
	add(corregirBtn);

	detectarBtn = new JButton("Detección");
	detectarBtn.setSize(Tools.largeBtnDims());
	detectarBtn.setLocation(x2, y1);
	detectarBtn.addActionListener((ActionEvent e) -> {
	    if (Lab_Redes.modo == Lab_Redes.MODO_ERROR) {
		dispModo.setText("Modo seleccionado: Envío");
		Lab_Redes.modo = 2;
		enviarBtn.setEnabled(false);
	    }
	    
	    corregirBtn.setEnabled(true);
	    detectarBtn.setEnabled(false);
	    
	    dispProceso.setText("Proceso seleccionado: Detección");
	    
	    Lab_Redes.modo %= 2;
	    Lab_Redes.modo += 2;
	    
	    Lab_Redes.main.updateUI();
	});
	add(detectarBtn);

	enviarBtn = new JButton("Enviar");
	enviarBtn.setSize(Tools.largeBtnDims());
	enviarBtn.setLocation(x1, y2);
	enviarBtn.addActionListener((ActionEvent e) -> {
	    if (Lab_Redes.modo == Lab_Redes.MODO_ERROR) {
		dispProceso.setText("Proceso seleccionado: Corrección");
		corregirBtn.setEnabled(false);
		Lab_Redes.modo = 0;
	    }
	    
	    recibirBtn.setEnabled(true);
	    enviarBtn.setEnabled(false);
	    
	    dispModo.setText("Modo seleccionado: Envío");
	    
	    Lab_Redes.modo -= Lab_Redes.modo % 2;
	    
	    Lab_Redes.main.updateUI();
	});
	add(enviarBtn);

	recibirBtn = new JButton("Recibir");
	recibirBtn.setSize(Tools.largeBtnDims());
	recibirBtn.setLocation(x2, y2);
	recibirBtn.addActionListener((ActionEvent e) -> {
	    if (Lab_Redes.modo == Lab_Redes.MODO_ERROR) {
		dispProceso.setText("Proceso seleccionado: Corrección");
		corregirBtn.setEnabled(false);
		Lab_Redes.modo = 1;
	    }
	    
	    recibirBtn.setEnabled(false);
	    enviarBtn.setEnabled(true);
	    
	    dispModo.setText("Modo seleccionado: Recepción");
	    
	    Lab_Redes.modo -= Lab_Redes.modo % 2;
	    Lab_Redes.modo += 1;
	    
	    Lab_Redes.main.updateUI();
	});
	add(recibirBtn);
    }
}
