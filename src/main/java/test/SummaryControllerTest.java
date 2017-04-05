package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
import org.mockito.Mockito;
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
		
		//date1 is 5/4/2017
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, 4);
		cal.set(Calendar.DATE, 5);
		date1 = cal.getTime();
		
		//date2 is 6/4/2017
		cal.set(Calendar.DATE, 6);
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
	//sortFirstThree test case 1
	@Test
	public void sortFirstThreeTest_Input213_Expected201() {
		int [] testInput = {2,1,3};
		int [] expectedResult = {2,0,1};
		assertArrayEquals(expectedResult,sc.sortFirstThree(testInput));
	}
	
	//sortFirstThree test case 2
	@Test
	public void sortFirstThreeTest_Input123_Expected210() {
		int [] testInput = {1,2,3};
		int [] expectedResult = {2,1,0};
		assertArrayEquals(expectedResult,sc.sortFirstThree(testInput));
	}
	
	//sortFirstThree test case 3
	@Test
	public void sortFirstThreeTest_Input312_Expected021() {
		int [] testInput = {3,1,2};
		int [] expectedResult = {0,2,1};
		assertArrayEquals(expectedResult,sc.sortFirstThree(testInput));
	}
	
	//sortFirstThree test case 4
	@Test
	public void sortFirstThreeTest_Input321_Expected012() {
		int [] testInput = {3,2,1};
		int [] expectedResult = {0,1,2};
		assertArrayEquals(expectedResult,sc.sortFirstThree(testInput));
	}
	
	//findTopThreeHighestIndexInArray test case 1
	@Test
	public void findTopThreeHighestIndexInArrayTest_Input246andIndexOfOthersIsNegative1_Expected210() {
		int [] testInput = {2,4,6};
		int [] expectedResult = {2,1,0};
		assertArrayEquals(expectedResult,sc.findTopThreeHighestIndexInArray(testInput,-1));
	}
	
	//findTopThreeHighestIndexInArray test case 2
	@Test
	public void findTopThreeHighestIndexInArrayTest_Input2468andIndexOfOthersIs3_Expected210() {
		int [] testInput = {2,4,6,8};
		int [] expectedResult = {2,1,0};
		assertArrayEquals(expectedResult,sc.findTopThreeHighestIndexInArray(testInput,3));
	}
	
	//findTopThreeHighestIndexInArray test case 3
	@Test
	public void findTopThreeHighestIndexInArrayTest_Input2468andneg1_Expected321() {
		int [] testInput = {2,4,6,8};
		int [] expectedResult = {3,2,1};
		assertArrayEquals(expectedResult,sc.findTopThreeHighestIndexInArray(testInput,-1));
	}
	
	//findTopThreeHighestIndexInArray test case 4
	@Test
	public void findTopThreeHighestIndexInArrayTest_Input2465andneg1_Expected231() {
		int [] testInput = {2,4,6,5};
		int [] expectedResult = {2,3,1};
		assertArrayEquals(expectedResult,sc.findTopThreeHighestIndexInArray(testInput,-1));
	}

	//findTopThreeHighestIndexInArray test case 5
	@Test
	public void findTopThreeHighestIndexInArrayTest_Input2463andneg1_Expected213() {
		int [] testInput = {2,4,6,3};
		int [] expectedResult = {2,1,3};
		assertArrayEquals(expectedResult,sc.findTopThreeHighestIndexInArray(testInput,-1));
	}

	//findTopThreeHighestIndexInArray test case 6
	@Test
	public void findTopThreeHighestIndexInArrayTest_Input2461andneg1_Expected210() {
		int [] testInput = {2,4,6,1};
		int [] expectedResult = {2,1,0};
		assertArrayEquals(expectedResult,sc.findTopThreeHighestIndexInArray(testInput,-1));
	}

	//checkSearch test case 1
	@Test
	public void checkSearchTest_DatesValidSearchOptionBoth_ShouldReturnAllAccidentsBetweenDates() {
		Date startDate = date1;
		Date endDate = date2;
		String searchOption = "both";

		sc.checkSearch(startDate,endDate,searchOption);
		verify(accidentReportServiceMock).getApprovedAndResolvedAccidentReport(startDate, endDate);
	}

	//checkSearch test case 2
	@Test
	public void checkSearchTest_DatesValidSearchOptionArchived_ShouldReturnResolvedAccidentsBetweenDates() {
		Date startDate = date1;
		Date endDate = date2;
		String searchOption = "archived";

		sc.checkSearch(startDate,endDate,searchOption);
		verify(accidentReportServiceMock).getResolvedAccidentReport(startDate, endDate);
	}

	//checkSearch test case 3
	@Test
	public void checkSearchTest_DatesValidSearchOptionCurrent_ShouldReturnAllApprovedAccidentsBetweenDates() {
		Date startDate = date1;
		Date endDate = date2;
		String searchOption = "current";

		sc.checkSearch(startDate,endDate,searchOption);
		verify(accidentReportServiceMock).getApprovedAccidentReport(startDate, endDate);
	}

	//checkSearch test case 4
	@Test
	public void checkSearchTest_DatesValidSearchOptionNull_ShouldReturnAllAccidentsBetweenDates() {
		Date startDate = date1;
		Date endDate = date2;
		String searchOption = null;

		sc.checkSearch(startDate,endDate,searchOption);
		verify(accidentReportServiceMock).getApprovedAndResolvedAccidentReport(startDate, endDate);
	}

	//checkSearch test case 5
	@Test
	public void checkSearchTest_StartDateNullSearchOptionBoth_ShouldReturnAllAccidents() {
		Date startDate = null;
		Date endDate = date2;
		String searchOption = "both";

		sc.checkSearch(startDate,endDate,searchOption);
		verify(accidentReportServiceMock).getApprovedOrResolvedAccidentReport();
	}

	//checkSearch test case 6
	@Test
	public void checkSearchTest_EndDateNullSearchOptionBoth_ShouldReturnAllAccidents() {
		Date startDate = date1;
		Date endDate = null;
		String searchOption = "both";

		sc.checkSearch(startDate,endDate,searchOption);
		verify(accidentReportServiceMock).getApprovedOrResolvedAccidentReport();
	}

	//checkSearchForCause test case 1
	@Test
	public void checkSearchForCauseTest_DatesValid_ShouldReturnResolvedAccidentsBetweenDates() {
		Date startDate = date1;
		Date endDate = date2;

		sc.checkSearchForCause(startDate,endDate);
		verify(accidentReportServiceMock).getResolvedAccidentReport(date1,date2);
	}

	//checkSearchForCause test case 2
	@Test
	public void checkSearchForCauseTest_EndDateInvalid_ShouldReturnResolvedAccidentsBetweenDates() {
		Date startDate = date1;
		Date endDate = null;

		sc.checkSearchForCause(startDate,endDate);
		verify(accidentReportServiceMock).getResolvedAccidentReport();
	}

	//checkSearchForCause test case 3
	@Test
	public void checkSearchForCauseTest_StartDateInvalid_ShouldReturnResolvedAccidentsBetweenDates() {
		Date startDate = null;
		Date endDate = date2;

		sc.checkSearchForCause(startDate,endDate);
		verify(accidentReportServiceMock).getResolvedAccidentReport();
	}
}