package fr.guiguilechat.jcelechat.libs.spring.connect.services;

import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.model.CharacterWallet;
import fr.guiguilechat.jcelechat.libs.spring.connect.repositories.CharacterWalletRepository;
import fr.guiguilechat.jcelechat.libs.spring.connect.services.templates.AConnectedCharDataService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterWalletService
    extends AConnectedCharDataService<CharacterWallet, Double, CharacterWalletRepository> {

	@Override
	protected Requested<Double> fetchCharacterData(ESIConnected esi, int charId,
			Map<String, String> properties) {
		return esi.get_characters_wallet(charId, properties);
	}

	@Override
	protected CharacterWallet create(int characterId) {
		return CharacterWallet.builder().characterId(characterId).build();
	}

	@Getter(lazy = true)
	private final Set<String> requiredScopes = Set.of("esi-wallet.read_character_wallet.v1");

}
