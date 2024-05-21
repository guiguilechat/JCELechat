package fr.guiguilechat.jcelechat.libs.spring.connect.character.informations;

import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.templates.AConnectedCharDataService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_roles;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterRolesService
		extends AConnectedCharDataService<CharacterRoles, R_get_characters_character_id_roles, CharacterRolesRepository> {

	@Override
	protected Requested<R_get_characters_character_id_roles> fetchCharacterData(ESIConnected esi, int charId,
			Map<String, String> properties) {
		return esi.get_characters_roles(charId, properties);
	}

	@Override
	protected CharacterRoles create(Integer RemoteId) {
		CharacterRoles ret = new CharacterRoles();
		ret.setRemoteId(RemoteId);
		return ret;
	}

	@Getter(lazy = true)
	private final Set<String> requiredScopes = Set.of("esi-characters.read_corporation_roles.v1");

}
