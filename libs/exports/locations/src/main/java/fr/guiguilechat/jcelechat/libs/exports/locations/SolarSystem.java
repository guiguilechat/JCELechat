package fr.guiguilechat.jcelechat.libs.exports.locations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.lelouet.tools.application.yaml.CleanRepresenter;
import fr.lelouet.tools.application.yaml.YAMLTools;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SolarSystem extends ALocation {

	// loading


	public static final String RESOURCE_PATH = "SDE/locations/solarsystems.yaml";

	protected static final LoaderOptions LOADEROPTIONS = new LoaderOptions();
	static {
		LOADEROPTIONS.setCodePointLimit(Integer.MAX_VALUE);
	}

	private static LinkedHashMap<String, SolarSystem> cache = null;

	public static synchronized LinkedHashMap<String, SolarSystem> load() {
		if (cache == null) {
			try (InputStreamReader reader = new InputStreamReader(
					SolarSystem.class.getClassLoader().getResourceAsStream(RESOURCE_PATH))) {
				cache = new Yaml(LOADEROPTIONS).loadAs(reader, Container.class).locations;
			} catch (Exception exception) {
				throw new UnsupportedOperationException("catch this", exception);
			}
		}
		return cache;
	}

	private static Map<Integer, SolarSystem> loadById = null;

	public static Map<Integer, SolarSystem> loadById() {
		if (loadById == null) {
			LinkedHashMap<?, SolarSystem> mcache = load();
			synchronized (mcache) {
				if (loadById == null) {
					loadById = mcache.values().stream().collect(Collectors.toMap(
							e -> e.id,
							o -> o));
				}
			}
		}
		return loadById;
	}

	private static Map<String, SolarSystem> loadByName = null;

	public static Map<String, SolarSystem> loadByName() {
		if (loadByName == null) {
			LinkedHashMap<?, SolarSystem> mcache = load();
			synchronized (mcache) {
				if (loadByName == null) {
					loadByName = mcache.values().stream().collect(Collectors.toMap(
							SolarSystem::name,
							e -> e));
				}
			}
		}
		return loadByName;
	}

	private static Map<String, SolarSystem> loadByLowerName = null;

	public static Map<String, SolarSystem> loadByLowerName() {
		if (loadByLowerName == null) {
			LinkedHashMap<?, SolarSystem> mcache = load();
			synchronized (mcache) {
				if (loadByLowerName == null) {
					loadByLowerName = mcache.values().stream().collect(Collectors.toMap(
							e -> e.name().toLowerCase(),
							o -> o));
				}
			}
		}
		return loadByLowerName;
	}

	public static void export(LinkedHashMap<String, SolarSystem> data, File folderout) {
		File output = new File(folderout, RESOURCE_PATH);
		output.mkdirs();
		output.delete();
		Container c = new Container();
		c.locations = data;
		try {
			new Yaml(new CleanRepresenter(), YAMLTools.blockDumper()).dump(c, new FileWriter(output));
		} catch (IOException e) {
			throw new UnsupportedOperationException("while exporting systems to " + output.getAbsolutePath(), e);
		}
	}

	private static final class Container {
		public LinkedHashMap<String, SolarSystem> locations;
	}

	// normalizing

	public static SolarSystem getSystem(String name) {
		if (name == null) {
			return null;
		}
		return loadByLowerName().get(name.toLowerCase());
	}

	public static SolarSystem getSystem(int id) {
		return loadById().get(id);
	}

	// structure

	public BigDecimal truesec;

	public String constellation, region;

	public Constellation constellation() {
		return Constellation.getConstellation(constellation);
	}

	public Region region() {
		return Region.getRegion(region);
	}

	public boolean anchorStructures = false;
	public boolean isBorder = false;
	public boolean isCorridor = false;
	public boolean isFringe = false;
	public boolean isHub = false;

	// helper

	public double centerDistanceAu(SolarSystem other) {
		if (other.centerX == null || other.centerY == null || other.centerZ == null
				|| centerX == null || centerY == null || centerZ == null) {
			return Double.NaN;
		}
		return Math.sqrt(
				Math.pow((other.centerX.doubleValue() - centerX.doubleValue()) / AU_IN_M, 2)
						+ Math.pow((other.centerY.doubleValue() - centerY.doubleValue()) / AU_IN_M, 2)
						+ Math.pow((other.centerZ.doubleValue() - centerZ.doubleValue()) / AU_IN_M, 2));
	}

	/**
	 * sec status represents the speed of intervention from Concord. HS means
	 * concord will destroy you, LS means turrets will defend you, and NS means
	 * you gonna die helplessly
	 */
	public enum SECSTATUS {
		HS, LS, NS;

		public static SECSTATUS of(double truesec) {
			return truesec > 0.45 ? SECSTATUS.HS : truesec <= 0 ? SECSTATUS.NS : SECSTATUS.LS;
		}
	}

	public SECSTATUS secStatus() {
		return SECSTATUS.of(truesec.doubleValue());
	}

	public boolean isHS() {
		return isKS && truesec.doubleValue() > 0.45;
	}

	public boolean isLS() {
		return 0 < truesec.doubleValue() && truesec.doubleValue() <= 0.45;
	}

	public boolean isNS() {
		return 0 >= truesec.doubleValue();
	}

	@Override
	public String toString() {
		return name;
	}

}
