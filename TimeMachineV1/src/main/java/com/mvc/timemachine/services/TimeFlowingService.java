package com.mvc.timemachine.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mvc.timemachine.models.Era;
import com.mvc.timemachine.repositories.EraRepo;

@Service
public class TimeFlowingService {
	
	@Autowired
	EraRepo erepo;

	// execute the service every day at 7 am
	// change the parameter to fixedRate = 50000 to execute every 5 minutes for testing
	@Scheduled(cron = "0 0 7 * * ?")
	public void flowTime(){
		Iterable<Era> eras = erepo.findAll();
		Iterator<Era> ite = eras.iterator();
		Era era;
		while(ite.hasNext()){
			era = ite.next();
			era.setPhotoInd((era.getPhotoInd() + 1) % era.getEraPhotos().size());
			era.setSoundInd((era.getSoundInd() + 1) % era.getEraSounds().size());
			if(era.getUsedDays() == era.getIncreaseDays() - 1){
				era.setStartInfoInd(era.getStartInfoInd() + era.getInfoIncreaseRate());
				era.setUsedDays(0);
			}
			else{
				era.setUsedDays(era.getUsedDays() + 1);
			}
		}
	}
	
}
