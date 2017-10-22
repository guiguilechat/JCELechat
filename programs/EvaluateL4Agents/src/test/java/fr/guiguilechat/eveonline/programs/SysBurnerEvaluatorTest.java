package fr.guiguilechat.eveonline.programs;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.model.database.yaml.Location;
import fr.guiguilechat.eveonline.model.database.yaml.YamlDatabase;
import fr.guiguilechat.eveonline.programs.SysBurnerEvaluator.SystemVisitor;

public class SysBurnerEvaluatorTest {

	@Test
	public void testSysEval() {
		YamlDatabase db = new YamlDatabase();
		Location altrinur = db.getLocation("Altrinur");
		SysBurnerEvaluator e1 = new SysBurnerEvaluator(1, db);

		SystemVisitor sv = e1.new SystemVisitor(altrinur);

		e1.visitSystemsWithDistance(altrinur, 1, sv);
		Assert.assertEquals(sv.sumWHS, 5.0);
		Assert.assertEquals(sv.sumWeight, 5.0);
		Assert.assertEquals(sv.sumWHSjumps, 4.0);

		sv = e1.new SystemVisitor(altrinur);
		sv.weightAdjConstel = 2;
		sv.multWeightHub = 1;
		e1.visitSystemsWithDistance(altrinur, 2, sv);
		Assert.assertEquals(sv.sumWHS, 15.0);
		Assert.assertEquals(sv.sumWeight, 15.0);
		Assert.assertEquals(sv.sumWHSjumps, 24.0);

		Location erindur = db.getLocation("Erindur");

		sv = e1.new SystemVisitor(erindur);
		e1.visitSystemsWithDistance(erindur, 2, sv);
		Assert.assertEquals(sv.sumWHS, 3.0);
		Assert.assertEquals(sv.sumWeight, 4.0);
		Assert.assertEquals(sv.sumWHSjumps, 3.0);
	}

}
