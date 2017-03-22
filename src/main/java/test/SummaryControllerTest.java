package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.transporter.controller.SummaryController;
import com.transporter.model.AccidentCause;
import com.transporter.model.AccidentReport;
import com.transporter.service.AccidentCauseService;
import com.transporter.service.AccidentReportService;
import com.transporter.service.SummaryReportService;

@RunWith(MockitoJUnitRunner.class)
public class SummaryControllerTest {
	
	@Mock
	private SummaryReportService summaryReportServiceMock = mock(SummaryReportService.class);
	@Mock
	private AccidentCauseService accidentCauseServiceMock = mock(AccidentCauseService.class);
	@Mock
	private AccidentReportService accidentReportServiceMock = mock(AccidentReportService.class);
	
	private SummaryController sc = new SummaryController();
	
	private AccidentReport rFirst = mock(AccidentReport.class);
	private AccidentReport rSecond = mock(AccidentReport.class);
	private AccidentReport rThird = mock(AccidentReport.class);
	private AccidentReport rFourth = mock(AccidentReport.class);
	private AccidentReport rFifth = mock(AccidentReport.class);
	private AccidentReport rSixth = mock(AccidentReport.class);
	
	private AccidentCause cFirst = mock(AccidentCause.class);
	private AccidentCause cSecond = mock(AccidentCause.class);
	
	String textStartDate;
	String textEndDate;
	String searchOption;
	
