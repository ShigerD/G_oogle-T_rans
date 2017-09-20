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

	/**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
    public static void readFileByBytes(String fileName) {
        File file = new File(fileName);
        InputStream in = null;
        try {
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
            // 一次读一个字节
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
            System.out.println("以字节为单位读取文件内容，一次读多个字节：");
            // 一次读多个字节
            byte[] tempbytes = new byte[100];
            int byteread = 0;
            in = new FileInputStream(fileName);
            FileOperator.showAvailableBytes(in);
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
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
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
    public static void readFileByChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        try {
            System.out.println("以字符为单位读取文件内容，一次读一个字节：");
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
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
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     * 以字符为单位读取文件内容，一次读多个字节
     */
    public  void readFileByMultiChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
//        System.out.println("以字符为单位读取文件内容，一次读多个字节：");
        try {     
            // 一次读多个字符
            char[] tempchars = new char[4000];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
            	System.out.println("\rcharread------" + charread);
                // 同样屏蔽掉\r不显示
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
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     * 以字符为单位读取文件内容，一次读多个字节
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
//        System.out.println("以字符为单位读取文件内容，一次读多个字节：");
   
        try {     
            // 一次读多个字符
            char[] tempchars = new char[4000];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
//            	System.out.println("\rcharread------" + charread);
                // 同样屏蔽掉\r不显示
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
//                  System.out.print("tempchars.length--" + tempchars.length);//4000
//                    System.out.print(tempchars);
                    String string2Translate = new String(tempchars);
                    System.out.print(string2Translate);//log
                    String stringOut= translateUtil.cn2tw(string2Translate);
                    stringOut = stringFilter( stringOut);
                    appendMethodB(stringOutFileName, stringOut);
                    //regex
//                    Matcher matcher = regexUtils.regexTranslateString(stringOut);
//                    while (matcher.find()) {
//                    	appendMethodB(stringOutFileName,"\r");	
//                    	String lineString = matcher.group(0);
//                    	lineString = lineString.replaceAll("</ string>","</string>");
//                    	lineString = lineString.replaceAll("</ xliff: g>","</xliff:g>");
//                    	lineString = lineString.replaceAll("<xliff: g", "<xliff:g");
//                    	appendMethodB(stringOutFileName,lineString);					
//					}
                    tempchars = new char[4000];//clear
                } else {
                	String string2Translate = new String(tempchars);
                	string2Translate = string2Translate.trim();
                    System.out.print(string2Translate);//log
                	String stringOut= translateUtil.cn2tw(string2Translate);
                	stringOut = stringFilter( stringOut);
                    appendMethodB(stringOutFileName, stringOut);
                    //regex
//                    Matcher matcher = regexUtils.regexTranslateString(stringOut);
//                    while (matcher.find()) {
//                    	appendMethodB(stringOutFileName,"\r");	
//                    	String lineString = matcher.group(0);
//                    	lineString = lineString.replaceAll("</ string>","</string>");
//                    	lineString = lineString.replaceAll("</ xliff: g>","</xliff:g>");
//                    	lineString = lineString.replaceAll("<xliff: g", "<xliff:g");
//                    	appendMethodB(stringOutFileName,lineString);					
//					}                 
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
        String sourceFileString = System.getProperty("user.dir") + "/strings.xml";
    	File sourcefile = new File(sourceFileString);
    	if(!sourcefile.exists()){
    		System.out.println("请把文件放在以下目录：" + sourceFileString);
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
//        System.out.println("以字符为单位读取文件内容，一次读多个字节：");
        try {     
            // 一次读多个字符
            char[] tempchars = new char[4000];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(sourceFileString));
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
//            	System.out.println("\rcharread------" + charread);
                // 同样屏蔽掉\r不显示
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
//                  System.out.print("tempchars.length--" + tempchars.length);//4000
//                    System.out.print(tempchars);
                    String string2Translate = new String(tempchars);
                    System.out.print(string2Translate);//log
                    String stringOut= translateUtil.translate(string2Translate, "zh-TW", targetString);
                    stringOut = stringFilter( stringOut);
                    appendMethodB(OutFileDir, stringOut);
                    tempchars = new char[4000];//clear
                } else {
                	String string2Translate = new String(tempchars);
                	string2Translate = string2Translate.trim();
                    System.out.print(string2Translate);//log
                	String stringOut= translateUtil.translate(string2Translate, "zh-TW", targetString);
                	stringOut = stringFilter( stringOut);
                    appendMethodB(OutFileDir, stringOut);                  
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
    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public  void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
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
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public  List<String> readFileByLine2List(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        List<String> list =new ArrayList<String>() ;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
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
     * 随机读取文件内容
     */
    public static void readFileByRandomAccess(String fileName ,String outFileNameString) {
        RandomAccessFile randomFile = null;
        try {
//            System.out.println("随机读取一段文件内容：");
            // 打开一个随机访问文件流，按只读方式
            randomFile = new RandomAccessFile(fileName, "r");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 读文件的起始位置
            int beginIndex = (fileLength > 4) ? 4 : 0;
            // 将读文件的开始位置移到beginIndex位置。
            randomFile.seek(beginIndex);
            byte[] bytes = new byte[10];
            int byteread = 0;
            // 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
            // 将一次读取的字节数赋给byteread
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
     * 显示输入流中还剩的字节数
     */
    private static void showAvailableBytes(InputStream in) {
        try {
            System.out.println("当前字节输入流中的字节数为:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A方法追加文件：使用RandomAccessFile
     */
    public static void appendMethodA(String fileName, String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            //将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * B方法追加文件：使用FileWriter
     */
    public static void appendMethodB(String fileName, String content) {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  
}


