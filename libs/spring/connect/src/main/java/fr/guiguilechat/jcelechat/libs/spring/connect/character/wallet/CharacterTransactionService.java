package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterTransaction.CharacterTransactionList;
import fr.guiguilechat.jcelechat.libs.spring.connect.templates.AConnectedCharDataService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_wallet_transactions;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterTransactionService extends AConnectedCharDataService<
    CharacterTransactionList,
		R_get_characters_character_id_wallet_transactions[],
		CharacterTransactionListRepository> {
	


	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	private CharacterTransactionRepository recordRepo;

	@Getter(lazy = true)
	private final Set<String> requiredScopes = Set.of("esi-wallet.read_character_wallet.v1");

	@Override
	protected Requested<R_get_characters_character_id_wallet_transactions[]> fetchCharacterData(ESIConnected esiConnected,
	    int characterId, Map<String, String> properties) {
		CharacterTransaction lastStored = recordRepo
		    .findTop1ByFetchResourceCharacterIdOrderByTransactionIdDesc(characterId);
		Long lastFetchedMinTransactionId = null;
		List<R_get_characters_character_id_wallet_transactions[]> fetchedArrays = new ArrayList<>();
		Requested<R_get_characters_character_id_wallet_transactions[]> lastResponse = null;
		do {
			lastResponse = esiConnected.get_characters_wallet_transactions(characterId, lastFetchedMinTransactionId,
			    properties);
			if (!lastResponse.isOk()) {
				return lastResponse;
			}
			R_get_characters_character_id_wallet_transactions[] added = lastResponse.getOK();
			lastFetchedMinTransactionId = null;
			if (added != null && added.length > 0) {
				long newfirstId = added[added.length - 1].transaction_id - 1;
				if ((lastFetchedMinTransactionId == null || newfirstId != lastFetchedMinTransactionId)
				    && (lastStored == null || newfirstId > lastStored.getTransactionId())) {
					lastFetchedMinTransactionId = newfirstId;
				}
				fetchedArrays.add(added);
			}
		} while (lastFetchedMinTransactionId != null);
		List<R_get_characters_character_id_wallet_transactions> ret = fetchedArrays.stream().flatMap(Stream::of)
		    .filter(t -> lastStored == null || lastStored.getTransactionId() < t.transaction_id)
		    .toList();
		return lastResponse.mapBody(arr -> ret.toArray(R_get_characters_character_id_wallet_transactions[]::new));
	}

	@Override
	protected CharacterTransactionList create(Integer entityId) {
		CharacterTransactionList ret = new CharacterTransactionList();
		ret.setCharacterId(entityId);
		return ret;
	}

	@Override
	protected void updateFromResponseOk(CharacterTransactionList data,
	    Requested<R_get_characters_character_id_wallet_transactions[]> response) {
		data.updateMeta(response);
		R_get_characters_character_id_wallet_transactions[] arr = response.getOK();
		if (arr != null && arr.length != 0) {
			saveNewResources(data, Stream.of(arr));
		}
	}

	protected void saveNewResources(CharacterTransactionList data,
	    Stream<R_get_characters_character_id_wallet_transactions> newResources) {
		recordRepo.saveAll(
		    newResources
		        .map(CharacterTransaction::from)
		        .peek(rec -> rec.setFetchResource(data))
		        .toList());
	}

	// service usage

	public List<CharacterTransaction> list(int characterId) {
		return recordRepo.findAllByFetchResourceCharacterId(characterId);
	}

}
