package fr.guiguilechat.eveonline.optimize;

import org.chocosolver.solver.Solution;
import org.chocosolver.solver.exception.ContradictionException;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.database.Database;
import fr.guiguilechat.eveonline.database.Parser;
import fr.guiguilechat.eveonline.database.elements.Hull;

public class ShipModelTest {

	@Test
	public void testFastestHullSpeed() throws ContradictionException {
		ShipModel sm = new ShipModel(Parser.getSDEDB());
		Database db = sm.bridge.database;
		Solution s = sm.getSolver().findOptimalSolution(sm.getHullSpeed(), true);
		s.restore();
		Hull fastest = db.hulls.get(sm.bridge.getEveHullId(sm.getHull().getValue()));
		System.err.println(fastest.name);
	}

}
