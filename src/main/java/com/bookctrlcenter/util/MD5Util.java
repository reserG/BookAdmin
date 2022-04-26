package com.bookctrlcenter.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

public class MD5Util {

	public static String md5(String src) {
		return DigestUtils.md5Hex(src);
	}

	private static final String salt = "q5z6g1w9";

	//注册时进行第一次md5
	public static String inputPassToFormPass(String inputPass) {
		String str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
//		System.out.println("inputPassToFormPass " + str);
		return md5(str);
	}
	//表单数据转换第二次md5，转换成数据库层次
	public static String formPassToDBPass(String formPass, String salt) {
		String str = ""+salt.charAt(0)+salt.charAt(3) + formPass +salt.charAt(2) + salt.charAt(7);
//		System.out.println("formPass " +formPass);
//		System.out.println("formPassToDBPass " +str);
		return md5(str);
	}
	//将注册表单二次转换
	public static String inputPassToDbPass(String inputPass, String saltDB) {
		String formPass = inputPassToFormPass(inputPass);
		String dbPass = formPassToDBPass(formPass, saltDB);
		return dbPass;
	}

	// 获取随机字符串
	public static String getRandomString(int length){
		Random random=new Random();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<length;i++){
			int number=random.nextInt(3);
			long result=0;
			switch(number){
				case 0:
					result=Math.round(Math.random()*25+65);
					sb.append(String.valueOf((char)result));
					break;
				case 1:
					result=Math.round(Math.random()*25+97);
					sb.append(String.valueOf((char)result));
					break;
				case 2:
					sb.append(String.valueOf(new Random().nextInt(10)));
					break;
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String oneMD = inputPassToFormPass("123123");
		System.out.println("oneMD  " + oneMD);
		System.out.println("twoMD " + formPassToDBPass("d93dc10466ba6fe548898c869bf8fc87", "r42Wg4So"));
		String dbPass = inputPassToDbPass("123123", "r42Wg4So");//d52efc68832da19f7d711d08ca3359c3
		System.out.println("dbPass   "+ dbPass);//
	}

}
