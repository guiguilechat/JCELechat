package fr.guiguilechat.jcelechat.libs.spring.connect.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CharDataUpdaterService {

	private final Optional<List<ACharDataService<?, ?, ?>>> services;

	@Value("${esiconnect.charupdater.skip:false}")
	private boolean skip;

	@Scheduled(fixedRateString = "${esiconnect.charupdater.fetchperiod:3600000}", initialDelayString = "${esiconnect.charupdater.fetchdelay:5000}")
	public void updateChars() throws IOException {
		if (!skip && services.isPresent()) {

		}
	}

}
