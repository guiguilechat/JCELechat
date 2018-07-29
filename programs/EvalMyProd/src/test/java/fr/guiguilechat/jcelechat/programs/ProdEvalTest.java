package fr.guiguilechat.jcelechat.programs;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.programs.ProdEval;

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
