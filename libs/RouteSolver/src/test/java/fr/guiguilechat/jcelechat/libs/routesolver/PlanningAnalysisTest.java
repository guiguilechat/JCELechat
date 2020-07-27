package fr.guiguilechat.jcelechat.libs.routesolver;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PlanningAnalysisTest {

	@Test
	public void testDevoid() {
		RouteParams p = new RouteParams().withStart("Faktun").withStop("Ulerah").withIncludeRegions("Devoid");
		PlanningAnalysis pa = new PlanningAnalysis(p);
		Assert.assertEquals(pa.required.size(), 8, "they are " + pa.required);
		Assert.assertEquals(pa.optional.size(), 0);
	}

}
