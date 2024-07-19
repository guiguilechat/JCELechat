package fr.guiguilechat.jcelechat.model.sde.load.fsd.universe;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

import org.yaml.snakeyaml.Yaml;

public class ERegion {
	public LinkedHashMap<String, EConstellation> constellations = new LinkedHashMap<>();

	public Position center;
	public int descriptionID;
	public int factionID;
	public Position min;
	public Position max;
	public int nameID;
	public int nebula;
	public int regionID;
	public int wormholeClassID;

	public static ERegion load(File regionDir) {
		if (!regionDir.isDirectory()) {
			return null;
		}
		File[] data = regionDir.listFiles((d, name) -> name.equals("region.yaml"));
		if (data == null || data.length != 1 || !data[0].exists() || !data[0].isFile()) {
			throw new UnsupportedOperationException(
					"while looking for one region.staticdata, found : " + Arrays.asList(data));
		}
		try {
			ERegion ret = from(new FileInputStream(data[0]));
			File[] constelFiles = Stream.of(regionDir.listFiles()).parallel().filter(File::isDirectory).sorted()
					.toArray(File[]::new);
			for (File constelFile : constelFiles) {
				ret.constellations.put(constelFile.getName(), EConstellation.load(constelFile));
			}
			return ret;
		} catch (Exception e) {
			throw new UnsupportedOperationException("while loading region from directory " + regionDir.getAbsolutePath(), e);
		}
	}

	public static ERegion from(InputStream is) {
		return new Yaml().loadAs(is, ERegion.class);
	}
}
