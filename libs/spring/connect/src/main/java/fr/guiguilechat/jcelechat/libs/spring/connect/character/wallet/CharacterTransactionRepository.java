package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterTransaction.CharacterTransactionList;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.IFetchedListElementRepository;

public interface CharacterTransactionRepository
    extends IFetchedListElementRepository<CharacterTransactionList, CharacterTransaction> {

	@Override
	@Modifying
	@Query("delete from EsiConnectCharacterTransaction where fetchResource.id in :ids")
	void deleteByFetchResourceIdIn(Iterable<? extends Number> ids);

	public CharacterTransaction findTop1ByFetchResourceIdOrderByTransactionIdDesc(int Id);

}
