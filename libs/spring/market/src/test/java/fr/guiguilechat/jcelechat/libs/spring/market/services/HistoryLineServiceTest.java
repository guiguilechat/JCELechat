package fr.guiguilechat.jcelechat.libs.spring.market.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

import fr.guiguilechat.jcelechat.libs.spring.market.history.HistoryLineService.WeightStrategy;

public class HistoryLineServiceTest {

	@Test
	public void testOneDayDiff() {
		Instant now = Instant.now();
		Instant yester = now.minus(1, ChronoUnit.DAYS);
		Assert.assertEquals(WeightStrategy.daysBetween(yester, now), 1);
	}

}
