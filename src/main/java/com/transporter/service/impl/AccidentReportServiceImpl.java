package com.transporter.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.transporter.dao.AccidentCauseDao;
import com.transporter.dao.AccidentReportDao;
import com.transporter.model.AccidentCause;
import com.transporter.model.AccidentReport;
import com.transporter.model.user.AuthenticatedUser;
import com.transporter.model.user.LTAPersonnel;
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
	@Autowired
	private AccidentCauseDao accidentCauseDao;
	// upload a full accident report
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
	// edit the accident report
	@Transactional
	public void edit(AccidentReport accidentReport) {
		accidentReportDao.edit(accidentReport);
	}
	// get the accident report based on its ID
	@Transactional
	public AccidentReport getAccidentReport(int reportId) {
		return accidentReportDao.getAccidentReport(reportId);
	}
	// delete an accident report with specific ID
	@Transactional
	public void delete(int reportId) {
		AccidentReport accidentReport = getAccidentReport(reportId);
		String imageLink = accidentReport.getImageLink();
		ImageUpload imageUpload = new ImageUpload(resourcePath);
		imageUpload.delete(imageLink, context);
		accidentReportDao.delete(reportId);
	}
	// list all the accident reports
	@Transactional
	public List<AccidentReport> getAllAccidentReport() {
		return accidentReportDao.getAllAccidentReport();
	}
	// get the number of pending reports
	@Transactional
	public Long getPendingAccidentCount() {
		return accidentReportDao.getPendingAccidentCount();
	}
	// list all of pending reports
	@Transactional
	public List<AccidentReport> getPendingAccidentReport() {
		return accidentReportDao.getPendingAccidentReport();
	}
	// allow authenticated user to approve a report with specific ID
	@Transactional
	public boolean approveAccidentReport(AuthenticatedUser authUser, int reportId) {
		if(!(authUser instanceof LTAPersonnel)) return false;
		AccidentReport accidentReport = this.getAccidentReport(reportId);
		accidentReport.setApprovedBy((LTAPersonnel) authUser);
		accidentReport.setApprovedDateTime(new Date());
		accidentReportDao.edit(accidentReport);
		return true;
	}
	// allow authenticated user to resolve a report with specific ID
	@Transactional
	public boolean resolveAccidentReport(AuthenticatedUser authUser, int reportId, int causeId, int numOfCasualties) {
		if(!(authUser instanceof LTAPersonnel)) return false;

		AccidentReport accidentReport = this.getAccidentReport(reportId);
		AccidentCause accidentCause = accidentCauseDao.getAccidentCause(causeId);

		accidentReport.setResolvedBy((LTAPersonnel) authUser);
		accidentReport.setResolvedDateTime(new Date());
		accidentReport.setOfficialCause(accidentCause);
		accidentReport.setNumOfCasualties(numOfCasualties);

		accidentReportDao.edit(accidentReport);
		return true;
	}
	// get the number of approved accidents
	@Transactional
	public Long getApprovedAccidentCount() {
		return accidentReportDao.getApprovedAccidentCount();
	}
	// get the list of approved accident reports
	@Transactional
	public List<AccidentReport> getApprovedAccidentReport() {
		return accidentReportDao.getApprovedAccidentReport();
	}
	// get the list of approved and resolved accident reports
	@Transactional
	public List<AccidentReport> getApprovedOrResolvedAccidentReport() {
		return accidentReportDao.getApprovedOrResolvedAccidentReport();
	}
	//	get the list of approved accidents reports based on a time interval
	@Transactional
	public List<AccidentReport> getApprovedAccidentReport(Date startDate, Date endDate) {
		return accidentReportDao.getApprovedAccidentReport(startDate, endDate);
	}
	// list all of resolved accident reports
	@Transactional
	public List<AccidentReport> getResolvedAccidentReport() {
		return accidentReportDao.getResolvedAccidentReport();
	}
	// get the list of approved and resolved accident reports based on a time interval
	@Transactional
	public List<AccidentReport> getApprovedAndResolvedAccidentReport(Date startDate, Date endDate) {
		return accidentReportDao.getApprovedAndResolvedAccidentReport(startDate, endDate);
	}
	// get list of resolved accident reports based on a time interval
	@Transactional
	public List<AccidentReport> getResolvedAccidentReport(Date startDate, Date endDate) {
		return accidentReportDao.getResolvedAccidentReport(startDate, endDate);
	}
}
