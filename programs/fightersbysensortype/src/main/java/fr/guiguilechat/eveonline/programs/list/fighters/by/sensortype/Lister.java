package fr.guiguilechat.eveonline.programs.list.fighters.by.sensortype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.sde.items.types.Fighter;
import fr.guiguilechat.eveonline.model.sde.items.types.fighter.HeavyFighter;
import fr.guiguilechat.eveonline.model.sde.items.types.fighter.LightFighter;
import fr.guiguilechat.eveonline.model.sde.items.types.fighter.SupportFighter;

public class Lister {

	public static void main(String[] args) {
		HashMap<String, String> fighter2type = new HashMap<>();
		Stream.concat(Stream.concat(HeavyFighter.load().entrySet().stream(), SupportFighter.load().entrySet().stream()),
				LightFighter.load().entrySet().stream()).forEach(e -> {
					String name = e.getKey();
					Fighter f = e.getValue();
					String sensor = null;
					if (f.ScanGravimetricStrength > 0) {
						sensor= "gravimetric";
					}
					if (f.ScanMagnetometricStrength > 0) {
						sensor="magnetometric";
					}
					if (f.ScanLadarStrength > 0) {
						sensor="ladar";
					}
					if (f.ScanRadarStrength > 0) {
						sensor="radar";
					}
					if (name.contains(" II")) {
						String previousmade = name.replaceAll(" II", " I");
						String t1sensor = fighter2type.get(previousmade);
						if (t1sensor!=null&& t1sensor.equals(sensor)) {
							fighter2type.remove(previousmade);
							fighter2type.put(name.replaceAll(" II", ""), sensor);
						} else {
							fighter2type.put(name, sensor);
						}
					} else if (name.contains(" I")) {
						String previousmade = name.replaceAll(" I", " II");
						String t2sensor = fighter2type.get(previousmade);
						if (t2sensor != null && t2sensor.equals(sensor)) {
							fighter2type.remove(previousmade);
							fighter2type.put(name.replaceAll(" I", ""), sensor);
						} else {
							fighter2type.put(name, sensor);
						}
					} else {
						fighter2type.put(name, sensor);
					}
				});
		ArrayList<String> list = new ArrayList<>(fighter2type.keySet());
		Collections.sort(list);
		for (String n : list) {
			if (fighter2type.get(n) != null) {
				System.out.println(n + "\t" + fighter2type.get(n));
			}
		}
	}


}
