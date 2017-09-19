package com.shiger.google;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class GoogleTrans {
	
	public void tanslateTest() {
		TranslateUtil translateUtil = new TranslateUtil();
		
		String iString = "这是一段翻译测试：hello world！";
		System.out.println("iString--" + iString);
		String oString = "null";
		try {
			oString = translateUtil.cn2en(iString);
			System.out.println("oString--" + oString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		System.out.println("Welcome!");
//		
        String pathString = System.getProperty("user.dir"); 
        
//        System.out.println("pathString="+pathString);
//        System.err.println("\r");
//        System.err.println("\r");
//        System.err.println("\r");
		
//		GoogleTrans googleTrans =new GoogleTrans();
//		googleTrans.tanslateTest();
		
//        String fileName = "/home/yangcanhu/javaPro/GoogleTrans_0/GoogleTrans/values-zh-rCN/strings.xml";
//        FileOperator.readFileByBytes(fileName);
//        FileOperator.readFileByChars(fileName);
//        FileOperator.readFileByLines(fileName);
//        FileOperator.readFileByRandomAccess(fileName);     
//        FileOperator.readFileByMultiChars(fileName);
        
        String stringInFileName = pathString + "/values-zh-rCN/strings.xml";
        String stringOutFileName = pathString + "/values/strings.xml";
        File file =new java.io.File(stringOutFileName);
        if(file.exists()){
        	file.delete();
        }
        FileOperator.readFileByMultiCharsAndTranslate(stringInFileName , stringOutFileName);

//        RegexUtils regexUtils = new RegexUtils();
//        System.out.println(regexUtils.regexString("", ""));
        

	}


}
