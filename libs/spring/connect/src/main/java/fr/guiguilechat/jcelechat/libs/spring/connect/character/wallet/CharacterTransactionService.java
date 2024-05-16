package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.informations.CharacterAffiliationService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.informations.CharacterInformationService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterTransaction.CharacterTransactionList;
import fr.guiguilechat.jcelechat.libs.spring.connect.corporation.CorporationInfoService;
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
	
	@Accessors(fluent = true)
	private final CharacterTransactionRepository recordRepo;

	@Lazy
	private final CharacterAffiliationService characterAffiliationService;

	@Lazy
	private final CharacterInformationService characterInformationService;

	@Lazy
	private final CorporationInfoService corporationInfoService;

	@Getter(lazy = true)
	private final Set<String> requiredScopes = Set.of("esi-wallet.read_character_wallet.v1");

	// We want to fetch the transactions that are newer than the last one, so higher
	// ID. each
	// "page" returns the next transaction, by id decreasing. So we stop digging
	// once we find an ID lower than the previous one

	@Override
	protected Requested<R_get_characters_character_id_wallet_transactions[]> fetchCharacterData(ESIConnected esiConnected,
	    int characterId, Map<String, String> properties) {
		// last stored higher id for a transaction of that character.
		CharacterTransaction lastStored = recordRepo
		    .findTop1ByFetchResourceCharacterIdOrderByTransactionIdDesc(characterId);
		// transaction with the lowest id fetched so far. We stop digging when it's set
		// to null.
		Long lastFetchedMinTransactionId = null;
		List<R_get_characters_character_id_wallet_transactions[]> fetchedArrays = new ArrayList<>();
		Requested<R_get_characters_character_id_wallet_transactions[]> lastResponse = null;
		do {
			lastResponse = esiConnected.get_characters_wallet_transactions(characterId, lastFetchedMinTransactionId,
			    properties);
			// if there is any problem when fetching the different pages, we return the
			// first problem.
			if (!lastResponse.isOk()) {
				return lastResponse;
			}
			R_get_characters_character_id_wallet_transactions[] pageTransactions = lastResponse.getOK();
			lastFetchedMinTransactionId = null;
			if (pageTransactions != null && pageTransactions.length > 0) {
				long newMinId = pageTransactions[pageTransactions.length - 1].transaction_id - 1;
				if ((lastFetchedMinTransactionId == null || newMinId != lastFetchedMinTransactionId)
				    && (lastStored == null || newMinId > lastStored.getTransactionId())) {
					lastFetchedMinTransactionId = newMinId;
				}
				fetchedArrays.add(pageTransactions);
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

	// this implementation only calls this with NEW data to store. So just update
	// the meta data and store the new records.
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
		List<CharacterTransaction> list = newResources
		    .map(CharacterTransaction::from)
		    .peek(rec -> rec.setFetchResource(data))
		    .peek(rec -> rec.setClientNpcCorp(corporationInfoService.isNpcCorp(rec.getClientId())))
		    .toList();
		recordRepo.saveAll(list);

		List<Integer> npcCorporationIds = list.stream()
		    .filter(CharacterTransaction::isClientNpcCorp)
		    .map(CharacterTransaction::getClientId)
		    .distinct()
		    .toList();
		corporationInfoService.createIfMissing(npcCorporationIds, false);

		List<Integer> characterIds = list.stream()
		    .filter(ct -> !ct.isClientNpcCorp())
		    .map(CharacterTransaction::getClientId)
		    .distinct()
		    .toList();
		characterInformationService.createIfMissing(characterIds, false);
		characterAffiliationService.createIfMissing(characterIds, false);
	}

	// service usage

	public List<CharacterTransaction> list(int characterId) {
		return recordRepo.findAllByFetchResourceCharacterId(characterId);
	}

}
