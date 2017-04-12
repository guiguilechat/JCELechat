package fr.guiguilechat.eveonline.optimize;

import org.chocosolver.solver.Solution;
import org.chocosolver.solver.exception.ContradictionException;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.database.DataBase;
import fr.guiguilechat.eveonline.database.yaml.Hull;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

public class ShipModelTest {

	@Test
	public void testFastestHullSpeed() throws ContradictionException {
		ShipModel sm = new ShipModel(new YamlDatabase());
		DataBase db = sm.bridge.database;
		Solution s = sm.getSolver().findOptimalSolution(sm.getHullSpeed(), true);
		s.restore();
		Hull fastest = db.getHulls().get(sm.bridge.getEveHullId(sm.getHull().getValue()));
		System.err.println(fastest.name);
	}

}
