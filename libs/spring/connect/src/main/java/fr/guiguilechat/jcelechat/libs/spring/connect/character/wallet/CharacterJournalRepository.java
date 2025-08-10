package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterJournal.CharacterJournalList;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.IFetchedListElementRepositoryAutoId;

public interface CharacterJournalRepository
		extends IFetchedListElementRepositoryAutoId<CharacterJournalList, CharacterJournal> {

	List<CharacterJournal> findByFetchResourceAndTransactionIdIn(CharacterJournalList fetchResource,
	    List<Long> transactionIds);

}
