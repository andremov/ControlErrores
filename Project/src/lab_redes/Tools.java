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

    public static final int BINARY = 0;
    public static final int ASCII = 1;

    //<editor-fold defaultstate="collapsed" desc="Diccionario">
    static String[][] dictionary = {
	{"00100000", " "},
	{"00101100", ","},
	{"00101110", "."},
	{"00111010", ":"},
	{"00111011", ";"},
	{"01000001", "A"},
	{"01000010", "B"},
	{"01000011", "C"},
	{"01000100", "D"},
	{"01000101", "E"},
	{"01000110", "F"},
	{"01000111", "G"},
	{"01001000", "H"},
	{"01001001", "I"},
	{"01001010", "J"},
	{"01001011", "K"},
	{"01001100", "L"},
	{"01001101", "M"},
	{"01001110", "N"},
	{"01001111", "O"},
	{"01010000", "P"},
	{"01010001", "Q"},
	{"01010010", "R"},
	{"01010011", "S"},
	{"01010100", "T"},
	{"01010101", "U"},
	{"01010110", "V"},
	{"01010111", "W"},
	{"01011000", "X"},
	{"01011001", "Y"},
	{"01011010", "Z"},
	{"01100001", "a"},
	{"01100010", "b"},
	{"01100011", "c"},
	{"01100100", "d"},
	{"01100101", "e"},
	{"01100110", "f"},
	{"01100111", "g"},
	{"01101000", "h"},
	{"01101001", "i"},
	{"01101010", "j"},
	{"01101011", "k"},
	{"01101100", "l"},
	{"01101101", "m"},
	{"01101110", "n"},
	{"01101111", "o"},
	{"01110000", "p"},
	{"01110001", "q"},
	{"01110010", "r"},
	{"01110011", "s"},
	{"01110100", "t"},
	{"01110101", "u"},
	{"01110110", "v"},
	{"01110111", "w"},
	{"01111000", "x"},
	{"01111001", "y"},
	{"01111010", "z"},};
    //</editor-fold>

    public static String translateAll(String original, int from, int to) throws Exception {
	String param = original;

	if (from == BINARY) {
	    param = param.replaceAll("\\s", "");
	    param = param.replaceAll("(\\d{8})", "$1" + "\n");
	}

	if (from == ASCII) {
	    param = param.replaceAll("\\n", "");
	    param = param.replaceAll("(.{1})", "$1" + "\n");
	}

	if (!validate(from, param)) {
	    throw new Exception("Error en el archivo.");
	}

	for (int i = 0; i < Tools.dictionary.length; i++) {
	    String find = Tools.dictionary[i][from];
	    find = (find.equals(".") ? "\\." : find);
	    param = param.replaceAll(find, Tools.dictionary[i][to]);
	}
	if (from == BINARY) {
	    param = param.replaceAll("[\\d]+", "?");
	}
	param = param.replaceAll("\\n", "");

	return param;
    }

    public static boolean validate(int format, String pData) {
	String data = pData.replace("\n", "");
	switch (format) {
	    case BINARY:
		if (data.length() % 8 != 0) {
		    return false;
		}
		if (data.matches("[01]+")) {
		    return true;
		}

		return false;
	    case ASCII:
		if (java.util.regex.Pattern.compile("[^a-zA-Z ,.;:]").matcher(data).find()) {
		    return false;
		}

		return true;
	    default:
		return false;
	}
    }

    public static String getFileDescription() {
	if (Lab_Redes.modo % 2 == 0) {
	    return "Archivos ASCII";
	} else {
	    return "Archivos codificado";
	}
    }

    public static String getInputFileFormat() {
	if (Lab_Redes.modo % 2 == 0) {
	    return "txt";
	} else if (Lab_Redes.modo == 1) {
	    return "ham";
	} else {
	    return "crc";
	}
    }

    public static String getOutputFileFormat() {
	if (Lab_Redes.modo % 2 == 1) {
	    return "txt";
	} else if (Lab_Redes.modo == 0) {
	    return "ham";
	} else {
	    return "crc";
	}
    }

    public static Dimension largeBtnDims() {
	return new Dimension(100, 40);
    }

    public static Rectangle getModuleSize(int id) {
	switch (id) {
	    case 1:
		return new Rectangle(10, 10, 250, 150);
	    case 2:
		return new Rectangle(270, 10, 515, 230);
	    case 3:
		return new Rectangle(10, 170, 250, 70);
	    case 4:
		return new Rectangle(270, 250, 515, 230);
	    case 5:
		return new Rectangle(10, 250, 250, 230);
	    default:
		return null;
	}
    }

    public static String joinStringArray(String[] array, boolean doNL) {
	String result = "";
	for (int i = 0; i < array.length; i++) {
	    result += array[i] + (doNL ? "\n" : "");
	}

	return (doNL ? result.substring(0, result.length() - 1) : result);
    }

    public static String[] splitCharacters(String source) {
	return source.split("(?!^)");
    }

    public static String[] splitLines(String source) {
	return source.split("\\n");
    }

    public static double log2(double a) {
	return Math.log10(a) / Math.log10(2);
    }
}
