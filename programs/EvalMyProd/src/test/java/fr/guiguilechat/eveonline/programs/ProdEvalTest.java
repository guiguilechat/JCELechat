package fr.guiguilechat.eveonline.programs;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProdEvalTest {

	@Test
	public void testNameFilter() {
		ProdEval pe = new ProdEval();
		pe.setNameFilter("guigui", "lechat");
		Assert.assertTrue(pe.acceptName("guigui lechat"), "doesnt accept guigui lechat");
		Assert.assertTrue(pe.acceptName("guigui"), "doesnt accept guigui");
		Assert.assertTrue(pe.acceptName("lechat"), "doesnt accept lechat");
		Assert.assertFalse(pe.acceptName("guiguo lechot"), "should not accept guiguo lechot");
	}

}
