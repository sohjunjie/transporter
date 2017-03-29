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
import com.transporter.service.impl.SummaryReportServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class SummaryControllerTest {
	
	private SummaryReportService summaryReportServiceMock = new SummaryReportServiceImpl();
	@Mock
	private AccidentCauseService accidentCauseServiceMock = mock(AccidentCauseService.class);
	@Mock
	private AccidentReportService accidentReportServiceMock = mock(AccidentReportService.class);
	
	private SummaryController sc = new SummaryController();
	
	private AccidentReport rFirst = new AccidentReport();
	private AccidentReport rSecond = new AccidentReport();
	private AccidentReport rThird = new AccidentReport();
	private AccidentReport rFourth = new AccidentReport();
	private AccidentReport rFifth = new AccidentReport();
	private AccidentReport rSixth = new AccidentReport();

	private AccidentCause cFirst = new AccidentCause();
	private AccidentCause cSecond = new AccidentCause();
	private AccidentCause cThird = new AccidentCause();
	private AccidentCause cFourth = new AccidentCause();
	
	String textStartDate;
	String textEndDate;
	String searchOption;
	
	@Before
	public void setup() {
		sc.setServices(accidentReportServiceMock, accidentCauseServiceMock, summaryReportServiceMock);
		cFirst.setCauseId(1);
		cSecond.setCauseId(2);
		cThird.setCauseId(3);
		cFourth.setCauseId(4);
		rFirst.setOfficialCause(cFirst);
		rSecond.setOfficialCause(cSecond);
		rThird.setOfficialCause(cThird);
		rFourth.setOfficialCause(cSecond);
		rFifth.setOfficialCause(cThird);
		rSixth.setOfficialCause(cFourth);
		
		Calendar cal = Calendar.getInstance();
		
		//00:30
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 30);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		rFirst.setAccidentDateTime(cal.getTime());
		
		//23:30
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 30);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		rSecond.setAccidentDateTime(cal.getTime());
		
		//12:00
		cal.set(Calendar.HOUR_OF_DAY, 12);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		rThird.setAccidentDateTime(cal.getTime());
		
		//12:59
		cal.set(Calendar.HOUR_OF_DAY, 12);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		rFourth.setAccidentDateTime(cal.getTime());
	}
	
	//Test summaryByCause method
	@SuppressWarnings("unchecked")
	@Test
	public void goSummaryCauseTest_ShouldReturnCorrectJspAndCauseListAndCountArray() {
		textStartDate = "s";
		textEndDate = "e";
		
		when(accidentCauseServiceMock.getAllAccidentCauses()).thenReturn(Arrays.asList(cFirst, cSecond,cThird,cFourth));
		when(sc.checkSearchForCause(textStartDate, textEndDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth, rSixth));
		
		Map<String, Object> map = new HashMap<String, Object>();
		String response = sc.goSummaryCause(textStartDate, textEndDate, map);
		
		List<AccidentCause> highestCauses = (List<AccidentCause>) map.get("topAccidentCauses");
		
		assertEquals(response, "summary_cause");
		assertEquals(4, ((List<AccidentCause>) map.get("accidentCauses")).size());
		assertArrayEquals(new int[] {1,2,2,1}, (int[]) map.get("causeCount"));
		assertArrayEquals(new int[] {2,2,1,1}, (int[]) map.get("topCauseCount"));
		assertEquals(cSecond,highestCauses.get(0));
		assertEquals(cThird,highestCauses.get(1));
		assertEquals(cFirst,highestCauses.get(2));
	}
	
	//test summaryByTime method
	@Test
	public void goSummaryTimeTest_ShouldReturnCorrectJspAndCauseListAndCountArray() {
		String textStartDate = "s";
		String textEndDate = "e";
		String searchOption = "a";
		
		List<AccidentReport> accidentReports = Arrays.asList(rFirst, rSecond, rThird, rFourth);
		
		when(sc.checkSearch(textStartDate, textEndDate, searchOption)).thenReturn(accidentReports);
		
		Map<String, int[]> map = new HashMap<String, int[]>();
		String response = sc.goSummaryTime(textStartDate, textEndDate, map, searchOption);
		
		int[] hrAccidentCount = map.get("hrAccidentCount");
		int[] hrsOfDay = map.get("hrsOfDay");
		
		assertEquals(response, "summary_time");
		assertEquals(24, hrsOfDay.length);
		assertEquals(24, hrAccidentCount.length);
		assertArrayEquals(new int[] {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, hrAccidentCount);
	}
	
	//Test summaryByLocation method
	@SuppressWarnings("unchecked")
	@Test
	public void goSummaryLocationTest_ShouldReturnCorrectJspAndReportList() {
		String textStartDate = "s";
		String textEndDate = "e";
		String searchOption = "a";
		
		when(sc.checkSearch(textStartDate, textEndDate, searchOption)).thenReturn(Arrays.asList(rFirst, rSecond, rThird));
		
		Map<String, Object> map = new HashMap<String, Object>();
		String response = sc.goSummaryLocation(textStartDate, textEndDate, map, searchOption);
		
		assertEquals(response, "summary_location");
		assertEquals(3, ((List<AccidentReport>) map.get("accidentReports")).size());
	}
	
	//test checkSearch method if dates are not parseable and searchOption is null
	@Test
	public void checkSearchTest_DatesWrongFormatSearchOptionNull_ShouldReturnAllAccidents() {
		String textStartDate = "a";
		String textEndDate = "b";
		String searchOption = null;
		
		Calendar startCalendar = new GregorianCalendar(2011, 0, 1);
		Calendar endCalendar = new GregorianCalendar(2012, 0, 1);
		Date startDate = startCalendar.getTime();
		Date endDate = endCalendar.getTime();
		
		when(accidentReportServiceMock.getApprovedAccidentReport()).thenReturn(Arrays.asList(rFirst));
		when(accidentReportServiceMock.getResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst, rSecond));
		when(accidentReportServiceMock.getApprovedOrResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst, rSecond, rThird));
		when(accidentReportServiceMock.getApprovedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth));
		when(accidentReportServiceMock.getResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth));
		when(accidentReportServiceMock.getApprovedAndResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth, rSixth));
		
		List<AccidentReport> result = sc.checkSearch(textStartDate,textEndDate,searchOption);
		assertEquals(3, result.size());
	}
	
	//test checkSearch method if date inputs are correct and searchOption is current
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
		when(accidentReportServiceMock.getResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst, rSecond));
		when(accidentReportServiceMock.getApprovedOrResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst, rSecond, rThird));
		when(accidentReportServiceMock.getApprovedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth));
		when(accidentReportServiceMock.getResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth));
		when(accidentReportServiceMock.getApprovedAndResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth, rSixth));
		
		List<AccidentReport> result = sc.checkSearch(textStartDate, textEndDate, searchOption);
		assertEquals(4, result.size());
	}
	//test checkSearch method if date inputs are correct and searchOption is both
	@Test
	public void checkSearchTest_DatesCorrectFormatSearchOptionBoth_ShouldReturnAllReportsBetweenDates() {
		String textStartDate = "01/01/2011";
		String textEndDate = "01/01/2012";
		String searchOption = "both";
		
		Calendar startCalendar = new GregorianCalendar(2011, 0, 1);
		Calendar endCalendar = new GregorianCalendar(2012, 0, 1);
		Date startDate = startCalendar.getTime();
		Date endDate = endCalendar.getTime();
		
		when(accidentReportServiceMock.getApprovedAccidentReport()).thenReturn(Arrays.asList(rFirst));
		when(accidentReportServiceMock.getResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst, rSecond));
		when(accidentReportServiceMock.getApprovedOrResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst, rSecond, rThird));
		when(accidentReportServiceMock.getApprovedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth));
		when(accidentReportServiceMock.getResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth));
		when(accidentReportServiceMock.getApprovedAndResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth, rSixth));
		
		List<AccidentReport> result = sc.checkSearch(textStartDate, textEndDate, searchOption);
		assertEquals(6, result.size());
	}
	
	//test checkSearchForCause method if date inputs are not parseable
	@Test
	public void checkSearchForCauseTest_DatesWrongFormat_ShouldReturnAllResolvedAccidents() {
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
		List<AccidentReport> result = sc.checkSearchForCause(textStartDate, textEndDate);
		assertEquals(1, result.size());
	}
	
	//check checkSearchForCause method if dates are correct
	@Test
	public void checkSearchForCauseTest_DatesRightFormat_ShouldReturnAllResolvedAccidentsBetweenDates() {
		String textStartDate = "01/01/2011";
		String textEndDate = "01/01/2012";
		
		AccidentReport rFirst = mock(AccidentReport.class);
		AccidentReport rSecond = mock(AccidentReport.class);
		Calendar startCalendar = new GregorianCalendar(2011, 0, 1);
		Calendar endCalendar = new GregorianCalendar(2012, 0, 1);
		Date startDate = startCalendar.getTime();
		Date endDate = endCalendar.getTime();

		when(accidentReportServiceMock.getResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst));
		when(accidentReportServiceMock.getResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst,rSecond));
		List<AccidentReport> result = sc.checkSearchForCause(textStartDate, textEndDate);
		assertEquals(2, result.size());
	}

	//check if findTopThreeHighestIndexInArray returns correct array
	@Test
	public void findTopThreeHighestIndexInArray_ShouldReturnCorrectOrder() {
		int arr[] =  {8, 5, 3, 9, 10};
		assertArrayEquals(new int[] {4,3,0},sc.findTopThreeHighestIndexInArray(arr));
	}
	
}