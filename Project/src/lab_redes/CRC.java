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
public abstract class CRC {

    public static String encode(String rawDatawords, String polynomial) {
	if (!polynomial.contains("1")) {
	    return "";
	}

	if (rawDatawords.isEmpty()) {
	    return "";
	}

	String[] datawords = Tools.splitLines(rawDatawords);
	String[] codewords = new String[datawords.length];

	for (int i = 0; i < datawords.length; i++) {
	    int datawordLength = datawords[i].length();

	    for (int j = 0; j < polynomial.length() - 1; j++) {
		datawords[i] += "0";
	    }

	    codewords[i] = datawordDivide(datawords[i], polynomial, datawordLength);
	}

	String joinedCodewords = Tools.joinStringArray(codewords, true);
	return joinedCodewords;
    }

    public static String decode(String rawCodewords, String polynomial) {
	if (!polynomial.contains("1")) {
	    return " ; ";
	}

	if (rawCodewords.isEmpty()) {
	    return " ; ";
	}

	String[] codewords = Tools.splitLines(rawCodewords);
	String[] datawords = new String[codewords.length];
	int syndromeLength = polynomial.length() - 1;

	int numErrors = 0;
	for (int i = 0; i < datawords.length; i++) {
	    int datawordLength = codewords[i].length() - syndromeLength;

	    String result = datawordDivide(codewords[i], polynomial, datawordLength);
	    String syndrome = result.substring(result.length() - syndromeLength);
	    if (syndrome.contains("1")) {
		datawords[i] = result.substring(0, result.length() - syndromeLength);
		numErrors++;
	    } else {
		datawords[i] = result.substring(0, result.length() - syndromeLength);
	    }
	}

	String joinedDataWords = Tools.joinStringArray(datawords, true) + ";" + numErrors;
	return joinedDataWords;
    }

    private static String datawordDivide(String longerDataword, String polynomial, int datawordLength) {
	String[] splitResponse = new String[datawordLength];
	String[] splitData = Tools.splitCharacters(longerDataword);
	String[] splitPoly = Tools.splitCharacters(polynomial);

	for (int i = 0; i < datawordLength; i++) {
	    splitResponse[i] = splitData[i];
	}

	for (int i = 0; i < datawordLength; i++) {
	    if (splitData[i].equals("1")) {
		for (int j = 0; j < splitPoly.length; j++) {
		    splitData[i + j] = (splitData[i + j].equals(splitPoly[j]) ? "0" : "1");
		}
	    }
	}

	String remainder = Tools.joinStringArray(splitData, false);
	remainder = remainder.substring(remainder.length() - (polynomial.length() - 1));

	return Tools.joinStringArray(splitResponse, false) + remainder;
    }
}
