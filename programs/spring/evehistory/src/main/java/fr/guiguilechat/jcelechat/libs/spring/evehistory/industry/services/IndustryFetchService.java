package fr.guiguilechat.jcelechat.libs.spring.evehistory.industry.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.industry.model.IndustryFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.industry.model.IndustryFetchResult.IndustryFetchResultBuilder;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.industry.model.IndustryFetchResult.STATUS;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.industry.model.IndustrySystemCostIndex;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_systems;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IndustryFetchService {

	@Autowired
	private IndustryFetchResultService ifrService;
	@Autowired
	private IndustrySystemCostIndexService isciService;

	@Scheduled(fixedRate = 30 * 60 * 1000, initialDelayString = "${evehistory.industry.fetchdelay:120000}")
	public void fetchIndexes() {
		long start = System.currentTimeMillis();
		String lastEtag = ifrService.getLastEtag();
		log.info("fetching indexes, last etag is " + lastEtag);
		Map<String, String> properties = new HashMap<>();
		if (lastEtag != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastEtag);
		}
		Requested<R_get_industry_systems[]> response = ESIStatic.INSTANCE.get_industry_systems(properties);
		IndustryFetchResultBuilder builder = IndustryFetchResult.builder().responseCode(response.getResponseCode());
		switch (response.getResponseCode()) {
			case 200:
				builder.status(STATUS.FETCHING);
				builder.etag(response.getETag());
			break;
			case 304:
				builder.status(STATUS.CACHED);
			break;
			default:
				builder.status(STATUS.FAIL).errors(response.getError());
		}
		IndustryFetchResult result = ifrService.save(builder.build());
		if (!response.isOk()) {
			log.info(" fetch indexes returned status " + result.getStatus());
			return;
		}
		List<IndustrySystemCostIndex> list = Stream.of(response.getOK())
				.flatMap(is -> IndustrySystemCostIndex.streamOf(is, result)).toList();
		isciService.saveAll(list);

		result.setStatus(STATUS.FETCHED);
		ifrService.save(result);

		long end = System.currentTimeMillis();
		log.info("fetch indexes returned " + list.size() + " indexes in " + (int) Math.ceil(0.001 * (end - start)) + "s");
	}

}
