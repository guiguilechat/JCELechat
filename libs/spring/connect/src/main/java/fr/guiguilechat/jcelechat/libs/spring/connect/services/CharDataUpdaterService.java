package fr.guiguilechat.jcelechat.libs.spring.connect.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharDataUpdaterService {

	private final Optional<List<ACharDataService<?, ?, ?>>> charServices;

	@Lazy
	private final CharacterAffiliationService characterAffiliationService;

	@Lazy
	private final CharacterInformationService characterInformationService;

	@Value("${esiconnect.charupdater.skip:false}")
	private boolean skip;

	@Scheduled(fixedRateString = "${esiconnect.charupdater.fetchperiod:10000}", initialDelayString = "${esiconnect.charupdater.fetchdelay:5000}")
	public void updateChars() throws IOException {
		if (!skip && charServices.isPresent()) {
			for (ACharDataService<?, ?, ?> charService : charServices.get()) {
				charService.update();
			}
		} else {
			System.err.println("no need to update");
		}

	}

}
