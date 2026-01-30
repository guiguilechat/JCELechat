package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.transaction;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterTransactionListService
		extends RemoteNumberEntityService<CharacterTransactionList, Integer, CharacterTransactionListRepository> {

	@Override
	protected CharacterTransactionList create(Integer entityId) {
		CharacterTransactionList ret = new CharacterTransactionList();
		ret.setId(entityId);
		return ret;
	}

}
