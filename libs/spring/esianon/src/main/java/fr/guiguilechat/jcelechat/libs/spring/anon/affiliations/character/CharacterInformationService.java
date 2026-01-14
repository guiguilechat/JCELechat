package fr.guiguilechat.jcelechat.libs.spring.anon.affiliations.character;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterInformationService
		extends RemoteEntityService<CharacterInformation, Integer, CharacterInformationRepository> {
	@Override
	protected CharacterInformation create(Integer characterId) {
		CharacterInformation ret = new CharacterInformation();
		ret.setId(characterId);
		return ret;
	}

}
