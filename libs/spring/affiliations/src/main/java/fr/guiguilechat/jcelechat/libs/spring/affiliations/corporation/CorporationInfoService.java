package fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.alliance.AllianceInfo;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.alliance.AllianceInfoService;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.faction.FactionInfoService;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntityService;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.id.IdResolution;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.id.IdResolutionListener;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.post_universe_names_category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.affiliations.corporation")
@Order(3) // depends on alliance and faction
public class CorporationInfoService extends
ARemoteEntityService<CorporationInfo, Integer, R_get_corporations_corporation_id, CorporationInfoRepository>
implements IdResolutionListener {

	@Lazy
	private final AllianceInfoService allianceInfoService;

	@Lazy
	private final FactionInfoService factionInfoService;

	@Override
	protected CorporationInfo create(Integer entityId) {
		CorporationInfo ret = new CorporationInfo();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_corporations_corporation_id> fetchData(Integer id, Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.get_corporations(id, properties);
	}

	protected void updateResponseOk(CorporationInfo data,
			R_get_corporations_corporation_id response,
			Map<Integer, AllianceInfo> idToAlliance) {
		data.setAlliance(idToAlliance.get(response.alliance_id));
		data.setFaction(factionInfoService.createIfAbsent(response.faction_id));
	}

	@Override
	protected void updateResponseOk(Map<CorporationInfo, R_get_corporations_corporation_id> responseOk) {
		super.updateResponseOk(responseOk);
		Map<Integer, AllianceInfo> idToAlliance = allianceInfoService
				.createIfAbsent(responseOk.values().stream()
						.mapToInt(r -> r.alliance_id)
						.distinct().filter(i -> i > 0)
						.boxed().toList());
		responseOk.entrySet().stream().forEach(e -> updateResponseOk(e.getKey(), e.getValue(), idToAlliance));
	}

	// fetch only npc corporations

	@Override
	protected Function<Map<String, String>, Requested<List<Integer>>> listFetcher() {
		return p -> ESIRawPublic.INSTANCE.get_corporations_npccorps(p).mapBody(List::of);
	}

	@Override
	protected void onNewListFetched(Set<Integer> newIds) {
		log.debug("received {} new created elements", newIds.size());
		List<CorporationInfo> newcorps = repo().findAllById(newIds);
		newcorps.forEach(ci -> ci.setNpc(true));
		saveAll(newcorps);
	}

	// create new entries when needed

	@Override
	public void onNewIdResolution(IdResolution idResolution) {
		if (idResolution.getCategory() == post_universe_names_category.corporation) {
			createIfAbsent(idResolution.getId());
		}
	}

}
