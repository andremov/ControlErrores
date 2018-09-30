/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_redes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Andrés Movilla
 */
public class PanelMenu extends JPanel {

    //////////////////////////////////////////////////////////////////////////////
    //				    PANEL DE MENU				//
    //////////////////////////////////////////////////////////////////////////////
    JButton corregirBtn;
    JButton detectarBtn;

    JButton enviarBtn;
    JButton recibirBtn;

    JLabel dispProceso;
    JLabel dispModo;
    

    public void PanelMenu() {

	int btnH = 40;
	int btnL = 100;
	int marg = 10;

	int x1 = 20;
	int x2 = x1 + btnL + marg;

	int y1 = 10;

	int panL = btnL * 2 + marg + x1 * 2;
	int panH = btnH * 2 + marg + y1 * 2 + 50;

	setSize(panL, panH);
	setLocation(10, 10);
	setLayout(null);
	setOpaque(true);
	setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
	setBackground(new Color(209, 233, 249));

	dispProceso = new JLabel("Proceso seleccionado: N/A");
	dispProceso.setSize(btnL * 2 + marg, btnH / 2);
	dispProceso.setLocation(x1, y1);
	add(dispProceso);

	dispModo = new JLabel("Modo seleccionado: N/A");
	dispModo.setSize(btnL * 2 + marg, btnH / 2);
	dispModo.setLocation(x1, y1 + btnH / 2);
	add(dispModo);

	y1 = 50;
	int y2 = y1 + btnH + marg;

	corregirBtn = new JButton("Corrección");
	corregirBtn.setSize(btnL, btnH);
	corregirBtn.setLocation(x1, y1);
	corregirBtn.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (Lab_Redes.modo == -1) {
		    dispModo.setText("Modo seleccionado: Envío");
		    Lab_Redes.modo = 0;
		    enviarBtn.setEnabled(false);
		}

		corregirBtn.setEnabled(false);
		detectarBtn.setEnabled(true);
		dispProceso.setText("Proceso seleccionado: Corrección");
		Lab_Redes.modo %= 2;
	    }
	});
	add(corregirBtn);

	detectarBtn = new JButton("Detección");
	detectarBtn.setSize(btnL, btnH);
	detectarBtn.setLocation(x2, y1);
	detectarBtn.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (Lab_Redes.modo == -1) {
		    dispModo.setText("Modo seleccionado: Envío");
		    Lab_Redes.modo = 2;
		}

		corregirBtn.setEnabled(true);
		detectarBtn.setEnabled(false);
		dispProceso.setText("Proceso seleccionado: Detección");
		Lab_Redes.modo %= 2;
		Lab_Redes.modo += 2;
	    }
	});
	add(detectarBtn);

	enviarBtn = new JButton("Enviar");
	enviarBtn.setSize(btnL, btnH);
	enviarBtn.setLocation(x1, y2);
	enviarBtn.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (Lab_Redes.modo == -1) {
		    dispProceso.setText("Proceso seleccionado: Corrección");
		    corregirBtn.setEnabled(false);
		    Lab_Redes.modo = 0;
		}

		recibirBtn.setEnabled(true);
		enviarBtn.setEnabled(false);
		dispModo.setText("Modo seleccionado: Envío");
		Lab_Redes.modo -= Lab_Redes.modo % 2;
	    }
	});
	add(enviarBtn);

	recibirBtn = new JButton("Recibir");
	recibirBtn.setSize(btnL, btnH);
	recibirBtn.setLocation(x2, y2);
	recibirBtn.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (Lab_Redes.modo == -1) {
		    dispProceso.setText("Proceso seleccionado: Corrección");
		    corregirBtn.setEnabled(false);
		    Lab_Redes.modo = 1;
		}

		recibirBtn.setEnabled(false);
		enviarBtn.setEnabled(true);
		dispModo.setText("Modo seleccionado: Recepción");
		Lab_Redes.modo -= Lab_Redes.modo % 2;
		Lab_Redes.modo += 1;
	    }
	});
	add(recibirBtn);

    }
}
