package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.journal;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.generic.CharRecordAppendListUpdater;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.id.IdResolutionService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_journal_13;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterJournalUpdater
	extends	CharRecordAppendListUpdater<
		CharacterJournalList,
		M_get_journal_13,
		CharacterJournalListRepository,
		CharacterJournalListService,
		CharacterJournal,
		CharacterJournalRepository> {

	@Lazy
	private final IdResolutionService idResolutionService;

	@Override
	protected CharacterJournal transformRecord(M_get_journal_13 f) {
		CharacterJournal ret = CharacterJournal.from(f);
		idResolutionService.createMissing(ret.getFirstPartyId());
		idResolutionService.createMissing(ret.getSecondPartyId());
		return ret;
	}

	@Override
	protected Requested<M_get_journal_13[]> fetchCharacterData(ESIConnected esiConnected,
			int Id, Map<String, String> properties) {
		return esiConnected
				.requestGetPages((page, props) -> esiConnected.get_characters_wallet_journal(Id, page, props),
						properties)
				.mapBody(l -> l.toArray(M_get_journal_13[]::new));
	}

	@Getter(lazy = true)
	private final Set<String> requiredScopes = Set.of("esi-wallet.read_character_wallet.v1");

	@Override
	protected Stream<M_get_journal_13> findMising(CharacterJournalList data, M_get_journal_13[] arr) {
		Map<Long, CharacterJournal> storedRecords = recordRepo()
				.findByFetchResourceAndTransactionIdIn(data, Stream.of(arr).map(j -> j.id).toList()).stream()
				.collect(Collectors.toMap(CharacterJournal::getTransactionId, cj -> cj));
		return Stream.of(arr).filter(j -> !storedRecords.containsKey(j.id));
	}

}
