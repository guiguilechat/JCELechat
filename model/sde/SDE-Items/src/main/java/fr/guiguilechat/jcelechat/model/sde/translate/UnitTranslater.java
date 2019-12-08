package fr.guiguilechat.jcelechat.model.sde.translate;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.load.bsd.EeveUnits;
import fr.guiguilechat.jcelechat.model.sde.meta.Unit;

public class UnitTranslater {

	public static LinkedHashMap<Integer, Unit> load() {
		LinkedHashMap<Integer, Unit> ret = new LinkedHashMap<>();
		// add null unit
		Unit created = new Unit();
		created.id = 0;
		created.suffix = "ø";
		created.name = "null_unit";
		ret.put(0, created);
		for (EeveUnits unit : EeveUnits.load()) {
			created = new Unit();
			created.name = unit.unitName;
			created.id = unit.unitID;
			String enumString = null;
			if (unit.displayName != null && isEnum(unit.displayName)) {
				enumString = unit.displayName;
			}
			if ((unit.displayName == null || unit.displayName.isEmpty()) && unit.description != null
					&& isEnum(unit.description)) {
				enumString = unit.description;
			}
			if (enumString != null) {
				Map<Integer, String> termsMap = Stream.of(enumString.split(" ")).filter(s -> s.contains("="))
						.collect(Collectors.toMap(s -> Integer.parseInt(s.split("=")[0]), s -> s.split("=")[1]));
				created.enums = new String[termsMap.keySet().stream().mapToInt(i -> i).max().getAsInt() + 1];
				for (Entry<Integer, String> e : termsMap.entrySet()) {
					created.enums[e.getKey()] = e.getValue();
				}
			} else {
				created.suffix = unit.displayName;
			}
			ret.put(created.id, created);
		}
		return ret;
	}

	public static void main(String[] args) {

		long timeStart = System.currentTimeMillis();

		File folderOut = new File(args.length == 0 ? "src/generated/resources/" : args[0]);
		folderOut.mkdirs();
		Unit.export(load(), folderOut);

		System.err.println("exported units in " + (System.currentTimeMillis() - timeStart) / 1000 + "s");
	}

	public static final Pattern enumMatcher = Pattern.compile(".*[^ ]=[^ ].*");

	public static boolean isEnum(String description) {
		return enumMatcher.matcher(description).matches();
	}


}
