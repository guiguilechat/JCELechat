package fr.guiguilechat.jcelechat.model.sde.load.bsd;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import org.yaml.snakeyaml.nodes.NodeId;

import fr.guiguilechat.jcelechat.model.sde.load.OldJacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde.load.OldSnakeYamlALLoader;
import lombok.Getter;
import lombok.experimental.Accessors;

public class EstaStations {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "bsd/staStations.yaml";

	public static final OldJacksonYamlLoader<ArrayList<EstaStations>> LOADER_JACKSON = new OldJacksonYamlLoader<>(
			SDE_FILE);

	public static final OldSnakeYamlALLoader<EstaStations> LOADER_SNAKEYAML = new OldSnakeYamlALLoader<>(
			SDE_FILE) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				node.setType(EstaStations.class);
			}
		}
	};

	public static final OldJacksonYamlLoader<ArrayList<EstaStations>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int constellationID;
	public int corporationID;
	public int dockingCostPerVolume;
	public int maxShipVolumeDockable;
	public int officeRentalCost;
	public int operationID;
	public int regionID;
	public double reprocessingEfficiency;
	public int reprocessingHangarFlag;
	public double reprocessingStationsTake;
	public double security;
	public int solarSystemID;
	public int stationID;
	public String stationName;
	public int stationTypeID;
	public double x;
	public double y;
	public double z;

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final Map<Integer, EstaStations> loadById = LOADER.load().stream()
			.collect(Collectors.toMap(sta -> sta.stationID, sta -> sta));

}
