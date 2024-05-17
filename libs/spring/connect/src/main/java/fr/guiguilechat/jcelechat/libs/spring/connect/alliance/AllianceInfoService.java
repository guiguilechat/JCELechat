package fr.guiguilechat.jcelechat.libs.spring.connect.alliance;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.resolve.IdResolution;
import fr.guiguilechat.jcelechat.libs.spring.connect.resolve.IdResolutionService.IdResolutionListener;
import fr.guiguilechat.jcelechat.libs.spring.templates.services.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_alliances_alliance_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.post_universe_names_category;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AllianceInfoService extends
    ARemoteFetchedResourceService<AllianceInfo, Integer, R_get_alliances_alliance_id, AllianceInfoRepository>
    implements IdResolutionListener {

	@Override
	protected AllianceInfo create(Integer entityId) {
		AllianceInfo ret = new AllianceInfo();
		ret.setAllianceId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_alliances_alliance_id> fetchData(Integer id, Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.get_alliances(id, properties);
	}

	public Map<Integer, AllianceInfo> allById() {
		return repo().findAll().stream().collect(Collectors.toMap(AllianceInfo::getAllianceId, c -> c));
	}

	@Override
	public void onNewIdResolution(IdResolution idResolution) {
		if (idResolution.getCategory() == post_universe_names_category.alliance) {
			createIfAbsent(idResolution.getRemoteId(), false);
		}
	}

}