	@Before
	public void setup() {
		sc.setServices(accidentReportServiceMock, accidentCauseServiceMock, summaryReportServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void goSummaryCauseTest_ShouldReturnCorrectJspAndCauseListAndCountArray() {
		textStartDate = "s";
		textEndDate = "e";
		
		List<AccidentReport> accidentReports = Arrays.asList(rFirst, rSecond, rThird);
		List<AccidentCause> accidentCauses = Arrays.asList(cFirst, cSecond);
		
		int arr[] = {2,1};
		
		when(accidentCauseServiceMock.getAllAccidentCauses()).thenReturn(accidentCauses);
		
		when(sc.checkSearchForCause(textStartDate, textEndDate)).thenReturn(accidentReports);
		
		when(summaryReportServiceMock.summariseByCause(accidentReports, accidentCauses)).thenReturn(arr);
		Map<String, Object> map = new HashMap<String, Object>();
		String s = sc.goSummaryCause(textStartDate, textEndDate, map);
		
		assertEquals(s, "summary_cause");
		assertEquals(2, ((List<AccidentCause>) map.get("accidentCauses")).size());
		assertArrayEquals(arr, (int[]) map.get("causeCount"));
	}
	
	@Test
	public void goSummaryTimeTest_ShouldReturnCorrectJspAndCauseListAndCountArray() {
		String textStartDate = "s";
		String textEndDate = "e";
		String searchOption = "a";
		
		List<AccidentReport> accidentReports = Arrays.asList(rFirst, rSecond, rThird);
		
		when(sc.checkSearch(textStartDate, textEndDate, searchOption)).thenReturn(accidentReports);
		
		int[] arr = {2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1};
		
		when(summaryReportServiceMock.summariseByTime(accidentReports)).thenReturn(arr);
		Map<String, int[]> map = new HashMap<String, int[]>();
		String s = sc.goSummaryTime(textStartDate, textEndDate, map, searchOption);
		
		int[] hrAccidentCount = map.get("hrAccidentCount");
		int[] hrsOfDay = map.get("hrsOfDay");
		
		assertEquals(s, "summary_time");
		assertEquals(24, hrsOfDay.length);
		assertEquals(24, hrAccidentCount.length);
		assertArrayEquals(arr,hrAccidentCount);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void goSummaryLocationTest_ShouldReturnCorrectJspAndReportList() {
		String textStartDate = "s";
		String textEndDate = "e";
		String searchOption = "a";
		
		when(sc.checkSearch(textStartDate, textEndDate, searchOption)).thenReturn(Arrays.asList(rFirst, rSecond, rThird));
		
		Map<String, Object> map = new HashMap<String, Object>();
		String s = sc.goSummaryLocation(textStartDate, textEndDate, map, searchOption);
		
		assertEquals(s, "summary_location");
		assertEquals(3, ((List<AccidentReport>) map.get("accidentReports")).size());
	}
	
	@Test
	public void checkSearchTest_DatesWrongFormatSearchOptionNull_ShouldReturnAllAccidents() {
		String textStartDate = "a";
		String textEndDate = "b";
		String searchOption = null;
		
		Calendar startCalendar = new GregorianCalendar(2011,0,1);
		Calendar endCalendar = new GregorianCalendar(2012,0,1);
		Date startDate = startCalendar.getTime();
		Date endDate = endCalendar.getTime();
		
		when(accidentReportServiceMock.getApprovedAccidentReport()).thenReturn(Arrays.asList(rFirst));
		when(accidentReportServiceMock.getResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst,rSecond));
		when(accidentReportServiceMock.getApprovedAndResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst,rSecond,rThird));
		when(accidentReportServiceMock.getApprovedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst,rSecond,rThird,rFourth));
		when(accidentReportServiceMock.getResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst,rSecond,rThird,rFourth,rFifth));
		when(accidentReportServiceMock.getApprovedAndResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst,rSecond,rThird,rFourth,rFifth,rSixth));
		
		List<AccidentReport> result = sc.checkSearch(textStartDate,textEndDate,searchOption);
		assertEquals(3, result.size());
	}
	
	@Test
	public void checkSearchTest_DatesRightFormatSearchOptionCurrent_ShouldReturnCurrentAccidentsBetweenDates() {
		String textStartDate = "01/01/2011";
		String textEndDate = "01/01/2012";
		String searchOption = "current";
		
		Calendar startCalendar = new GregorianCalendar(2011,0,1);
		Calendar endCalendar = new GregorianCalendar(2012,0,1);
		Date startDate = startCalendar.getTime();
		Date endDate = endCalendar.getTime();
		
		when(accidentReportServiceMock.getApprovedAccidentReport()).thenReturn(Arrays.asList(rFirst));
		when(accidentReportServiceMock.getResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst,rSecond));
		when(accidentReportServiceMock.getApprovedAndResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst,rSecond,rThird));
		when(accidentReportServiceMock.getApprovedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst,rSecond,rThird,rFourth));
		when(accidentReportServiceMock.getResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst,rSecond,rThird,rFourth,rFifth));
		when(accidentReportServiceMock.getApprovedAndResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst,rSecond,rThird,rFourth,rFifth,rSixth));
		
		List<AccidentReport> result = sc.checkSearch(textStartDate,textEndDate,searchOption);
		assertEquals(4, result.size());
	}
	
	@Test
	public void checkSearchTest_DatesCorrectFormatSearchOptionBoth_ShouldReturnAllReportsBetweenDates() {
		String textStartDate = "01/01/2011";
		String textEndDate = "01/01/2012";
		String searchOption = "both";
		
		Calendar startCalendar = new GregorianCalendar(2011,0,1);
		Calendar endCalendar = new GregorianCalendar(2012,0,1);
		Date startDate = startCalendar.getTime();
		Date endDate = endCalendar.getTime();
		
		when(accidentReportServiceMock.getApprovedAccidentReport()).thenReturn(Arrays.asList(rFirst));
		when(accidentReportServiceMock.getResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst,rSecond));
		when(accidentReportServiceMock.getApprovedAndResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst,rSecond,rThird));
		when(accidentReportServiceMock.getApprovedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst,rSecond,rThird,rFourth));
		when(accidentReportServiceMock.getResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst,rSecond,rThird,rFourth,rFifth));
		when(accidentReportServiceMock.getApprovedAndResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst,rSecond,rThird,rFourth,rFifth,rSixth));
		
		List<AccidentReport> result = sc.checkSearch(textStartDate,textEndDate,searchOption);
		assertEquals(6, result.size());
	}
	
	@Test
	public void checkSearchTestForCause_DatesWrongFormat_ShouldReturnAllResolvedAccidents() {
		String textStartDate = "a";
		String textEndDate = "b";
		
		AccidentReport rFirst = mock(AccidentReport.class);
		AccidentReport rSecond = mock(AccidentReport.class);
		Calendar startCalendar = new GregorianCalendar(2011,0,1);
		Calendar endCalendar = new GregorianCalendar(2012,0,1);
		Date startDate = startCalendar.getTime();
		Date endDate = endCalendar.getTime();
		
		when(accidentReportServiceMock.getResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst));
		when(accidentReportServiceMock.getResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst,rSecond));
		List<AccidentReport> result = sc.checkSearchForCause(textStartDate,textEndDate);
		assertEquals(1, result.size());
	}
	
	@Test
	public void checkSearchTestForCause_DatesRightFormat_ShouldReturnAllResolvedAccidentsBetweenDates() {
		String textStartDate = "01/01/2011";
		String textEndDate = "01/01/2012";
		
		AccidentReport rFirst = mock(AccidentReport.class);
		AccidentReport rSecond = mock(AccidentReport.class);
		Calendar startCalendar = new GregorianCalendar(2011,0,1);
		Calendar endCalendar = new GregorianCalendar(2012,0,1);
		Date startDate = startCalendar.getTime();
		Date endDate = endCalendar.getTime();
		
		when(accidentReportServiceMock.getResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst));
		when(accidentReportServiceMock.getResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst,rSecond));
		List<AccidentReport> result = sc.checkSearchForCause(textStartDate,textEndDate);
		assertEquals(2, result.size());
	}
}