package fr.guiguilechat.jcelechat.libs.spring.connect.character.informations;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.RemoteEntityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterRolesService
		extends RemoteEntityService<CharacterRoles, Integer, CharacterRolesRepository> {

	@Override
	protected CharacterRoles create(Integer Id) {
		CharacterRoles ret = new CharacterRoles();
		ret.setId(Id);
		return ret;
	}

}
