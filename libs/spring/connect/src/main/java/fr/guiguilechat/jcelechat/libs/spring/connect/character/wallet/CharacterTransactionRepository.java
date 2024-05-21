package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterTransaction.CharacterTransactionList;
import fr.guiguilechat.jcelechat.libs.spring.templates.repositories.IFetchedListElementRepository;

public interface CharacterTransactionRepository
    extends IFetchedListElementRepository<CharacterTransactionList, CharacterTransaction> {

	public CharacterTransaction findTop1ByFetchResourceRemoteIdOrderByTransactionIdDesc(int RemoteId);

}
