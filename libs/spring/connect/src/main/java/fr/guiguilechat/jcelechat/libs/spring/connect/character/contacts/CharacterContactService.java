package fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.alliance.AllianceInfoService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts.CharacterContact.CharacterContactList;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.informations.CharacterAffiliationService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.informations.CharacterInformationService;
import fr.guiguilechat.jcelechat.libs.spring.connect.corporation.CorporationInfoService;
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
	private final AllianceInfoService allianceInfoService;

	@Lazy
	private final CharacterAffiliationService characterAffiliationService;

	@Lazy
	private final CharacterInformationService characterInformationService;

	@Lazy
	private final CorporationInfoService corporationInfoService;

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
		CharacterContactList ret = new CharacterContactList();
		ret.setCharacterId(characterId);
		return ret;
	}

	@Getter(lazy = true)
	private final Set<String> requiredScopes = Set.of("esi-characters.read_contacts.v1");

	// service usage

	public List<CharacterContact> fromTo(List<Integer> fromIds, List<Integer> toIds) {
		return recordRepo().findAllByFetchResourceCharacterIdInAndContactIdIn(fromIds, toIds);
	}

	public List<CharacterContact> forContactIds(List<Integer> toIds) {
		return recordRepo().findAllByContactIdIn(toIds);
	}

	@Override
	protected void updateFromResponseOk(CharacterContactList data,
	    Requested<R_get_characters_character_id_contacts[]> response) {
		R_get_characters_character_id_contacts[] ok = response.getOK();
		if (ok != null) {
			for(R_get_characters_character_id_contacts contact  : ok ) {
				switch (contact.contact_type) {
				case alliance:
					allianceInfoService.createIfMissing(contact.contact_id, false);
					break;
				case character:
					characterAffiliationService.createIfMissing(contact.contact_id, false);
					characterInformationService.createIfMissing(contact.contact_id, false);
					break;
				case corporation:
					corporationInfoService.createIfMissing(contact.contact_id, false);
					break;
				case faction:
					// nothing : factions are automatically updated from
					break;
				default:
					throw new UnsupportedOperationException("case not supported " + contact.contact_type);
				}
			}
		}
		super.updateFromResponseOk(data, response);
	}

}
