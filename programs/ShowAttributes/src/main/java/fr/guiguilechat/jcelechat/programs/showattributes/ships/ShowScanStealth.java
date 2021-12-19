package fr.guiguilechat.jcelechat.programs.showattributes.ships;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.model.sde.types.Ship;

public class ShowScanStealth {

	public static void main(String[] args) {
		Map<String, Double> vals = new HashMap<>();
		for (Ship s : Ship.METACAT.load().values()) {
			double scan = 0.0;
			scan = Math.max(scan, s.scangravimetricstrength);
			scan = Math.max(scan, s.scanladarstrength);
			scan = Math.max(scan, s.scanmagnetometricstrength);
			scan = Math.max(scan, s.scanradarstrength);
			vals.put(s.name, scan / s.signatureradius);
		}
		ArrayList<Entry<String, Double>> list = new ArrayList<>(vals.entrySet());
		Collections.sort(list, Comparator.comparing(e -> e.getValue()));
		for (Entry<String, Double> e : list) {
			System.out.println("" + e.getKey() + "\t" + e.getValue());
		}
	}

}
