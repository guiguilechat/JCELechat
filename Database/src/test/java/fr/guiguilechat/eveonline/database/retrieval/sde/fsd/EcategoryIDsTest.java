package fr.guiguilechat.eveonline.database.retrieval.sde.fsd;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EcategoryIDsTest {

	@Test
	public void testLoad() {
		Assert.assertEquals(EcategoryIDs.load().get(35).name.get("en"), "Decryptors");
	}

}
