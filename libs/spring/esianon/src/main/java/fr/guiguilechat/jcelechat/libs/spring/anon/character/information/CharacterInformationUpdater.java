package fr.guiguilechat.jcelechat.libs.spring.anon.character.information;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.anon.alliance.information.AllianceInfo;
import fr.guiguilechat.jcelechat.libs.spring.anon.alliance.information.AllianceInfoService;
import fr.guiguilechat.jcelechat.libs.spring.anon.corporation.information.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.anon.corporation.information.CorporationInfoService;
import fr.guiguilechat.jcelechat.libs.spring.anon.faction.information.FactionInfoService;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntityUpdater;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.character.info")
public class CharacterInformationUpdater extends
		RemoteEntityUpdater<CharacterInformation, Integer, R_get_characters_character_id, CharacterInformationRepository, CharacterInformationService> {

	@Lazy
	private final AllianceInfoService allianceInfoService;

	@Lazy
	private final CorporationInfoService corporationInfoService;

	@Lazy
	private final FactionInfoService factionInfoService;

	@Override
	protected Requested<R_get_characters_character_id> fetchData(Integer characterId,
			Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.get_characters(characterId, properties);
	}

	protected void updateResponseOk(CharacterInformation data,
			R_get_characters_character_id response,
			Map<Integer, AllianceInfo> idToAlliance,
			Map<Integer, CorporationInfo> idToCorporation) {
		data.setAlliance(idToAlliance.get(response.alliance_id));
		data.setCorporation(idToCorporation.get(response.corporation_id));
		data.setFaction(factionInfoService.createIfAbsent(response.faction_id));
	}

	@Override
	protected void updateResponseOk(Map<CharacterInformation, R_get_characters_character_id> responseOk) {
		super.updateResponseOk(responseOk);
		Map<Integer, AllianceInfo> idToAlliance = allianceInfoService
				.createIfAbsent(responseOk.values().stream()
						.mapToInt(r -> r.alliance_id)
						.distinct().filter(i -> i > 0)
						.boxed().toList());
		Map<Integer, CorporationInfo> idToCorporation = corporationInfoService
				.createIfAbsent(responseOk.values().stream()
						.mapToInt(r -> r.corporation_id)
						.distinct().filter(i -> i > 0)
						.boxed().toList());
		responseOk.entrySet().stream()
				.forEach(e -> updateResponseOk(e.getKey(), e.getValue(), idToAlliance, idToCorporation));
	}

}
