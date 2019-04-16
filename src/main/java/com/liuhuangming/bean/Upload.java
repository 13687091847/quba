package com.liuhuangming.bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.GenericRequest;

public class Upload {

	private static final String ENDPOINT = "oss-cn-shanghai.aliyuncs.com";
	private static final String ACCESS_KEY_ID = "LTAIUAD3kRsfXt0y";
	private static final String ACCESS_KEY_ID_SECRET = "gpLbowd0hQTU4IxNd82V2WbsGARDvH";
	private static final String BUCKET_NAME = "quba";
	
	/**
	 * 将用户上传的图片保存到oss对象存储，并返回该图片的url
	 * @param file
	 * @return
	 */
	public static String uploadFile(MultipartFile file) {
		if (file.getSize() > 10 * 1024 * 1024) {
			return "上传图片大小不能超过10M！";
		}
		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_ID_SECRET);
		try {
			InputStream inputStream = file.getInputStream();
			// 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
			ossClient.putObject(BUCKET_NAME, file.getOriginalFilename(), inputStream);
			//返回图片在oss上生成的url
			return ("https://"+BUCKET_NAME+"."+ENDPOINT.replace("http://", "http://" + BUCKET_NAME + ".") + "/" + file.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭OSSClient。
			ossClient.shutdown();
		}
		return null;
	}
	/**
	 * 根据url去oss删除文件
	 * @param fileUrl
	 * @return
	 */
	public static boolean deleteFile(String fileUrl) {
		String fileName = getFileName(fileUrl); // 根据url获取fileName
		if (BUCKET_NAME == null || fileName == null)
			return false;
		OSSClient ossClient = null;
		try {
			// 创建OSSClient实例。
			ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_ID_SECRET);
			GenericRequest request = new DeleteObjectsRequest(BUCKET_NAME).withKey(fileName);
			ossClient.deleteObject(request);
		} catch (Exception oe) {
			oe.printStackTrace();
			return false;
		} finally {
			ossClient.shutdown();
		}
		return true;
	}
	/**
	 * 
	 * @MethodName: getFileName
	 * @Description: 根据url获取fileName
	 * @param fileUrl
	 *            文件url
	 * @return String fileName
	 */
	private static String getFileName(String fileUrl) {
		String str = "aliyuncs.com/";
		int beginIndex = fileUrl.indexOf(str);
		if (beginIndex == -1)
			return null;
		return fileUrl.substring(beginIndex + str.length());
	}
}
