package fr.guiguilechat.jcelechat.libs.spring.connect.character.informations;

import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.templates.ACharDataService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterInformationService
    extends
    ACharDataService<CharacterInformation, R_get_characters_character_id, CharacterInformationRepository> {

	@Override
	protected CharacterInformation create(Integer characterId) {
		return CharacterInformation.builder()
		    .characterId(characterId)
		    .build();
	}

	@Override
	protected Requested<R_get_characters_character_id> fetchData(Integer characterId,
	    Map<String, String> properties) {
		return ESIStatic.INSTANCE.get_characters(characterId, properties);
	}


}
