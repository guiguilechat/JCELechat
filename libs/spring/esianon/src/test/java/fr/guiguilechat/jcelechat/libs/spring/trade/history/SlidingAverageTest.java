package fr.guiguilechat.jcelechat.libs.spring.trade.history;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history.AggregatedHL;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history.SlidingAverage;

public class SlidingAverageTest {

	@Test
	public void testDaysVolume() {
		Instant now = Instant.now();
		SlidingAverage sa0 = new SlidingAverage(0);
		SlidingAverage sa1 = new SlidingAverage(1);
		SlidingAverage sa3 = new SlidingAverage(3);
		AggregatedHL hl4 = new AggregatedHL(now.minus(4, ChronoUnit.DAYS), 1, 1.0, 1.0, 1.0, 1);
		sa0.add(hl4);
		sa1.add(hl4);
		sa3.add(hl4);
		AggregatedHL hl3 = new AggregatedHL(now.minus(3, ChronoUnit.DAYS), 1, 1.0, 1.0, 1.0, 1);
		sa0.add(hl3);
		sa1.add(hl3);
		sa3.add(hl3);
		AggregatedHL hl2 = new AggregatedHL(now.minus(2, ChronoUnit.DAYS), 1, 1.0, 1.0, 1.0, 1);
		sa0.add(hl2);
		sa1.add(hl2);
		sa3.add(hl2);
		AggregatedHL hl1 = new AggregatedHL(now.minus(1, ChronoUnit.DAYS), 1, 1.0, 1.0, 1.0, 1);
		sa0.add(hl1);
		sa1.add(hl1);
		sa3.add(hl1);
		AggregatedHL hl0 = new AggregatedHL(now, 1, 1.0, 1.0, 1.0, 1);
		sa0.add(hl0);
		sa1.add(hl0);
		sa3.add(hl0);
		Assert.assertEquals(sa0.volume(), 1L);
		Assert.assertEquals(sa1.volume(), 2L);
		Assert.assertEquals(sa3.volume(), 4L);
	}

}
