/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_redes;

/**
 *
 * @author Andrés Movilla
 */
public abstract class Hamming {

    public static String encode(String rawDatawords) {
	if (rawDatawords.isEmpty()) {
	    return "";
	}

	String[] datawords = Tools.splitLines(rawDatawords);
	String[] codewords = new String[datawords.length];
	int datawordLength = datawords[0].length();
	int bitsP = numParities(datawordLength);

	for (int i = 0; i < datawords.length; i++) {
	    codewords[i] = addParities(datawords[i], datawordLength, bitsP);
	    codewords[i] = solveParities(codewords[i]);
	}

	return Tools.joinStringArray(codewords,true);
    }

    public static String decode(String rawCodewords) {
	return "";
    }

    private static String solveParities(String codeword) {
	String[] cw = Tools.splitCharacters(codeword);
	
	int length = cw.length;
	
	System.out.println(codeword);
	for (int i = 0; i < length; i++) {
	    if (cw[i].equals("2")) {
		int bitParNum = length - i;
		int value = 0;
		for (int j = 0; j < length; j++) {
		    int bitNum = length - j;
		    value += isParity(bitParNum, bitNum) ? 1 : 0;
		}
		cw[i] = ""+(value%2);
	    }
	}
	
	return Tools.joinStringArray(cw, false);
    }

    private static boolean isParity(int parity, int bitNumber) {
	int b = numParities(bitNumber);
	int r = bitNumber;

	boolean returnValue = false;

	while (r > 0) {
	    if (Math.pow(2, b) > r) {
		if (b == parity) {
		    returnValue = false;
		}
		b--;
	    } else {
		if (b == parity) {
		    returnValue = true;
		}
		r -= Math.pow(2, b);
		b--;
	    }
	}
	return returnValue;
    }

    private static String addParities(String dataword, int datawordLength, int parityBits) {
	String codeword = "";
	int bitD = 0;
	for (int j = 0; j < datawordLength + parityBits; j++) {
	    if (Math.log10(j + 1) / Math.log10(2) % 1 == 0) {
		codeword = 2 + codeword;
	    } else {
		codeword = dataword.charAt(bitD) + codeword;
		bitD++;
	    }
	}
	return codeword;
    }

    private static int numParities(int datawordLength) {
	int exp = 0;

	while (Math.pow(2, exp) < datawordLength) {
	    exp++;
	}

	return exp + 1;
    }

}
