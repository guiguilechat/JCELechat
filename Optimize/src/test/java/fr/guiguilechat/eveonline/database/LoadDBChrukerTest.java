package fr.guiguilechat.eveonline.database;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.database.Parser;

public class LoadDBChrukerTest {

	@Test
	public void testLoad() {
		Assert.assertNotNull(Parser.getChrukerDB());
	}

}
