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

public class EConstellation {

	private static final Logger logger = LoggerFactory.getLogger(EConstellation.class);

	public LinkedHashMap<String, ESolarSystem> systems = new LinkedHashMap<>();

	public Position center;
	public int constellationID;
	public int factionID;
	public Position max;
	public Position min;
	public int nameID;
	public double radius;
	public int wormholeClassID;

	public static EConstellation load(File contellationDir) {
		if (!contellationDir.isDirectory()) {
			return null;
		}
		File[] data = contellationDir.listFiles((_, name) -> name.equals("constellation.yaml"));
		if (data == null || data.length != 1 || !data[0].exists() || !data[0].isFile()) {
			throw new UnsupportedOperationException(
					"while looking for one file of constellation data, found " + Arrays.asList(data));
		}
		try {
			Object ret = new Yaml().loadAs(SDECache.fileReader(data[0]), EConstellation.class);
			if (ret.getClass().getClassLoader() != EConstellation.class.getClassLoader()) {
				logger.warn("returned object " + ret + " from cl " + ret.getClass().getClassLoader()
						+ " while Constellation cl is "
						+ EConstellation.class.getClassLoader() + " , current thread cl is "
						+ Thread.currentThread().getClass().getClassLoader() + " and current thread context cl is "
						+ Thread.currentThread().getContextClassLoader());
			}
			EConstellation cst = (EConstellation) ret;
			File[] sysFiles = Stream.of(contellationDir.listFiles()).parallel().filter(File::isDirectory).sorted()
					.toArray(File[]::new);
			for (File sysFile : sysFiles) {
				cst.systems.put(sysFile.getName(), ESolarSystem.load(sysFile));
			}
			return cst;
		} catch (ClassCastException e) {
			throw new UnsupportedOperationException("while loading constellation from directory "
					+ contellationDir.getAbsolutePath() + " : Constellation cl is " + EConstellation.class.getClassLoader(), e);
		} catch (FileNotFoundException e) {
			throw new UnsupportedOperationException(
					"while loading constellation from directory " + contellationDir.getAbsolutePath(), e);
		}
	}

}
