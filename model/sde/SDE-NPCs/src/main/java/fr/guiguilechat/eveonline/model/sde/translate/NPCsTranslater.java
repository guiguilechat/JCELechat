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

import fr.guiguilechat.eveonline.model.esi.connect.ESIConnection;
import fr.guiguilechat.eveonline.model.esi.connect.ESIRawConnection;
import fr.guiguilechat.eveonline.model.sde.load.bsd.EagtAgentTypes;
import fr.guiguilechat.eveonline.model.sde.load.bsd.EagtAgents;
import fr.guiguilechat.eveonline.model.sde.load.fsd.Eblueprints;
import fr.guiguilechat.eveonline.model.sde.load.fsd.Eblueprints.Material;
import fr.guiguilechat.eveonline.model.sde.load.fsd.EtypeIDs;
import fr.guiguilechat.eveonline.model.sde.locations.Station;
import fr.guiguilechat.eveonline.model.sde.npcs.Agent;
import fr.guiguilechat.eveonline.model.sde.npcs.Corporation;
import fr.guiguilechat.eveonline.model.sde.npcs.LPOffer;
import fr.guiguilechat.eveonline.model.sde.npcs.LPOffer.ItemRef;
import is.ccp.tech.esi.responses.R_get_alliances_alliance_id;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id;
import is.ccp.tech.esi.responses.R_get_loyalty_stores_corporation_id_offers;
import is.ccp.tech.esi.responses.R_get_loyalty_stores_corporation_id_offers_required_items;

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
		LinkedHashMap<Integer, LPOffer> lpoffers = new LinkedHashMap<>();

		translate(EagtAgents.load(), EagtAgentTypes.loadById(), agents, corporations, lpoffers);

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
			LinkedHashMap<String, Agent> agents, LinkedHashMap<String, Corporation> corporations,
			LinkedHashMap<Integer, LPOffer> offers) {
		ESIConnection esi = new ESIConnection(null, null);
		Map<Integer, String> stationsByID = Station.loadById();
		LinkedHashMap<String, Station> stations = Station.load();
		Map<Long, R_get_corporations_corporation_id> corpNames = LongStream.of(esi.raw.get_corporations_npccorps())
				.parallel().mapToObj(l -> l).collect(Collectors.toMap(l -> l, esi.raw::get_corporations_corporation_id));
		Map<Long, String> allianceNames = new HashMap<>();
		corpNames.values().stream().mapToLong(corp -> corp.alliance_id).distinct().filter(l -> l > 0).forEachOrdered(l -> {
			R_get_alliances_alliance_id ally = esi.raw.get_alliances_alliance_id(l);
			if (ally != null) {
				allianceNames.put(l, ally.name);
			}
		});
		Map<Long, String> agentNames = Stream
				.of(esi.names.characterNames(eagents.stream().parallel().mapToLong(a -> a.agentID).toArray()))
				.collect(Collectors.toMap(n -> ((Long) n.character_id), n -> n.character_name));
		for (EagtAgents eagt : eagents) {
			Agent agent = new Agent();
			agent.corporation = corpNames.get((long) eagt.corporationID).name;
			agent.id = eagt.agentID;
			agent.isLocator = eagt.isLocator;
			agent.level = eagt.level;
			agent.type = agentTypes.get(eagt.agentTypeID);
			String station = stationsByID.get(eagt.locationID);
			if (station != null) {
				agent.station = station;
				agent.system = stations.get(station).solarSystem;
			}
			agents.put(agentNames.get((long) eagt.agentID), agent);
		}
		for (Entry<Long, R_get_corporations_corporation_id> e : corpNames.entrySet()) {
			Corporation add = new Corporation();
			add.id = (int) (long) e.getKey();
			add.alliance = allianceNames.get(e.getValue().alliance_id);
			corporations.put(e.getValue().name, add);
		}
		corporations.values().stream().parallel().flatMap(c -> {
			R_get_loyalty_stores_corporation_id_offers[] values = esi.raw.get_loyalty_stores_corporation_id_offers(c.id);
			return values == null ? Stream.empty() : Stream.of(values);
		}).forEachOrdered(o -> {
			if (!offers.containsKey((int) o.offer_id)) {
				offers.put((int) o.offer_id, makeOffer(o));
			}
		});
		corporations.values().stream().parallel().forEach(c -> loadCorpOffers(c, esi.raw, offers));
	}

	protected static LPOffer makeOffer(R_get_loyalty_stores_corporation_id_offers o) {
		LinkedHashMap<Integer, EtypeIDs> typesbyID = EtypeIDs.load();
		LinkedHashMap<Integer, Eblueprints> bps = Eblueprints.load();
		LPOffer lpo = new LPOffer();
		lpo.requirements.isk += o.isk_cost;
		lpo.requirements.lp += o.lp_cost;
		lpo.offer_name = typesbyID.get((int) o.type_id).enName();
		lpo.id = (int) o.offer_id;

		for (R_get_loyalty_stores_corporation_id_offers_required_items ir : o.required_items) {
			ItemRef translated = new ItemRef();
			translated.quantity = (int) ir.quantity;
			translated.item = typesbyID.get((int) ir.type_id).enName();
			lpo.requirements.items.add(translated);
		}

		Eblueprints bp = bps.get((int) o.type_id);

		if (bp != null) {// the lp offers a BPC
			for (Material m : bp.activities.manufacturing.materials) {
				ItemRef translated = new ItemRef();
				translated.quantity = (int) (m.quantity * o.quantity);
				translated.item = typesbyID.get(m.typeID).enName();
				lpo.requirements.items.add(translated);
			}
			Material prod = bp.activities.manufacturing.products.get(0);
			lpo.product.item = typesbyID.get(prod.typeID).enName();
			lpo.product.quantity = (int) (prod.quantity * o.quantity);
			lpo.offer_name = (o.quantity == 1 ? "" : "" + o.quantity + "* ") + lpo.product.item + "(BPC)";
		} else {// the lp offers a non-bpc
			lpo.product.quantity = (int) o.quantity;
			lpo.product.item = typesbyID.get((int) o.type_id).enName();
			lpo.offer_name = (o.quantity == 1 ? "" : "" + o.quantity + "* ") + lpo.product.item;
		}
		return lpo;
	}

	protected static void loadCorpOffers(Corporation c, ESIRawConnection raw, LinkedHashMap<Integer, LPOffer> alloffers) {
		R_get_loyalty_stores_corporation_id_offers[] offers = raw.get_loyalty_stores_corporation_id_offers(c.id);
		if (offers != null) {
			for (R_get_loyalty_stores_corporation_id_offers o : offers) {
				c.lpoffers.add((int) o.offer_id);
			}
		}
		Collections.sort(c.lpoffers);
	}

}
