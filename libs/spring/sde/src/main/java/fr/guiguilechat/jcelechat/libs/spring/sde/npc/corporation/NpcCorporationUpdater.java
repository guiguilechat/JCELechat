package fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCorporations;
import fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.lpexchange.LPExchange;
import fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.lpexchange.LPExchangeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.StationService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NpcCorporationUpdater extends SdeEntityUpdater<NpcCorporation, NpcCorporationService, EnpcCorporations> {

	public NpcCorporationUpdater() {
		super(EnpcCorporations.LOADER);
	}

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private LPExchangeService lpExchangeService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private SolarSystemService solarSystemService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private StationService stationService;

	@Override
	protected void processSource(LinkedHashMap<Integer, EnpcCorporations> sources) {
		var getSystem = solarSystemService()
				.getter(sources.values().stream()
						.map(p -> p.solarSystemID)
						.filter(i -> i != 0));
		var getStation = stationService()
				.getter(sources.values().stream()
						.map(p -> p.stationID)
						.filter(i -> i != 0));
		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue(), getSystem, getStation);
		}
		service().saveAll(storedEntities.values());
		updateExchanges(storedEntities, sources);
	}

	protected void updateExchanges(HashMap<Integer, NpcCorporation> storedEntities,
			LinkedHashMap<Integer, EnpcCorporations> sources) {
		lpExchangeService.clear();
		List<LPExchange> created = new ArrayList<>();
		for (Entry<Integer, EnpcCorporations> e : sources.entrySet()) {
			if (e.getValue().exchangeRates != null && !e.getValue().exchangeRates.isEmpty()) {
				NpcCorporation targetCorporation = storedEntities.get(e.getKey());
				if (targetCorporation == null) {
					log.error("can't find target corporation for id " + e.getKey());
				} else {
					for (Entry<Integer, BigDecimal> e2 : e.getValue().exchangeRates.entrySet()) {
						NpcCorporation sourceCorporation = storedEntities.get(e2.getKey());
						if (sourceCorporation == null) {
							log.error("can't find source corporation for id " + e2.getKey());
						} else {
							created.add(new LPExchange(null, sourceCorporation, e2.getValue(), targetCorporation));
						}
					}
				}
			}
		}
		log.debug("saving {} lp exchange rates", created.size());
		lpExchangeService.saveAll(created);
	}

}
