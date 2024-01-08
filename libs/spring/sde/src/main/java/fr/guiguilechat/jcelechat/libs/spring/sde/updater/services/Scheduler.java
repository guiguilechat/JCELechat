package fr.guiguilechat.jcelechat.libs.spring.sde.updater.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Scheduler {

	@Autowired
	private SDEUpdateService sdeService;

	@Scheduled(fixedRate = 3600 * 1000, initialDelayString = "${springsde.updater.fetchdelay:5000}")
	public void checkSDE() {
		sdeService.updateSDE();
	}

}
