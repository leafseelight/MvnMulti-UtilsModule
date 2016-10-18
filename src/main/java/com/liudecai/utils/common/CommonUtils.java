package com.liudecai.utils.common;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * 小小工具
 * @author liudecai  
 * @email 1911939348@qq.com
 */
public class CommonUtils {
	/**
	 * UUID：返回一个不重复的字符串
	 * @return
	 */
	public static String Uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	/**
	 * 返回一个定长的随机字符串(包含大、小写字母、数字)
	 * 
	 * @param length 随机字符串长度
	 * @return 随机字符串
	 */
	public static String GenerateRandomString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * 把map转换成对象
	 * 
	 * @param map map对象
	 * @param clazz JavaBean对象实体类
	 * @return
	 * 		把Map转换成指定类型
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T MapToBean(Map map, Class<T> clazz) {
		try {
			/*
			 * 1. 通过参数clazz创建实例 	2. 使用BeanUtils.populate把map的数据封闭到bean中
			 */
			T bean = clazz.newInstance();
			ConvertUtils.register(new DateConverter(), java.util.Date.class);
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将表单提交过来的数据，转换成Bean对象 
	 * 使用方式： 
	 * 调用方法完成对表单数据封装到Bean中 
	 * XXXBean bean = (XXXBean)MyCommonUtils.MyRequest2Bean(request, new XXXBean());
	 * 但是还有问题: 不能有过滤的进行转换，当bean中没有的属性出现时报错
	 * @param request
	 * @param bean
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Object MyReq2Bean(HttpServletRequest request, Object bean) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		// 取得所有参数列表
		Enumeration<?> enume = request.getParameterNames();
		// 遍历所有参数列表
		while (enume.hasMoreElements()) {
			Object obj = enume.nextElement();
			try {
				// 取得这个参数在Bean中的数据类开
				Class<?> cls = PropertyUtils.getPropertyType(bean, obj.toString());
				// 把相应的数据转换成对应的数据类型
				Object beanValue = ConvertUtils.convert(request.getParameter(obj.toString()), cls);
				// 添冲Bean值
				PropertyUtils.setProperty(bean, obj.toString(), beanValue);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return bean;
	}
	
	/**
	 * 将提交的表单信息有过滤的转换的Bean对象。
	 * Bean中没有的属性将被过滤掉
	 * @param request
	 * @param response
	 * @param clazz 要转换的目标对象XXXBean
	 * @return 返回转换后带值的bean对象
	 */
	public static <T> T MyReqest2Bean (HttpServletRequest request, HttpServletResponse response, Class<T> clazz) {
		T bean = null;
		// 设置请求和响应的编码格式
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
//			response.setContentType("text/html; charset=utf-8");	// 与上面一句等效
		} catch (UnsupportedEncodingException e) {
			System.out.println("request.setCharacterEncoding() 出现异常!");
			e.printStackTrace();
		}
		// 实例化Bean对象
		try {
			bean = clazz.newInstance();
		} catch (InstantiationException e) {
			System.out.println("bean = clazz.newInstance() Bean对象实例化异常!");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("安全权限异常!");
			e.printStackTrace();
		}
		// 将Request转换成Bean对象
		try {
			BeanUtils.populate(bean, request.getParameterMap());
		} catch (IllegalAccessException e) {
			System.out.println("安全权限异常!");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println("转化成Bean异常!");
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 * 返回当前日期和时间 (2016-06-22 22:25:45)
	 * @return
	 */
	public static String MyDateTimeFormat() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 返回当前日期 (2016-06-22)
	 * @return
	 */
	public static String MyDateFormat() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	/**
	 * 返回当前时间 (17:26:16)
	 * @return
	 */
	public static String GetTimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String str = sdf.format(new Date());
		return str;
	}
	
	/**
	 * 获取当前时间戳
	 * @return 
	 * @return Timestamp 时间戳 (1468592401058)
	 */
	public static long GetTimeStamp() {
		return System.currentTimeMillis();
	}
	
}
