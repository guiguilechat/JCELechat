package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.journal;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.list.FetchedListElementService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterJournalService extends FetchedListElementService<CharacterJournal, CharacterJournalRepository> {

}
