package fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.Assert;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.EveHistoryApp;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchLine;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult.STATUS;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.ObservedRegion;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.market.ObservedRegionRepository;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market.FetchJitaMarket.Fetch;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market.FetchJitaMarket.SavedLines;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = EveHistoryApp.class)
@TestPropertySource(locations = "classpath:integrationtests.properties")
public class MarketFetchLineServiceTest {

	@Autowired
	private MarketFetchLineService lineService;

	@Autowired
	private MarketFetchResultService resultService;

	@Autowired
	private MarketSchedulerService fetchScheduler;

	@Autowired
	private ObservedRegionService regionService;

	@Autowired
	private ObservedRegionRepository regionRepo;

	@Test
	public void testObservedRegions() {
		Map<ObservedRegion, MarketFetchResult> requests = regionService.listRequests();
		Assert.state(requests.size() > 0, "no region to observe, existing are " + regionRepo.findAll()
				+ " repo observed are " + regionRepo.listRequests());
		// log.info("found observed regions : " + requests);
	}

	@Test
	public void testAnalyzeTheForgeData() throws IOException {
		log.info("start test");
		SavedLines savedData = FetchJitaMarket.loadTestLines();
		MarketFetchResult lastResult = null;
		List<Fetch> fetchesList = savedData.fetched();
		for (Fetch f : fetchesList) {
			MarketFetchResult mfr = f.result();
			mfr.setStatus(STATUS.FETCHED);
			mfr.setPreviousResult(lastResult);
			mfr = resultService.save(mfr);
			lastResult = mfr;
			Assert.state(resultService.listOnStatusWithPreviousAfter(STATUS.FETCHED).size() > 0, "contains 0 results");
			for (MarketFetchLine mfl : f.lines()) {
				mfl.setFetchResult(mfr);
				lineService.save(mfl);
			}
			mfr.getRegion().setLastFetchSuccess(mfr);
			regionService.save(mfr.getRegion());
			log.info("saved one result");
		}
// for (MarketFetchResult mfr : resultService.list()) {
// log.info(
// "result " + mfr + " previous=" + (mfr.getPreviousResult() == null ? null :
// mfr.getPreviousResult().getId()));
// }
		for (int replay = 0; replay < 3; replay++) {
			fetchScheduler.createOrders();
		}
		List<MarketFetchResult> withOrdersExists = resultService.listByStatus(STATUS.ORDERSEXIST);
		Assert.state(withOrdersExists.size() > 2,
				"found with orders exists = " + withOrdersExists + " all=" + resultService.list());
		log.info("done test");
	}

}
