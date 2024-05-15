package fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts.CharacterContact.CharacterContactList;
import fr.guiguilechat.jcelechat.libs.spring.templates.repositories.ICharDataRecordRepository;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_contacts_contact_type;

public interface CharacterContactRepository extends ICharDataRecordRepository<CharacterContactList, CharacterContact> {

	public List<CharacterContact> findAllByFetchResourceCharacterIdInAndContactIdIn(Iterable<Integer> fromIds,
	    Iterable<Integer> toIds);

	public List<CharacterContact> findAllByContactIdIn(Iterable<Integer> toIds);

	public List<CharacterContact> findAllByFetchResourceAndContactType(CharacterContactList owner,
	    get_characters_character_id_contacts_contact_type contactType);

}
