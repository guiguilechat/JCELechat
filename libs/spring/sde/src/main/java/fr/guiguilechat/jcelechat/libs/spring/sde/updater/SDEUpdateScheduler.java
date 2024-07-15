package fr.guiguilechat.jcelechat.libs.spring.sde.updater;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SDEUpdateScheduler {

	final private SDEUpdateService sdeUpdateService;

	@Value("${sde.update.skip:false}")
	private boolean skip;

	@Scheduled(fixedRateString = "${sde.update.delayms:3600000}", initialDelayString = "${sde.update.waitms:5000}")
	public void checkSDE() throws IOException {
		if (!skip) {
			sdeUpdateService.updateSDE();
		}
	}

}
