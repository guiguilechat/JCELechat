package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterTransaction.CharacterTransactionList;
import fr.guiguilechat.jcelechat.libs.spring.templates.repositories.ICharDataRecordRepository;

public interface CharacterTransactionRepository
    extends ICharDataRecordRepository<CharacterTransactionList, CharacterTransaction> {

	public CharacterTransaction findTop1ByFetchResourceCharacterIdOrderByTransactionIdDesc(int characterId);

}
