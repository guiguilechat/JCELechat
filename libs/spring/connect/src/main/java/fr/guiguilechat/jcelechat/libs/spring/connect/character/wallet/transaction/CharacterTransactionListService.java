package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.transaction;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterTransactionListService
		extends RemoteEntityService<CharacterTransactionList, Integer, CharacterTransactionListRepository> {

	@Override
	protected CharacterTransactionList create(Integer entityId) {
		CharacterTransactionList ret = new CharacterTransactionList();
		ret.setId(entityId);
		return ret;
	}

}
