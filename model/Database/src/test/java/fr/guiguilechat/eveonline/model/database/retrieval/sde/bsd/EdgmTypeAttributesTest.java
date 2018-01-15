package fr.guiguilechat.eveonline.model.database.retrieval.sde.bsd;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.model.sde.load.bsd.EdgmTypeAttributes;

public class EdgmTypeAttributesTest {

	/***
	 * ensure we can parse the corresponding file
	 *
	 * @throws FileNotFoundException
	 */
	@Test
	public void testParse() throws FileNotFoundException {
		HashMap<Integer, HashMap<Integer, EdgmTypeAttributes>> idx = EdgmTypeAttributes.loadByTypeIDAttributeID();
		HashMap<Integer, EdgmTypeAttributes> thrasher = idx.get(16242);
		Assert.assertNotNull(
				thrasher.keySet());
	}

}
