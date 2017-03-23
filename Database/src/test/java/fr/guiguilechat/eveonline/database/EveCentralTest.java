package fr.guiguilechat.eveonline.database;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EveCentralTest {

	@Test
	public void testLoadTritanium() {
		EveCentral ec = new EveCentral();
		double bo = ec.getBO(34);
		double so = ec.getSO(34);
		Assert.assertTrue(so > bo, "bo is " + bo + " and so is " + so);
		Assert.assertTrue(so < 10, "bo is " + bo + " and so is " + so);
		Assert.assertTrue(bo > 2, "bo is " + bo + " and so is " + so);
	}

}
