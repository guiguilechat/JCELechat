package fr.guiguilechat.jcelechat.jcesi.request.impl;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.jcesi.request.interfaces.RateLimitations;

public class RateLimitationsTest {

	@Test
	public void testSimpleValue() {
		RateLimitations test = RateLimitations.parse("150/15m");
		Assert.assertEquals(test.windowDurationS(), 15 * 60);
		Assert.assertEquals(test.windowTokens(), 150);
	}

}
