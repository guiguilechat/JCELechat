package fr.guiguilechat.eveonline.model.database.retrieval.sde.bsd;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.model.sde.bsd.EdgmTypeEffects;

public class EdgmTypeEffectsTest {

	/***
	 * ensure we can parse the corresponding file
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testParse() throws FileNotFoundException {
		HashMap<Integer, HashMap<Integer, EdgmTypeEffects>> idx = EdgmTypeEffects.loadByTypeIDEffectID();
		HashMap<Integer, EdgmTypeEffects> thrasher = idx.get(16242);
		Assert.assertEquals(new HashSet<>(Arrays.asList(new Integer(2132), new Integer(5317), new Integer(5318))),
				thrasher.keySet());
	}
}
