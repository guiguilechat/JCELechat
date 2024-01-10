package fr.guiguilechat.jcelechat.libs.spring.sde.updater.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SDEUpdateScheduler {

	@Autowired
	private SDEUpdateService sdeService;

	@Scheduled(fixedRateString = "${sde.updater.fetchperiod:3600000}", initialDelayString = "${sde.updater.fetchdelay:5000}")
	public void checkSDE() {
		sdeService.updateSDE();
	}

}
