package fr.guiguilechat.eveonline.database.retrieval.sde.fsd;

import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.sde.fsd.Eblueprints;
import fr.guiguilechat.eveonline.sde.fsd.Eblueprints.Material;

public class EblueprintsTest {

	@Test
	public void testLoad() {
		LinkedHashMap<Integer, Eblueprints> map = Eblueprints.load();
		Eblueprints thrasherBP = map.get(16243);
		Material prod = thrasherBP.activities.manufacturing.products.get(0);
		Assert.assertEquals(prod.probability, 1.0f);
		Assert.assertEquals(prod.quantity, 1);
		Assert.assertEquals(prod.typeID, 16242);
	}

}
