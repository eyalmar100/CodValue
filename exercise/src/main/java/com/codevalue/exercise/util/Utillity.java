package com.codevalue.exercise.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.apache.log4j.Logger;

import com.codevalue.exercise.domain.Tank;

/*
 * We use Utility to separate logic from data
 */
public class Utillity {

	private  static final Logger logger = Logger.getLogger(Utillity.class);

	public static   boolean addWaterToTank(Tank tank,int capacity) {

		boolean retValue=true;

		if(capacity>tank.getMaxCapacity())// cannot add more max capacity ..
			retValue= false;

		int currentCapacity=calculateCurrentCapacity(tank);
		if(currentCapacity+capacity>tank.getMaxCapacity()) {
			logger.warn(String.format("trying to add to much water %d",capacity)); 
			setTankWithNewParameters(tank, currentCapacity);
			retValue= false;
		}
		else {
			setTankWithNewParameters(tank, currentCapacity+capacity);
		}


		return retValue;


	}

	private static void setTankWithNewParameters(Tank tank,  int currentCapacity) {
		tank.setCurrentCapacity(currentCapacity);
		tank.setLastUpdate(new Date());
	}

	public static int calculateCurrentCapacity(Tank tank) {
		LocalDateTime ldt=tank.getLastUpdate().toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();

		LocalDateTime now = LocalDateTime.now();
		// for testing we use seconds , but in real app use minutes
		// I was thinking to use separate bean - one for test profile , one for prod,or just add a flag in the method which version to use
		//, but took me a while to try to do that
		// so in the end I gave up - In real app I would do 2 versions - one fot testing one for prod ..
		long diff = ChronoUnit.SECONDS.between(ldt, now);
		int currentCapacity=(int) (tank.getCurrentCapacity()-(diff*tank.getLeakPerMinute()));
		return currentCapacity>0?currentCapacity:0;

	}

}
