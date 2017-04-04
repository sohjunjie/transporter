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
	
	Calendar cal;
	Date date1;
	Date date2;
	
	Date startDate;
	Date endDate;
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
		
		cal = Calendar.getInstance();
		
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);
		date1 = cal.getTime();
		
		cal.set(Calendar.DATE, 11);
		date2 = cal.getTime();
		
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
		startDate = date1;
		endDate = date2;
		
		when(accidentCauseServiceMock.getAllAccidentCauses()).thenReturn(Arrays.asList(cFirst, cSecond,cThird,cFourth));
		when(sc.checkSearchForCause(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth, rSixth));
		
		Map<String, Object> map = new HashMap<String, Object>();
		String response = sc.goSummaryCause(startDate, endDate, map);
		
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
		Date startDate = date1;
		Date endDate = date2;
		String searchOption = "a";
		
		List<AccidentReport> accidentReports = Arrays.asList(rFirst, rSecond, rThird, rFourth);
		
		when(sc.checkSearch(startDate, endDate, searchOption)).thenReturn(accidentReports);
		
		Map<String, int[]> map = new HashMap<String, int[]>();
		String response = sc.goSummaryTime(startDate, endDate, map, searchOption);
		
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
		Date startDate = date1;
		Date endDate = date2;
		String searchOption = "a";
		
		when(sc.checkSearch(startDate, endDate, searchOption)).thenReturn(Arrays.asList(rFirst, rSecond, rThird));
		
		Map<String, Object> map = new HashMap<String, Object>();
		String response = sc.goSummaryLocation(startDate, endDate, map, searchOption);
		
		assertEquals(response, "summary_location");
		assertEquals(3, ((List<AccidentReport>) map.get("accidentReports")).size());
	}
	
	//test checkSearch method if dates and searchOption are null
	@Test
	public void checkSearchTest_DatesAndSearchOptionNull_ShouldReturnAllAccidents() {
		Date startDate = date1;
		Date endDate = date2;
		String searchOption = null;
		
		when(accidentReportServiceMock.getApprovedAccidentReport()).thenReturn(Arrays.asList(rFirst));
		when(accidentReportServiceMock.getResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst, rSecond));
		when(accidentReportServiceMock.getApprovedOrResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst, rSecond, rThird));
		when(accidentReportServiceMock.getApprovedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth));
		when(accidentReportServiceMock.getResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth));
		when(accidentReportServiceMock.getApprovedAndResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth, rSixth));
		
		List<AccidentReport> result = sc.checkSearch(startDate,endDate,searchOption);
		assertEquals(6, result.size());
	}
	
	//test checkSearch method if startDate and searchOption are null
	@Test
	public void checkSearchTest_StartDateAndSearchOptionNull_ShouldReturnAllAccidents() {
		Date startDate = null;
		Date endDate = date2;
		String searchOption = "both";

		when(accidentReportServiceMock.getApprovedAccidentReport()).thenReturn(Arrays.asList(rFirst));
		when(accidentReportServiceMock.getResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst, rSecond));
		when(accidentReportServiceMock.getApprovedOrResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst, rSecond, rThird));
		when(accidentReportServiceMock.getApprovedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth));
		when(accidentReportServiceMock.getResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth));
		when(accidentReportServiceMock.getApprovedAndResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth, rSixth));

		List<AccidentReport> result = sc.checkSearch(startDate,endDate,searchOption);
		assertEquals(3, result.size());
	}

	//test checkSearch method if dates are correct and searchOption is both
	@Test
	public void checkSearchTest_DatesCorrectAndSearchOptionBoth_ShouldReturnAllAccidentsBetweenDates() {
		Date startDate = date1;
		Date endDate = date2;
		String searchOption = "both";

		when(accidentReportServiceMock.getApprovedAccidentReport()).thenReturn(Arrays.asList(rFirst));
		when(accidentReportServiceMock.getResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst, rSecond));
		when(accidentReportServiceMock.getApprovedOrResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst, rSecond, rThird));
		when(accidentReportServiceMock.getApprovedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth));
		when(accidentReportServiceMock.getResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth));
		when(accidentReportServiceMock.getApprovedAndResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth, rSixth));

		List<AccidentReport> result = sc.checkSearch(startDate,endDate,searchOption);
		assertEquals(6, result.size());
	}

	//test checkSearch method if dates are null and searchOption is both
	@Test
	public void checkSearchTest_DatesNullAndSearchOptionBoth_ShouldReturnAllAccidentsBetweenDates() {
		Date startDate = date1;
		Date endDate = null;
		String searchOption = "both";

		when(accidentReportServiceMock.getApprovedAccidentReport()).thenReturn(Arrays.asList(rFirst));
		when(accidentReportServiceMock.getResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst, rSecond));
		when(accidentReportServiceMock.getApprovedOrResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst, rSecond, rThird));
		when(accidentReportServiceMock.getApprovedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth));
		when(accidentReportServiceMock.getResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth));
		when(accidentReportServiceMock.getApprovedAndResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth, rSixth));

		List<AccidentReport> result = sc.checkSearch(startDate,endDate,searchOption);
		assertEquals(3, result.size());
	}

	
	//test checkSearch method if dates are correct and searchOption is current
	@Test
	public void checkSearchTest_DatesCorrectAndSearchOptionCurrent_ShouldReturnCurrentAccidentsBetweenDates() {
		Date startDate = date1;
		Date endDate = date2;
		String searchOption = "current";

		when(accidentReportServiceMock.getApprovedAccidentReport()).thenReturn(Arrays.asList(rFirst));
		when(accidentReportServiceMock.getResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst, rSecond));
		when(accidentReportServiceMock.getApprovedOrResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst, rSecond, rThird));
		when(accidentReportServiceMock.getApprovedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth));
		when(accidentReportServiceMock.getResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth));
		when(accidentReportServiceMock.getApprovedAndResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth, rSixth));

		List<AccidentReport> result = sc.checkSearch(startDate,endDate,searchOption);
		assertEquals(4, result.size());
	}
	
	//test checkSearch method if dates are correct and searchOption is archived
	@Test
	public void checkSearchTest_DatesCorrectAndSearchOptionArchived_ShouldReturnArchivedAccidentsBetweenDates() {
		Date startDate = date1;
		Date endDate = date2;
		String searchOption = "archived";

		when(accidentReportServiceMock.getApprovedAccidentReport()).thenReturn(Arrays.asList(rFirst));
		when(accidentReportServiceMock.getResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst, rSecond));
		when(accidentReportServiceMock.getApprovedOrResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst, rSecond, rThird));
		when(accidentReportServiceMock.getApprovedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth));
		when(accidentReportServiceMock.getResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth));
		when(accidentReportServiceMock.getApprovedAndResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst, rSecond, rThird, rFourth, rFifth, rSixth));

		List<AccidentReport> result = sc.checkSearch(startDate,endDate,searchOption);
		assertEquals(5, result.size());
	}
	
	//test checkSearchForCause method if  enddate input is not parseable
	@Test
	public void checkSearchForCauseTest_StartDateWrongFormat_ShouldReturnAllResolvedAccidents() {
		Date startDate = null;
		Date endDate = date2;
		
		when(accidentReportServiceMock.getResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst));
		when(accidentReportServiceMock.getResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst,rSecond));
		List<AccidentReport> result = sc.checkSearchForCause(startDate, endDate);
		assertEquals(1, result.size());
	}
	
	//test checkSearchForCause method if  enddate input is not parseable
		@Test
		public void checkSearchForCauseTest_EndDateWrongFormat_ShouldReturnAllResolvedAccidents() {
			Date startDate = date1;
			Date endDate = null;
			
			when(accidentReportServiceMock.getResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst));
			when(accidentReportServiceMock.getResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst,rSecond));
			List<AccidentReport> result = sc.checkSearchForCause(startDate, endDate);
			assertEquals(1, result.size());
		}
	
	//check checkSearchForCause method if dates are correct
	@Test
	public void checkSearchForCauseTest_DatesRightFormat_ShouldReturnAllResolvedAccidentsBetweenDates() {
		Date startDate = date1;
		Date endDate = date2;

		when(accidentReportServiceMock.getResolvedAccidentReport()).thenReturn(Arrays.asList(rFirst));
		when(accidentReportServiceMock.getResolvedAccidentReport(startDate, endDate)).thenReturn(Arrays.asList(rFirst,rSecond));
		List<AccidentReport> result = sc.checkSearchForCause(startDate, endDate);
		assertEquals(2, result.size());
	}

	//check if findTopThreeHighestIndexInArray returns correct array
	@Test
	public void findTopThreeHighestIndexInArray_FirstAtLastPos_ShouldReturnCorrectOrder() {
		int arr[] =  {8, 5, 3, 9, 10};
		assertArrayEquals(new int[] {4,3,0},sc.findTopThreeHighestIndexInArray(arr));
	}
	
	//check if findTopThreeHighestIndexInArray returns correct array
		@Test
		public void findTopThreeHighestIndexInArray_Size3ArrayOrderTwoOneThree_ShouldReturnCorrectOrder() {
			int arr[] =  {3,8,2};
			assertArrayEquals(new int[] {1,0,2},sc.findTopThreeHighestIndexInArray(arr));
		}
}