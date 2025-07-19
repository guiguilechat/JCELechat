package fr.guiguilechat.jcelechat.libs.spring.affiliations.character;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.alliance.AllianceInfo;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.alliance.AllianceInfoService;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfoService;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.faction.FactionInfoService;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntityService;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.id.IdResolution;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.id.IdResolutionListener;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.post_universe_names_category;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.affiliations.charinfo")
@Order(4) // depends on corporation
public class CharacterInformationService
    extends ARemoteEntityService<
    	CharacterInformation,
    	Integer,
    	R_get_characters_character_id,
        CharacterInformationRepository>
    implements IdResolutionListener {

	@Lazy
	private final AllianceInfoService allianceInfoService;

	@Lazy
	private final CorporationInfoService corporationInfoService;

	@Lazy
	private final FactionInfoService factionInfoService;

	@Override
	protected CharacterInformation create(Integer characterId) {
		CharacterInformation ret = new CharacterInformation();
		ret.setId(characterId);
		return ret;
	}

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

	@Override
	public void onNewIdResolution(IdResolution idResolution) {
		if (idResolution.getCategory() == post_universe_names_category.character) {
			createIfAbsent(idResolution.getId());
		}
	}

}
