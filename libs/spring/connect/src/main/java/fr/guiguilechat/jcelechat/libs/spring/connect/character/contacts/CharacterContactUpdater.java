package fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts;

import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.anon.alliance.information.AllianceInfoService;
import fr.guiguilechat.jcelechat.libs.spring.anon.character.affiliation.CharacterAffiliationService;
import fr.guiguilechat.jcelechat.libs.spring.anon.character.information.CharacterInformationService;
import fr.guiguilechat.jcelechat.libs.spring.anon.corporation.information.CorporationInfoService;
import fr.guiguilechat.jcelechat.libs.spring.connect.generic.CharRecordListUpdater;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_contacts;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterContactUpdater
		extends	CharRecordListUpdater<
			CharacterContactList,
			R_get_characters_character_id_contacts,
			CharacterContactListRepository,
			CharacterContactListService,
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

	@Getter(lazy = true)
	private final Set<String> requiredScopes = Set.of("esi-characters.read_contacts.v1");

}
