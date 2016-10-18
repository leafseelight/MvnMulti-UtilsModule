package com.liudecai.utils.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * @description: 文件读写工具类
 * @author LiuDeCai
 * @E_mail 1911939348@qq.com
 * 
 */
public class FileIOUtils {

	/**
	 * 创建目录
	 * 
	 * @param savePath
	 *            文件或者路径(D:/App/test/test.txt或者D:/App/test/test/)
	 */
	public static void markDir(String savePath) {
		String path = savePath.substring(0, savePath.lastIndexOf("/") + 1);
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * @param fileSavePath
	 *            文件路径(D:/App/test/test.txt)
	 * @param object
	 *            要保存的对象(对象或者字符串都可以)
	 */
	public static void saveObject2File(String fileSavePath, Object object) {

		markDir(fileSavePath);

		File file = new File(fileSavePath);
		FileOutputStream out = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			out = new FileOutputStream(file);
			// osw = new OutputStreamWriter(out,Charset.forName("GBK"));
			osw = new OutputStreamWriter(out, Charset.forName("UTF-8"));
			bw = new BufferedWriter(osw);
			bw.append(object.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null || osw != null || out != null) {
				try {
					bw.close();
					osw.close();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					bw = null;
					osw = null;
					out = null;
				}
			}
		}
	}

}
