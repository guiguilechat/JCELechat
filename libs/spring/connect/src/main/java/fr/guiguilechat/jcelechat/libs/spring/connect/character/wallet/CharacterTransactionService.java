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
import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfoService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterTransaction.CharacterTransactionList;
import fr.guiguilechat.jcelechat.libs.spring.connect.templates.AConnectedCharDataService;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resolve.IdResolutionService;
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
	private final CorporationInfoService corporationInfoService;

	@Lazy
	private final IdResolutionService idResolutionService;

	@Getter(lazy = true)
	private final Set<String> requiredScopes = Set.of("esi-wallet.read_character_wallet.v1");

	// We want to fetch the transactions that are newer than the last one, so higher
	// ID. each"page" returns the next transactions, by id decreasing. So we stop
	// digging once we find an ID lower than the previous highest known one

	@Override
	protected Requested<R_get_characters_character_id_wallet_transactions[]> fetchCharacterData(ESIConnected esiConnected,
	    int Id, Map<String, String> properties) {
		// last stored higher id for a transaction of that character.
		CharacterTransaction lastStored = recordRepo
		    .findTop1ByFetchResourceIdOrderByTransactionIdDesc(Id);
		// lowest id of a transaction we fetched so far. We stop digging when it's set
		// to null.
		Long lastFetchedMinTransactionId = null;
		List<R_get_characters_character_id_wallet_transactions[]> fetchedArrays = new ArrayList<>();
		Requested<R_get_characters_character_id_wallet_transactions[]> lastResponse = null;
		do {
			lastResponse = esiConnected.get_characters_wallet_transactions(Id, lastFetchedMinTransactionId,
			    properties);
			// if there is any problem when fetching the different pages, we return the
			// first problem.
			if (!lastResponse.isOk()) {
				return lastResponse;
			}
			R_get_characters_character_id_wallet_transactions[] pageTransactions = lastResponse.getOK();
			lastFetchedMinTransactionId = null;
			if (pageTransactions != null && pageTransactions.length > 0) {
				System.err.println("received " + pageTransactions.length + " wallet transactions with id range: min="
				    + pageTransactions[pageTransactions.length - 1].transaction_id + " max="
				    + pageTransactions[0].transaction_id);
				long newMinId = pageTransactions[pageTransactions.length - 1].transaction_id - 1;
				if (lastStored == null || newMinId > lastStored.getTransactionId()) {
					lastFetchedMinTransactionId = newMinId;
				} // otherwise we already have known transactions so break
				fetchedArrays.add(pageTransactions);
			}
		} while (lastFetchedMinTransactionId != null);
		List<R_get_characters_character_id_wallet_transactions> ret = fetchedArrays.stream().flatMap(Stream::of)
		    // filter out those already known, that is id <= highest n=known id
		    .filter(t -> lastStored == null || lastStored.getTransactionId() < t.transaction_id)
		    .toList();
		return lastResponse.mapBody(arr -> ret.toArray(R_get_characters_character_id_wallet_transactions[]::new));
	}

	@Override
	protected CharacterTransactionList create(Integer entityId) {
		CharacterTransactionList ret = new CharacterTransactionList();
		ret.setId(entityId);
		return ret;
	}

	// this implementation only calls this with NEW data to store. So just update
	// the meta data and store the new records.
	@Override
	protected void updateResponseOk(CharacterTransactionList data,
	    Requested<R_get_characters_character_id_wallet_transactions[]> response) {
		updateMetaOk(data, response);
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

		List<Integer> allIds = list.stream()
		    .map(CharacterTransaction::getClientId)
		    .distinct()
		    .toList();
		idResolutionService.createIfAbsent(allIds);
	}

	// service usage

	public List<CharacterTransaction> list(int Id) {
		return recordRepo.findAllByFetchResourceId(Id);
	}

}
