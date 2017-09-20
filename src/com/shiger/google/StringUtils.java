package com.shiger.google;

public class StringUtils {
    /*
     * 字符串删除指定字符
     */
    public static String deleteCharString(String sourceString, char chElemData) {

    	String stringOfChar ="";
    	stringOfChar += chElemData;
    	sourceString.replace(stringOfChar, "");
    	return sourceString;

    }
	
	
    public static String deleteCharString8(String sourceString, char chElemData) {
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < sourceString.length(); i++) {
            if (sourceString.charAt(i) != chElemData) {
                stringBuffer.append(sourceString.charAt(i));
            }
        }
        return stringBuffer.toString();
    }
    
    public static String deleteCharString10(String sourceString, char chElemData) {
        String tmpString = "";
        tmpString += chElemData;
        StringBuffer stringBuffer = new StringBuffer(sourceString);
        int iFlag = -1;
        do {
            iFlag = stringBuffer.indexOf(tmpString);
            if (iFlag != -1) {
                stringBuffer.deleteCharAt(iFlag);
            }
        } while (iFlag != -1);
        return stringBuffer.toString();
    }
}
