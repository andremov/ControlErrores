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

	String[] datawords = rawDatawords.split("\\n");
	String[] codewords = new String[datawords.length];

	for (int i = 0; i < datawords.length; i++) {
	    int datawordLength = datawords[i].length();
	    for (int j = 0; j < polynomial.length() - 1; j++) {
		datawords[i] += "0";
	    }
	    codewords[i] = datawordDivide(datawords[i], polynomial, datawordLength);
	}

	String joinedCodewords = "";
	for (int i = 0; i < codewords.length; i++) {
	    joinedCodewords += codewords[i] + "\\n";
	}
	joinedCodewords = joinedCodewords.substring(0, joinedCodewords.length() - 2);

	return joinedCodewords;
    }

    public static String decode(String rawCodewords, String polynomial) {
	if (!polynomial.contains("1")) {
	    return "";
	}

	if (rawCodewords.isEmpty()) {
	    return "";
	}

	String[] codewords = rawCodewords.split("\\n");
	String[] datawords = new String[codewords.length];

	int numErrors = 0;

	for (int i = 0; i < datawords.length; i++) {
	    int datawordLength = codewords[i].length() - (polynomial.length() - 1);
	    String result = datawordDivide(codewords[i], polynomial, datawordLength);
	    String syndrome = result.substring(result.length() - 3);
	    if (syndrome.equals("000")) {
		datawords[i - numErrors] = result.substring(0, result.length() - 3);
	    } else {
		numErrors++;
	    }
	}

	String joinedDataWords = "";
	if (datawords.length - numErrors > 0) {
	    for (int i = 0; i < datawords.length - numErrors; i++) {
		joinedDataWords += datawords[i] + "\\n";
	    }
	    joinedDataWords = joinedDataWords.substring(0, joinedDataWords.length() - 2);
	}
	joinedDataWords += ";" + numErrors;

	return joinedDataWords;
    }

    private static String datawordDivide(String longerDataword, String polynomial, int datawordLength) {
	String[] splitResponse = new String[datawordLength];
	String[] splitData = longerDataword.split("(?!^)");
	String[] splitPoly = polynomial.split("(?!^)");

	for (int i = 0; i < datawordLength; i++) {
	    splitResponse[i] = splitData[i];
	}

	for (int i = 0; i < datawordLength; i++) {
	    if (splitData[i].equals("1")) {
		for (int j = 0; j < splitPoly.length; j++) {
		    int realAddress = i + j;
		    splitData[realAddress] = (splitData[realAddress].equals(splitPoly[j]) ? "0" : "1");
		}
	    }
	}

	String response = "";
	for (int i = 0; i < splitResponse.length; i++) {
	    response += splitResponse[i];
	}

	String remainder = "";
	for (int i = 0; i < splitData.length; i++) {
	    remainder += splitData[i];
	}
	remainder = remainder.substring(remainder.length() - 3);
	response += remainder;

	return response;
    }
}
