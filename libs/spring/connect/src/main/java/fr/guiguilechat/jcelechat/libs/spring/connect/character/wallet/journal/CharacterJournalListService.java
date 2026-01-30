package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.journal;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterJournalListService
		extends RemoteNumberEntityService<CharacterJournalList, Integer, CharacterJournalListRepository> {

	@Override
	protected CharacterJournalList create(Integer Id) {
		CharacterJournalList ret = new CharacterJournalList();
		ret.setId(Id);
		return ret;
	}
}
