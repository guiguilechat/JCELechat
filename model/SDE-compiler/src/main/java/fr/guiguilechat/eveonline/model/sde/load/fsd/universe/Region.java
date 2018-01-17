package fr.guiguilechat.eveonline.model.sde.load.fsd.universe;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

public class Region {
	public LinkedHashMap<String, Constellation> constellations = new LinkedHashMap<>();

	public double[] center, min, max;
	public int descriptionID;
	public int factionID;
	public int nameID;
	public int nebula;
	public int regionID;
	public int wormholeClassID;

	public static Region load(File regionDir) {
		if (!regionDir.isDirectory()) {
			return null;
		}
		File[] data = regionDir.listFiles((d, name) -> name.equals("region.staticdata"));
		try {
			if (data == null || data.length != 1 || !data[0].exists() || !data[0].isFile()) {
				throw new UnsupportedOperationException(
						"while looking for one file of system data, found " + Arrays.asList(data));
			}
			Region ret = new Yaml().loadAs(new FileReader(data[0]), Region.class);
			for (File child : regionDir.listFiles()) {
				if (child.isDirectory()) {
					ret.constellations.put(child.getName(), Constellation.load(child));
				}
			}
			return ret;
		} catch (Exception e) {
			throw new UnsupportedOperationException("while loading region from directory " + regionDir.getAbsolutePath(), e);
		}
	}
}
