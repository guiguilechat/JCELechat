package fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.EveHistoryApp;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchLine;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult;
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
	private MarketFetchScheduler fetchScheduler;

// @Test
	public void testAnalyzeTheForgeData() throws IOException {
		log.info("start test");
		SavedLines savedData = FetchJitaMarket.loadTestLines();
		List<Fetch> fetchesList = savedData.fetched();
		for (Fetch f : fetchesList) {
			MarketFetchResult mfr = f.result();
			mfr = resultService.save(mfr);
			for (MarketFetchLine mfl : f.lines()) {
				mfl.setFetchResult(mfr);
				lineService.save(mfl);
			}
			log.info("saved one result");
		}
		// fetchScheduler.analyzeResults();
		log.info("done test");
	}

}
