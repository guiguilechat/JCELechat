package fr.guiguilechat.jcelechat.libs.spring.sde.updater.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SDEUpdateScheduler {

	final private SDEUpdateService sdeService;

	@Value("${sde.updater.skip:false}")
	private boolean skip;

	@Scheduled(fixedRateString = "${sde.updater.fetchperiod:3600000}", initialDelayString = "${sde.updater.fetchdelay:5000}")
	public void checkSDE() throws IOException {
		if (!skip) {
			sdeService.updateSDE();
		}
	}

}
