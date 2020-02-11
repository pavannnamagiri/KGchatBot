package com.avantari.controller;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avantari.service.s3UploadService;


@Controller
public class fileUploadController {

	@ResponseBody
	@RequestMapping("test/fileUpload")
	public String uploadFile(HttpServletRequest request) throws Exception {

		ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
		List<FileItem> elements = servletFileUpload.parseRequest(request);
		FileItem item = elements.get(0);
		String filename = item.getName();

		InputStream ios = item.getInputStream();

		System.out.println(filename);
		
		s3UploadService s3upload= new s3UploadService();
		s3upload.s3Upload(item, filename, ios);

		return  filename;

	}

}
