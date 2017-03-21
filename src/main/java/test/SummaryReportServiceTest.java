package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

import com.transporter.model.AccidentCause;
import com.transporter.model.AccidentReport;
import com.transporter.service.SummaryReportService;
import com.transporter.service.impl.SummaryReportServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class SummaryReportServiceTest {
	
	private SummaryReportService summaryReportService = new SummaryReportServiceImpl();
	
	private AccidentReport rFirst = new AccidentReport();
	private AccidentReport rSecond = new AccidentReport();
	private AccidentReport rThird = new AccidentReport();
	private AccidentReport rFourth = new AccidentReport();
	
	private AccidentCause cFirst = new AccidentCause();
	private AccidentCause cSecond = new AccidentCause();
	
	@Before
	public void setup() {
		cFirst.setCauseId(1);
		cSecond.setCauseId(2);
		
		Calendar cal = Calendar.getInstance();
		
		rFirst.setOfficialCause(cFirst);
		rSecond.setOfficialCause(cSecond);
		rThird.setOfficialCause(cFirst);
		rFourth.setOfficialCause(cSecond);
		
		//00:30
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE,30);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		rFirst.setAccidentDateTime(cal.getTime());
		
		//23:30
		cal.set(Calendar.HOUR_OF_DAY,23);
		cal.set(Calendar.MINUTE,30);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		rSecond.setAccidentDateTime(cal.getTime());
		
		//12:00
		cal.set(Calendar.HOUR_OF_DAY,12);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		rThird.setAccidentDateTime(cal.getTime());
		
		//12:59
		cal.set(Calendar.HOUR_OF_DAY,12);
		cal.set(Calendar.MINUTE,59);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		rFourth.setAccidentDateTime(cal.getTime());
	}
	
	@Test
	public void SummariseByCause_CorrectInput_ShouldCorrectOutput() {
		List<AccidentReport> allReports = Arrays.asList(rFirst, rSecond, rThird, rFourth);
		List<AccidentCause> allCauses = Arrays.asList(cFirst,cSecond);
		int[] result = summaryReportService.summariseByCause(allReports, allCauses);
		assertArrayEquals(new int[] {2,2}, result);
	}
	
	@Test
	public void SummariseByCause_EmptyAccidentReportList_ShouldReturnArrayOfZeros() {
		List<AccidentReport> allReports = new ArrayList<AccidentReport>();
		List<AccidentCause> allCauses = Arrays.asList(cFirst,cSecond);
		int[] result = summaryReportService.summariseByCause(allReports, allCauses);
		assertArrayEquals(new int[] {0,0}, result);
	}
	
	@Test
	public void SummaryReportService_summariseByTime() {
		List<AccidentReport> allReports = Arrays.asList(rFirst, rSecond, rThird, rFourth);
		int[] result = summaryReportService.summariseByTime(allReports);
		assertArrayEquals(new int[] {1,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,1}, result);
	}
	
}