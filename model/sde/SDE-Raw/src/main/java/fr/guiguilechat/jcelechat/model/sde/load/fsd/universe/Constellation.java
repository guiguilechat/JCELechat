package fr.guiguilechat.jcelechat.model.sde.load.fsd.universe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;

public class Constellation {

	private static final Logger logger = LoggerFactory.getLogger(Constellation.class);

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
		if (data == null || data.length != 1 || !data[0].exists() || !data[0].isFile()) {
			throw new UnsupportedOperationException(
					"while looking for one file of constellation data, found " + Arrays.asList(data));
		}
		try {
			Object ret = new Yaml().loadAs(SDECache.fileReader(data[0]), Constellation.class);
			if (ret.getClass().getClassLoader() != Constellation.class.getClassLoader()) {
				logger.warn("returned object " + ret + " from cl " + ret.getClass().getClassLoader()
						+ " while Constellation cl is "
						+ Constellation.class.getClassLoader() + " , current thread cl is "
						+ Thread.currentThread().getClass().getClassLoader() + " and current thread context cl is "
						+ Thread.currentThread().getContextClassLoader());
			}
			Constellation cst = (Constellation) ret;
			File[] sysFiles = Stream.of(contellationDir.listFiles()).parallel().filter(File::isDirectory)
					.toArray(File[]::new);
			for (File sysFile : sysFiles) {
				cst.systems.put(sysFile.getName(), SolarSystem.load(sysFile));
			}
			return cst;
		} catch (ClassCastException e) {
			throw new UnsupportedOperationException("while loading constellation from directory "
					+ contellationDir.getAbsolutePath() + " : Constellation cl is " + Constellation.class.getClassLoader(), e);
		} catch (FileNotFoundException e) {
			throw new UnsupportedOperationException(
					"while loading constellation from directory " + contellationDir.getAbsolutePath(), e);
		}
	}

}
