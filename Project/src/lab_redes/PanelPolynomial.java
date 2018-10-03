/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_redes;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *
 * @author Andrés Movilla
 */
public class PanelPolynomial extends JPanel {

    JTextField poly;

    public PanelPolynomial() {
	setBounds(Tools.getModuleSize(3));
	setLayout(null);
	setOpaque(true);
	setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
	setBackground(new Color(209, 233, 249));

	init();

	setVisible(false);
    }

    public void clearDisplay() {
	updatePolynomial("");
    }

    public void updatePolynomial(String polyText) {
	poly.setText(polyText);
    }

    public void setWarning(boolean value) {
	poly.setBackground(value ? new Color(255, 105, 97) : Color.white);
    }

    private void init() {
	JLabel label1 = new JLabel("Polinomio Generador:");
	label1.setLocation(10, 10);
	label1.setSize(getWidth() - 20, 20);
	add(label1);

	poly = new JTextField();
	poly.setLocation(10, 30);
	poly.setSize(getWidth() - 20, 30);
	poly.addCaretListener(new CaretListener() {
	    @Override
	    public void caretUpdate(CaretEvent e) {
		Lab_Redes.main.updatePolyText(poly.getText());
	    }
	});
	add(poly);
    }
}
