package fr.guiguilechat.jcelechat.model.sde.industry;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ActivityTest {
	@Test
	public void testLoad() {
		Activity act = Activity.storage().load().get(0);
		Assert.assertNotNull(act);
	}

}
