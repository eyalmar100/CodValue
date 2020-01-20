package com.codevalue.exercise.service;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.codevalue.exercise.domain.Tank;
import com.codevalue.exercise.util.Utillity;

@Service
public class TankService {

	@Value("${tanks.max}")
	private Integer[] tankMax;
	
	@Value("${tank.leakPerMinute}")
	private Integer[] leakPerMinuteInTank;// in testing we use per sec
	
	
	
	
	private ConcurrentHashMap<Integer, Tank> tanksCacheMap;
	
	@PostConstruct
	public void initTanksCacheMap() {
		tanksCacheMap=new ConcurrentHashMap<>();
		int key=0;
		for (Integer maxValue:tankMax) {
			tanksCacheMap.put(key, new Tank.TankBuilder(key).currentCapacity(0).leakPerMinute(leakPerMinuteInTank[key])
					.maxCapacity(maxValue).lastUpdate(new Date()).build());
			key++;
		}
		
	}
	 
	public int calculateCapacity(int tankId) {
		
		Tank currTank=tanksCacheMap.get(tankId);
		if(currTank==null) {
			return -1;
		}
		return currTank.getCurrentCapacity();
 	}
	
	public int getMaxValueOfTank(int tankId)  {
		if(tanksCacheMap.get(tankId)==null) {
			return -1;
		}
		return tanksCacheMap.get(tankId).getMaxCapacity();
	}
	
	public boolean addWater(int tankId,int capacityToAdd )  {
		
		Tank currTank=tanksCacheMap.get(tankId);
		if(currTank==null) {
			return false;
		}
		return Utillity.addWaterToTank(currTank, capacityToAdd);
	 		 	  
	}

}
