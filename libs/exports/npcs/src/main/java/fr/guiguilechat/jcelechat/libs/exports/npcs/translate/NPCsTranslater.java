package fr.guiguilechat.jcelechat.libs.exports.npcs.translate;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.CacheStatic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.libs.exports.npcs.Agent;
import fr.guiguilechat.jcelechat.libs.exports.npcs.Agent.AGENT_DIVISION;
import fr.guiguilechat.jcelechat.libs.exports.npcs.Agent.AGENT_TYPE;
import fr.guiguilechat.jcelechat.libs.exports.npcs.Corporation;
import fr.guiguilechat.jcelechat.libs.exports.npcs.LPOffer;
import fr.guiguilechat.jcelechat.libs.exports.npcs.LPOffer.ItemRef;
import fr.guiguilechat.jcelechat.libs.sde.model.industry.BluePrint;
import fr.guiguilechat.jcelechat.libs.sde.model.industry.BluePrint.Consumed;
import fr.guiguilechat.jcelechat.libs.sde.model.industry.BluePrint.Produced;
import fr.guiguilechat.jcelechat.libs.sde.model.items.Type;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.Station;
import fr.guiguilechat.jcelechat.libs.sde.model.npcs.Faction;
import fr.guiguilechat.jcelechat.libs.sde.model.npcs.NPCCharacter;
import fr.guiguilechat.jcelechat.libs.sde.model.npcs.NPCCorporation;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_loyalty_stores_corporation_id_offers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NPCsTranslater {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(NPCsTranslater.class);

	static void delDir(File delete) {
		if (delete.exists()) {
			if (delete.isDirectory()) {
				for (File child : delete.listFiles()) {
					delDir(child);
				}
			}
			delete.delete();
		}
	}

	/**
	 * @param args
	 *             should be [database destination root], typically
	 *             src/generated/resources/
	 */
	public static void main(String[] args) {

		int parrallelism = Runtime.getRuntime().availableProcessors() * 100;
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + parrallelism);

		long timeStart = System.currentTimeMillis();
		File folderOut = new File(args.length == 0 ? "src/generated/resources/" : args[0]);
		delDir(folderOut);
		folderOut.mkdirs();

		LoadedNPCs loaded = loadData();

		// check null values

		loaded.agents().forEach(Agent::toString);
		loaded.npcCorporations().forEach(Corporation::toString);

		// save

		Collections.sort(loaded.agents(), Comparator.comparing(o -> o.name));
		LinkedHashMap<String, Agent> agents = new LinkedHashMap<>();
		loaded.agents().forEach(a -> agents.put(a.name, a));
		Agent.export(agents, folderOut);

		Collections.sort(loaded.npcCorporations(), Comparator.comparing(o -> o.name));
		LinkedHashMap<String, Corporation> corporations = new LinkedHashMap<>();
		loaded.npcCorporations().forEach(c -> corporations.put(c.name, c));
		Corporation.export(corporations, folderOut);

		Collections.sort(loaded.lpOffers(), Comparator.comparing(o -> o.id));
		LinkedHashMap<Integer, LPOffer> lpoffers = new LinkedHashMap<>();
		loaded.lpOffers().forEach(o -> lpoffers.put(o.id, o));
		LPOffer.export(lpoffers, folderOut);

		logger.info("exported npcs in " + (System.currentTimeMillis() - timeStart) / 1000 + "s");
	}

	public static record LoadedNPCs(List<Agent> agents, List<Corporation> npcCorporations, List<LPOffer> lpOffers) {
	}

	private static LoadedNPCs loadData() {

		List<Agent> agents = new ArrayList<>();
		List<Corporation> corporations = new ArrayList<>();
		List<LPOffer> offers = new ArrayList<>();
		CacheStatic esicache = ESIRawPublic.INSTANCE.cache();
		// prefetch the LP offers
		NPCCorporation.CACHE.ids().forEach(id -> esicache.loyalty.stores_offers(id));

		// agents

		NPCCharacter.CACHE.all().forEach(ag -> {
			Agent add = new Agent();

			Station station = Station.CACHE.of(ag.source().locationID);
			if (station == null) {
				// ignore agents not in station
				return;
			} else {
				add.station = station.enName();
				add.stationId = station.id();
				add.system = station.solarSystem().enName();
			}

			add.corporation = ag.corporation().name();
			if(ag.data()==null) {
				// npc character that is not an agent
				return;
			}
			add.division = AGENT_DIVISION.of(ag.data().npcCorporationDivision().id());
			if (add.division == null) {
				logger.warn("null agent_division conversion for agent " + add.id + " division="
						+ ag.data().npcCorporationDivision());
			}
			add.id = ag.id();
			add.isLocator = ag.data().isLocator;
			add.level = ag.data().level;
			add.name = ag.name();
			add.type = AGENT_TYPE.of(ag.data().agentType().id());
			if (add.type == null) {
				logger.warn("null type conversion for agent " + add.id + " type=" + ag.data().agentType());
			}
			agents.add(add);
		});

		logger.info("translated agents data");

		// npc corporations

		final int CONCORD_ID = 1000125;
		HashMap<Integer, BigDecimal> concordRates = NPCCorporation.CACHE.of(CONCORD_ID).source().exchangeRates;
		NPCCorporation.CACHE.all().forEach(c -> {
			Corporation add = new Corporation();
			add.id = c.id();
			Faction faction = c.faction();
			if (faction != null) {
				add.faction =  faction.name();
				if (faction.source().militiaCorporationID == c.id()) {
					add.warfare = faction.name();
				}
			}
			add.concordRate = concordRates.getOrDefault(add.id, BigDecimal.ZERO).doubleValue();
			add.name = c.name();

			corporations.add(add);
		});

		logger.info("translated corporations");

		// lp offers


		Map<Integer, List<R_get_loyalty_stores_corporation_id_offers>> corpId2offers = NPCCorporation.CACHE.ids()
				.stream().collect(Collectors.toMap(
						id -> id,
						id -> esicache.loyalty.stores_offers(id).get()));

		logger.info("fetched offers");

		Map<Integer, LPOffer> convertedOffers = corpId2offers.values().stream().flatMap(List::stream)
				.map(NPCsTranslater::makeOffer)
				.filter(o -> o != null)
				.collect(Collectors.toMap(off -> off.id, off -> off, (o1, _) -> o1));
		offers.addAll(convertedOffers.values());
		corporations.stream().parallel().forEach(c -> loadCorpOffers(c, corpId2offers.get(c.id)));

		logger.info("added offers to corporations");

		return new LoadedNPCs(agents, corporations, offers);

	}

	protected static LPOffer makeOffer(R_get_loyalty_stores_corporation_id_offers o) {
		LPOffer lpo = new LPOffer();
		lpo.requirements.isk += o.isk_cost;
		lpo.requirements.lp += o.lp_cost;
		lpo.name = Type.CACHE.of(o.type_id).enName();
		lpo.id = o.offer_id;

		Stream.of(o.required_items).forEach(ir -> {
			ItemRef translated = new ItemRef();
			translated.quantity = ir.quantity;
			translated.id = ir.type_id;
			lpo.requirements.items.add(translated);
		});

		BluePrint bp = BluePrint.CACHE.of(o.type_id);

		if (bp != null) {// the LP offers a BPC
			lpo.bpid = bp.id();
			lpo.bpruns = o.quantity;
			for (Consumed m : bp.manufacturing().materials) {
				ItemRef translated = new ItemRef();
				translated.quantity = m.quantity * o.quantity;
				translated.id = m.type.id();
				lpo.requirements.items.add(translated);
			}
			Produced prod = bp.manufacturing().products.get(0);
			lpo.product.id = prod.type.id();
			lpo.product.quantity = prod.quantity * o.quantity;
			if (lpo.product.type() == null) {
				logger.debug("discard offer " + o.offer_id + " as product type " + prod.type.id() + " can't be loaded");
				return null;
			} else {
				lpo.name = (o.quantity == 1 ? "" : "" + o.quantity + "* ") + lpo.product.name() + "(BPC)";
			}
		} else {// the LP offers a non-bpc
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

	protected static void loadCorpOffers(Corporation c, List<R_get_loyalty_stores_corporation_id_offers> offers) {
		if (offers != null) {
			for (R_get_loyalty_stores_corporation_id_offers o : offers) {
				c.lpoffers.add(o.offer_id);
			}
		}
		Collections.sort(c.lpoffers);
	}

}
