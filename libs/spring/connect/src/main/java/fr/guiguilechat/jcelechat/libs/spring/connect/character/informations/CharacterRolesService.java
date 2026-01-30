package fr.guiguilechat.jcelechat.libs.spring.connect.character.informations;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterRolesService
		extends RemoteNumberEntityService<CharacterRoles, Integer, CharacterRolesRepository> {

	@Override
	protected CharacterRoles create(Integer Id) {
		CharacterRoles ret = new CharacterRoles();
		ret.setId(Id);
		return ret;
	}

}
