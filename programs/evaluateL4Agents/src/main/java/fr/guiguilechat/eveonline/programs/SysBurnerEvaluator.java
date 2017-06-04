package fr.guiguilechat.eveonline.programs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import fr.guiguilechat.eveonline.database.EveDatabase;
import fr.guiguilechat.eveonline.database.yaml.Location;

/**
 * evaluate a system with two factors : its true sec and the probability to find
 * a low/null system into distance jumps.
 *
 * @author guigui lechat
 *
 */
public class SysBurnerEvaluator {

	public final int distance;

	public final EveDatabase db;

	public SysBurnerEvaluator(int distance, EveDatabase db) {
		this.distance = distance;
		this.db = db;
	}

	protected final HashMap<String, Double> cache = new HashMap<>();

	public double evaluate(String sn) {
		if (cache.containsKey(sn)) {
			return cache.get(sn);
		}
		Location sys = db.getLocations().get(sn.replaceAll(" ", ""));
		double ret = 2 - sys.minSec;
		double probaNonHigh = findProbaNonHighSystem(sn);

		ret *= probaNonHigh;
		cache.put(sn, ret);
		return ret;
	}

	public double findProbaNonHighSystem(String sn) {
		HashMap<String, Integer> hsDistances = new HashMap<>();
		HashMap<String, Integer> lnsDistances = new HashMap<>();
		int jumps = 0;
		HashSet<String> nextHSLocations = new HashSet<>(Arrays.asList(sn));
		HashSet<String> nextLNSLocations = new HashSet<>();
		while (jumps <= distance) {
			HashSet<String> futHSLocations = new HashSet<>();
			HashSet<String> futLNSLocations = new HashSet<>();

			// for each system we can reach through HS
			for (String sysname : nextHSLocations) {
				if (hsDistances.containsKey(sysname)) {
					continue;
				}
				Location loc = db.getLocations().get(sysname.replaceAll(" ", ""));
				if (loc.minSec < 0.46) {
					if (lnsDistances.containsKey(sysname)) {
						continue;
					} else {
						lnsDistances.put(sysname, jumps);
						futLNSLocations.addAll(Arrays.asList(loc.adjacentSystems));
					}
				} else {
					futHSLocations.addAll(Arrays.asList(loc.adjacentSystems));
					hsDistances.put(sysname, jumps);
				}
			}
			// for each new system we reach from low or null sec
			for (String sysname : nextLNSLocations) {
				if (hsDistances.containsKey(sysname) || lnsDistances.containsKey(sysname)) {
					continue;
				}
				Location loc = db.getLocations().get(sysname.replaceAll(" ", ""));
				lnsDistances.put(sysname, jumps);
				futLNSLocations.addAll(Arrays.asList(loc.adjacentSystems));
			}

			nextHSLocations=futHSLocations;
			nextLNSLocations=futLNSLocations;
			jumps++;
		}
		return 1.0 * hsDistances.size() / (hsDistances.size() + lnsDistances.size());
	}

}
