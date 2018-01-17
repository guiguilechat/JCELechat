package fr.guiguilechat.eveonline.model.sde.load.fsd.universe;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

public class Constellation {
	public LinkedHashMap<String, SolarSystem> systems = new LinkedHashMap<>();

	public double[] center, min, max;
	public int nameID;
	public int constellationID;
	public double radius;
	public int factionID;
	public int wormholeClassID;

	public static Constellation load(File contellationDir) {
		if (!contellationDir.isDirectory()) {
			return null;
		}
		File[] data = contellationDir.listFiles((d, name) -> name.equals("constellation.staticdata"));
		try {
			if (data == null || data.length != 1 || !data[0].exists() || !data[0].isFile()) {
				throw new UnsupportedOperationException(
						"while looking for one file of constellation data, found " + Arrays.asList(data));
			}
			Constellation ret = new Yaml().loadAs(new FileReader(data[0]), Constellation.class);
			for( File child : contellationDir.listFiles()) {
				if(child.isDirectory()) {
					ret.systems.put(child.getName(), SolarSystem.load(child));
				}
			}
			return ret;
		} catch (Exception e) {
			throw new UnsupportedOperationException(
					"while loading constellation from directory " + contellationDir.getAbsolutePath(), e);
		}
	}

}
