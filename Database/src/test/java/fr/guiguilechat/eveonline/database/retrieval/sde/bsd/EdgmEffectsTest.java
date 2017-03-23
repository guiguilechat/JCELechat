package fr.guiguilechat.eveonline.database.retrieval.sde.bsd;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.database.retrieval.sde.bsd.EdgmEffects;

public class EdgmEffectsTest {
	
	/***
	 * ensure we can parse the corresponding file
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testParse() throws FileNotFoundException {
		HashMap<Integer, EdgmEffects> idx = EdgmEffects.loadByEffectID();
		Assert.assertEquals(idx.get(11).effectName, "loPower");
	}

}
