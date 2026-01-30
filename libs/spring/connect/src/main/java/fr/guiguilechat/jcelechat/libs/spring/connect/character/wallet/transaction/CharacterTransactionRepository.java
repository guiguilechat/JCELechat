package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.transaction;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.list.IFetchedListElementRepositoryAutoId;

public interface CharacterTransactionRepository
		extends IFetchedListElementRepositoryAutoId<CharacterTransactionList, CharacterTransaction> {

	CharacterTransaction findTop1ByFetchResourceIdOrderByTransactionIdDesc(int Id);

}
