package com.shiger.google;

public class GoogleTrans {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome!");
		
	}

	public void tanslateTest() {
		TranslateUtil translateUtil = new TranslateUtil();
		
		String iString = "这是一段翻译测试：hello world！";
		System.out.println("iString--" + iString);
		String oString = "null";
		try {
			oString = TranslateUtil.cn2en(iString);
			System.out.println("oString--" + oString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	}
}
