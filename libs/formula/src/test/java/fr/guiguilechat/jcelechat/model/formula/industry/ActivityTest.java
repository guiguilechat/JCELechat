package fr.guiguilechat.jcelechat.model.formula.industry;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ActivityTest {

	@Test
	public void testTypeRetrieval() {
		Assert.assertEquals(Activity.Type.of("me").getId(), 4);
		Assert.assertEquals(Activity.Type.of("ME").getId(), 4);
	}

}
