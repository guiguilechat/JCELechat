package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterTransaction.CharacterTransactionList;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.IFetchedListElementRepository;

public interface CharacterTransactionRepository
    extends IFetchedListElementRepository<CharacterTransactionList, CharacterTransaction> {

	CharacterTransaction findTop1ByFetchResourceIdOrderByTransactionIdDesc(int Id);

}
