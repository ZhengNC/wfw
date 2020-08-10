package com.qixi.wfw.common.core.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author zheng
 */
public class Tools {

	/**
	 * 字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if(str == null || "".equals(str) || "".equals(str.trim())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 得到随机的四位验证码
	 * @return
	 */
	public static String getMsgCode() {
		int a = (int) (Math.random() * 10000D);
		String s = Integer.toString(a);
		int sLength = s.length();
		StringBuilder sb = new StringBuilder(s);
		for(int i = 0; i < 4 - sLength; i++) {
			sb.insert(0, "0");
		}
		return sb.toString();
	}

	private static final Pattern PATTERN = Pattern.compile("[0-9]*");
	/**
	 * 判断字符串内容是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str) {
		if(!Tools.isBlank(str)){
			Matcher isNum = PATTERN.matcher(str);
			if (isNum.matches()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取指定长度的随机字符串
	 * @param length （1 - 32）传空默认为32
	 * @param upperOrLower （upper或lower）传null默认为不转换
	 * @return
	 */
	public static String randomStr(Integer length, String upperOrLower){
		if (length != null){
			if (length < 1 || length > 32){
				throw new RuntimeException("字符串长度设置错误");
			}
		}
		if (upperOrLower != null && !"upper".equals(upperOrLower) && !"lower".equals(upperOrLower)){
			throw new RuntimeException("字符串转换方式设置错误");
		}
		String result = UUID.randomUUID().toString().replace("-","");
		if (length != null){
			result = result.substring(0, length);
		}
		if ("upper".equals(upperOrLower)){
			result = result.toUpperCase();
		}
		if ("lower".equals(upperOrLower)){
			result = result.toLowerCase();
		}
		return result;
	}

	/**
	 * 从请求里取参数（顺序为：header,param,cookie）
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getParamByRequest(HttpServletRequest request, String name){
		if (Tools.isBlank(name)){
			return null;
		}
		String param = request.getHeader(name);
		if (Tools.isBlank(param)){
			param = request.getParameter(name);
		}
		if (Tools.isBlank(param)){
			Cookie[] cookies = request.getCookies();
			if (cookies!= null){
				for(Cookie cookie : cookies){
					if (name.equals(cookie.getName())){
						param = cookie.getValue();
						break;
					}
				}
			}
		}
		return param;
	}

	/**
	 * 获取一个随机数，范围在[min, max]
	 * @param min 最小数（包括）
	 * @param max 最大数（包括）
	 * @return
	 */
	public static int randomNum(int min, int max){
		if (min < 0 || max < 0 || min > max){
			throw new RuntimeException("选择的范围错误");
		}
		int result = (int)(Math.random() * (max - min + 1)) + min;
		return result;
	}

	public static void main(String[] args){
		Arrays.stream(new int[10]).forEach(i -> {
			System.out.println(getMsgCode());
		});
	}
}
