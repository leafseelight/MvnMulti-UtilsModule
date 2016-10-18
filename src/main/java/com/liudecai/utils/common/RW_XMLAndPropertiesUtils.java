package com.liudecai.utils.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

/**
 * 读写XML和Properties配置文件
 * 
 * @author liudecai
 * @email 1911939348@qq.com
 * 
 */
public class RW_XMLAndPropertiesUtils {
	
	/**
	 * ===========================================================================
	 * 读写xxx.xml配置文件部分
	 * ===========================================================================
	 */
	/**
	 * 读取xml配置文件的所有属性信息
	 * @param xmlFilePath xml文件路径
	 * @return 成功与否(true|false)
	 */
	public static Properties GetAllPropertiesFromXML(String xmlFilePath) {
		Properties properties = new Properties();
        InputStream inputStream = null ;
        try {
			inputStream = new FileInputStream(xmlFilePath);
			properties.loadFromXML(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}
	
	/**
	 * 说明：读取xml配置文件的单个键值
	 * @param xmlFilePath xml文件路径
	 * @param key 属性键
	 * @return 属性值
	 */
	public static String GetPropertyFromXML(String xmlFilePath, String key) {
		return new Properties().getProperty(key);
	}

	/**
	 * 说明：创建XML配置文件,并写入信息 (不会删掉原有信息,原来有的则更新,没有的则添加)
	 * 
	 * @param xmlFilePath xml文件路径
	 * @param hashtable 要写入的键值对Hashtable, 
	 * 			如：Hashtable<String, String> htKeyValue = new Hashtable<>();
	 * 				htKeyValue.put("name", "刘德财");
	 * @return 成功与否(true|false)
	 */
	public static boolean CreateXMLFile(String xmlFilePath, Hashtable<String, String> hashtable) {
		Properties properties = new Properties();
		properties = GetAllPropertiesFromXML(xmlFilePath);
		properties.putAll(hashtable);
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(xmlFilePath);
			properties.storeToXML(outputStream, "Temporary Properties");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 说明：创建XML配置文件,并写入信息 
	 * @param xmlFilePath
	 * @param hashtable 要写入的键值对Hashtable, 
	 * @param isnew 是否新建 (true: 删除原有信息再新建, false: 不会删掉原有信息,原来有的则更新,没有的则添加)
	 * @return 成功与否(true|false)
	 */
	public static boolean CreateXMLFile(String xmlFilePath, Hashtable<String, String> hashtable, boolean isnew) {
		Properties properties = new Properties();
		OutputStream outputStream = null;
		if (isnew) {
			properties.putAll(hashtable);
		} else {
			properties = GetAllPropertiesFromXML(xmlFilePath);
			properties.putAll(hashtable);
		}
		try {
			outputStream = new FileOutputStream(xmlFilePath);
			properties.storeToXML(outputStream, "Temporary Properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 说明：添加键值对到xml文件 (不会删掉原有信息,原来有的则更新,没有的则添加)
	 * @param xmlFilePath xml文件路径
	 * @param key 属性键
	 * @param value 属性值
	 * @return 成功与否(true|false)
	 */
	public static boolean AddKeyValueToXML(String xmlFilePath, String key, String value) {
		Properties properties = new Properties();
		properties = GetAllPropertiesFromXML(xmlFilePath);
		properties.put(key, value);
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(xmlFilePath);
			properties.storeToXML(outputStream, "Temporary Properties");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 说明：添加键值对到xml文件 会删除原有文件的所有信息
	 * @param xmlFilePath xml文件路径
	 * @param key 属性键
	 * @param value 属性值
	 * @param isnew 是否新建 (true: 删除原有信息再新建, false: 不会删掉原有信息,原来有的则更新,没有的则添加)
	 * @return 成功与否(true|false)
	 */
	public static boolean AddKeyValueToXML(String xmlFilePath, String key, String value, boolean isnew) {
		Properties properties = new Properties();
		OutputStream outputStream = null;
		if (isnew) {
			properties.put(key, value);
		} else {
			properties = GetAllPropertiesFromXML(xmlFilePath);
			properties.put(key, value);
		}
		try {
			outputStream = new FileOutputStream(xmlFilePath);
			properties.storeToXML(outputStream, "Temporary Properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	/**
	 * ===========================================================================
	 * 读写xxx.properties配置文件部分
	 * ===========================================================================
	 */
	
	/**
	 * 说明：读取xxx.properties配置文件的所有属性
	 * @param propertiesFilePath xxx.properties文件路径
	 * @return Properties对象 (所有属性值)
	 */
	public static Properties GetAllPropertiesFromProperties(String propertiesFilePath) {
		Properties properties = new Properties();
        InputStream inputStream = null ;
        try {
			inputStream = new FileInputStream(propertiesFilePath);
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}
	
	/**
	 * 说明：读取xxx.properties配置文件的单个键值
	 * @param xmlFilePath xxx.properties文件路径
	 * @param key 属性键
	 * @return 属性值
	 */
	public static String GetPropertyFromProperties(String propertiesFilePath, String key) {
		return new Properties().getProperty(key);
	}

	/**
	 * 说明：创建xxx.properties配置文件,并写入信息 (不会删掉原有信息,原来有的则更新,没有的则添加)
	 * 
	 * @param propertiesFilePath xxx.properties文件路径
	 * @param hashtable 要写入的键值对Hashtable, 
	 * 			如：Hashtable<String, String> htKeyValue = new Hashtable<>();
	 * 				htKeyValue.put("name", "刘德财");
	 * @return 成功与否(true|false)
	 */
	public static boolean CreatePropertiesFile(String propertiesFilePath, Hashtable<String, String> hashtable) {
		Properties properties = new Properties();
		properties = GetAllPropertiesFromProperties(propertiesFilePath);
		properties.putAll(hashtable);
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(propertiesFilePath);
			properties.store(outputStream, "Temporary Properties");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 说明：创建xxx.properties配置文件,并写入信息 
	 * @param propertiesFilePath
	 * @param hashtable 要写入的键值对Hashtable, 
	 * @param isnew 是否新建 (true: 删除原有信息再新建, false: 不会删掉原有信息,原来有的则更新,没有的则添加)
	 * @return 成功与否(true|false)
	 */
	public static boolean CreatePropertiesFile(String propertiesFilePath, Hashtable<String, String> hashtable, boolean isnew) {
		Properties properties = new Properties();
		OutputStream outputStream = null;
		if (isnew) {
			properties.putAll(hashtable);
		} else {
			properties = GetAllPropertiesFromProperties(propertiesFilePath);
			properties.putAll(hashtable);
		}
		try {
			outputStream = new FileOutputStream(propertiesFilePath);
			properties.store(outputStream, "Temporary Properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 说明：添加键值对到xxx.properties文件 (不会删掉原有信息,原来有的则更新,没有的则添加)
	 * @param propertiesFilePath xxx.properties文件路径
	 * @param key 属性键
	 * @param value 属性值
	 * @return 成功与否(true|false)
	 */
	public static boolean AddKeyValueToProperties(String propertiesFilePath, String key, String value) {
		Properties properties = new Properties();
		properties = GetAllPropertiesFromProperties(propertiesFilePath);
		properties.put(key, value);
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(propertiesFilePath);
			properties.store(outputStream, "Temporary Properties");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 说明：添加键值对到xxx.properties文件 会删除原有文件的所有信息
	 * @param propertiesFilePath xxx.properties文件路径
	 * @param key 属性键
	 * @param value 属性值
	 * @param isnew 是否新建 (true: 删除原有信息再新建, false: 不会删掉原有信息,原来有的则更新,没有的则添加)
	 * @return 成功与否(true|false)
	 */
	public static boolean AddKeyValueToProperties(String propertiesFilePath, String key, String value, boolean isnew) {
		Properties properties = new Properties();
		OutputStream outputStream = null;
		if (isnew) {
			properties.put(key, value);
		} else {
			properties = GetAllPropertiesFromProperties(propertiesFilePath);
			properties.put(key, value);
		}
		try {
			outputStream = new FileOutputStream(propertiesFilePath);
			properties.store(outputStream, "Temporary Properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	
}
