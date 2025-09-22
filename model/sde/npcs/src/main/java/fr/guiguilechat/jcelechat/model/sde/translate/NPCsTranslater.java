package fr.guiguilechat.jcelechat.model.sde.translate;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.CacheStatic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.jcesi.tools.locations.Location;
import fr.guiguilechat.jcelechat.model.FileTools;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_loyalty_stores_corporation_id_offers;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_factions;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stations_station_id;
import fr.guiguilechat.jcelechat.model.sde.load.bsd.EinvNames;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eagents;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.Material;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EnpcCorporations;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Etypes;
import fr.guiguilechat.jcelechat.model.sde.npcs.Agent;
import fr.guiguilechat.jcelechat.model.sde.npcs.Agent.AGENT_DIVISION;
import fr.guiguilechat.jcelechat.model.sde.npcs.Agent.AGENT_TYPE;
import fr.guiguilechat.jcelechat.model.sde.npcs.Corporation;
import fr.guiguilechat.jcelechat.model.sde.npcs.LPOffer;
import fr.guiguilechat.jcelechat.model.sde.npcs.LPOffer.ItemRef;
import fr.lelouet.tools.holders.interfaces.collections.MapHolder;

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

		int parrallelism = Runtime.getRuntime().availableProcessors() * 100;
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + parrallelism);

		long timeStart = System.currentTimeMillis();
		File folderOut = new File(args.length == 0 ? "src/generated/resources/" : args[0]);
		FileTools.delDir(folderOut);
		folderOut.mkdirs();

		LinkedHashMap<String, Agent> agents = new LinkedHashMap<>();
		LinkedHashMap<String, Corporation> corporations = new LinkedHashMap<>();
		LinkedHashMap<Integer, LPOffer> lpoffers = new LinkedHashMap<>();

		translate(Eagents.LOADER.load(), agents, corporations,
				lpoffers);

		for (Entry<String, Agent> e : agents.entrySet()) {
			if (e.getKey() == null || e.getValue() == null) {
				throw new NullPointerException("agent key=" + e.getKey() + " value=" + e.getValue());
			}
		}
		for (Entry<String, Corporation> e : corporations.entrySet()) {
			if (e.getKey() == null || e.getValue() == null) {
				throw new NullPointerException("corporation key=" + e.getKey() + " value=" + e.getValue());
			}
		}

		// sort

		Stream.of(agents, corporations).forEach(m -> {
			ArrayList<Entry<String, ?>> list = new ArrayList<>(m.entrySet());
			Collections.sort(list, Comparator.comparing(Entry::getKey));
			m.clear();
			for (Entry<String, ?> e : list) {
				((Map<String, Object>) m).put(e.getKey(), e.getValue());
			}
		});

		ArrayList<Entry<Integer, LPOffer>> copy = new ArrayList<>(lpoffers.entrySet());
		lpoffers.clear();
		copy.stream()
		    .sorted(Comparator.comparing(Entry::getKey))
		    .forEach(e -> lpoffers.put(e.getKey(), e.getValue()));

		// save

		Agent.export(agents, folderOut);
		Corporation.export(corporations, folderOut);
		LPOffer.export(lpoffers, folderOut);

		logger.info("exported npcs in " + (System.currentTimeMillis() - timeStart) / 1000 + "s");
	}

	private static void translate(LinkedHashMap<Integer, Eagents> eagents,
			LinkedHashMap<String, Agent> agents,
			LinkedHashMap<String, Corporation> corporations, LinkedHashMap<Integer, LPOffer> offers) {
		ESIAccess esi = ESIAccess.INSTANCE;
		CacheStatic cache = ESIRawPublic.INSTANCE.cache();

		// prefetch
		MapHolder<Integer, R_get_corporations_corporation_id>
		corporationsHolder = cache.corporations.npccorps().follow(l -> {
			l.parallelStream().forEach(cache.corporations::get);
		}).toMap(i -> i, i -> cache.corporations.get(i).get());
		MapHolder<Integer, R_get_universe_factions> factionsHolder = cache.universe.factions().toMap(f -> f.faction_id);
		eagents.values().parallelStream().map(ag -> ag.locationID).distinct()
		.forEach(lid -> Location.resolve(null, lid).system());
		//

		LinkedHashMap<Integer, EnpcCorporations> ecorps = EnpcCorporations.LOADER.load();
		Map<Integer, String> idx2name = EinvNames.loadById();
		Map<Integer, R_get_corporations_corporation_id> npcCorps =
				corporationsHolder.get();
		Map<Integer, R_get_universe_factions> factionById = factionsHolder.get();
		Map<Integer, Location> agentsLocation = eagents.entrySet().parallelStream()
				.collect(Collectors.toMap(Entry::getKey, eag -> Location.resolve(null, eag.getValue().locationID)));
		logger.info("NPC prefetch received");

		for (Entry<Integer, Eagents> eagt : eagents.entrySet()) {
			Agent agent = new Agent();
			Eagents agt = eagt.getValue();
			agent.id = eagt.getKey();
			agent.corporation = ecorps.get(agt.corporationID).enName();
			agent.name = idx2name.get(agent.id);
			if (agent.name == null) {
				agent.name="unknown_"+agent.id;
			}
			agent.isLocator = agt.isLocator;
			agent.level = agt.level;
			agent.type = AGENT_TYPE.of(agt.agentTypeID);
			if (agent.type == null) {
				logger.warn("no type for agent " + agent + " typeID=" + agt.agentTypeID);
			}
			agent.division = AGENT_DIVISION.of(agt.divisionID);
			if (agent.division == null) {
				logger.warn("no division for agent " + agent.name + " divisionID=" + agt.divisionID);
			}
			Location loc = agentsLocation.get(agent.id);
			if (loc != null) {
				agent.system = loc.system().name;
				R_get_universe_stations_station_id station = loc.station();
				if (station != null) {
					agent.station = station.name;
					agent.stationId = agt.locationID;
				}
				agents.put(agent.name, agent);
			} else {
				logger.warn("invalid location for agent " + agent.name + " locid=" + agt.locationID);
			}
		}

		logger.info("translated agents data");

		for (Entry<Integer, EnpcCorporations> e : ecorps.entrySet()) {
			Corporation add = new Corporation();
			add.id = e.getKey();
			EnpcCorporations snpc = e.getValue();
			add.name = snpc.enName();

			R_get_universe_factions faction = factionById.get(snpc.factionID);
			if (faction == null) {
				logger.debug("can't find faction from id " + snpc.factionID + " for corporation " + add.name);
			} else {
				add.faction = faction.name;
			}
			R_get_corporations_corporation_id esicorp = npcCorps.get(add.id);
			if (esicorp != null && esicorp.faction_id != 0) {
				add.warfare = factionById.get(esicorp.faction_id).name;
			}

			add.concordRate = EnpcCorporations.concordRates().getOrDefault(add.id, 0.0);
			if (add.id == EnpcCorporations.CONCORD_ID) {
				add.concordRate=1.0;
			}
			corporations.put(add.name, add);
		}

		logger.info("translated corporations");

		Map<Integer, R_get_loyalty_stores_corporation_id_offers[]> corpId2offers = corporations.values().stream().parallel()
				.collect(Collectors.toMap(c -> c.id, c -> esi.connection
						.get_loyalty_stores_offers(c.id, null).getOKOr(new R_get_loyalty_stores_corporation_id_offers[] {})));

		logger.info("cached offers");

		Map<Integer, LPOffer> convertedOffers = corpId2offers.values().stream().flatMap(Stream::of)
		    .map(NPCsTranslater::makeOffer)
		    .filter(o -> o != null)
				.collect(Collectors.toMap(off -> off.id, off -> off, (o1, _) -> o1));
		offers.putAll(convertedOffers);
		corporations.values().stream().parallel().forEach(c -> loadCorpOffers(c, corpId2offers.get(c.id), offers));

		logger.info("added offers to corporations");

	}

	protected static LPOffer makeOffer(R_get_loyalty_stores_corporation_id_offers o) {
		LinkedHashMap<Integer, Eblueprints> bps = Eblueprints.LOADER.load();
		LPOffer lpo = new LPOffer();
		lpo.requirements.isk += o.isk_cost;
		lpo.requirements.lp += o.lp_cost;
		lpo.name = Etypes.getName(o.type_id);
		lpo.id = o.offer_id;

		Stream.of(o.required_items).forEach(ir -> {
			ItemRef translated = new ItemRef();
			translated.quantity = ir.quantity;
			translated.id = ir.type_id;
			lpo.requirements.items.add(translated);
		});

		Eblueprints bp = bps.get(o.type_id);

		if (bp != null) {// the lp offers a BPC
			lpo.bpid = bp.blueprintTypeID;
			lpo.bpruns = o.quantity;
			for (Material m : bp.activities.manufacturing.materials) {
				ItemRef translated = new ItemRef();
				translated.quantity = m.quantity * o.quantity;
				translated.id = m.typeID;
				lpo.requirements.items.add(translated);
			}
			Material prod = bp.activities.manufacturing.products.get(0);
			lpo.product.id = prod.typeID;
			lpo.product.quantity = prod.quantity * o.quantity;
			if (lpo.product.type() == null) {
				logger.debug("discard offer " + o.offer_id + " as product type " + prod.typeID + " can't be loaded");
				return null;
			} else {
				lpo.name = (o.quantity == 1 ? "" : "" + o.quantity + "* ") + lpo.product.name() + "(BPC)";
			}
		} else {// the lp offers a non-bpc
			lpo.product.quantity = o.quantity;
			lpo.product.id = o.type_id;
			if (lpo.product.type() == null) {
				logger.debug("discard offer " + o.offer_id + " as product type " + o.type_id + " can't be loaded");
				return null;
			} else {
				lpo.name = (o.quantity == 1 ? "" : "" + o.quantity + "* ") + lpo.product.name();
			}
		}
		Collections.sort(lpo.requirements.items, Comparator.comparing(ir -> ir.id));
		return lpo;
	}

	protected static void loadCorpOffers(Corporation c, R_get_loyalty_stores_corporation_id_offers[] offers,
			LinkedHashMap<Integer, LPOffer> alloffers) {
		if (offers != null) {
			for (R_get_loyalty_stores_corporation_id_offers o : offers) {
				if (alloffers.containsKey(o.offer_id)) {
					c.lpoffers.add(o.offer_id);
				}
			}
		}
		Collections.sort(c.lpoffers);
	}

}
