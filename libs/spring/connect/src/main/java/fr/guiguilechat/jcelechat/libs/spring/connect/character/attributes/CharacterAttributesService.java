package fr.guiguilechat.jcelechat.libs.spring.connect.character.attributes;

import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.templates.AConnectedCharDataService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_attributes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterAttributesService
    extends
    AConnectedCharDataService<CharacterAttributes, R_get_characters_character_id_attributes, CharacterAttributesRepository> {

	@Override
	protected Requested<R_get_characters_character_id_attributes> fetchCharacterData(ESIConnected esi, int charId,
			Map<String, String> properties) {
		return esi.get_characters_attributes(charId, properties);
	}

	@Override
	protected CharacterAttributes create(Integer characterId) {
		return CharacterAttributes.builder().characterId(characterId).build();
	}

	@Getter(lazy = true)
	private final Set<String> requiredScopes = Set.of("esi-skills.read_skills.v1");

}
