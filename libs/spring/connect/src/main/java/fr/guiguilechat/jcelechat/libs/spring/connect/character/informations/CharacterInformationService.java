package fr.guiguilechat.jcelechat.libs.spring.connect.character.informations;

import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.resolve.IdResolution;
import fr.guiguilechat.jcelechat.libs.spring.connect.resolve.IdResolutionService.IdResolutionListener;
import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiConnectionInterceptor.EsiUserListener;
import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiUser;
import fr.guiguilechat.jcelechat.libs.spring.templates.services.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.post_universe_names_category;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterInformationService
    extends ARemoteFetchedResourceService<
    	CharacterInformation,
    	Integer,
    	R_get_characters_character_id,
    	CharacterInformationRepository>
    implements IdResolutionListener, EsiUserListener {

	@Override
	protected CharacterInformation create(Integer characterId) {
		CharacterInformation ret = new CharacterInformation();
		ret.setRemoteId(characterId);
		return ret;
	}

	@Override
	protected Requested<R_get_characters_character_id> fetchData(Integer characterId,
	    Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.get_characters(characterId, properties);
	}

	@Override
	public void onNewIdResolution(IdResolution idResolution) {
		if (idResolution.getCategory() == post_universe_names_category.character) {
			createIfAbsent(idResolution.getRemoteId(), false);
		}
	}

	@Override
	public void onNewEsiUser(EsiUser user) {
		createIfAbsent(user.getCharacterId(), false);
	}

}
