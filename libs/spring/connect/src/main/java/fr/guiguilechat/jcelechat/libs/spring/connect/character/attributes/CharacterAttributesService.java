package fr.guiguilechat.jcelechat.libs.spring.connect.character.attributes;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterAttributesService
		extends RemoteNumberEntityService<CharacterAttributes, Integer, CharacterAttributesRepository> {

	@Override
	protected CharacterAttributes create(Integer characterId) {
		CharacterAttributes ret = new CharacterAttributes();
		ret.setId(characterId);
		return ret;
	}
}
