package fr.guiguilechat.eveonline.database.retrieval.sde.tables;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EdgmEffectsTest {
	
	/***
	 * ensure we can parse the corresponding file
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testParse() throws FileNotFoundException {
		HashMap<Integer, EdgmEffects> idx = EdgmEffects.loadByIndex();
		Assert.assertEquals(idx.get(11).effectName, "loPower");
	}

}
