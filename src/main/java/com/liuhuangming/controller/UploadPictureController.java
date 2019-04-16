package com.liuhuangming.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.liuhuangming.bean.Upload;

/**
 * 用户上传游记图片控制器
 * @author Administrator
 *
 */
@RestController
@RequestMapping("")
public class UploadPictureController {
	/**
	 * 处理上传过来的图片集合
	 * @param request
	 * @return
	 */
	@RequestMapping("uploadImage")
	public List<String> uploadImage( HttpServletRequest request) {
		
	    CommonsMultipartResolver cmr = new CommonsMultipartResolver(
	            request.getServletContext());
	    if (cmr.isMultipart(request)) {
	        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (request);
	        Iterator<String> files = mRequest.getFileNames();
	        List<String> imgPathList = new ArrayList<>();
	        while (files.hasNext()) {
	            MultipartFile mFile = mRequest.getFile(files.next());
	            if (mFile != null) {
	                String fileName = UUID.randomUUID() + mFile.getOriginalFilename();
	                String path = Upload.uploadFile(mFile);
	                System.err.println(path);
	                imgPathList.add(path);
	            }
	        }
	        return imgPathList;
	    }
	 
	    return null;
	}
}
