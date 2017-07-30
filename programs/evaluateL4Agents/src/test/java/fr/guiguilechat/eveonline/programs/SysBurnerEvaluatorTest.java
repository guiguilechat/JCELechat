package fr.guiguilechat.eveonline.programs;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.database.yaml.Location;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;
import fr.guiguilechat.eveonline.programs.SysBurnerEvaluator.SystemVisitor;

public class SysBurnerEvaluatorTest {

	@Test
	public void testSysEval() {
		YamlDatabase db = new YamlDatabase();
		Location altrinur = db.getLocation("Altrinur");
		SysBurnerEvaluator e1 = new SysBurnerEvaluator(1, db);

		SystemVisitor sv = e1.new SystemVisitor(altrinur);
		e1.visitSystemsWithDistance(altrinur, 1, sv);
		Assert.assertEquals(sv.nbPHS, 5.0);
		Assert.assertEquals(sv.sumPonderations, 5.0);
		Assert.assertEquals(sv.sumPHSjumps, 4.0);

		sv = e1.new SystemVisitor(altrinur);
		e1.visitSystemsWithDistance(altrinur, 2, sv);
		Assert.assertEquals(sv.nbPHS, 23.0);
		Assert.assertEquals(sv.sumPonderations, 23.0);
		Assert.assertEquals(sv.sumPHSjumps, 40.0);

		Location erindur = db.getLocation("Erindur");

		sv = e1.new SystemVisitor(erindur);
		e1.visitSystemsWithDistance(erindur, 2, sv);
		Assert.assertEquals(sv.nbPHS, 3.0);
		Assert.assertEquals(sv.sumPonderations, 4.0);
		Assert.assertEquals(sv.sumPHSjumps, 3.0);
	}

}
