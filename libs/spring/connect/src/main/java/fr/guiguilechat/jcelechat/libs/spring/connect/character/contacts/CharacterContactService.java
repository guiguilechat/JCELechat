package fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.alliance.AllianceInfoService;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.character.CharacterAffiliationService;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.character.CharacterInformationService;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfoService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts.CharacterContact.CharacterContactList;
import fr.guiguilechat.jcelechat.libs.spring.connect.templates.ACharDataRecordListService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_contacts;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_contacts_contact_type;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterContactService extends
    ACharDataRecordListService<
    	CharacterContactList, 
    	R_get_characters_character_id_contacts,
    	CharacterContactListRepository,
    	CharacterContact, 
    	CharacterContactRepository> {

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
	    int Id, Map<String, String> properties) {
		return esiConnected.requestGetPages((page, props) -> esiConnected.get_characters_contacts(Id, page, props),
		    properties).mapBody(l -> l.toArray(R_get_characters_character_id_contacts[]::new));
	}

	@Override
	protected CharacterContactList create(Integer Id) {
		CharacterContactList ret = new CharacterContactList();
		ret.setId(Id);
		return ret;
	}

	@Getter(lazy = true)
	private final Set<String> requiredScopes = Set.of("esi-characters.read_contacts.v1");

	// service usage

	public List<CharacterContact> fromTo(List<Integer> fromIds, List<Integer> toIds) {
		return recordRepo().findAllByFetchResourceIdInAndContactIdIn(fromIds, toIds);
	}

	public List<CharacterContact> forContactIds(List<Integer> toIds) {
		return recordRepo().findAllByContactIdIn(toIds);
	}

	@Override
	protected void updateResponseOk(CharacterContactList data,
	    R_get_characters_character_id_contacts[] ok) {
		super.updateResponseOk(data, ok);
		if (ok != null) {
			List<Integer> allianceIds = Stream.of(ok)
			    .filter(c -> c.contact_type == get_characters_character_id_contacts_contact_type.alliance)
			    .map(c -> c.contact_id).toList();
			allianceInfoService.createIfAbsent(allianceIds);

			// nothing to handle for factions, they are all automatically updated

			List<Integer> corporationIds = Stream.of(ok)
			    .filter(c -> c.contact_type == get_characters_character_id_contacts_contact_type.corporation)
			    .map(c -> c.contact_id).toList();
			corporationInfoService.createIfAbsent(corporationIds);

			List<Integer> characterIds = Stream.of(ok)
			    .filter(c -> c.contact_type == get_characters_character_id_contacts_contact_type.character)
			    .map(c -> c.contact_id).toList();
			characterAffiliationService.createIfAbsent(characterIds);
			characterInformationService.createIfAbsent(characterIds);
		}
	}

}
