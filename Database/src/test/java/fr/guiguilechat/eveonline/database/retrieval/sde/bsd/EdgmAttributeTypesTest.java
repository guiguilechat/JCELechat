package fr.guiguilechat.eveonline.database.retrieval.sde.bsd;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EdgmAttributeTypesTest {

	@Test
	public void testLoad() {
		Assert.assertEquals(EdgmAttributeTypes.loadByAttributeID().get(1013).attributeName, "eliteBonusInterdictors2");
	}

}
