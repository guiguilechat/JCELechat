package fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterContactListService
		extends RemoteNumberEntityService<CharacterContactList, Integer, CharacterContactListRepository> {

	@Override
	protected CharacterContactList create(Integer Id) {
		CharacterContactList ret = new CharacterContactList();
		ret.setId(Id);
		return ret;
	}

}
