package fr.guiguilechat.jcelechat.libs.spring.connect.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.connect.model.templates.ACharData;
import fr.guiguilechat.jcelechat.libs.spring.connect.repositories.templates.ICharDataRepository;
import fr.guiguilechat.jcelechat.libs.spring.connect.services.templates.ACharDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
				updateService(charService);
			}
		}

	}

	protected <Entity extends ACharData<Fetched>, Fetched, Repository extends ICharDataRepository<Entity>> void updateService(
	    ACharDataService<Entity, Fetched, Repository> charService) {
		Map<Entity, CompletableFuture<Void>> futures = charService.isSupportsBatchUpdate()
		    ? charService.update(charService.listForUpdate().toList())
		    : charService.listForUpdate().collect(Collectors.toMap(e -> e, charService::update));
		futures.entrySet().forEach(f -> {
			try {
				f.getValue().join();
			} catch (CompletionException e) {
				log.error(
				    "while updating entity " + f.getKey().getClass().getSimpleName() + " id=" + f.getKey().getRemoteId(), e);
			}
		});
	}

}