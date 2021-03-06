package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.transporter.controller.BaseController;
import com.transporter.model.AccidentCause;
import com.transporter.model.AccidentReport;
import com.transporter.model.Camera;
import com.transporter.service.AccidentCauseService;
import com.transporter.service.AccidentReportService;
import com.transporter.service.AuthenticatedUserService;
import com.transporter.service.CameraService;

@RunWith(MockitoJUnitRunner.class)
public class BaseControllerTest {
	
	@Mock
	private AuthenticatedUserService authUserServiceMock = mock(AuthenticatedUserService.class);
	@Mock
	private AccidentCauseService accidentCauseServiceMock = mock(AccidentCauseService.class);
	@Mock
	private AccidentReportService accidentReportServiceMock = mock(AccidentReportService.class);
	@Mock
	private CameraService cameraServiceMock = mock(CameraService.class);
	@Mock
	private HttpSession httpSession = mock(HttpSession.class);
	
	private BaseController bc = new BaseController();
	
	private AccidentReport rFirst = mock(AccidentReport.class);
	private AccidentReport rSecond = mock(AccidentReport.class);
	private AccidentReport rThird = mock(AccidentReport.class);
	private AccidentReport rFourth = mock(AccidentReport.class);

	private AccidentCause cFirst = mock(AccidentCause.class);
	private AccidentCause cSecond = mock(AccidentCause.class);

	private Camera cameraFirst = mock(Camera.class);
	private Camera cameraSecond = mock(Camera.class);
	private Camera cameraThird = mock(Camera.class);
	
	private String response;
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	@Before
	public void setup() {
		bc.setServices(authUserServiceMock, accidentReportServiceMock, accidentCauseServiceMock, 
				cameraServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void goMainPage_ShouldReturnHomeAndCorrectMapValues() {
		when(accidentReportServiceMock.getApprovedAccidentReport()).thenReturn(Arrays.asList(rFirst,rSecond,rThird,rFourth));
		
		response = bc.goMainPage(map, httpSession);

		assertEquals(response, "home");
		assertEquals(4, ((List<AccidentReport>) map.get("currentReports")).size());
	}
	
	@Test
	public void goAccidentReportViewPending_UserNotAuthenticated_ShouldReturnRedirect() {
		when(authUserServiceMock.isAuthenticated(httpSession)).thenReturn(false);
		when(accidentReportServiceMock.getPendingAccidentReport()).thenReturn(Arrays.asList(rFirst,rSecond));
		
		response = bc.goAccidentReportViewPending(map, httpSession);
		
		assertEquals(response, "redirect:/");
		assertTrue(map.isEmpty());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void goAccidentReportViewPending_UserAuthenticated_ShouldReturnPendingAccidents() {
		when(authUserServiceMock.isAuthenticated(httpSession)).thenReturn(true);
		when(accidentReportServiceMock.getPendingAccidentReport()).thenReturn(Arrays.asList(rFirst,rSecond));
		
		response = bc.goAccidentReportViewPending(map, httpSession);
		
		assertEquals(response, "accident_view_pending");
		assertEquals(2, ((List<AccidentReport>) map.get("pendingAccidents")).size());
	}
	
	@Test
	public void goAccidentReportResolveApproved_UserNotAuthenticated_ShouldReturnRedirect() {
		when(authUserServiceMock.isAuthenticated(httpSession)).thenReturn(false);
		when(accidentReportServiceMock.getApprovedAccidentReport()).thenReturn(Arrays.asList(rFirst,rSecond,rThird));
		when(accidentCauseServiceMock.getAllAccidentCauses()).thenReturn(Arrays.asList(cFirst,cSecond));
		
		response = bc.goAccidentReportResolveApproved(map, httpSession);
		
		assertEquals(response, "redirect:/");
		assertTrue(map.isEmpty());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void goAccidentReportResolveApproved_UserAuthenticated_ShouldReturnPendingAccidents() {
		when(authUserServiceMock.isAuthenticated(httpSession)).thenReturn(true);
		when(accidentReportServiceMock.getApprovedAccidentReport()).thenReturn(Arrays.asList(rFirst,rSecond,rThird));
		when(accidentCauseServiceMock.getAllAccidentCauses()).thenReturn(Arrays.asList(cFirst,cSecond));
		
		response = bc.goAccidentReportResolveApproved(map, httpSession);
		
		assertEquals(response, "accident_view_approved");
		assertEquals(3, ((List<AccidentReport>) map.get("approvedAccidents")).size());
		assertEquals(2, ((List<AccidentReport>) map.get("accidentCauses")).size());
	}
	
	@Test
	public void goSuggestCameraPage_UserNotAuthenticated_ShouldReturnRedirect() {
		when(authUserServiceMock.isAuthenticated(httpSession)).thenReturn(false);
		when(cameraServiceMock.getAllCamera()).thenReturn(Arrays.asList(cameraFirst,cameraSecond,cameraThird));
		
		response = bc.goSuggestCameraPage(map, httpSession);
		
		assertEquals(response, "redirect:/");
		assertTrue(map.isEmpty());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void goSuggestCameraPage_UserAuthenticated_ShouldReturnPendingAccidents() {
		when(authUserServiceMock.isAuthenticated(httpSession)).thenReturn(true);

		when(cameraServiceMock.getAllCamera()).thenReturn(Arrays.asList(cameraFirst,cameraSecond,cameraThird));

		response = bc.goSuggestCameraPage(map, httpSession);
		
		assertEquals(response, "camera_suggest");
		assertEquals(3, ((List<Camera>) map.get("enforcementCamera")).size());
	}
	
	@Test
	public void goManageCameraPage_UserNotAuthenticated_ShouldReturnRedirect() {
		when(authUserServiceMock.isAuthenticated(httpSession)).thenReturn(false);
		when(cameraServiceMock.getAllCamera()).thenReturn(Arrays.asList(cameraFirst,cameraSecond,cameraThird));
		
		response = bc.goManageCameraPage(map, httpSession);
		
		assertEquals(response, "redirect:/");
		assertTrue(map.isEmpty());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void goManageCameraPage_UserAuthenticated_ShouldReturnCameraManage() {
		when(authUserServiceMock.isAuthenticated(httpSession)).thenReturn(true);
		when(cameraServiceMock.getAllCamera()).thenReturn(Arrays.asList(cameraFirst,cameraSecond,cameraThird));
		
		response = bc.goManageCameraPage(map, httpSession);
		
		assertEquals(response, "camera_manage");
		assertEquals(3, ((List<Camera>) map.get("enforcementCamera")).size());
	}
	
	@Test
	public void logout_ShouldInvalidateSessionAndReturnRedirect() {
		response = bc.logout(httpSession);
		verify(httpSession).invalidate();
		assertEquals(response, "redirect:/");
	}

}