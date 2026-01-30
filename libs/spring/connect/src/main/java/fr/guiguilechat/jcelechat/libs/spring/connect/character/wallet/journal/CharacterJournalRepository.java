package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.journal;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.list.IFetchedListElementRepositoryAutoId;

public interface CharacterJournalRepository
		extends IFetchedListElementRepositoryAutoId<CharacterJournalList, CharacterJournal> {

	List<CharacterJournal> findByFetchResourceAndTransactionIdIn(CharacterJournalList fetchResource,
	    List<Long> transactionIds);

}
