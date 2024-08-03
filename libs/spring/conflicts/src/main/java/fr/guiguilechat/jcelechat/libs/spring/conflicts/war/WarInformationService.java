package fr.guiguilechat.jcelechat.libs.spring.conflicts.war;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.alliance.AllianceInfo;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.alliance.AllianceInfoService;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfoService;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntityService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_wars_war_id;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.conflicts.war.info")
@Order(4) // depends on corporationinfo and allianceinfo
public class WarInformationService
    extends ARemoteEntityService<WarInformation, Integer, R_get_wars_war_id, WarInformationRepository> {

	@Lazy
	private final AllianceInfoService allianceInfoService;

	@Lazy
	private final CorporationInfoService corporationInfoService;

	@Override
	protected Requested<R_get_wars_war_id> fetchData(Integer id, Map<String, String> properties) {
		Requested<R_get_wars_war_id> ret = ESIRawPublic.INSTANCE.get_wars((int) id, properties);
		return ret;
	}

	@Override
	protected WarInformation create(Integer entityId) {
		WarInformation ret = new WarInformation();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected void updateResponseOk(Map<WarInformation, R_get_wars_war_id> responseOk) {
		super.updateResponseOk(responseOk);
		Map<Integer, AllianceInfo> idToAllianceInfo = allianceInfoService.createIfAbsent(
		    responseOk.values().stream()
		    .flatMap(w -> Stream.concat(
		    		Stream.of(w.aggressor.alliance_id, w.defender.alliance_id),
		            Stream.of(w.allies).map(ally -> ally.alliance_id)))
		    .filter(i -> i != null && i != 0)
		    .distinct().toList());
		Map<Integer, CorporationInfo> idToCorporationInfo = corporationInfoService.createIfAbsent(
		    responseOk.values().stream()
		        .flatMap(w -> Stream.concat(
		            Stream.of(w.aggressor.corporation_id, w.defender.corporation_id),
		            Stream.of(w.allies).map(ally -> ally.corporation_id)))
		        .filter(i -> i != null && i != 0)
		        .distinct().toList());
		for (Entry<WarInformation, R_get_wars_war_id> e : responseOk.entrySet()) {
			WarInformation wi = e.getKey();
			R_get_wars_war_id w = e.getValue();
			wi.setAggressorAlliance(idToAllianceInfo.get(w.aggressor.alliance_id));
			wi.setDefenderAlliance(idToAllianceInfo.get(w.defender.alliance_id));
			wi.setAggressorAlliance(idToAllianceInfo.get(w.aggressor.alliance_id));
			wi.setAggressorAlliance(idToAllianceInfo.get(w.aggressor.alliance_id));
		}
	}

	@Override
	protected Function<Map<String, String>, Requested<List<Integer>>> listFetcher() {
		return this::listNewWars;
	}

	protected Requested<List<Integer>> listNewWars(Map<String, String> properties) {
		Requested<List<Integer>> ret = ESIRawPublic.INSTANCE.get_wars(null, null).mapBody(List::of);
		if (!ret.isOk()) {
			return ret;
		}
		WarInformation highestStoredId = repo().findTop1ByOrderByIdDesc();
		if (highestStoredId != null) {
			// we already got max id : so we need to fetch down to that id.
			List<Integer> allFetchedIds = new ArrayList<>(ret.getOK());
			int lowestRetrievedId = allFetchedIds.get(allFetchedIds.size() - 1);
			while (lowestRetrievedId >= highestStoredId.getId()) {
				Requested<Integer[]> nextPage = ESIRawPublic.INSTANCE.get_wars((Integer) lowestRetrievedId, null);
				if (nextPage.isOk()) {
					allFetchedIds.addAll(List.of(nextPage.getOK()));
					lowestRetrievedId = allFetchedIds.get(allFetchedIds.size() - 1);
				} else {
					return nextPage.mapBody(List::of);
				}
			}

			// then we need to fetch one page more than the MIN id.
			WarInformation firstStoredId = repo().findTop1ByOrderByIdDesc();
			if (firstStoredId != null) {
				Requested<Integer[]> nextPage = ESIRawPublic.INSTANCE.get_wars(firstStoredId.getId(), null);
				if (nextPage.isOk()) {
					allFetchedIds.addAll(List.of(nextPage.getOK()));
				} else {
					log.warn("while fetching war ids before {}, got {}", firstStoredId, nextPage);
				}
			}

			ret = ret.mapBody(l -> allFetchedIds);

		}
		// changed expires for next list to be ASAP. In effect will be udpated by
		// getList().getDelay()
		return ret.mapExpires(i -> Instant.now());
	}

}
