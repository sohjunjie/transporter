package com.transporter.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.transporter.dao.AccidentReportDao;
import com.transporter.dao.CameraDao;
import com.transporter.model.Camera;
import com.transporter.model.Camera.CameraStatus;
import com.transporter.model.Camera.CameraType;
import com.transporter.service.CameraService;

public class CameraServiceImpl implements CameraService {

	@Autowired
    ServletContext context;

	@Autowired
	private CameraDao cameraDao;
	
	@Override
	public void add(int cameraId, Date dateInstalled, double longitude, double latitude, CameraType type, CameraStatus status) {
		Camera camera = new Camera(cameraId, dateInstalled, longitude, latitude, type, status);
		cameraDao.add(camera);
	}

	@Override
	public void edit(Camera camera) {
		cameraDao.edit(camera);
	}

	@Override
	public Camera getCamera(int cameraId) {
		return cameraDao.getCamera(cameraId);
	}

	@Override
	public void delete(int cameraId) {
		cameraDao.delete(cameraId);
	}

	@Override
	public List<Camera> getAllCamera() {
		return cameraDao.getAllCamera();
	}

	@Override
	public List<Camera> getAllSpeedCamera() {
		return cameraDao.getAllSpeedCamera();
	}

	@Override
	public List<Camera> getAllTrafficCamera() {
		return cameraDao.getAllTrafficCamera();
	}

	@Override
	public List<Camera> getAllPendingCamera() {
		return cameraDao.getAllPendingCamera();
	}

	@Override
	public List<Camera> getAllInstalledCamera() {
		return cameraDao.getAllInstalledCamera();
	}

	@Override
	public Long getCameraCount() {
		return cameraDao.getCameraCount();
	}

	@Override
	public Long getSpeedCameraCount() {
		return cameraDao.getSpeedCameraCount();
	}

	@Override
	public Long getTrafficCameraCount() {
		return cameraDao.getTrafficCameraCount();
	}

	@Override
	public Long getPendingCameraCount() {
		return cameraDao.getPendingCameraCount();
	}

	@Override
	public Long getInstalledCameraCount() {
		return cameraDao.getInstalledCameraCount();
	}

}
