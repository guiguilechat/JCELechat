package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterTransaction.CharacterTransactionList;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.IFetchedListElementRepositoryAutoId;

public interface CharacterTransactionRepository
		extends IFetchedListElementRepositoryAutoId<CharacterTransactionList, CharacterTransaction> {

	CharacterTransaction findTop1ByFetchResourceIdOrderByTransactionIdDesc(int Id);

}
