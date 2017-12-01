package com.shiger.google;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GoogleTrans {
	
	public void tanslateTest() {
		TranslateUtil translateUtil = new TranslateUtil();
		
		String iString = "hello world";
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
		
		System.out.println("-----Welcome!--------");
	
//		GoogleTrans googleTrans =new GoogleTrans();
//		googleTrans.tanslateTest();		
//        String fileName = "/home/yangcanhu/javaPro/GoogleTrans_0/GoogleTrans/values-zh-rCN/strings.xml";
//        FileOperator.readFileByBytes(fileName);
//        FileOperator.readFileByChars(fileName);
//        FileOperator.readFileByLines(fileName);
//        FileOperator.readFileByRandomAccess(fileName);     
//        FileOperator.readFileByMultiChars(fileName);
	    
	    String pathString = System.getProperty("user.dir");   
	    System.out.println("pathString="+pathString);
//	    System.out.println("\r");
        FileOperator fileOperator =new FileOperator();
        String languageFileNameString = pathString + "/language.txt";
        System.out.println("languageFileNameString="+languageFileNameString);
//	    System.out.println("\r");

	   
        List<String> list = fileOperator.readFileByLine2List(languageFileNameString);
//        System.out.print("Please chose the target language above.");
	    List<String> lauguageList = new ArrayList<String>();
	    for (String string : list) {
	    	String[] stringsline= string.split(" ");
	    	lauguageList.add(stringsline[1].trim());
	    	 System.out.println("\r");
	    	System.out.print("  "+stringsline[1].trim()+"  ----means:"+stringsline[0].trim());
		}
	    boolean isContinue = true;
	    Scanner scan;
		while(isContinue){

		    //
		    System.out.println("\r");
		    System.out.print("Please input the simple name above of your target language"
		    		+ " \r,such as en,af and zh-TW .");		    
	        System.out.println("\rYour input is:");
		    scan = new Scanner(System.in);
		    String targetString =  scan.nextLine();
		    if(lauguageList.contains(targetString)){
		    	System.out.println("You have chosed : "+targetString); 	
		    }else {
		    	System.out.println("Sorry ,don not support lauguage : "+targetString); 
			}
		    
	        fileOperator.readFileAndTranslate(targetString);
	        
	        System.out.println("\rDo you want to continue? y/n");
		    scan = new Scanner(System.in);
		    String yesOrNo =  scan.nextLine();
		    if(yesOrNo.trim().equals("n")||yesOrNo.equals("N")){
		    	isContinue = false;
		    }
	    }
	   

	}
}
