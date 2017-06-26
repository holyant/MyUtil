package test;

import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4jTest {
	
	@Test
	public void  test1(){
		Logger logger = Logger.getLogger(this.getClass());
		logger.error("里好");
		
		
	}
}
