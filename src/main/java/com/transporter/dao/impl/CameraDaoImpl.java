package com.transporter.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.transporter.dao.CameraDao;
import com.transporter.model.Camera;
import com.transporter.model.Camera.CameraStatus;
import com.transporter.model.Camera.CameraType;

@Repository
public class CameraDaoImpl implements CameraDao {

	@Autowired
	private SessionFactory session;
	
	//add new camera into the database
	public void add(Camera camera) {
		session.getCurrentSession().save(camera);
	}
	
	//edit existing camera details in the database
	public void edit(Camera camera) {
		session.getCurrentSession().update(camera);
	}
	
	//retrieve a particular camera from the database
	public Camera getCamera(int cameraId) {
		return (Camera)session.getCurrentSession().get(Camera.class, cameraId);
	}
	
	//delete a particular camera from the database
	public void delete(int cameraId) {
		session.getCurrentSession().delete(getCamera(cameraId));
	}
	
	//retrieve a list of all cameras from the database
	@SuppressWarnings("unchecked")
	public List<Camera> getAllCamera() {
		return session.getCurrentSession().createQuery("from Camera order by status").list();
	}
	
	//retrieve a list of speed cameras from the database
	@SuppressWarnings("unchecked")
	public List<Camera> getAllSpeedCamera() {
		return session.getCurrentSession().createQuery("from Camera "
				+ "where type=" + CameraType.SPEED.ordinal()).list();
	}
	
	//retrieve a list of traffic cameras from the database
	@SuppressWarnings("unchecked")
	public List<Camera> getAllTrafficCamera() {
		return session.getCurrentSession().createQuery("from Camera "
				+ "where type=" + CameraType.TRAFFIC.ordinal()).list();		
	}

	//retrieve a list of pending cameras from the database
	@SuppressWarnings("unchecked")
	public List<Camera> getAllPendingCamera() {
		return session.getCurrentSession().createQuery("from Camera "
				+ "where status=" + CameraStatus.PENDING.ordinal()).list();
	}

	//retrieve a list of installed cameras from the database
	@SuppressWarnings("unchecked")
	public List<Camera> getAllInstalledCamera() {
		return session.getCurrentSession().createQuery("from Camera "
				+ "where status=" + CameraStatus.INSTALLED.ordinal()).list();
	}
	
	//retrieve a list of installed speed cameras from the database
	@SuppressWarnings("unchecked")
	public List<Camera> getAllInstalledSpeedCamera() {
		return session.getCurrentSession().createQuery("from Camera "
				+ "where status=" + CameraStatus.INSTALLED.ordinal()
				+ " and type=" + CameraType.SPEED.ordinal()).list();
	}
		
	//retrieve a list of installed traffic cameras from the database
	@SuppressWarnings("unchecked")
	public List<Camera> getAllInstalledTrafficCamera() {
		return session.getCurrentSession().createQuery("from Camera "
				+ "where status=" + CameraStatus.INSTALLED.ordinal()
				+ " and type=" + CameraType.TRAFFIC.ordinal()).list();
	}

	//retrieve the total number of cameras, regardless of status and type
	public Long getCameraCount() {
		return (Long) session.getCurrentSession().createQuery("select count(*) from Camera ").uniqueResult();
	}

	//retrieve the number of speed camera
	public Long getSpeedCameraCount() {
		return (Long) session.getCurrentSession().createQuery("select count(*) from Camera "
				+ "where type=" + CameraType.SPEED.ordinal()).uniqueResult();
	}

	//retrieve the number of traffic camera
	public Long getTrafficCameraCount() {
		return (Long) session.getCurrentSession().createQuery("select count(*) from Camera "
				+ "where type=" + CameraType.TRAFFIC.ordinal()).uniqueResult();
	}

	//retrieve the number of pending cameras
	public Long getPendingCameraCount() {
		return (Long) session.getCurrentSession().createQuery("select count(*) from Camera "
				+ "where status=" + CameraStatus.PENDING.ordinal()).uniqueResult();
	}

	//retrieve the number of installed cameras
	public Long getInstalledCameraCount() {
		return (Long) session.getCurrentSession().createQuery("select count(*) from Camera "
				+ "where status=" + CameraStatus.INSTALLED.ordinal()).uniqueResult();
	}
	
}
