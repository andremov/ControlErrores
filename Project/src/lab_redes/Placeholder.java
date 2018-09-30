/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_redes;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Andrés Movilla
 */
public class Placeholder extends JPanel{

    public Placeholder(int moduleNumber) {
	setBounds(Tools.getModuleSize(moduleNumber));
	setLayout(null);
	setOpaque(true);
	setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
	setBackground(new Color(209, 233, 249));
	
	JLabel label = new JLabel("Module Number: "+moduleNumber);
	label.setLocation(10,10);
	label.setSize(200,30);
	label.setFont(new Font("Arial",Font.BOLD,20));
	add(label);

	setVisible(true);
    }
    
}
