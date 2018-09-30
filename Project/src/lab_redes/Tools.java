/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_redes;

import java.awt.Dimension;
import java.awt.Rectangle;

/**
 *
 * @author Andrés Movilla
 */
public abstract class Tools {

    public static int BINARY = 0;
    public static int DECIMAL = 1;
    public static int ASCII = 2;

    //<editor-fold defaultstate="collapsed" desc="Diccionario">
    static String[][] dictionary = {
	{"00100000", "32", " "},
	{"00101100", "44", ","},
	{"00101110", "46", "."},
	{"00111010", "58", ":"},
	{"00111011", "59", ";"},
	{"01000001", "65", "A"},
	{"01000010", "66", "B"},
	{"01000011", "67", "C"},
	{"01000100", "68", "D"},
	{"01000101", "69", "E"},
	{"01000110", "70", "F"},
	{"01000111", "71", "G"},
	{"01001000", "72", "H"},
	{"01001001", "73", "I"},
	{"01001010", "74", "J"},
	{"01001011", "75", "K"},
	{"01001100", "76", "L"},
	{"01001101", "77", "M"},
	{"01001110", "78", "N"},
	{"01001111", "79", "O"},
	{"01010000", "80", "P"},
	{"01010001", "81", "Q"},
	{"01010010", "82", "R"},
	{"01010011", "83", "S"},
	{"01010100", "84", "T"},
	{"01010101", "85", "U"},
	{"01010110", "86", "V"},
	{"01010111", "87", "W"},
	{"01011000", "88", "X"},
	{"01011001", "89", "Y"},
	{"01011010", "90", "Z"},
	{"01100001", "97", "a"},
	{"01100010", "98", "b"},
	{"01100011", "99", "c"},
	{"01100100", "100", "d"},
	{"01100101", "101", "e"},
	{"01100110", "102", "f"},
	{"01100111", "103", "g"},
	{"01101000", "104", "h"},
	{"01101001", "105", "i"},
	{"01101010", "106", "j"},
	{"01101011", "107", "k"},
	{"01101100", "108", "l"},
	{"01101101", "109", "m"},
	{"01101110", "110", "n"},
	{"01101111", "111", "o"},
	{"01110000", "112", "p"},
	{"01110001", "113", "q"},
	{"01110010", "114", "r"},
	{"01110011", "115", "s"},
	{"01110100", "116", "t"},
	{"01110101", "117", "u"},
	{"01110110", "118", "v"},
	{"01110111", "119", "w"},
	{"01111000", "120", "x"},
	{"01111001", "121", "y"},
	{"01111010", "122", "z"},
    };
    //</editor-fold>
    
    public static String translate(String data, int from, int to) {
	if (from == BINARY && data.length() != 8) {
	    return null;
	}

	if (from == ASCII && data.length() != 1) {
	    return null;
	}

	if (from == DECIMAL && data.length() != 2 && data.length() != 3) {
	    return null;
	}

	int id = -1;
	for (int i = 0; i < dictionary.length; i++) {
	    if (dictionary[i][from].equals(data)) {
		id = i;
	    }
	}

	if (id == -1) {
	    return null;
	}

	return dictionary[id][to];

    }

    public static Dimension largeBtnDims() {
	return new Dimension(100, 40);
    }

    public static Rectangle getModuleSize(int id) {
	switch (id) {
	    case 1:
		return new Rectangle(10, 10, 250, 150);
	    case 2:
		return new Rectangle(270, 10, 500, 210);
	    case 3:
		return new Rectangle(10, 170, 250, 50);
	    case 4:
		return new Rectangle(780, 10, 205, 210);
	    case 5:
		return new Rectangle(10, 230, 975, 190);
	    default:
		return null;
	}
    }

}
