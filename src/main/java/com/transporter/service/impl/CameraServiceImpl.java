package com.transporter.service.impl;

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
	// add a new camera with its details such as location, typte, status,...	
	@Transactional
	public void add(int cameraId, double longitude, double latitude, CameraType type, CameraStatus status) {
		Camera camera = new Camera(cameraId, longitude, latitude, type, status);
		cameraDao.add(camera);
	}
	// edit the details of an existing camera
	@Transactional
	public void edit(Camera camera) {
		cameraDao.edit(camera);
	}
	// get the details of a camera based on its ID number
	@Transactional
	public Camera getCamera(int cameraId) {
		return cameraDao.getCamera(cameraId);
	}
	// delete a camera based on its ID number
	@Transactional
	public void delete(int cameraId) {
		cameraDao.delete(cameraId);
	}
	// add a new camera with details and it will return the result if the camera is added
	@Transactional
	public boolean addNewCamera(double lat, double lng, String formattedAddress, int cameraTypeOrdinal){
		Camera camera = new Camera();
		camera.setLatitude(lat);
		camera.setLongitude(lng);
		camera.setFormattedAddress(formattedAddress);
		camera.setStatus(CameraStatus.PENDING);
		camera.setType(CameraType.values()[cameraTypeOrdinal]);
		cameraDao.add(camera);
		return true;
	}
	// set the status of the camera and it will return if the status is updated
	public boolean setCameraStatus(int cameraId, int cameraStatusOrdinal){
		Camera camera = cameraDao.getCamera(cameraId);
		if(camera == null) return false;
		camera.setStatus(CameraStatus.values()[cameraStatusOrdinal]);
		cameraDao.edit(camera);
		return true;
	}
	// set the type of camera with an ID and it will return if the type is updated
	public boolean setCameraType(int cameraId, int cameraTypeOrdinal){
		Camera camera = cameraDao.getCamera(cameraId);
		if(camera == null) return false;
		camera.setType(CameraType.values()[cameraTypeOrdinal]);
		cameraDao.edit(camera);
		return true;
	}
	// get the list of all cameras	
	@Transactional
	public List<Camera> getAllCamera() {
		return cameraDao.getAllCamera();
	}
	// return the list of all speed cameras
	@Transactional
	public List<Camera> getAllSpeedCamera() {
		return cameraDao.getAllSpeedCamera();
	}
	// return the list of all traffic cameras
	@Transactional
	public List<Camera> getAllTrafficCamera() {
		return cameraDao.getAllTrafficCamera();
	}
	// return the list of all pending cameras
	@Transactional
	public List<Camera> getAllPendingCamera() {
		return cameraDao.getAllPendingCamera();
	}
	// return the list of all installed cameras
	@Transactional
	public List<Camera> getAllInstalledCamera() {
		return cameraDao.getAllInstalledCamera();
	}
	// get the number of camera
	@Transactional
	public Long getCameraCount() {
		return cameraDao.getCameraCount();
	}
	// get the number of speed camera
	@Transactional
	public Long getSpeedCameraCount() {
		return cameraDao.getSpeedCameraCount();
	}
	// get  the number of traffic camera
	@Transactional
	public Long getTrafficCameraCount() {
		return cameraDao.getTrafficCameraCount();
	}
	// get the number of pending camera
	@Transactional
	public Long getPendingCameraCount() {
		return cameraDao.getPendingCameraCount();
	}
	// get the number of installed camera
	@Transactional
	public Long getInstalledCameraCount() {
		return cameraDao.getInstalledCameraCount();
	}

}
