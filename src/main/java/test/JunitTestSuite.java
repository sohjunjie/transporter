package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   BaseControllerTest.class,
   SummaryControllerTest.class,
   SummaryReportServiceTest.class
})

public class JunitTestSuite {   
}  	