package fr.guiguilechat.jcelechat.libs.spring.evehistory.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.MarketFetchResultRepository;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MarketFetchService {

	@Autowired
	private MarketFetchResultRepository repo;

	public MarketFetchResult save(MarketFetchResult entity) {
		if (entity.getCreatedDate() == null)
			entity.setCreatedDate(Instant.now());
		return repo.save(entity);
	}

	@Scheduled(fixedRate = 4 * 60 * 1000, initialDelay = 5000)
	public void fetchMarkets() {
		List<MarketFetchResult> last_fetch = repo.findLastResults();
		log.info("call generates " + last_fetch);
		for (MarketFetchResult mft : last_fetch) {
			String lastEtag = mft.isFail() ? null : mft.getEtag();
			int regionId = mft.getRegionId();
			fetchMarket(regionId, lastEtag);
		}
	}

	public void fetchMarket(int regionId, String lastEtag) {
		Map<String, String> properties = new HashMap<>();
		if (lastEtag != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastEtag);
		}
		Requested<R_get_markets_region_id_orders[]> firstResult = ESIStatic.INSTANCE.get_markets_orders(null, null,
				regionId, null, properties);
		String newEtag = firstResult.getETag();
		boolean failed = false;
		String error = null;
		Integer pages = null;
		int responseCode = firstResult.getResponseCode();
		switch (responseCode) {
		case 200:
			pages = firstResult.getNbPages();
			break;
		case 304:
			repo.save(MarketFetchResult.builder().regionId(regionId).etag(newEtag).build());
			return;
		default:
			failed = true;
			error = firstResult.getError();
		}
		List<R_get_markets_region_id_orders> result = new ArrayList<>();
		if (!failed) {
			result.addAll(Arrays.asList(firstResult.getOK()));
			boolean expireMismatch = false;
			if (pages != null && pages > 1) {
				List<Requested<R_get_markets_region_id_orders[]>> nextPages = IntStream.rangeClosed(2, pages - 1)
						.parallel()
						.mapToObj(p -> ESIStatic.INSTANCE.get_markets_orders(null, p, regionId, null, properties))
						.toList();
				for (Requested<R_get_markets_region_id_orders[]> pageResult : nextPages) {
					if (pageResult.getResponseCode() != 200) {
						failed = true;
						responseCode = pageResult.getResponseCode();
						error = (error == null ? "" : error) + pageResult.getError();
					} else if (!Objects.equals(firstResult.getExpires(), pageResult.getExpires())) {
						failed = true;
						log.error("mismatching expire for " + pageResult.getURL());
						expireMismatch = true;
					} else {
						result.addAll(Arrays.asList(pageResult.getOK()));
					}
				}
				if (expireMismatch) {
					error = (error == null ? "" : error) + "Expires mismatch";
				}
			}
		}
		if (failed) {
			newEtag = null;

		}
		repo.save(MarketFetchResult.builder().errors(error).etag(newEtag).fail(failed).pages(pages).regionId(regionId)
				.responseCode(responseCode).build());
	}

	public boolean addRegion(int regionId) {
		Region region = Region.loadById().get(regionId);
		if (region == null)
			return false;
		if (!repo.existsByRegionId(regionId)) {
			repo.save(MarketFetchResult.builder()
					.fail(true)
					.regionId(regionId).build());
		}
		return true;
	}

}
