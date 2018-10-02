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
	    codewords[i] = solveParities(addParities(datawords[i], bitsP));
	}

	return Tools.joinStringArray(codewords, true);
    }

    public static String decode(String rawCodewords) {
	if (rawCodewords.isEmpty()) {
	    return "";
	}

	String[] codewords = Tools.splitLines(rawCodewords);
	String[] datawords = new String[codewords.length];
	for (int i = 0; i < codewords.length; i++) {
	    String cw = codewords[i];
	    String dw = extractBits(cw, false);
	    String p = extractBits(cw, true);
	    String correct = solveParities(addParities(dw, p.length()));
	    if (cw.equals(correct)) {
		datawords[i] = dw;
	    } else {
		datawords[i] = extractBits(fixError(cw, correct),false);
	    }
	}

	return Tools.joinStringArray(datawords, true);
    }

    private static String fixError(String codeword, String correct) {
	int length = codeword.length();
	int errorBit = 0;

	for (int i = 0; i < length; i++) {
	    if (codeword.charAt(i) != correct.charAt(i)) {
		if (isParityBit(length - i)) {
		    errorBit += length - i;
		}
	    }
	}
	
	String corrected = "";
	for (int i = 0; i < length; i++) {
	    if (length - i != errorBit) {
		corrected += codeword.charAt(i);
	    } else {
		corrected += "" + ((Integer.parseInt("" + codeword.charAt(i)) + 1) % 2);
	    }
	}
	return corrected;
    }

    private static String extractBits(String codeword, boolean extractParity) {
	String[] splitWord = Tools.splitCharacters(codeword);
	String result = "";

	for (int i = 0; i < splitWord.length; i++) {
	    int bitN = splitWord.length - i;
	    if (isParityBit(bitN) == extractParity) {
		result += splitWord[i];
	    }
	}

	return result;
    }

    private static String solveParities(String codeword) {
	String[] cw = Tools.splitCharacters(codeword);

	int length = cw.length;

	for (int i = 0; i < length; i++) {
	    if (cw[i].equals("2")) {
		int bitParNum = length - i;
		int value = 0;
		for (int j = 0; j < length; j++) {
		    int bitNum = length - j;
		    value += inParitySequence(bitParNum, bitNum) ? Integer.parseInt(cw[j]) : 0;
		}
		cw[i] = "" + (value % 2);
	    }
	}

	return Tools.joinStringArray(cw, false);
    }

    private static boolean isParityBit(int bit) {
	return Tools.log2(bit) % 1 == 0;
    }

    private static boolean inParitySequence(int parity, int bitNumber) {
	if (isParityBit(bitNumber)) {
	    return false;
	}

	if (bitNumber < parity) {
	    return false;
	}

	int b = numParities(bitNumber);
	int r = bitNumber;
	boolean returnValue = false;

	while (r > 0) {
	    if (Math.pow(2, b) > r) {
		if (Math.pow(2, b) == parity) {
		    returnValue = false;
		}
		b--;
	    } else {
		if (Math.pow(2, b) == parity) {
		    returnValue = true;
		}
		r -= Math.pow(2, b);
		b--;
	    }
	}

	return returnValue;
    }

    private static String addParities(String dataword, int parityBits) {
	String codeword = "";
	int datawordLength = dataword.length();
	int bitD = 0;

	for (int j = 0; j < datawordLength + parityBits; j++) {
	    if (isParityBit((datawordLength + parityBits) - j)) {
		codeword = codeword + 2;
	    } else {
		codeword = codeword + dataword.charAt(bitD);
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
