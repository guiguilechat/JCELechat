package fr.guiguilechat.jcelechat.libs.spring.affiliations.alliance;

import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfoService;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.faction.FactionInfoService;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntityService;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.id.IdResolution;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.id.IdResolutionListener;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_alliances_alliance_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.post_universe_names_category;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.affiliations.alliance")
@Order(2) // depends on factions
public class AllianceInfoService extends
    ARemoteEntityService<AllianceInfo, Integer, R_get_alliances_alliance_id, AllianceInfoRepository>
    implements IdResolutionListener {

	@Lazy
	private final CorporationInfoService corporationInfoService;

	@Lazy
	private final FactionInfoService factionInfoService;

	@Override
	protected AllianceInfo create(Integer entityId) {
		AllianceInfo ret = new AllianceInfo();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_alliances_alliance_id> fetchData(Integer id, Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.get_alliances(id, properties);
	}

	protected void updateResponseOk(AllianceInfo data,
	    R_get_alliances_alliance_id response,
	    Map<Integer, CorporationInfo> idToCorporation) {
		data.setCreatorCorporation(idToCorporation.get(response.creator_corporation_id));
		data.setExecutorCorporation(idToCorporation.get(response.executor_corporation_id));
		data.setFaction(factionInfoService.createIfAbsent(response.faction_id));
	}

	@Override
	protected void updateResponseOk(Map<AllianceInfo, R_get_alliances_alliance_id> responseOk) {
		super.updateResponseOk(responseOk);
		Map<Integer, CorporationInfo> idToCorporation = corporationInfoService
		    .createIfAbsent(responseOk.values().stream()
		        .flatMapToInt(r -> IntStream.of(r.creator_corporation_id, r.executor_corporation_id))
		        .distinct().filter(i -> i > 0)
		        .boxed().toList());
		responseOk.entrySet().stream().forEach(e -> updateResponseOk(e.getKey(), e.getValue(), idToCorporation));
	}

	@Override
	public void onNewIdResolution(IdResolution idResolution) {
		if (idResolution.getCategory() == post_universe_names_category.alliance) {
			createIfAbsent(idResolution.getId());
		}
	}

}
