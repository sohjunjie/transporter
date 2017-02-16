package com.transporter.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transporter.dao.CameraDao;
import com.transporter.model.Camera;
import com.transporter.model.Camera.CameraStatus;
import com.transporter.model.Camera.CameraType;
import com.transporter.service.CameraService;

@Service
public class CameraServiceImpl implements CameraService {

	@Autowired
    ServletContext context;

	@Autowired
	private CameraDao cameraDao;
	
	@Transactional
	public void add(int cameraId, Date dateInstalled, double longitude, double latitude, CameraType type, CameraStatus status) {
		Camera camera = new Camera(cameraId, dateInstalled, longitude, latitude, type, status);
		cameraDao.add(camera);
	}

	@Transactional
	public void edit(Camera camera) {
		cameraDao.edit(camera);
	}

	@Transactional
	public Camera getCamera(int cameraId) {
		return cameraDao.getCamera(cameraId);
	}

	@Transactional
	public void delete(int cameraId) {
		cameraDao.delete(cameraId);
	}

	@Transactional
	public List<Camera> getAllCamera() {
		return cameraDao.getAllCamera();
	}

	@Transactional
	public List<Camera> getAllSpeedCamera() {
		return cameraDao.getAllSpeedCamera();
	}

	@Transactional
	public List<Camera> getAllTrafficCamera() {
		return cameraDao.getAllTrafficCamera();
	}

	@Transactional
	public List<Camera> getAllPendingCamera() {
		return cameraDao.getAllPendingCamera();
	}

	@Transactional
	public List<Camera> getAllInstalledCamera() {
		return cameraDao.getAllInstalledCamera();
	}

	@Transactional
	public Long getCameraCount() {
		return cameraDao.getCameraCount();
	}

	@Transactional
	public Long getSpeedCameraCount() {
		return cameraDao.getSpeedCameraCount();
	}

	@Transactional
	public Long getTrafficCameraCount() {
		return cameraDao.getTrafficCameraCount();
	}

	@Transactional
	public Long getPendingCameraCount() {
		return cameraDao.getPendingCameraCount();
	}

	@Transactional
	public Long getInstalledCameraCount() {
		return cameraDao.getInstalledCameraCount();
	}

}
