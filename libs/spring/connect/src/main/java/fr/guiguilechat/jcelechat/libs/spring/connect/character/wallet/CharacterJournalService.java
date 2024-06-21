package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterJournal.CharacterJournalList;
import fr.guiguilechat.jcelechat.libs.spring.connect.templates.AAppendCharDataRecordListService;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.resolve.id.IdResolutionService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_journal_13;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterJournalService extends AAppendCharDataRecordListService<
		CharacterJournalList, 
		M_get_journal_13,
		CharacterJournalListRepository,
		CharacterJournal,
		CharacterJournalRepository> {

	@Lazy
	private final IdResolutionService idResolutionService;

	@Override
	protected CharacterJournal transformRecord(M_get_journal_13 f) {
		CharacterJournal ret = CharacterJournal.from(f);
		idResolutionService.createIfAbsent(ret.getFirstPartyId());
		idResolutionService.createIfAbsent(ret.getSecondPartyId());
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

	@Override
	protected CharacterJournalList create(Integer Id) {
		CharacterJournalList ret = new CharacterJournalList();
		ret.setId(Id);
		return ret;
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


	// service usage

}
