package fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.affiliation.CharacterAffiliation;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.affiliation.CharacterAffiliationService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts.CharacterContact.CharacterContactList;
import fr.guiguilechat.jcelechat.libs.spring.connect.templates.ACharDataRecordListService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_contacts;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterContactService extends ACharDataRecordListService<
	CharacterContactList, 
	R_get_characters_character_id_contacts,
	CharacterContactListRepository,
	CharacterContact,
	CharacterContactRepository>{

	@Lazy
	private final CharacterAffiliationService characterAffiliationService;

	@Override
	protected CharacterContact transformRecord(R_get_characters_character_id_contacts f) {
		return CharacterContact.from(f);
	}

	@Override
	protected Requested<R_get_characters_character_id_contacts[]> fetchCharacterData(ESIConnected esiConnected,
	    int characterId, Map<String, String> properties) {
		return esiConnected.requestGetPages((page, props) -> esiConnected.get_characters_contacts(characterId, page, props),
		    properties).mapBody(l -> l.toArray(R_get_characters_character_id_contacts[]::new));
	}

	@Override
	protected CharacterContactList create(Integer characterId) {
		return CharacterContactList.builder().characterId(characterId).build();
	}

	@Getter(lazy = true)
	private final Set<String> requiredScopes = Set.of("esi-characters.read_contacts.v1");

	// service usage

	public List<CharacterContact> fromTo(List<Integer> fromIds, List<Integer> toIds) {
		return recordRepo().findAllByFetchResourceCharacterIdInAndContactIdIn(fromIds, toIds);
	}

	/**
	 * @param targetCharacterIds list of characterId, corporationId, allianceId if
	 *                             present.
	 * @param contacts           existing contacts from a single "from" character
	 *                             related to target character, its corporation, or
	 *                             alliance.
	 * @return first existing standing for the target ids, therefore effective
	 *           standing.
	 */
	protected float computeStanding(List<Integer> targetCharacterIds, Collection<CharacterContact> contacts) {
		return (float) targetCharacterIds.stream()
		    .map(id -> contacts.stream().filter(cs -> cs.getContactId() == id).findAny().orElse(null))
		    .filter(cc -> cc != null)
		    .mapToDouble(CharacterContact::getStanding)
		    .findFirst()
		    .orElse(-100);
	}

	/**
	 * @param characterFromId
	 * @param characterToId
	 * @return effective standing affected from a character to another. Will be -100
	 *           if no standing affected.
	 */
	public float effectiveStanding(int characterFromId, int characterToId) {
		CharacterAffiliation ca = characterAffiliationService.fetched(characterToId);
		List<Integer> toIds = new ArrayList<>();
		toIds.add(characterToId);
		if (ca != null) {
			if (ca.getCorporation_id() != 0) {
				toIds.add(ca.getCorporation_id());
			}
			if (ca.getAlliance_id() != 0) {
				toIds.add(ca.getAlliance_id());
			}
		}
		List<CharacterContact> contacts = fromTo(List.of(characterFromId), toIds);
		return computeStanding(toIds, contacts);
	}

	public List<CharacterContact> forContactIds(List<Integer> toIds) {
		return recordRepo().findAllByContactIdIn(toIds);
	}

	/**
	 * @param toIds ids of (character, corporation, faction) if exist
	 * @return for each character having a contact for at least one of the given
	 *           ids, the effective standing of that character to given "to"
	 *           character ids.
	 */
	public Map<Integer, Float> effectiveStandings(List<Integer> toIds) {
		Map<Integer, List<CharacterContact>> existingContacts = forContactIds(toIds).stream()
		    .collect(Collectors.groupingBy(cc -> cc.getFetchResource().getCharacterId()));
		return existingContacts.entrySet().stream()
		    .collect(Collectors.toMap(Entry::getKey, e -> computeStanding(toIds, e.getValue())));
	}

	public Map<Integer, Float> effectiveStandings(int characterToId) {
		CharacterAffiliation ca = characterAffiliationService.fetched(characterToId);
		List<Integer> toIds = new ArrayList<>();
		toIds.add(characterToId);
		if (ca != null) {
			if (ca.getCorporation_id() != 0) {
				toIds.add(ca.getCorporation_id());
			}
			if (ca.getAlliance_id() != 0) {
				toIds.add(ca.getAlliance_id());
			}
		}
		return effectiveStandings(toIds);
	}

}
