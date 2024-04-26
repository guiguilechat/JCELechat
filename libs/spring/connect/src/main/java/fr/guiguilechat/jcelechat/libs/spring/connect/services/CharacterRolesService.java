package fr.guiguilechat.jcelechat.libs.spring.connect.services;

import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.model.CharacterRoles;
import fr.guiguilechat.jcelechat.libs.spring.connect.repositories.CharacterRolesRepository;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_roles;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterRolesService
		extends ACharDataService<CharacterRoles, R_get_characters_character_id_roles, CharacterRolesRepository> {

	@Override
	protected Requested<R_get_characters_character_id_roles> fetchCharacterData(ESIConnected esi, int charId,
			Map<String, String> properties) {
		return esi.get_characters_roles(charId, properties);
	}

	@Override
	protected CharacterRoles create(int characterId) {
		return CharacterRoles.builder().characterId(characterId).build();
	}

}
