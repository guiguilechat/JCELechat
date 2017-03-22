package fr.guiguilechat.eveonline.database;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoadDBSDETest {

	@Test
	public void testLoad() {
		Assert.assertNotNull(Parser.getSDEDB());
	}

}
