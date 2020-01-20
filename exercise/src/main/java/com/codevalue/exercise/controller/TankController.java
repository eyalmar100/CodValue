package com.codevalue.exercise.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codevalue.exercise.service.TankService;

@RestController
@RequestMapping("/api/") // all the apis starts with 'api' and after that concatenate the rest of query
public class TankController {


	private  static final Logger logger = Logger.getLogger(TankController.class);


	@Autowired
	private TankService tankService;

	@GetMapping("queryMaxCapcity/{tankId}")
	public int queryMaxCapacity(@PathVariable int tankId) {
		logger.debug("Tank id is "+tankId);
		// could throw exception or either just return false in case tank id not found
 		return tankService.getMaxValueOfTank(tankId);
	 
	}


	@GetMapping("queryCurrentCapcity/{tankId}")	
   	public int queryCurrentCapacity(@PathVariable int tankId) throws Exception {
		logger.debug("TankController.queryCurrentCapacity(): id is "+tankId);

		
		int result=tankService.calculateCapacity(tankId);
		 if(result==-1) {
			 // No mapping for exceptions here .. need to be handeld but no time for it ..
			 throw new Exception("Could not find the requested tank !"); 
		 }
	   return result;
 	}

    // we need to provide  both tank id and capacity to add
	@GetMapping("addWater/{tankId}/{capacityToAdd}")	
	public boolean addWater(@PathVariable int tankId ,@PathVariable int capacityToAdd) {

		logger.debug(String.format("tankId is %d ,capacity is %d", tankId,capacityToAdd));
		// could throw exception or either just return false in case tank id not found
		return tankService.addWater(tankId,capacityToAdd);

	}

}
