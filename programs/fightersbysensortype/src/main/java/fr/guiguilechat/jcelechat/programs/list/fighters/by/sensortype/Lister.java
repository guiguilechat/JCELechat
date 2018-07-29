package fr.guiguilechat.jcelechat.programs.list.fighters.by.sensortype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.items.types.Fighter;
import fr.guiguilechat.jcelechat.model.sde.items.types.fighter.HeavyFighter;
import fr.guiguilechat.jcelechat.model.sde.items.types.fighter.LightFighter;
import fr.guiguilechat.jcelechat.model.sde.items.types.fighter.SupportFighter;

public class Lister {

	public static enum SensorTypes {
		gravimetric("blue", "aqua"), magnetometric("green", "lime"), ladar("red", "red"), radar("yellow", "orange");

		private SensorTypes(String color, String webColor) {
			this.webColor = webColor;
			this.color = color;
		}

		public final String color;
		public final String webColor;

	}

	public static void main(String[] args) {
		HashMap<String, SensorTypes> fighter2type = new HashMap<>();
		Stream.concat(Stream.concat(HeavyFighter.load().entrySet().stream(), SupportFighter.load().entrySet().stream()),
				LightFighter.load().entrySet().stream()).forEach(e -> {
					String name = e.getKey();
					Fighter f = e.getValue();
					SensorTypes sensor = null;
					if (f.ScanGravimetricStrength > 0) {
						sensor = SensorTypes.gravimetric;
					}
					if (f.ScanMagnetometricStrength > 0) {
						sensor = SensorTypes.magnetometric;
					}
					if (f.ScanLadarStrength > 0) {
						sensor = SensorTypes.ladar;
					}
					if (f.ScanRadarStrength > 0) {
						sensor = SensorTypes.radar;
					}
					if (name.contains(" II")) {
						String previousmade = name.replaceAll(" II", " I");
						SensorTypes t1sensor = fighter2type.get(previousmade);
						if (t1sensor!=null&& t1sensor.equals(sensor)) {
							fighter2type.remove(previousmade);
							fighter2type.put(name.replaceAll(" II", ""), sensor);
						} else {
							fighter2type.put(name, sensor);
						}
					} else if (name.contains(" I")) {
						String previousmade = name.replaceAll(" I", " II");
						SensorTypes t2sensor = fighter2type.get(previousmade);
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
		System.out.println("<table>");
		for (String n : list) {
			SensorTypes s = fighter2type.get(n);
			if (s != null) {
				System.out.println("<tr><td>" + n + "</td><td style=\"color:" + s.webColor + "\">" + s.name() + "</td></tr>");
			}
		}
		System.out.println("</table>");
	}


}
