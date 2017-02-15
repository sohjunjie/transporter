package com.transporter.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class ImageUpload {

	private static final String UPLOADPATH = "/uploads";

	private String resourcePath;

	@Autowired
	public ImageUpload(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String save(MultipartFile file, String saveToPath, ServletContext context){
		if (!file.isEmpty()) {
			String imgType = getFileExtension(file.getOriginalFilename());
			String saveFileName = file.hashCode() + "_" + Calendar.getInstance().hashCode() + "." + imgType;
			File destination = new File(context.getRealPath(resourcePath + UPLOADPATH + saveToPath) + "/" + saveFileName);
			BufferedImage src;
			try {
				src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
				ImageIO.write(src, imgType, destination);
				return "/uploads" + saveToPath + "/" + saveFileName;
			} catch (IOException e) {}
		}
		return "";
	}

	private String getFileExtension(String originalFileName){
		String[] splitFileName = originalFileName.split("\\.");
		return splitFileName[splitFileName.length-1];
	}
	
}
