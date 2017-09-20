package com.shiger.google;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




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
	    System.out.println("\r");
	    System.out.println("\r");
	    System.out.println("\r");
        String stringInFileName = pathString + "/strings.xml";
        String stringOutFileName = pathString + "/values/strings.xml";
        FileOperator fileOperator =new FileOperator();
        String languageFileNameString = pathString + "/language.txt";
        System.out.println("languageFileNameString="+languageFileNameString);
	    System.out.println("\r");
	    System.out.println("\r");
	    System.out.println("\r");
        List<String> list = fileOperator.readFileByLine2List(languageFileNameString);
	    List<String> lauguageList = new ArrayList<String>();
	    for (String string : list) {
	    	String[] stringsline= string.split(" ");
	    	lauguageList.add(stringsline[1].trim());
	    	System.out.println(stringsline[1].trim());
		}
	    //
//	    System.out.print("请输入源文件的语言种类：");
//	    Scanner scan = new Scanner(System.in);
//	    String sourceString = scan.nextLine();
//	    if(lauguageList.contains(sourceString)){
//	    	System.out.println("源文件的语言种类："+sourceString); 	
//	    }else {
//	    	System.out.println("不支持语言种类："+sourceString); 
//		}
//	    //    
	    System.out.print("请输入目标文件的语言种类：");
	    Scanner scan = new Scanner(System.in);
	    String targetString =  scan.nextLine();
	    if(lauguageList.contains(targetString)){
	    	System.out.println("目标文件的语言种类："+targetString); 	
	    }else {
	    	System.out.println("不支持语言种类："+targetString); 
		}

	    
	    //readFileByMultiCharsAndTranslate
 
//        fileOperator.readFileByMultiCharsAndTranslate( stringOutFileName ,"zh-CN","en");
        
        fileOperator.readFileAndTranslate(targetString);


        

	}


}
