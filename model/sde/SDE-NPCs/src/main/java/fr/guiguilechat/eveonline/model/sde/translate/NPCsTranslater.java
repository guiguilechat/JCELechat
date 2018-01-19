package fr.guiguilechat.eveonline.model.sde.translate;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.esi.raw.ESIRaw;
import fr.guiguilechat.eveonline.model.sde.load.bsd.EagtAgentTypes;
import fr.guiguilechat.eveonline.model.sde.load.bsd.EagtAgents;
import fr.guiguilechat.eveonline.model.sde.locations.Station;
import fr.guiguilechat.eveonline.model.sde.npcs.Agent;
import fr.guiguilechat.eveonline.model.sde.npcs.Corporation;
import is.ccp.tech.esi.responses.R_get_alliances_alliance_id;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id;

public class NPCsTranslater {

	/**
	 *
	 * @param args
	 *          should be [database destination root], typically
	 *          src/generated/resources/
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		long timeStart = System.currentTimeMillis();
		File folderOut = new File(args.length == 0 ? "src/generated/resources/" : args[0]);
		folderOut.mkdirs();

		LinkedHashMap<String, Agent> agents = new LinkedHashMap<>();
		LinkedHashMap<String, Corporation> corporations = new LinkedHashMap<>();

		translate(EagtAgents.load(), EagtAgentTypes.loadById(), agents, corporations);

		// sort

		Stream.of(agents, corporations).forEach(m -> {
			ArrayList<Entry<String, ?>> list = new ArrayList<>(m.entrySet());
			Collections.sort(list, (e1, e2) -> e1.getKey().compareTo(e2.getKey()));
			m.clear();
			for (Entry<String, ?> e : list) {
				((Map<String, Object>) m).put(e.getKey(), e.getValue());
			}
		});

		// save

		Agent.export(agents, folderOut);
		Corporation.export(corporations, folderOut);

		System.err.println("exported npcs in " + (System.currentTimeMillis() - timeStart) / 1000 + "s");
	}

	private static void translate(ArrayList<EagtAgents> eagents, HashMap<Integer, String> agentTypes,
			LinkedHashMap<String, Agent> agents, LinkedHashMap<String, Corporation> corporations) {
		ESIRaw esi = new ESIRaw(null, null);
		Map<Integer, String> stationsByID = Station.loadById();
		LinkedHashMap<String, Station> stations = Station.load();
		Map<Long, R_get_corporations_corporation_id> corpNames = LongStream.of(esi.get_corporations_npccorps()).parallel()
				.mapToObj(l -> l).collect(Collectors.toMap(l -> l, esi::get_corporations_corporation_id));
		Map<Long, String> allianceNames = new HashMap<>();
		corpNames.values().stream().mapToLong(corp -> corp.alliance_id).distinct().filter(l -> l > 0).forEachOrdered(l -> {
			R_get_alliances_alliance_id ally = esi.get_alliances_alliance_id(l);
			if (ally != null) {
				allianceNames.put(l, ally.name);
			}
		});
		Map<Long, String> agentNames = Stream
				.of(esi.get_characters_names(eagents.stream().parallel().mapToLong(a -> a.agentID).toArray()))
				.collect(Collectors.toMap(n -> ((Long) n.character_id), n -> n.character_name));
		for (EagtAgents eagt : eagents) {
			Agent add = new Agent();
			add.corporation = corpNames.get((long) eagt.corporationID).name;
			add.id = eagt.agentID;
			add.isLocator = eagt.isLocator;
			add.level = eagt.level;
			String station = stationsByID.get(eagt.locationID);
			if (station != null) {
				add.station = station;
				add.system = stations.get(station).solarSystem;
			}
			agents.put(agentNames.get((long) eagt.agentID), add);
		}
		for (Entry<Long, R_get_corporations_corporation_id> e : corpNames.entrySet()) {
			Corporation add = new Corporation();
			add.id = (int) (long) e.getKey();
			add.alliance = allianceNames.get(e.getValue().alliance_id);
			corporations.put(e.getValue().name, add);
		}

	}

}
