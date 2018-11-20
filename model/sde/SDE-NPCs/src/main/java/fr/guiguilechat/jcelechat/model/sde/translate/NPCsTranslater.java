package fr.guiguilechat.jcelechat.model.sde.translate;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.model.FileTools;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_loyalty_stores_corporation_id_offers;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_factions;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_corporations_corporation_id_starbases_starbase_id_fuels;
import fr.guiguilechat.jcelechat.model.sde.load.bsd.EagtAgentTypes;
import fr.guiguilechat.jcelechat.model.sde.load.bsd.EagtAgents;
import fr.guiguilechat.jcelechat.model.sde.load.bsd.EcrpNPCCorporations;
import fr.guiguilechat.jcelechat.model.sde.load.bsd.EcrpNPCDivisions;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.Material;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeIDs;
import fr.guiguilechat.jcelechat.model.sde.locations.Station;
import fr.guiguilechat.jcelechat.model.sde.npcs.Agent;
import fr.guiguilechat.jcelechat.model.sde.npcs.Corporation;
import fr.guiguilechat.jcelechat.model.sde.npcs.LPOffer;
import fr.guiguilechat.jcelechat.model.sde.npcs.LPOffer.ItemRef;

public class NPCsTranslater {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(NPCsTranslater.class);

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
		FileTools.delDir(folderOut);
		folderOut.mkdirs();

		LinkedHashMap<String, Agent> agents = new LinkedHashMap<>();
		LinkedHashMap<String, Corporation> corporations = new LinkedHashMap<>();
		LinkedHashMap<Integer, LPOffer> lpoffers = new LinkedHashMap<>();

		translate(EagtAgents.load(), EagtAgentTypes.loadById(), EcrpNPCDivisions.loadById(), agents, corporations,
				lpoffers);

		// sort

		Stream.of(agents, corporations).forEach(m -> {
			ArrayList<Entry<String, ?>> list = new ArrayList<>(m.entrySet());
			Collections.sort(list, (e1, e2) -> e1.getKey().compareTo(e2.getKey()));
			m.clear();
			for (Entry<String, ?> e : list) {
				((Map<String, Object>) m).put(e.getKey(), e.getValue());
			}
		});
		ArrayList<Entry<Integer, LPOffer>> list = new ArrayList<>(lpoffers.entrySet());
		Collections.sort(list, (e1, e2) -> e1.getKey().compareTo(e2.getKey()));
		lpoffers.clear();
		for (Entry<Integer, LPOffer> e : list) {
			lpoffers.put(e.getKey(), e.getValue());
		}

		// save

		Agent.export(agents, folderOut);
		Corporation.export(corporations, folderOut);
		LPOffer.export(lpoffers, folderOut);

