package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterTransaction.CharacterTransactionList;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.list.IFetchedListElementRepository;

public interface CharacterTransactionRepository
    extends IFetchedListElementRepository<CharacterTransactionList, CharacterTransaction> {

	public CharacterTransaction findTop1ByFetchResourceIdOrderByTransactionIdDesc(int Id);

}
