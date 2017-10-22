package fr.guiguilechat.eveonline.model.database.retrieval.sde.bsd;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.model.sde.bsd.EdgmAttributeTypes;

public class EdgmAttributeTypesTest {

	@Test
	public void testLoad() {
		Assert.assertEquals(EdgmAttributeTypes.loadByAttributeID().get(1013).attributeName, "eliteBonusInterdictors2");
	}

}
