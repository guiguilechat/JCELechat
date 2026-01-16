package fr.guiguilechat.jcelechat.libs.spring.anon.faction.information;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.anon.corporation.information.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.anon.corporation.information.CorporationInfoService;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.batch.AResourceBatchUpdater;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_factions;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.faction.info")
public class FactionInfoUpdater
		extends	AResourceBatchUpdater<
			FactionInfo,
			Integer,
			R_get_universe_factions,
			FactionInfoRepository,
			FactionInfoService> {

	@Lazy
	private final CorporationInfoService corporationInfoService;

	@Override
	protected Integer extractId(R_get_universe_factions fetched) {
		return fetched.faction_id;
	}

	@Override
	protected Requested<List<R_get_universe_factions>> fetchList(Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.get_universe_factions(properties).mapBody(List::of);
	}

	@Override
	protected void updateEntities(Map<FactionInfo, R_get_universe_factions> mapped) {
		Map<Integer, CorporationInfo> idToCorporationInfo = corporationInfoService.createIfAbsent(
				mapped.values().stream().map(d -> d.corporation_id)
						.distinct().filter(i -> i > 0)
						.toList());
		mapped.entrySet().forEach(e -> update(e.getKey(), e.getValue(), idToCorporationInfo));
	}

	private void update(FactionInfo entity, R_get_universe_factions fetched,
			Map<Integer, CorporationInfo> idToCorporationInfo) {
		entity.update(fetched);
		entity.setCorporation(idToCorporationInfo.get(fetched.corporation_id));
	}

}
