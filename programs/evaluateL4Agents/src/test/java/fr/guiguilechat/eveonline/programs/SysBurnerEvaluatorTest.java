package fr.guiguilechat.eveonline.programs;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

public class SysBurnerEvaluatorTest {

	@Test
	public void testSysEval() {
		YamlDatabase db = new YamlDatabase();
		SysBurnerEvaluator e0 = new SysBurnerEvaluator(0, db);
		SysBurnerEvaluator e1 = new SysBurnerEvaluator(1, db);
		SysBurnerEvaluator e2 = new SysBurnerEvaluator(2, db);
		Assert.assertEquals(e0.findProbaHighSystem("Jita"), 1.0);
		Assert.assertEquals(e1.findProbaHighSystem("Friggi"), 0.8);
		Assert.assertEquals(e2.findProbaHighSystem("Amo"), 0.5);
	}

}
