package com.transporter.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.transporter.dao.AccidentReportDao;
import com.transporter.model.AccidentReport;
import com.transporter.service.AccidentReportService;
import com.transporter.util.ImageUpload;

@Service
public class AccidentReportServiceImpl implements AccidentReportService {

	@Value("${accident.imageuploads}")
	private String saveToPath;

	@Value("${project.resources}")
	private String resourcePath;

    @Autowired
    ServletContext context;

	@Autowired
	private AccidentReportDao accidentReportDao;

	@Transactional
	public boolean add(String accidentLocation, double lat, double lng, Date accidentDateTime, String accidentDescription, MultipartFile accidentImage) {

		ImageUpload imageUpload = new ImageUpload(resourcePath);
		String savePath = imageUpload.save(accidentImage, saveToPath, context);
		if(!savePath.isEmpty()){
			AccidentReport accidentReport = new AccidentReport();
			accidentReport.setFormattedAddress(accidentLocation);
			accidentReport.setLatitude(lat);
			accidentReport.setLongitude(lng);
			accidentReport.setAccidentDateTime(accidentDateTime);
			accidentReport.setDescription(accidentDescription);
			accidentReport.setImageLink(savePath);
			accidentReportDao.add(accidentReport);
			return true;
		}
		return false;
	}

	@Transactional
	public void edit(AccidentReport accidentReport) {
		accidentReportDao.edit(accidentReport);
	}

	@Transactional
	public AccidentReport getAccidentReport(int reportId) {
		return accidentReportDao.getAccidentReport(reportId);
	}

	@Transactional
	public void delete(int reportId) {
		AccidentReport accidentReport = getAccidentReport(reportId);
		String imageLink = accidentReport.getImageLink();
		ImageUpload imageUpload = new ImageUpload(resourcePath);
		imageUpload.delete(imageLink, context);
		accidentReportDao.delete(reportId);
	}

	@Transactional
	public List<AccidentReport> getAllAccidentReport() {
		return accidentReportDao.getAllAccidentReport();
	}

	@Transactional
	public Long getPendingAccidentCount() {
		return accidentReportDao.getPendingAccidentCount();
	}

	@Transactional
	public List<AccidentReport> getPendingAccidentReport() {
		return accidentReportDao.getPendingAccidentReport();
	}

}
