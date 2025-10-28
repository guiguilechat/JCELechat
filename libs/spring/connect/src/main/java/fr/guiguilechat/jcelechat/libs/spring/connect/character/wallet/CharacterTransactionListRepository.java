package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterTransaction.CharacterTransactionList;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntityRepository;

public interface CharacterTransactionListRepository
    extends RemoteEntityRepository<CharacterTransactionList, Integer> {

}
