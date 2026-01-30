package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterWalletService
		extends RemoteNumberEntityService<CharacterWallet, Integer, CharacterWalletRepository> {

	@Override
	protected CharacterWallet create(Integer characterId) {
		CharacterWallet ret = new CharacterWallet();
		ret.setId(characterId);
		return ret;
	}

}
