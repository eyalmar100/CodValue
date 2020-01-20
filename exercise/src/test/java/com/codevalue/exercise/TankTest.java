package com.codevalue.exercise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.codevalue.exercise.domain.Tank;
import com.codevalue.exercise.util.Utillity;

import junit.framework.TestCase;

public class TankTest extends TestCase {

	private  static final Logger logger = Logger.getLogger(TankTest.class);

	protected void setUp() throws Exception {
		super.setUp();
		BasicConfigurator.configure();
	}








	@Test
	public void testAddCapacityToTankByTheGuide() throws ParseException, InterruptedException {
		logger.info("TankTest.testAddCapacityToTank()");
 
		Tank tank1=new Tank.TankBuilder(0).maxCapacity(50).leakPerMinute(1).lastUpdate(new Date()).build();

		assertTrue(tank1.getCurrentCapacity()==0);// T0=0
		logger.info("tank capacity[T0] is :"+tank1.getCurrentCapacity()+"\n\n");

		Thread.sleep(1000);//T0+1
		boolean added=Utillity.addWaterToTank(tank1, 50);

		assertTrue(added==true & tank1.getCurrentCapacity()==50 );
		logger.info("tank capacity[T0+1] is :"+tank1.getCurrentCapacity()+"\n\n");


		Thread.sleep(2000);// T0+3
		added=Utillity.addWaterToTank(tank1, 5);
		assertTrue(added==false & tank1.getCurrentCapacity()==48);
		logger.info("tank capacity[T0+3] is :"+tank1.getCurrentCapacity()+"\n\n");

		logger.info("waiting for more 8 sec ..");
		Thread.sleep(8000);// T0+11
		added=Utillity.addWaterToTank(tank1, 5);
		assertTrue(added==true & tank1.getCurrentCapacity()==45);
		logger.info("tank capacity[T0+11] is :"+tank1.getCurrentCapacity()+"\n\n");


		Thread.sleep(1000);// T0+12
		// added=tank1.addWater(7);
		added=Utillity.addWaterToTank(tank1, 7);
		assertTrue(added==false & tank1.getCurrentCapacity()==44 );
		logger.info("tank capacity[T0+12] is :"+tank1.getCurrentCapacity());


	}

	@Test
	public void testTankOverFlow() throws ParseException {
		logger.debug("TankTest.testTankOverFlow()");
		Date d1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-01-14 15:57:33");
		Tank tank1=new Tank.TankBuilder(0).maxCapacity(250).leakPerMinute(10).lastUpdate(d1).build();
		boolean added=Utillity.addWaterToTank(tank1, 130);
		assertTrue(added==true);
		added=Utillity.addWaterToTank(tank1, 130);
		assertTrue(added==false);
		logger.debug("tank capacity is :"+tank1.getCurrentCapacity());

	}
}
