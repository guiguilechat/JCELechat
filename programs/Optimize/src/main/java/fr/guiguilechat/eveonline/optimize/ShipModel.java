package fr.guiguilechat.eveonline.optimize;

import java.util.stream.IntStream;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

import fr.guiguilechat.eveonline.database.EveDatabase;

public class ShipModel extends Model {

	public final Bridge bridge;

	public ShipModel(EveDatabase database) {
		bridge = new Bridge(database);
		makeVars();
	}

	public ShipModel(Bridge bridge) {
		this.bridge = bridge;
		makeVars();
	}

	IntVar hull;

	public IntVar getHull() {
		return hull;
	}

	protected void makeVars() {
		hull = intVar(0, bridge.hulls.length - 1, false);
	}

	protected IntVar hullSpeed;

	public IntVar getHullSpeed() {
		if (hullSpeed == null) {
			int[] speeds = bridge.getHullSpeed();
			IntStream.of(speeds).min().getAsInt();
			hullSpeed = intVar("hullSpeed", IntStream.of(speeds).min().getAsInt(), IntStream.of(speeds).max().getAsInt(),
					false);
			post(element(hullSpeed, speeds, hull));
		}
		return hullSpeed;
	}

}
