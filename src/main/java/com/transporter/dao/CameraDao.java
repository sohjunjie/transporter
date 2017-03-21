package com.transporter.dao;

import java.util.List;

import com.transporter.model.Camera;

public interface CameraDao {

	public void add(Camera camera);
	public void edit(Camera camera);
	public Camera getCamera(int cameraId);
	public void delete(int cameraId);
	
	public List<Camera> getAllCamera();
	public List<Camera> getAllSpeedCamera();
	public List<Camera> getAllTrafficCamera();
	public List<Camera> getAllPendingCamera();
	public List<Camera> getAllInstalledCamera();
	public List<Camera> getAllInstalledSpeedCamera();
	public List<Camera> getAllInstalledTrafficCamera();
	
	public Long getCameraCount();
	public Long getSpeedCameraCount();
	public Long getTrafficCameraCount();
	public Long getPendingCameraCount();
	public Long getInstalledCameraCount();
	
}