		System.err.println("exported npcs in " + (System.currentTimeMillis() - timeStart) / 1000 + "s");
	}

	private static void translate(ArrayList<EagtAgents> eagents, HashMap<Integer, String> agentTypes,
			Map<Integer, String> divisionTypes, LinkedHashMap<String, Agent> agents,
			LinkedHashMap<String, Corporation> corporations, LinkedHashMap<Integer, LPOffer> offers) {
		ESIAccess esi = ESIAccess.INSTANCE;
		Map<Integer, String> stationsByID = Station.loadById();
		LinkedHashMap<String, Station> stations = Station.load();
		HashMap<Integer, EcrpNPCCorporations> snpcCorps = EcrpNPCCorporations.loadById();
		Map<Integer, R_get_corporations_corporation_id> npcCorps = IntStream
				.of(esi.connection.get_corporations_npccorps(null))
				.parallel().mapToObj(l -> l)
				.collect(Collectors.toMap(l -> l, l -> esi.connection.get_corporations(l, null)));
		Integer[] allyIds = npcCorps.keySet().stream().map(corp -> snpcCorps.get(corp)).filter(corp -> corp != null)
				.map(c -> c.factionID).filter(i -> i > 0).distinct().toArray(Integer[]::new);
		if (allyIds.length == 0) {
			for (Entry<Integer, R_get_corporations_corporation_id> e : npcCorps.entrySet()) {
				System.err.println(" " + e.getKey() + " : " + e.getValue().name + " : " + e.getValue().alliance_id + " : "
						+ e.getValue().faction_id);
			}
		} else {
			System.err.println("npc alliances are " + Arrays.asList(allyIds));
		}
		Map<Integer, R_get_universe_factions> factionById = Stream.of(esi.connection.get_universe_factions(null))
				.collect(Collectors.toMap(f -> f.faction_id, f -> f));
		Map<Integer, String> agentNames = Stream
				.of(esi.universe.names(eagents.stream().parallel().mapToInt(a -> a.agentID).toArray()))
				.collect(Collectors.toMap(n -> (int) n.id, n -> n.name));

		for (EagtAgents eagt : eagents) {
			Agent agent = new Agent();
			agent.corporation = npcCorps.get(eagt.corporationID).name;
			agent.id = eagt.agentID;
			agent.name = agentNames.get(eagt.agentID);
			agent.isLocator = eagt.isLocator;
			agent.level = eagt.level;
			agent.type = agentTypes.get(eagt.agentTypeID);
			agent.division = divisionTypes.get(eagt.divisionID);
			String station = stationsByID.get(eagt.locationID);
			if (station != null) {
				agent.station = station;
				if (station != null) {
					agent.stationId=eagt.locationID;
				}
				agent.system = stations.get(station).solarSystem;
			}
			agents.put(agent.name, agent);
		}
		for (Entry<Integer, R_get_corporations_corporation_id> e : npcCorps.entrySet()) {
			Corporation add = new Corporation();
			add.id = e.getKey();
			EcrpNPCCorporations snpc = snpcCorps.get(add.id);
			if (snpc == null) {
				logger.warn("can't find corporation from id " + add.id);
				continue;
			}
			add.name = e.getValue().name;
			R_get_universe_factions faction = factionById.get(snpc.factionID);

			if (faction == null) {
				logger.warn("can't find faction from id " + snpc.factionID + " for corporation " + add.name);
				continue;
			}
			add.faction = faction.name;
			if (e.getValue().faction_id != 0) {
				add.warfare = factionById.get(e.getValue().faction_id).name;
			}
			add.concordRate = convertConcordRate(add.name, add.faction, add.warfare);
			corporations.put(e.getValue().name, add);
		}
		corporations.values().stream().parallel().flatMap(c -> {
			R_get_loyalty_stores_corporation_id_offers[] values = esi.connection.get_loyalty_stores_offers(c.id,
					null);
			return values == null ? Stream.empty() : Stream.of(values);
		}).forEachOrdered(o -> {
			if (!offers.containsKey(o.offer_id)) {
				offers.put(o.offer_id, makeOffer(o));
			}
		});
		corporations.values().stream().parallel().forEach(c -> loadCorpOffers(c, esi.connection, offers));
	}

	private static double convertConcordRate(String name, String alliance, String fw) {
		if (fw != null) {
			return 0;
		}
		switch (alliance) {
		case "CONCORD Assembly":
			return 1;
		case "Amarr Empire":
		case "Ammatar Mandate":
		case "Caldari State":
		case "Gallente Federation":
		case "Khanid Kingdom":
		case "Minmatar Republic":
			return 0.8;
		case "ORE":
		case "The Syndicate":
		case "Thukker Tribe":
		case "Servant Sisters of EVE":
			return 0.4;
		}
		return 0;
	}

	protected static LPOffer makeOffer(R_get_loyalty_stores_corporation_id_offers o) {
		LinkedHashMap<Integer, EtypeIDs> typesbyID = EtypeIDs.load();
		LinkedHashMap<Integer, Eblueprints> bps = Eblueprints.load();
		LPOffer lpo = new LPOffer();
		lpo.requirements.isk += o.isk_cost;
		lpo.requirements.lp += o.lp_cost;
		lpo.name = typesbyID.get(o.type_id).enName();
		lpo.id = o.offer_id;

		for (get_corporations_corporation_id_starbases_starbase_id_fuels ir : o.required_items) {
			ItemRef translated = new ItemRef();
			translated.quantity = ir.quantity;
			translated.item = typesbyID.get(ir.type_id).enName();
			lpo.requirements.items.add(translated);
		}

		Eblueprints bp = bps.get(o.type_id);

		if (bp != null) {// the lp offers a BPC
			for (Material m : bp.activities.manufacturing.materials) {
				ItemRef translated = new ItemRef();
				translated.quantity = m.quantity * o.quantity;
				translated.item = typesbyID.get(m.typeID).enName();
				lpo.requirements.items.add(translated);
			}
			Material prod = bp.activities.manufacturing.products.get(0);
			lpo.product.item = typesbyID.get(prod.typeID).enName();
			lpo.product.quantity = prod.quantity * o.quantity;
			lpo.name = (o.quantity == 1 ? "" : "" + o.quantity + "* ") + lpo.product.item + "(BPC)";
		} else {// the lp offers a non-bpc
			lpo.product.quantity = o.quantity;
			lpo.product.item = typesbyID.get(o.type_id).enName();
			lpo.name = (o.quantity == 1 ? "" : "" + o.quantity + "* ") + lpo.product.item;
		}
		return lpo;
	}

	protected static void loadCorpOffers(Corporation c, ESIStatic raw, LinkedHashMap<Integer, LPOffer> alloffers) {
		R_get_loyalty_stores_corporation_id_offers[] offers = raw.get_loyalty_stores_offers(c.id, null);
		if (offers != null) {
			for (R_get_loyalty_stores_corporation_id_offers o : offers) {
				c.lpoffers.add(o.offer_id);
			}
		}
		Collections.sort(c.lpoffers);
	}

}
