package fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.affiliation.CharacterAffiliation;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.affiliation.CharacterAffiliationService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class C2CStandingsService {

	@Lazy
	private final CharacterAffiliationService characterAffiliationService;

	@Lazy
	private final CharacterContactService characterContactService;

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
		if (characterFromId == characterToId) {
			return 100.0f;
		}
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
		List<CharacterContact> contacts = characterContactService.fromTo(List.of(characterFromId), toIds);
		return computeStanding(toIds, contacts);
	}

	/**
	 * @param toIds ids of (character, corporation, faction) if exist
	 * @return for each character having a contact for at least one of the given
	 *           ids, the effective standing of that character to given "to"
	 *           character ids.
	 */
	public Map<Integer, Float> effectiveStandings(List<Integer> toIds) {
		Map<Integer, List<CharacterContact>> existingContacts = characterContactService.forContactIds(toIds).stream()
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
