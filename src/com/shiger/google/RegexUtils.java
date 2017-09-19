package com.shiger.google;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class RegexUtils {

	
	public ArrayList<String> regexString(String string ,String regex) {
//      String stmt = "xx[new Date('08/24/2013'), 9.39],[new Date('08/24/2013'), 9.39],";  
//      String regex = "\\[(.*?)\\],";  
		
	  ArrayList<String> arrayList =new ArrayList<String>();
      String stmt = "<string name=application_name msgid=5181331383435256801>Launcher3</string><string name=home msgid=7658288663002113681>主屏</string>";
      regex = "<string(.*?)\\>"; 
      Pattern p = Pattern.compile(regex);  
      Matcher m = p.matcher(stmt);  
      System.out.println(m.groupCount()); 
      
      while(m.find()){  
      	System.out.println(m.group(0));  
      	arrayList.add( m.group(0));
//          System.out.println(m.group(1));  
      }  
      
      return arrayList;
	}
	
	
	public Matcher regexTranslateString(String stringIn) {
	
	  ArrayList<String> arrayList =new ArrayList<String>();
	  String regex = "<string(.*?)string>"; 
      Pattern p = Pattern.compile(regex);  
      Matcher matcher = p.matcher(stringIn);  
      System.out.println(matcher);  
//      while(matcher.find()){  
//      	System.out.println(matcher.group(0));  
//      	arrayList.add(matcher.group(0));
// 
//      }      
      return matcher;
	}
	
}
