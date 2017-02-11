package com.transporter.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.springframework.web.multipart.MultipartFile;

public class ImageUpload {

	private static final String UPLOADPATH = "/resources/uploads";
	
	public String save(MultipartFile file, String saveToPath, ServletContext context){
		if (!file.isEmpty()) {
			String imgType = getFileExtension(file.getOriginalFilename());
			String saveFileName = file.hashCode() + Calendar.getInstance().hashCode() + "." + imgType;
			File destination = new File(context.getRealPath(UPLOADPATH + saveToPath) + "/" + saveFileName);
			BufferedImage src;
			try {
				src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
				ImageIO.write(src, imgType, destination);
				return UPLOADPATH + saveToPath + "/" + saveFileName;
			} catch (IOException e) {}
		}
		return "";
	}

	private String getFileExtension(String originalFileName){
		String[] splitFileName = originalFileName.split("\\.");
		return splitFileName[splitFileName.length-1];
	}
	
}
