package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EmapMoons {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "mapMoons";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EmapMoons>> LOADER_JACKSON_YAML = new JacksonYamlLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Integer, EmapMoons> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("attributes"::equals).findAny().isPresent()) {
						node.setType(EmapMoons.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EmapMoons>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public static class Attributes {
		public int heightMap1;
		public int heightMap2;
		public int shaderPreset;
	}

	public Attributes attributes;
	public int celestialIndex;
	public LinkedHashMap<String, String> name;
	public List<Integer> npcStationIDs;
	public int orbitID;
	public int orbitIndex;

	public static class Position {
		public BigDecimal x;
		public BigDecimal y;
		public BigDecimal z;
	}

	public Position position;

	public BigDecimal radius;
	public long solarSystemID;

	public static class Statistics {
		public BigDecimal density;
		public BigDecimal eccentricity;
		public BigDecimal escapeVelocity;
		public boolean locked;
		public BigDecimal massDust;
		public BigDecimal massGas;
		public BigDecimal orbitPeriod;
		public BigDecimal orbitRadius;
		public BigDecimal pressure;
		public BigDecimal rotationRate;
		public String spectralClass;
		public BigDecimal surfaceGravity;
		public BigDecimal temperature;
	}

	public Statistics statistics;

	public int typeID;

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.err.println("loaded : " + loaded.size());
		long withname = loaded.values().stream().filter(m -> m.name != null).count();
		System.err.println("named=" + withname);
		var first = loaded.entrySet().iterator().next().getValue();
		System.err.println("first : solarSystemID=" + first.solarSystemID + " typeID=" + first.typeID);

	}

}
