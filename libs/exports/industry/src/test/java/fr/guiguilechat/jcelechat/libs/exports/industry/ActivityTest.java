package fr.guiguilechat.jcelechat.libs.exports.industry;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ActivityTest {
	@Test
	public void testLoad() {
		Activity act = Activity.storage().load().get(1);
		Assert.assertNotNull(act);
	}

}
