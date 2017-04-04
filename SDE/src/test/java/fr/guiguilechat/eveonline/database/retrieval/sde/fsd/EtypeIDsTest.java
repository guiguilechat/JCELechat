package fr.guiguilechat.eveonline.database.retrieval.sde.fsd;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.sde.fsd.EtypeIDs;


public class EtypeIDsTest {

	/***
	 * ensure we can parse the corresponding file
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testParse() throws FileNotFoundException {
		LinkedHashMap<Integer, EtypeIDs> map = EtypeIDs.load();
		EtypeIDs thrasher = map.get(16242);
		Assert.assertEquals(thrasher.traits.roleBonuses.get(0).unitID, 105);
	}

}
