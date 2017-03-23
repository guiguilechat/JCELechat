package fr.guiguilechat.eveonline.database.retrieval.sde.fsd;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EgroupIDsTest {

	@Test
	public void testLoad() {
		Assert.assertEquals(EgroupIDs.load().get(721).name.get("en"), "Temp");
	}

}
