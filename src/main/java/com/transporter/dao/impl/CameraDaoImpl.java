package com.transporter.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.transporter.dao.CameraDao;
import com.transporter.model.AccidentReport;
import com.transporter.model.Camera;
import com.transporter.model.Camera.CameraStatus;
import com.transporter.model.Camera.CameraType;

public class CameraDaoImpl implements CameraDao {

	@Autowired
	private SessionFactory session;
	
	public void add(Camera camera) {
		session.getCurrentSession().save(camera);
	}
	
	public void edit(Camera camera) {
		session.getCurrentSession().update(camera);
	}
	
	public Camera getCamera(int cameraId) {
		return (Camera)session.getCurrentSession().get(Camera.class, cameraId);
	}
	
	public void delete(int cameraId) {
		session.getCurrentSession().delete(getCamera(cameraId));
	}
	
	@SuppressWarnings("unchecked")
	public List<Camera> getAllCamera() {
		return session.getCurrentSession().createQuery("from Camera").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Camera> getAllSpeedCamera() {
		return session.getCurrentSession().createQuery("from Camera "
				+ "where type="+CameraType.SPEED).list();
	}
	@SuppressWarnings("unchecked")
	public List<Camera> getAllTrafficCamera() {
		return session.getCurrentSession().createQuery("from Camera "
				+ "where type="+CameraType.TRAFFIC).list();
	}
	@SuppressWarnings("unchecked")
	public List<Camera> getAllPendingCamera() {
		return session.getCurrentSession().createQuery("from Camera "
				+ "where status="+CameraStatus.PENDING).list();
	}
	@SuppressWarnings("unchecked")
	public List<Camera> getAllInstalledCamera() {
		return session.getCurrentSession().createQuery("from Camera "
				+ "where status="+CameraStatus.INSTALLED).list();
	}
	
	public Long getCameraCount() {
		return (Long) session.getCurrentSession().createQuery("select count(*) from Camera ").uniqueResult();
	}
	public Long getSpeedCameraCount() {
		return (Long) session.getCurrentSession().createQuery("select count(*) from Camera "
				+ "where type="+CameraType.SPEED).uniqueResult();
	}
	public Long getTrafficCameraCount() {
		return (Long) session.getCurrentSession().createQuery("select count(*) from Camera "
				+ "where type="+CameraType.TRAFFIC).uniqueResult();
	}
	public Long getPendingCameraCount() {
		return (Long) session.getCurrentSession().createQuery("select count(*) from Camera "
				+ "where status="+CameraStatus.PENDING).uniqueResult();
	}
	public Long getInstalledCameraCount() {
		return (Long) session.getCurrentSession().createQuery("select count(*) from Camera "
				+ "where status="+CameraStatus.INSTALLED).uniqueResult();
	}
	
}
