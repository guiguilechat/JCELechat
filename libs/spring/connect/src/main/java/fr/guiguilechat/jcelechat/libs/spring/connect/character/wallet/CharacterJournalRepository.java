package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterJournal.CharacterJournalList;
import fr.guiguilechat.jcelechat.libs.spring.templates.repositories.IFetchedListElementRepository;

public interface CharacterJournalRepository extends IFetchedListElementRepository<CharacterJournalList, CharacterJournal> {

	public List<CharacterJournal> findByFetchResourceAndTransactionIdIn(CharacterJournalList fetchResource,
	    List<Long> transactionIds);

}
