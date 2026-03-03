package fr.guiguilechat.jcelechat.libs.spring.update.manager.servicepulse;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.manager.servicepulse.UpdaterPulseRepository.TimeUsage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UpdaterPulseService {

	private final UpdaterPulseRepository repository;

	public void save(String serviceName, Instant start, Instant end) {
		repository.save(new UpdaterPulse(serviceName, start, end));
	}

	/**
	 * @return service name to its % time usage over the last X seconds
	 */
	public Map<String, Double> recentUsage(int seconds) {
		return repository.activities(Instant.now().minusSeconds(seconds))
				.stream()
				.collect(Collectors.toMap(TimeUsage::serviceName,
						u -> 0.1 * u.ms() / seconds));
	}

}
