package com.shiger.google;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class FileOperator {

	String sourceFileString = System.getProperty("user.dir") + "/source.txt";
	/**
     * 
     */
    public static void readFileByBytes(String fileName) {
        File file = new File(fileName);
        InputStream in = null;
        try {
            System.out.println("--readFileByBytes--begin--");
            //
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                System.out.write(tempbyte);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        try {
            System.out.println("浠ュ瓧鑺備负鍗曚綅璇诲彇鏂囦欢鍐呭锛屼竴娆¤澶氫釜瀛楄妭锛�");
            // 
            byte[] tempbytes = new byte[100];
            int byteread = 0;
            in = new FileInputStream(fileName);
            FileOperator.showAvailableBytes(in);
            // 
            while ((byteread = in.read(tempbytes)) != -1) {
                System.out.write(tempbytes, 0, byteread);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     */
    public static void readFileByChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        try {
            System.out.println("--readFileByChars---begin--");
            // 
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                if (((char) tempchar) != '\r') {
                    System.out.print((char) tempchar);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    /**
     */
    public  void readFileByMultiChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        try {     
       
            char[] tempchars = new char[4000];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            //
            while ((charread = reader.read(tempchars)) != -1) {
            	System.out.println("\rcharread------" + charread);
                // 
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
//                  System.out.print("tempchars.length--" + tempchars.length);//4000
                    System.out.print(tempchars);
                    
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            System.out.print(tempchars[i]);
                        }
                    }
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
	    
    /*
     * 
     */
    public  String stringFilter(String stringOut) {
    	//</resources>
        stringOut = stringOut.replace("<resources", "\n"+"<resources");
        stringOut = stringOut.replace("</ resources>", "\n"+"</resources>");//</resources>
        stringOut = stringOut.replace("</resources>", "\n"+"</resources>");//</resources> 
        //xmlns
        stringOut = stringOut.replace("xmlns", "\n"+"xmlns");
        stringOut = stringOut.replace("xmlns: ", "\n"+"xmlns:");//xmlns: 
        //
        stringOut = stringOut.replace("<string", "\n"+"<string");
//        stringOut = stringOut.replaceAll("<string", "\n"+"<string");// string
        stringOut = stringOut.replace("</ ","</");
        stringOut = stringOut.replace("< /","</");//< /
        
        stringOut = stringOut.replace("% ","%");//% 
      
        stringOut = stringOut.replace("$ ","$");
        stringOut = stringOut.replace(" $","$");
//        stringOut = stringOut.replace("$ d","$d");//$ d
        
        stringOut = stringOut.replace("<! -","<!--");//<! -
        stringOut = stringOut.replace("->","-->");//->
//        stringOut = stringOut.replaceAll("// *","//*");// * 
//        stringOut = stringOut.replaceAll("* //","*//");// * 
        stringOut = stringOut.replace("<? xml","<?xml");//<?xml
        
//        stringOut = stringOut.replaceAll("<xliff: g", "<xliff:g");
        stringOut = stringOut.replace("xliff: g", "xliff:g");//xliff: g
        stringOut = stringOut.replace("xliff: g>", "xliff:g>");
        stringOut = deleteCharC2A0(stringOut);
        stringOut = stringOut.trim();
    	return stringOut;
    }
	/*
	 * 
	 */
    public  String deleteCharC2A0(String stringOut) {
        String string1 ="";
        string1 +=(char) 0xC2;
        stringOut = stringOut.replace(string1,"");
        String string2 ="";
        string2 +=(char) 0xA0;
        stringOut = stringOut.replace(string2,"");
        return stringOut;
	}
    /**
     * 
     */
    public  void readFileByMultiCharsAndTranslate(String fileName ,String stringOutFileName) {
        File outfile =new java.io.File(stringOutFileName);
        if(outfile.exists()){
        	outfile.delete();
        }        
        File file = new File(fileName);
        Reader reader = null;
        TranslateUtil translateUtil = new TranslateUtil();
        RegexUtils regexUtils = new RegexUtils();
   
        try {     
          
            char[] tempchars = new char[4000];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            while ((charread = reader.read(tempchars)) != -1) {
//            	System.out.println("\rcharread------" + charread);

                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
//                  System.out.print("tempchars.length--" + tempchars.length);//4000
//                    System.out.print(tempchars);
                    String string2Translate = new String(tempchars);
                    System.out.print(string2Translate);//log
                    String stringOut= translateUtil.cn2tw(string2Translate);
                    stringOut = stringFilter( stringOut);
                    appendMethodB(stringOutFileName, stringOut);

                    tempchars = new char[4000];//clear
                } else {
                	String string2Translate = new String(tempchars);
                	string2Translate = string2Translate.trim();
                    System.out.print(string2Translate);//log
                	String stringOut= translateUtil.cn2tw(string2Translate);
                	stringOut = stringFilter( stringOut);
                    appendMethodB(stringOutFileName, stringOut);

               
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
    /*
     * 
     */
    public  void readFileAndTranslate(String targetString) {
        
    	File sourcefile = new File(sourceFileString);
    	if(!sourcefile.exists()){
    		System.out.println("sourcefile do not exist ! file path:" + sourceFileString);
    		System.out.println("\rplease put your sourcefile the path above!" );
    		return;
    	}     	
    	String OutFileDir = System.getProperty("user.dir") + "/values-" + targetString + "/strings.xml";

//        String strPath = "E:\\a\\aa\\aaa.txt";  
        File file = new File(OutFileDir);  
        if(!file.getParentFile().exists()){  
            file.getParentFile().mkdirs();             
        } 
        File outfile =new java.io.File(OutFileDir);
        if(outfile.exists()){
        	outfile.delete();
        }     
  	
//    	String stringOutFileName =  System.getProperty("user.dir") + "/strings.xml";   	
        Reader reader = null;
        TranslateUtil translateUtil = new TranslateUtil();
        RegexUtils regexUtils = new RegexUtils();
//        System.out.println("浠ュ瓧绗︿负鍗曚綅璇诲彇鏂囦欢鍐呭锛屼竴娆¤澶氫釜瀛楄妭锛�");
        try {     
            // 
            char[] tempchars = new char[4000];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(sourceFileString));
            // 
            while ((charread = reader.read(tempchars)) != -1) {
//            	System.out.println("\rcharread------" + charread);
                // 
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
//                  System.out.print("tempchars.length--" + tempchars.length);//4000
//                    System.out.print(tempchars);
                    String string2Translate = new String(tempchars);
                    System.out.print(string2Translate);//log
                    String stringOut= translateUtil.translate(string2Translate, "zh-CN", targetString);
//                    stringOut = stringFilter( stringOut);
                    appendMethodB(OutFileDir, stringOut);
                    tempchars = new char[4000];//clear
                } else {
                	String string2Translate = new String(tempchars);
                	string2Translate = string2Translate.trim();
                    System.out.print(string2Translate);//log
                	String stringOut= translateUtil.translate(string2Translate, "zh-CN", targetString);
//                	stringOut = stringFilter( stringOut);
                    appendMethodB(OutFileDir, stringOut);                  
                }
            }
            System.out.print(" \rTranslate success!\r");//log
            
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
    /**
     * readFileByLines
     */
    public  void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
//            System.out.println("readFileByLines");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 
            while ((tempString = reader.readLine()) != null) {             
//                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
    /**
     * read by line
     */
    public  List<String> readFileByLine2List(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        List<String> list =new ArrayList<String>() ;
        try {
            System.out.println("readFileByLine---List:");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            //
            while ((tempString = reader.readLine()) != null) {
                // 
//                System.out.println("line " + line + ": " + tempString);
                list.add(tempString);
                line++;
            }       
            reader.close();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return list;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     */
    public static void readFileByRandomAccess(String fileName ,String outFileNameString) {
        RandomAccessFile randomFile = null;
        try {
//            System.out.println("闅忔満璇诲彇涓�娈垫枃浠跺唴瀹癸細");
            // 鎵撳紑涓�涓殢鏈鸿闂枃浠舵祦锛屾寜鍙鏂瑰紡
            randomFile = new RandomAccessFile(fileName, "r");
            // 鏂囦欢闀垮害锛屽瓧鑺傛暟
            long fileLength = randomFile.length();
            // 璇绘枃浠剁殑璧峰浣嶇疆
            int beginIndex = (fileLength > 4) ? 4 : 0;
            // 灏嗚鏂囦欢鐨勫紑濮嬩綅缃Щ鍒癰eginIndex浣嶇疆銆�
            randomFile.seek(beginIndex);
            byte[] bytes = new byte[10];
            int byteread = 0;
            // 涓�娆¤10涓瓧鑺傦紝濡傛灉鏂囦欢鍐呭涓嶈冻10涓瓧鑺傦紝鍒欒鍓╀笅鐨勫瓧鑺傘��
            // 灏嗕竴娆¤鍙栫殑瀛楄妭鏁拌祴缁檅yteread
            while ((byteread = randomFile.read(bytes)) != -1) {
                System.out.write(bytes, 0, byteread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 鏄剧ず杈撳叆娴佷腑杩樺墿鐨勫瓧鑺傛暟
     */
    private static void showAvailableBytes(InputStream in) {
        try {
            System.out.println("褰撳墠瀛楄妭杈撳叆娴佷腑鐨勫瓧鑺傛暟涓�:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A鏂规硶杩藉姞鏂囦欢锛氫娇鐢≧andomAccessFile
     */
    public static void appendMethodA(String fileName, String content) {
        try {
            // 鎵撳紑涓�涓殢鏈鸿闂枃浠舵祦锛屾寜璇诲啓鏂瑰紡
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 鏂囦欢闀垮害锛屽瓧鑺傛暟
            long fileLength = randomFile.length();
            //灏嗗啓鏂囦欢鎸囬拡绉诲埌鏂囦欢灏俱��
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * B鏂规硶杩藉姞鏂囦欢锛氫娇鐢‵ileWriter
     */
    public static void appendMethodB(String fileName, String content) {
        try {
            //鎵撳紑涓�涓啓鏂囦欢鍣紝鏋勯�犲嚱鏁颁腑鐨勭浜屼釜鍙傛暟true琛ㄧず浠ヨ拷鍔犲舰寮忓啓鏂囦欢
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  
}


