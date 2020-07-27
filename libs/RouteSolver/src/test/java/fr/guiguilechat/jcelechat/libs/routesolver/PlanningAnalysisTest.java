package fr.guiguilechat.jcelechat.libs.routesolver;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PlanningAnalysisTest {

	/**
	 * https://evemaps.dotlan.net/map/Devoid/Faktun#const
	 */
	@Test
	public void testDevoidFaktunUlerahRegion() {
		RouteParams p = new RouteParams().withStart("Faktun").withStop("Ulerah").withIncludeRegions("Devoid");
		PlanningAnalysis pa = new PlanningAnalysis(p);
		Assert.assertEquals(pa.required.size(), 8, "they are " + pa.required);
		Assert.assertEquals(pa.optional.size(), 0);
	}

	/**
	 * https://evemaps.dotlan.net/map/Devoid/Faktun#const
	 */
	@Test
	public void testDevoidFaktunPalpis() {
		RouteParams p = new RouteParams().withStart("Faktun").withStop("Palpis");
		PlanningAnalysis pa = new PlanningAnalysis(p);
		Assert.assertEquals(pa.required.size(), 2, "they are " + pa.required);
		Assert.assertEquals(pa.optional.size(), 2);
	}

}
