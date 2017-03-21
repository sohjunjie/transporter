package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.transporter.controller.SummaryController;
import com.transporter.model.AccidentCause;
import com.transporter.model.AccidentReport;
import com.transporter.model.Camera;
import com.transporter.service.AccidentCauseService;
import com.transporter.service.AccidentReportService;
import com.transporter.service.AuthenticatedUserService;
import com.transporter.service.CameraService;
import com.transporter.service.SummaryReportService;

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
	private AccidentReport rFifth = mock(AccidentReport.class);
	private AccidentReport rSixth = mock(AccidentReport.class);
	
	private AccidentCause cFirst = mock(AccidentCause.class);
	private AccidentCause cSecond = mock(AccidentCause.class);
	
	private Camera speedFirst = mock(Camera.class);
	private Camera speedSecond = mock(Camera.class);
	private Camera speedThird = mock(Camera.class);
	
	private Camera trafficFirst = mock(Camera.class);
	private Camera trafficSecond = mock(Camera.class);
	
	@Before
	public void setup() {
		bc.setServices(authUserServiceMock, accidentReportServiceMock, accidentCauseServiceMock, 
				cameraServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void goMainPage_ShouldReturnHomeAndCorrectMapValues() {
		when(accidentReportServiceMock.getApprovedAccidentReport()).thenReturn(Arrays.asList(rFirst,rSecond,rThird,rFourth));
		when(cameraServiceMock.getAllInstalledSpeedCamera()).thenReturn(Arrays.asList(speedFirst,speedSecond,speedThird));
		when(cameraServiceMock.getAllInstalledTrafficCamera()).thenReturn(Arrays.asList(trafficFirst,trafficSecond));
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String s = bc.goMainPage(map, httpSession);
		
		assertEquals(s, "home");
		assertEquals(4, ((List<AccidentReport>) map.get("currentReports")).size());
		assertEquals(3, ((List<Camera>) map.get("speedCameras")).size());
		assertEquals(2, ((List<Camera>) map.get("trafficCameras")).size());
	}
}