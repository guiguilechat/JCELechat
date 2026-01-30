package fr.guiguilechat.jcelechat.libs.spring.connect.character.standings;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterStandingListService
		extends RemoteNumberEntityService<CharacterStandingList, Integer, CharacterStandingListRepository> {

	@Override
	protected CharacterStandingList create(Integer Id) {
		CharacterStandingList ret = new CharacterStandingList();
		ret.setId(Id);
		return ret;
	}

}
